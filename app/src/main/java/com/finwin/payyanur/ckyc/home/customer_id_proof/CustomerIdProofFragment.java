package com.finwin.payyanur.ckyc.home.customer_id_proof;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;


import com.bumptech.glide.Glide;
import com.finwin.payyanur.ckyc.R;
import com.finwin.payyanur.ckyc.databinding.FragmentIdProofBinding;
import com.finwin.payyanur.ckyc.home.customer_id_proof.action.CustomerIdProofAction;
import com.finwin.payyanur.ckyc.imageHandler.ImageActivity;
import com.finwin.payyanur.ckyc.main_activity.MainActivity;
import com.finwin.payyanur.ckyc.supportClass.ConstSaveDataClass;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CustomerIdProofFragment extends Fragment {

    CustomerIdProofViewModel viewModel;
    FragmentIdProofBinding binding;
    MainActivity mainActivity;
    private static final int ID_PROOF_SIDE_1 = 123;
    private static final int ID_PROOF_SIDE_2 = 124;

    public static CustomerIdProofFragment instance;

    public static CustomerIdProofFragment newInstance() {
        if (instance == null) {
            instance = new CustomerIdProofFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_id_proof, container, false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(CustomerIdProofViewModel.class);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    public CustomerIdProofViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reset();

        viewModel.getmAction().observe(getViewLifecycleOwner(), new Observer<CustomerIdProofAction>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(CustomerIdProofAction customerIdProofAction) {

                viewModel.cancelLoading();
                switch (customerIdProofAction.getAction()) {
                    case CustomerIdProofAction.CLICK_ID_PROOF_SIDE1:
                        selectPic(ID_PROOF_SIDE_1);
                        break;

                    case CustomerIdProofAction.CLICK_ID_PROOF_SIDE2:
                        selectPic(ID_PROOF_SIDE_2);
                        break;

                    case CustomerIdProofAction.GET_ID_PROOF_DETAILS:
                        try {


                            viewModel.setIdProofData(customerIdProofAction.getGetIdProofDetailsResponse().getData().getTable());
                            if (customerIdProofAction.getGetIdProofDetailsResponse().getData().getTable().get(0).getId_Proof_Image_Side1() != null &&
                                    customerIdProofAction.getGetIdProofDetailsResponse().getData().getTable().get(0).getId_Proof_Image_Side2() != null) {
                                String[] IdProofOne = (customerIdProofAction.getGetIdProofDetailsResponse().getData().getTable().get(0).getId_Proof_Image_Side1()).split("base64,");
                                String[] IdProofTwo = (customerIdProofAction.getGetIdProofDetailsResponse().getData().getTable().get(0).getId_Proof_Image_Side2()).split("base64,");
                                String IdProofOne64 = IdProofOne[1];
                                String IdProofTwo64 = IdProofTwo[1];

                                viewModel.idProofSideOne64.set(customerIdProofAction.getGetIdProofDetailsResponse().getData().getTable().get(0).getId_Proof_Image_Side1());
                                viewModel.idProofSideTwo64.set(customerIdProofAction.getGetIdProofDetailsResponse().getData().getTable().get(0).getId_Proof_Image_Side2());
                                setCustomerImages(IdProofOne64, IdProofTwo64);
                            }
                        }catch (Exception e){

                        }
                        break;

                    case CustomerIdProofAction.UPDATE_ID_PROOF_SUCCESS:
                      SweetAlertDialog alertDialog= new  SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                        alertDialog .setTitleText("Customer documents uploaded");
                        alertDialog.setContentText("Cust ID="+customerIdProofAction.getUpdateIdproofResponse().getData().getTable1().get(0).getReturnID());
                        alertDialog.setConfirmClickListener(sweetAlertDialog -> {

                            mainActivity=new MainActivity();
                            mainActivity.goToNextTab();
                            alertDialog.dismiss();
                        });
                        alertDialog.show();
                        break;

                }
            }
        });

        binding.imgClearIdProof1.setOnClickListener(v -> {
            binding.imgIdProofSideOne.setImageResource(R.drawable.img_proof);
            viewModel.idProofSideOne64.set("");
        });
        binding.imgClearIdProof2.setOnClickListener(v -> {
            binding.imgIdProofSideTwo.setImageResource(R.drawable.img_proof);
            viewModel.idProofSideTwo64.set("");
        });

    }

    public void reset() {
        binding.imgIdProofSideOne.setImageResource(R.drawable.img_proof);
        binding.imgIdProofSideTwo.setImageResource(R.drawable.img_proof);
        viewModel.setIdProof(0);
        viewModel.etIdProofNumber.set("");
        viewModel.idProofSideOne64.set("");
        viewModel.idProofSideTwo64.set("");
    }

    private void setCustomerImages(String idProofOne64, String idProofTwo64) {
        try {

            if (idProofOne64 != "") {
                //binding.imgIdProofSideOne.setVisibility(View.VISIBLE);
                byte[] decodedProfile = Base64.decode(idProofOne64, Base64.DEFAULT);
                Bitmap bitmapProfile = BitmapFactory.decodeByteArray(decodedProfile, 0, decodedProfile.length);

                Glide.with(Objects.requireNonNull(this))
                        .load(bitmapProfile)
                        .into(binding.imgIdProofSideOne);
            }
            if (idProofTwo64 != "") {
                //binding.imgIdProofSideTwo.setVisibility(View.VISIBLE);
                byte[] decodedProfile = Base64.decode(idProofTwo64, Base64.DEFAULT);
                Bitmap bitmapProfile = BitmapFactory.decodeByteArray(decodedProfile, 0, decodedProfile.length);

                Glide.with(Objects.requireNonNull(this))
                        .load(bitmapProfile)
                        .into(binding.imgIdProofSideTwo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void selectPic(int requestCode) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
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
                Intent id = new Intent(getContext(), ImageActivity.class);
                id.putExtra(ConstantClass.CROP_TYPE, ConstantClass.CROP_ID_PROOF);
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_CAMERA);
                startActivityForResult(id, requestCode);
            }
        });

        linGalry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent id = new Intent(getContext(), ImageActivity.class);
                id.putExtra(ConstantClass.CROP_TYPE, ConstantClass.CROP_ID_PROOF);
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_GALRY);
                startActivityForResult(id, requestCode);
            }
        });
        alertDialog.show();
    }


    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("ID proof Result", requestCode + "-" + resultCode + "-" + data);

        switch (requestCode) {
            case ID_PROOF_SIDE_1:
                if (resultCode == Activity.RESULT_OK) {
                    binding.imgIdProofSideOne.setVisibility(View.VISIBLE);
                    try {
                        String sUri = data.getStringExtra("img_result");
                        Glide.with(Objects.requireNonNull(getContext()))
                                .load(sUri)
                                .into(binding.imgIdProofSideOne);

                        File file = new File(sUri);
                        byte[] buffer = new byte[(int) file.length() + 100];
                        @SuppressWarnings("resource")
                        int length = new FileInputStream(file).read(buffer);
                        String base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
                        viewModel.idProofSideOne64.set("data:image/png;base64," + base64);
                        //ConstSaveDataClass.SAVE_Id_proof_IMG = "data:image/png;base64," + base64;
                        Log.e("ID 64 encoded: ", ConstSaveDataClass.SAVE_Id_proof_IMG);
                    } catch (Exception e) {
                    }
                }
                break;

            case ID_PROOF_SIDE_2:
                if (resultCode == Activity.RESULT_OK) {
                    binding.imgIdProofSideTwo.setVisibility(View.VISIBLE);
                    try {
                        String sUri = data.getStringExtra("img_result");
                        Glide.with(Objects.requireNonNull(getContext()))
                                .load(sUri)
                                .into(binding.imgIdProofSideTwo);

                        File file = new File(sUri);
                        byte[] buffer = new byte[(int) file.length() + 100];
                        @SuppressWarnings("resource")
                        int length = new FileInputStream(file).read(buffer);
                        String base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
                        viewModel.idProofSideTwo64.set("data:image/png;base64," + base64);
                        //ConstSaveDataClass.SAVE_Id_proof_IMG = "data:image/png;base64," + base64;
                        Log.e("ID 64 encoded: ", ConstSaveDataClass.SAVE_Id_proof_IMG);
                    } catch (Exception e) {
                    }
                }
                // default:
                // throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    //-------------------------------------------------------------------------

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

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
                        //selectPic();
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d("onRequestPer", "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) ||
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        getActivity(), Manifest.permission.CAMERA)) {

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
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("Needed Camera and Storage permission, Go to settings and enable it!")
                                    .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
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
        new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    private boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int locationPermission = ContextCompat.checkSelfPermission(getContext(),
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


}