package com.finwin.payyanur.ckyc.upload

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.finwin.payyanur.ckyc.R
import com.finwin.payyanur.ckyc.databinding.ActivityUploadBinding
import com.finwin.payyanur.ckyc.imageHandler.ImageActivity
import com.finwin.payyanur.ckyc.login.LoginActivity
import com.finwin.payyanur.ckyc.supportClass.ConstSaveDataClass
import com.finwin.payyanur.ckyc.supportClass.ConstantClass
import kotlinx.coroutines.*
import org.apache.commons.io.IOUtils
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class   Upload : AppCompatActivity() {

    lateinit var binding: ActivityUploadBinding
    lateinit var viewmodel: UploadViewmodel
    private val PROFILE_PIC = 11
    private val ID_PROOF = 12
    private val ADDRESS_PROOF = 13
    private val RESULT_IMAGE_CLICK = 14
    var cameraImageUri: Uri? = null
    private val TAG = "UploadActivity"
    private val scope = CoroutineScope(SupervisorJob())


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.clear()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)
        viewmodel = ViewModelProvider(this).get(UploadViewmodel::class.java)
        binding.viewModel = viewmodel
        viewmodel.binding = binding

        checkAndRequestPermissions();
        viewmodel.getmAction().observe(this, Observer<UploadAction> { uploadAction ->
            viewmodel.cancelLoading()
            when (uploadAction.getAction()) {
                UploadAction.CLICK_PROFILE_PIC -> selectPic(PROFILE_PIC, ConstantClass.CROP_PROFILE)
                UploadAction.CLICK_ADDRESS_PROOF -> selectPic(ADDRESS_PROOF, ConstantClass.CROP_ADDRESS_PROOF)
                UploadAction.CLICK_ID_PROOF -> selectPic(ID_PROOF, ConstantClass.CROP_ID_PROOF)
                UploadAction.CLICK_SUBMIT -> {
                }
                UploadAction.API_ERROR -> {
                    viewmodel.cancelLoading()
                    SweetAlertDialog(this@Upload, SweetAlertDialog.BUTTON_NEGATIVE)
                            .setTitleText("ERROR")
                            .setContentText(uploadAction.getError())
                            .show()
                }
                UploadAction.INVALID_CUST_ID -> {
                    viewmodel.cancelLoading()
                    viewmodel.verifyCustidError()
                    SweetAlertDialog(this@Upload, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid Customer Id")
                            .setContentText(uploadAction.getError())
                            .show()
                }
                UploadAction.VERIFY_CUST_ID_SUCCESS -> {
                    var addressProof64: String = ""
                    var idProof64: String = ""
                    var profile64: String = ""
                    viewmodel.verifyCustidSuccess()
                    viewmodel.setCustomerDetails(uploadAction.verifyCustIdResponse.data.table[0])
                    if (uploadAction.verifyCustIdResponse.data.table.size > 0) {
                        try {


                            if (uploadAction.verifyCustIdResponse.data.table[0].applicantImage != "" && uploadAction.verifyCustIdResponse.data.table[0].applicantImage != null) {
                                val profileString: List<String> = (uploadAction.verifyCustIdResponse.data.table[0].applicantImage).split("base64,")
                                profile64 = profileString[1]
                                //viewmodel.profilePhoto64.set(uploadAction.verifyCustIdResponse.data.table[0].applicantImage)
                            }
                            if (uploadAction.verifyCustIdResponse.data.table[0].idProofImageSide1 != "" && uploadAction.verifyCustIdResponse.data.table[0].idProofImageSide1 != null) {
                                val idProofString: List<String> = (uploadAction.verifyCustIdResponse.data.table[0].idProofImageSide1).split("base64,")
                                idProof64 = idProofString[1]
                                //viewmodel.idProof64.set(uploadAction.verifyCustIdResponse.data.table[0].applicantIdProof)
                            }

                            if (uploadAction.verifyCustIdResponse.data.table[0].addressProofImageSide1 != "" && uploadAction.verifyCustIdResponse.data.table[0].addressProofImageSide1 != null) {
                                val addressProofString: List<String> = (uploadAction.verifyCustIdResponse.data.table[0].addressProofImageSide1).split("base64,")
                                addressProof64 = addressProofString[1]
                                //viewmodel.addressProof64.set(uploadAction.verifyCustIdResponse.data.table[0].applicantAddressProof)
                            }

                            //setProfile(profile64)
                            setCustomerImages(profile64, idProof64, addressProof64)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                }
                UploadAction.UPLOAD_PHOTOS_SUCCESS -> {
                    SweetAlertDialog(this@Upload, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Customer documents uploaded")
                            .setContentText(uploadAction.getError())
                            .show()
                    viewmodel.clearData()
                }
                UploadAction.UPLOAD_PHOTOS_ERROR -> SweetAlertDialog(this@Upload, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Something error! Please try again")
                        .setContentText(uploadAction.getError())
                        .show()
                UploadAction.CLICK_BACK -> alertsignout()
            }
        })

        viewmodel.getVerifyCustId().observe(this, Observer {
            when (it) {
                false -> {
                    binding.imgCustidVerified.setImageResource(R.drawable.ic_not_verified)
                    binding.layoutCustomerDetails.visibility = View.GONE
                    binding.imgProfilePhoto.visibility=View.GONE
                    binding.imgAddressProof.visibility=View.GONE
                    binding.imgIdProof.visibility=View.GONE
                }
            }
        })
    }

    private fun setCustomerImages(profile64: String, idProof64: String, addressProof64: String) {
        try {
            runOnUiThread {

                if (profile64 != ""){
                    binding.imgProfilePhoto.visibility=View.VISIBLE
                    val decodedProfile = Base64.decode(profile64, Base64.DEFAULT)
                    val bitmapProfile = BitmapFactory.decodeByteArray(decodedProfile, 0, decodedProfile.size)

                    Glide.with(Objects.requireNonNull(this@Upload))
                            .load(bitmapProfile)
                            .into(binding.imgProfilePhoto)
                }

                if (idProof64 != ""){
                    binding.imgIdProof.visibility=View.VISIBLE
                    val decodedIdProof = Base64.decode(idProof64, Base64.DEFAULT)
                    val bitmapIdProof = BitmapFactory.decodeByteArray(decodedIdProof, 0, decodedIdProof.size)

                    Glide.with(Objects.requireNonNull(this@Upload))
                            .load(bitmapIdProof)
                            .into(binding.imgIdProof)

                }
                if (addressProof64 != ""){
                    binding.imgAddressProof.visibility=View.VISIBLE
                    val decodedAddressProof = Base64.decode(addressProof64, Base64.DEFAULT)
                    val bitmapAddressProof = BitmapFactory.decodeByteArray(decodedAddressProof, 0, decodedAddressProof.size)
                    Glide.with(Objects.requireNonNull(this@Upload))
                            .load(bitmapAddressProof)
                            .into(binding.imgAddressProof)
                }
            }
        }catch (e: Exception)
        {
            e.printStackTrace()
        }

    }


    override fun onStop() {
        super.onStop()
        //Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show();
        viewmodel.getmAction().setValue(UploadAction(UploadAction.DEFAULT))
    }

    private fun selectPic(requestCode: Int, cropType: String) {
        if (checkAndRequestPermissions()) {
            val b = AlertDialog.Builder(Objects.requireNonNull(this@Upload))
            b.setTitle("Select Image")
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.select_pic_view, null)
            b.setView(dialogView)
            val linCam = dialogView.findViewById<View>(R.id.linr_cam) as LinearLayout
            val linGalry = dialogView.findViewById<View>(R.id.linr_galry) as LinearLayout
            val alertDialog = b.create()
            linCam.setOnClickListener {
                alertDialog.dismiss()
                val id = Intent(this@Upload, ImageActivity::class.java)
                id.putExtra(ConstantClass.CROP_TYPE, cropType)
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_CAMERA)
                startActivityForResult(id, requestCode)
            }
            linGalry.setOnClickListener {
                alertDialog.dismiss()
                val id = Intent(this@Upload, ImageActivity::class.java)
                id.putExtra(ConstantClass.CROP_TYPE, cropType)
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_GALRY)
                startActivityForResult(id, requestCode)
            }
            alertDialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PROFILE_PIC -> if (resultCode == RESULT_OK) {
                binding.imgProfilePhoto.setVisibility(View.VISIBLE)
                try {
//                    var base64FromIntent: String = ""
//                    val sUri = data!!.getStringExtra("img_result")
//                    val file = File(sUri)
//                    GlobalScope.launch {
//                        base64FromIntent = convertImageFileToBase64(getCompressedImage(file))
//                        val decodedString = Base64.decode(base64FromIntent, Base64.DEFAULT)
//                        val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                        val bm = Bitmap.createScaledBitmap(bitmap, 200, 230, true)
//                        setImage(bm)
//                        saveImageToMemory(bm, "profile_image")
//                        var base64 = getBase64String(bm)
//                        base64 = getBase64String(bm)
//                        viewmodel.profilePhoto64.set("data:image/png;base64,$base64")
//                    }

//                    Glide.with(Objects.requireNonNull(this@Upload))
//                            .load(bm)
//                            .into(binding.imgProfilePhoto)

                    //imageSize = getImageSize(base64)
                    //Toast.makeText(this, "image size is " + imageSize + "kb", Toast.LENGTH_SHORT).show()

//                    Log.d(Upload.TAG, "base64 encoded: $base64")
//                    Log.d(Upload.TAG, "base64 encoded: $sUri")

                    val sUri = data!!.getStringExtra("img_result")
                    Glide.with(Objects.requireNonNull(this@Upload))
                            .load(sUri)
                            .into(binding.imgProfilePhoto)
                    val file = File(sUri)
                    val buffer = ByteArray(file.length().toInt() + 100)
                    val length = FileInputStream(file).read(buffer)
                    val base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT)
                    viewmodel.profilePhoto64.set("data:image/jpg;base64,$base64")
                    //val imageSize = getImageSize(base64)
                    //Toast.makeText(this, "image size is " + imageSize + "kb", Toast.LENGTH_SHORT).show()
                    val decodedString = Base64.decode(base64, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    saveImageToMemory(bitmap, "profile_image")

//                    val decodedString = Base64.decode(convertImageFileToBase64(file), Base64.DEFAULT)
//                    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                    val bm = Bitmap.createScaledBitmap(bitmap, 200, 230, true)
//                    saveImageToMemory(bm, "profile_image")
//                    val buffer = ByteArray(file.length().toInt() + 100)
//                    val length = FileInputStream(file).read(buffer)
//                    //val base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT)
//                    val base64 =getBase64String(bm)
//                    viewmodel.profilePhoto64.set("data:image/jpg;base64,$base64")
//                    Log.d(TAG, "base64: $base64")

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            ID_PROOF -> if (resultCode == RESULT_OK) {
                binding.imgIdProof.setVisibility(View.VISIBLE)
                try {
                    val sUri = data!!.getStringExtra("img_result")
                    Glide.with(Objects.requireNonNull(this@Upload))
                            .load(sUri)
                            .into(binding.imgIdProof)
//                    val file = File(sUri)
//                    val buffer = ByteArray(file.length().toInt() + 100)
//                    val length = FileInputStream(file).read(buffer)
//                    //val base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT)
//                    // var imageSize = getImageSize(base64)
//                   // Toast.makeText(this, "image size is " + imageSize + "kb", Toast.LENGTH_SHORT).show()
//
//                    val decodedString = Base64.decode(convertImageFileToBase64(file), Base64.DEFAULT)
//                    val  bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                    val bm = Bitmap.createScaledBitmap(bitmap, 360, 230, true)
//                    saveImageToMemory(bm, "id_proof")
////                    val buffer = ByteArray(file.length().toInt() + 100)
////                    val length = FileInputStream(file).read(buffer)
//                   // val base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT)
//                    val base64 =getBase64String(bm)
//                    viewmodel.idProof64.set("data:image/jpg;base64,$base64")
//                    Log.d(TAG, "base64: $base64")
//                    GlobalScope.launch {
//                       //var imgFile = (getCompressedImage(file))
//
//                        val observable=getCompressedImage(file)
//                        observable?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
//
////                            val decodedString = Base64.decode(convertImageFileToBase64(it), Base64.DEFAULT)
////                            val  bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
////                            val bm = Bitmap.createScaledBitmap(bitmap, 200, 230, true)
////                            saveImageToMemory(bm, "id_proof")
////                            val base64 = getBase64String(bm)
//                        }
//
//                    }
//                    val base64 = convertToString(sUri)
//                    val decodedString = Base64.decode(base64, Base64.DEFAULT)
//                    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                    saveImageToMemory(bitmap, "id_proof")
//                    viewmodel.idProof64.set("data:image/jpg;base64,$base64")

                    val file = File(sUri)
                    val buffer = ByteArray(file.length().toInt() + 100)
                    val length = FileInputStream(file).read(buffer)
                    val base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT)
                    viewmodel.idProof64.set("data:image/jpg;base64,$base64")
                    //val imageSize = getImageSize(base64)
                    //Toast.makeText(this, "image size is " + imageSize + "kb", Toast.LENGTH_SHORT).show()
                    val decodedString = Base64.decode(base64, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    saveImageToMemory(bitmap, "id_proof")
                    ConstSaveDataClass.SAVE_Profile_IMG = "data:image/png;base64," + base64;
                    Log.d(TAG, "base64 encoded: $base64")

                } catch (e: Exception) {

                }
            }
            ADDRESS_PROOF -> if (resultCode == RESULT_OK) {
                binding.imgAddressProof.setVisibility(View.VISIBLE)
                try {
                    val sUri = data!!.getStringExtra("img_result")
                    Glide.with(Objects.requireNonNull(this@Upload))
                            .load(sUri)
                            .into(binding.imgAddressProof)

//                    val file = File(sUri)
//                    val decodedString = Base64.decode(convertImageFileToBase64(file), Base64.DEFAULT)
//                    val  bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                    val bm = Bitmap.createScaledBitmap(bitmap, 360, 230, true)
//                    saveImageToMemory(bm, "address_proof")
//                    val buffer = ByteArray(file.length().toInt() + 100)
//                    val length = FileInputStream(file).read(buffer)
//                    // val base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT)
//                    val base64 =getBase64String(bm)
//                    viewmodel.addressProof64.set("data:image/jpg;base64,$base64")
//                    Log.d(TAG, "base64: $base64")


                    val file = File(sUri)
                    val buffer = ByteArray(file.length().toInt() + 100)
                    val length = FileInputStream(file).read(buffer)
                    val base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT)
                    viewmodel.addressProof64.set("data:image/jpg;base64,$base64")
                    //val imageSize = getImageSize(base64)
                    //Toast.makeText(this, "image size is " + imageSize + "kb", Toast.LENGTH_SHORT).show()
                    val decodedString = Base64.decode(base64, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    saveImageToMemory(bitmap, "address_proof")
                    ConstSaveDataClass.SAVE_Profile_IMG = "data:image/png;base64," + base64;
                    Log.d(TAG, "base64 encoded: $base64")
                } catch (e: Exception) {
                }
            }
        }
    }
    @Throws(IOException::class)
    private fun convertToString(filepath: String): String? {
        val inputStream: InputStream = FileInputStream(filepath)
        val byteArray: ByteArray = IOUtils.toByteArray(inputStream)
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
    private fun getBase64String(bm: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


    val REQUEST_ID_MULTIPLE_PERMISSIONS = 1


    private fun checkAndRequestPermissions(): Boolean {
        val permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)

        val permissionStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val locationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            requestPermissions(listPermissionsNeeded.toTypedArray(),
                    REQUEST_ID_MULTIPLE_PERMISSIONS)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        Log.e("onRequestPer", "Permission callback called-------")
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> {
                val perms: MutableMap<String, Int> = HashMap()
                // Initialize the map with both permissions
                perms[Manifest.permission.READ_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                if (grantResults.size > 0) {
                    var i = 0
                    while (i < permissions.size) {
                        perms[permissions[i]] = grantResults[i]
                        i++
                    }
                    // Check for both permissions
                    if (perms[Manifest.permission.READ_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                            && perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("onRequestPer", "Camera and Storage permission granted")
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d("onRequestPer", "Some permissions are not granted ask again ")
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                        Objects.requireNonNull(this), Manifest.permission.READ_EXTERNAL_STORAGE) ||
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        this, Manifest.permission.CAMERA)) {
                            showDialogOK("Camera and Storage Permission required for this app"
                            ) { dialog, which ->
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                    }
                                }
                            }
                        } else {
                            AlertDialog.Builder(this)
                                    .setMessage("Needed Camera and Storage permission, Go to settings and enable it!")
                                    .setPositiveButton("Open Settings") { dialog, which ->
                                        val intent = Intent()
                                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                        val uri = Uri.fromParts("package", packageName, null)
                                        intent.data = uri
                                        startActivity(intent)
                                    }
                                    .setNegativeButton("Cancel") { dialog, which -> }
                                    .create()
                                    .show()
                            //                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG).show();
//                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }
    }

    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
    }

    private fun alertsignout() {
        val alertDialog2 = AlertDialog.Builder(this@Upload, R.style.alertDialog)
        alertDialog2.setTitle("Confirm SignOut")
        alertDialog2.setMessage("Are you sure you want to Sign out?")
        alertDialog2.setPositiveButton("YES"
        ) { dialog, which -> // Write your code here to execute after dialog
            val i = Intent(this@Upload, LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        alertDialog2.setNegativeButton("NO"
        ) { dialog, which -> // Write your code here to execute after dialog
            dialog.cancel()
        }
        alertDialog2.show()
    }

    override fun onBackPressed() {
        alertsignout()
    }

    private fun getImageSize(base64String: String): Double {
        var result = -1.0
        if (!base64String.isEmpty()) {
            var padding = 0
            if (base64String.endsWith("==")) {
                padding = 2
            } else {
                if (base64String.endsWith("=")) padding = 1
            }
            result = Math.ceil((base64String.length / 4).toDouble()) * 3 - padding
        }
        return result / 1000
    }


    private fun saveImageToMemory(finalBitmap: Bitmap, file_name: String) {

        //String root = Environment.getExternalStorageState(Environment.getExternalStorageState()).toString();
        val root = Environment.getExternalStorageDirectory().toString() + "/ckyc/"
        val myPicDirectory = File(root + viewmodel.customerId.get())
        myPicDirectory.mkdirs()
        val file = File(myPicDirectory, viewmodel.customerId.get().toString() + "-" + file_name + ".jpg")
        if (file.exists()) file.delete()
        file.createNewFile();
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out) // 90 refers to quality of image

            out.flush()
            out.close()
            var length = file.length() / 1024

            Handler(Looper.getMainLooper()).post {
                if (file_name=="profile_image"){
                    viewmodel.profileSize.set(length)
                }else if (file_name=="id_proof"){
                    viewmodel.idProofSize.set(length)
                }else if (file_name=="address_proof"){
                    viewmodel.addressProofSize.set(length)
                }
                Toast.makeText(this, "Image size is $length kb", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            //Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    public fun save64(base64: String): String {

        val decodedString = Base64.decode(base64, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {

            currentPhotoPath = absolutePath
            try {
                val out = FileOutputStream(this)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out) // 90 refers to quality of image
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
                //Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
        return currentPhotoPath

    }

//    public fun setProfile(applicantAddressProof: String) {
//        var imageSize = getImageSize(applicantAddressProof)
//        if (imageSize > 50) {
//            if (!applicantAddressProof.equals("")) {
//                //binding.imgProfilePhoto.setVisibility(View.VISIBLE)
////        var imageSize: Double
////        imageSize = getImageSize(applicantAddressProof)
//                //if (imageSize<50 || imageSize>20)
//                //{
//                var profilePath = save64(applicantAddressProof)
//                val file = File(profilePath)
//
//
//                scope.launch {
//                    try {
//                        var imageFile: File
//                        //= getCompressedImage(file)
//                        val decodedString = Base64.decode(convertImageFileToBase64(imageFile), Base64.DEFAULT)
//                        val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//                        val bm = Bitmap.createScaledBitmap(bitmap, 200, 230, true)
//                        saveImageToMemory(bm, "profile_image")
//                        val base64 = getBase64String(bm)
////            imageSize = getImageSize(base64)
////            Toast.makeText(this, "image size is " + imageSize + "kb", Toast.LENGTH_SHORT).show()
//                        viewmodel.profilePhoto64.set("data:image/jpg;base64,$base64")
//                        setImage(bm)
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                }
//            }
//        }
//    }

    private fun setImage(bm: Bitmap?) {
        try {
            runOnUiThread {
                binding.imgProfilePhoto.visibility=View.VISIBLE
                Glide.with(Objects.requireNonNull(this@Upload))
                        .load(bm)
                        .into(binding.imgProfilePhoto)
            }
        }catch (e: Exception)
        {
            e.printStackTrace()
        }


    }

    fun convertImageFileToBase64(imageFile: File): String {
        return ByteArrayOutputStream().use { outputStream ->
            Base64OutputStream(outputStream, Base64.DEFAULT).use { base64FilterStream ->
                imageFile.inputStream().use { inputStream ->
                    inputStream.copyTo(base64FilterStream)
                }
            }
            return@use outputStream.toString()
        }
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
    private fun convert(bitmap: Bitmap, width: Int, height: Int): Bitmap {

        val document_Encoded_Photo_one: Bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        val canvas = Canvas(document_Encoded_Photo_one)
        val paint = Paint()
        paint.color = Color.parseColor("#ffffff")
        canvas.drawPaint(paint)
        canvas.drawBitmap(document_Encoded_Photo_one, 0f, 0f, paint)
        //canvas.drawBitmap(attr.bitmap, null, RectF(0f, 0f, 0f, 0f), null)
        return document_Encoded_Photo_one
    }
}


