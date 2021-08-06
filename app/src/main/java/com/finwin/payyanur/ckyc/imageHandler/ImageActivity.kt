package com.finwin.payyanur.ckyc.imageHandler

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.finwin.payyanur.ckyc.R
import com.finwin.payyanur.ckyc.supportClass.ConstantClass
import com.github.dhaval2404.imagepicker.ImagePicker
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ImageActivity : AppCompatActivity() {
    companion object {
        private const val PROOF_IMAGE_REQ_CODE = 111
        val REQUEST_IMAGE_CAPTURE = 1
        lateinit var photoURI: Uri
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_kotlin)

        val extras = intent.extras

        if (null != extras) {
            val xtraValue = extras.getString(ConstantClass.CROP_TYPE)
            val typeValue = extras.getString(ConstantClass.IMG_SELECTION_TYPE)

            @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            if (xtraValue.equals(ConstantClass.CROP_PROFILE)) {
                typeValue?.let { pickIDproofImage(200f, 230f, 200, 230, it) }

                //clickProfile(typeValue)
            } else if (xtraValue.equals(ConstantClass.CROP_ID_PROOF)) {
                typeValue?.let { pickIDproofImage(360F, 230f, 360, 230, it) }
            } else if (xtraValue.equals(ConstantClass.CROP_ADDRESS_PROOF)) {
                typeValue?.let { pickIDproofImage(360F, 230f, 360, 230, it) }
            }
        }
    }

    fun clickProfile(typeValue: String?)
    {
        if (typeValue == ConstantClass.IMG_SELECT_CAMERA) {
            dispatchTakePictureIntent()
        }else
        {
            Toast.makeText(this,"not available",Toast.LENGTH_SHORT).show()
        }
    }

    private fun pickIDproofImage(x: Float, y: Float, w: Int, h: Int, type: String) {
        if (type == ConstantClass.IMG_SELECT_CAMERA) {
            ImagePicker.Companion.with(this)
                    .crop(x, y)

                    .cameraOnly()
                    .compress(70)
                    //.maxResultSize(1024, 1024)
                    .start(PROOF_IMAGE_REQ_CODE)


        } else {
            ImagePicker.Companion.with(this)

                    .crop(x, y)
                    .galleryOnly()
                    .compress(70)
                    //.maxResultSize(1024, 1024)
                    .start(PROOF_IMAGE_REQ_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.e("TAG", "Path:${ImagePicker.getFilePath(data)}")
            // File object will not be null for RESULT_OK

            when (requestCode) {

                PROOF_IMAGE_REQ_CODE -> {
                    val file = ImagePicker.getFile(data)!!
                    val returnIntent = Intent()
                    returnIntent.putExtra("img_result", file.toString())
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }

                REQUEST_IMAGE_CAPTURE -> {

                    val file = File(currentPhotoPath)

                    //val str: String = FileUtils.re(file)

                    var base64: String
//                    GlobalScope.launch {
//                        try {


                            //base64 = convertImageFileToBase64(getCompressedImage(file))
                            val returnIntent = Intent()

                            returnIntent.putExtra("img_result", currentPhotoPath)

                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
//                        }catch (e:Exception)
//                        {
//                            e.printStackTrace()
//                        }
//
//                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        else {
            finish()
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

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                            this,
                            "com.example.android.fileprovider",
                            it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

   public suspend fun getCompressedImage(file: File,x: Int, y: Int): File {

       return Compressor.compress(this, file) {
           resolution(x, y)
           quality(100)
           format(Bitmap.CompressFormat.PNG)
           //size(70)

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
}
