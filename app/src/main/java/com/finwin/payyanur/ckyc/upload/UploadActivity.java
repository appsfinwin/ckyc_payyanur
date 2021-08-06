package com.finwin.payyanur.ckyc.upload;

import        android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import com.finwin.payyanur.ckyc.R;
import com.finwin.payyanur.ckyc.databinding.ActivityUploadBinding;
import com.finwin.payyanur.ckyc.imageHandler.ImageActivity;
import com.finwin.payyanur.ckyc.login.LoginActivity;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UploadActivity extends AppCompatActivity {

    ActivityUploadBinding binding;
    UploadViewmodel viewmodel;
    private static final int PROFILE_PIC = 111;
    private static final int ID_PROOF = 112;
    private static final int ADDRESS_PROOF = 113;
    private static final int RESULT_IMAGE_CLICK = 114;
    private static final String TAG = "UploadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload);
        viewmodel = new ViewModelProvider(this).get(UploadViewmodel.class);
        binding.setViewModel(viewmodel);
        viewmodel.ssetBinding(binding);

        viewmodel.getmAction().observe(this, new Observer<UploadAction>() {
            @Override
            public void onChanged(UploadAction uploadAction) {
                viewmodel.cancelLoading();
                switch (uploadAction.getAction()) {
                    case UploadAction.CLICK_PROFILE_PIC:
                        selectPic(PROFILE_PIC, ConstantClass.CROP_PROFILE);
                        break;

                    case UploadAction.CLICK_ADDRESS_PROOF:
                        selectPic(ADDRESS_PROOF, ConstantClass.CROP_ADDRESS_PROOF);
                        break;

                    case UploadAction.CLICK_ID_PROOF:
                        selectPic(ID_PROOF, ConstantClass.CROP_ID_PROOF);
                        break;

                    case UploadAction.CLICK_SUBMIT:
                        break;

                    case UploadAction.API_ERROR:
                        viewmodel.cancelLoading();
                        new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.BUTTON_NEGATIVE)
                                .setTitleText("ERROR")
                                .setContentText(uploadAction.getError())
                                .show();
                        break;


                    case UploadAction.INVALID_CUST_ID:
                        viewmodel.cancelLoading();
                        viewmodel.verifyCustidError();

                        new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Invalid Customer Id")
                                .setContentText(uploadAction.getError())
                                .show();
                        break;

                    case UploadAction.VERIFY_CUST_ID_SUCCESS:
                        viewmodel.cancelLoading();
                        viewmodel.setCustomerDetails(uploadAction.verifyCustIdResponse.getData().getTable().get(0));
                        viewmodel.verifyCustidSuccess();

                        break;
                    case UploadAction.UPLOAD_PHOTOS_SUCCESS:
                        new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Customer documents uploaded")
                                .setContentText(uploadAction.getError())
                                .show();
                        viewmodel.clearData();
                        break;

                    case UploadAction.UPLOAD_PHOTOS_ERROR:
                        new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Something error! Please try again")
                                .setContentText(uploadAction.getError())
                                .show();
                        break;

                    case UploadAction.CLICK_BACK:
                        alertsignout();
                        break;

                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewmodel.getmAction().setValue(new UploadAction(UploadAction.DEFAULT));
    }

    private void selectPic(int requestCode, String cropType) {
        if (checkAndRequestPermissions()) {
            AlertDialog.Builder b = new AlertDialog.Builder(Objects.requireNonNull(UploadActivity.this));
            b.setTitle("Select Image");
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.select_pic_view, null);
            b.setView(dialogView);

            LinearLayout linCam = (LinearLayout) dialogView.findViewById(R.id.linr_cam);
            LinearLayout linGalry = (LinearLayout) dialogView.findViewById(R.id.linr_galry);

            final AlertDialog alertDialog = b.create();
            linCam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    Intent id = new Intent(UploadActivity.this, ImageActivity.class);
                    id.putExtra(ConstantClass.CROP_TYPE, cropType);
                    id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_CAMERA);
                    startActivityForResult(id, requestCode);
                }
            });

            linGalry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    Intent id = new Intent(UploadActivity.this, ImageActivity.class);
                    id.putExtra(ConstantClass.CROP_TYPE, cropType);
                    id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_GALRY);
                    startActivityForResult(id, requestCode);
                }
            });
            alertDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case PROFILE_PIC:
                if (resultCode == Activity.RESULT_OK) {
                    binding.imgProfilePhoto.setVisibility(View.VISIBLE);
                    try {
                        String sUri = data.getStringExtra("img_result");

                        //File file = new File(sUri);
                        double imageSize ;

                        byte[] decodedString = Base64.decode(sUri, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                       Bitmap bm = Bitmap.createScaledBitmap(bitmap, 200, 230, true);
                        Glide.with(Objects.requireNonNull(UploadActivity.this))
                                .load(bm)
                                .into(binding.imgProfilePhoto);
                        saveImageToMemory(bm,"profile_image");

                        String base64=getBase64String(bm);
                        imageSize = getImageSize(base64);
                        Toast.makeText(this, "image size is "+imageSize+"kb", Toast.LENGTH_SHORT).show();
                        viewmodel.profilePhoto64.set("data:image/png;base64," + base64);
                        Log.d(TAG, "base64 encoded: " + base64);




                        Log.d(TAG, "base64 encoded: " + sUri);
                    } catch (Exception e) {

                    }
                }
                break;
            case ID_PROOF:
                if (resultCode == Activity.RESULT_OK) {
                    binding.imgIdProof.setVisibility(View.VISIBLE);
                    try {
                        String sUri = data.getStringExtra("img_result");
                        Glide.with(Objects.requireNonNull(UploadActivity.this))
                                .load(sUri)
                                .into(binding.imgIdProof);

                        File file = new File(sUri);
                        byte[] buffer = new byte[(int) file.length() + 100];
                        @SuppressWarnings("resource")
                        int length = new FileInputStream(file).read(buffer);
                        String base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
                        double imageSize = getImageSize(base64);
                        Toast.makeText(this, "image size is "+imageSize+"kb", Toast.LENGTH_SHORT).show();
                        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        saveImageToMemory(bitmap,"id_proof");
                        imageSize = getImageSize(base64);
                        viewmodel.idProof64.set("data:image/png;base64," + base64);
                        Log.d(TAG, "base64 encoded: " + base64);
                    } catch (Exception e) {

                    }

                }
                break;

            case ADDRESS_PROOF:
                if (resultCode == Activity.RESULT_OK) {
                    binding.imgAddressProof.setVisibility(View.VISIBLE);
                    try {
                        String sUri = data.getStringExtra("img_result");
                        Glide.with(Objects.requireNonNull(UploadActivity.this))
                                .load(sUri)
                                .into(binding.imgAddressProof);

                        File file = new File(sUri);
                        byte[] buffer = new byte[(int) file.length() + 100];
                        @SuppressWarnings("resource")
                        int length = new FileInputStream(file).read(buffer);
                        String base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);

                        viewmodel.addressProof64.set("data:image/png;base64," + base64);
                        double imageSize = getImageSize(base64);
                        Toast.makeText(this, "image size is "+imageSize+"kb", Toast.LENGTH_SHORT).show();

                        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        saveImageToMemory(bitmap,"address_proof");
                        //ConstSaveDataClass.SAVE_Profile_IMG = "data:image/png;base64," + base64;
                        Log.d(TAG, "base64 encoded: " + base64);
                    } catch (Exception e) {

                    }

                }

                break;
        }
    }

    private String getBase64String(Bitmap bm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return  encoded;
    }



    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;



    public boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int locationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.e("onRequestPer", "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("onRequestPer", "Camera and Storage permission granted");
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d("onRequestPer", "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                Objects.requireNonNull(this), Manifest.permission.READ_EXTERNAL_STORAGE) ||
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        this, Manifest.permission.CAMERA)) {

                            showDialogOK("Camera and Storage Permission required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            new AlertDialog.Builder(this)
                                    .setMessage("Needed Camera and Storage permission, Go to settings and enable it!")
                                    .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .create()
                                    .show();
//                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG).show();
//                            //                            //proceed with logic by disabling the related features or quit the app.

                        }
                    }
                }
            }
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    public void alertsignout() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(UploadActivity.this, R.style.alertDialog);
        alertDialog2.setTitle("Confirm SignOut");

        alertDialog2.setMessage("Are you sure you want to Sign out?");

        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Intent i = new Intent(UploadActivity.this, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                });

        alertDialog2.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        alertDialog2.show();
    }

    @Override
    public void onBackPressed() {
        alertsignout();
    }

    private Double getImageSize(String base64String) {
        Double result = -1.0;
        if (!base64String.isEmpty()) {
            Integer padding = 0;
            if (base64String.endsWith("==")) {
                padding = 2;
            } else {
                if (base64String.endsWith("=")) padding = 1;
            }
            result = (Math.ceil(base64String.length() / 4) * 3) - padding;
        }
        return result / 1000;
    }


    public void saveImageToMemory(Bitmap finalBitmap,String file_name)
    {

        //String root = Environment.getExternalStorageState(Environment.getExternalStorageState()).toString();
        String root = (Environment.getExternalStorageDirectory()+"/ckyc/").toString();
        File myPicDirectory = new File(root +viewmodel.customerId.get());
        myPicDirectory.mkdirs();



        File file = new File(myPicDirectory, viewmodel.customerId.get()+"-"+file_name);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // 90 refers to quality of image
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}