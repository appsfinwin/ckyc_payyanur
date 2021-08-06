package com.finwin.payyanur.ckyc.home.customer_address_proof;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import com.bumptech.glide.Glide;
import com.finwin.payyanur.ckyc.R;
import com.finwin.payyanur.ckyc.databinding.FragmentAddressProofBinding;
import com.finwin.payyanur.ckyc.home.customer_address_proof.action.CustomerAddressProofAction;
import com.finwin.payyanur.ckyc.imageHandler.ImageActivity;
import com.finwin.payyanur.ckyc.main_activity.MainActivity;
import com.finwin.payyanur.ckyc.supportClass.ConstSaveDataClass;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;
import com.finwin.payyanur.ckyc.utils.Services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CustomerAddressProof extends Fragment {

    CustomerAddressProofViewModel viewModel;
    FragmentAddressProofBinding binding;
    private static final int ADDRESS_PROOF_SIDE_1 = 125;
    private static final int ADDRESS_PROOF_SIDE_2 = 126;
//this is a commit from my home
    public static CustomerAddressProof instance;
    MainActivity mainActivity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static CustomerAddressProof newInstance() {
        if (instance == null) {
            instance = new CustomerAddressProof();
        }
        return instance;
    }

    public CustomerAddressProofViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences(ConstantClass.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address_proof, container, false);
        viewModel = new ViewModelProvider(this).get(CustomerAddressProofViewModel.class);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.initLoading(view.getContext(),"getState");
        viewModel.getStates();

        viewModel.getmAction().observe(getViewLifecycleOwner(), new Observer<CustomerAddressProofAction>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(CustomerAddressProofAction customerAddressProofAction) {
                viewModel.cancelLoading();
                switch (customerAddressProofAction.getAction()) {
                    case CustomerAddressProofAction.GET_STATES_SUCCESS:
                        viewModel.cancelLoading();
                        viewModel.setStatesData(customerAddressProofAction.getGetStatesResponse().getData().getStates());
                        break;

                    case CustomerAddressProofAction.GET_DISTRICTS_SUCCESS:
                        viewModel.cancelLoading();
                        viewModel.setDistrictsData(customerAddressProofAction.getGetDistrictResponse().getData().getDistrict());
                        break;

                    case CustomerAddressProofAction.API_ERROR:
                        viewModel.cancelLoading();
                        break;

                    case CustomerAddressProofAction.GET_PINCODE_SUCCESS:
                        viewModel.cancelLoading();
                        viewModel.setPincodeData(customerAddressProofAction.getGetPincodeResponse().getData().getPincode());
                        break;

                    case CustomerAddressProofAction.GET_ADDRESS_DETAILS_SUCCESS:
                        viewModel.cancelLoading();
                        viewModel.setAddressProofDetails(customerAddressProofAction.getGetAddressProofResponse().getData().getAddressProof());

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewModel.cancelLoading();
                                viewModel.setDistricts(customerAddressProofAction.getGetAddressProofResponse().getData().getAddressProof().get(0));

                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewModel.setPin(customerAddressProofAction.getGetAddressProofResponse().getData().getAddressProof().get(0));
                                    }
                                }, 1000);
                            }
                        }, 1000);


                        try {
                            if (customerAddressProofAction.getGetAddressProofResponse().getData().getAddressProof().get(0).getAddressProofImageSide1() != null ||
                                    customerAddressProofAction.getGetAddressProofResponse().getData().getAddressProof().get(0).getAddressProofImageSide1().equals("")) {
                                String[] addressSide1 = (customerAddressProofAction.getGetAddressProofResponse().getData().getAddressProof().get(0).getAddressProofImageSide1()).split("base64,");
                                String addressSide1_64 = addressSide1[1];
                                binding.tvAddressProofSide1Size.setText("image size= "+ Services.getImageSize(addressSide1_64));
                                String[] addressSide2 = (customerAddressProofAction.getGetAddressProofResponse().getData().getAddressProof().get(0).getAddressProofImageSide1()).split("base64,");
                                String addressSide2_64 = addressSide2[1];
                                binding.tvAddressProofSide2Size.setText("image size= "+ Services.getImageSize(addressSide2_64));

                                setCustomerImages(addressSide1_64,addressSide2_64);
                            }
                        } catch (Exception e) {

                        }
                        break;

                    case CustomerAddressProofAction.UPDATE_ADDRESS_DETAILS_SUCCESS:
                        editor.putString(ConstantClass.CUST_ID,"");
                        editor.apply();
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setCancelable(false);
                        sweetAlertDialog.setTitle("Success");
                        sweetAlertDialog.setContentText("cust ID=" + customerAddressProofAction.getUpdateAddressProofResponse().getData().getTable1().get(0).getReturnID());
                        sweetAlertDialog.setConfirmClickListener(sweetAlertDialog1 -> {


                            mainActivity=new MainActivity();
                            mainActivity.goToFirst();
                            sweetAlertDialog.cancel();
                        })
                                .show();
                        break;

                    case CustomerAddressProofAction.CLICK_ADDRESS_PROOF_1:
                        selectPic(ADDRESS_PROOF_SIDE_1);
                        break;

                    case CustomerAddressProofAction.CLICK_ADDRESS_PROOF_2:
                        selectPic(ADDRESS_PROOF_SIDE_2);
                        break;

                        case CustomerAddressProofAction.Default:
                            break;
                }
            }
        });

        binding.imgClearAddressProofProof1.setOnClickListener(v ->
        {
            viewModel.addressProofsideOne64.set("");
            binding.imgAddressProofSide1.setImageResource(R.drawable.img_proof);
        });

        binding.imgClearAddressProofProof2.setOnClickListener(v ->
        {
            viewModel.addressProofsideTwo64.set("");
            binding.imgAddressProofSide2.setImageResource(R.drawable.img_proof);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getmAction().setValue(new CustomerAddressProofAction(CustomerAddressProofAction.Default));
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
                id.putExtra(ConstantClass.CROP_TYPE, ConstantClass.CROP_ADDRESS_PROOF);
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_CAMERA);
                startActivityForResult(id, requestCode);
            }
        });

        linGalry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent id = new Intent(getContext(), ImageActivity.class);
                id.putExtra(ConstantClass.CROP_TYPE, ConstantClass.CROP_ADDRESS_PROOF);
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_GALRY);
                startActivityForResult(id, requestCode);
            }
        });
        alertDialog.show();
    }


    private void setCustomerImages(String addressSide1_64, String addressSide2_64) {

        try {
            if (addressSide1_64 != "") {
                byte[] decodedProfile = Base64.decode(addressSide1_64, Base64.DEFAULT);
                Bitmap bitmapProfile = BitmapFactory.decodeByteArray(decodedProfile, 0, decodedProfile.length);

                Glide.with(Objects.requireNonNull(this))
                        .load(bitmapProfile)
                        .into(binding.imgAddressProofSide1);

            }  if (addressSide2_64 != "") {
                byte[] decodedProfile = Base64.decode(addressSide2_64, Base64.DEFAULT);
                Bitmap bitmapProfile = BitmapFactory.decodeByteArray(decodedProfile, 0, decodedProfile.length);

                Glide.with(Objects.requireNonNull(this))
                        .load(bitmapProfile)
                        .into(binding.imgAddressProofSide2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        viewModel.setAddressType(0);
        viewModel.setAddressProof(0);
        viewModel.etCityOrTown.set("");
        viewModel.etAddressLineOne.set("");
        viewModel.etAddressLineTwo.set("");
        viewModel.etAddressLineThree.set("");
        viewModel.setState(0);
        viewModel.setDistrict(0);
        viewModel.setPincode(0);
        viewModel.resetDistrict();
        viewModel.resetPincode();
        viewModel.addressProofsideOne64.set("");
        viewModel.addressProofsideTwo64.set("");
        binding.imgAddressProofSide1.setImageResource(R.drawable.img_proof);
        binding.imgAddressProofSide2.setImageResource(R.drawable.img_proof);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("Addrs proof Result", requestCode + "-" + resultCode + "-" + data);

        switch (requestCode) {
            case ADDRESS_PROOF_SIDE_1:
                if (resultCode == Activity.RESULT_OK) {

                    try {
                        String sUri = data.getStringExtra("img_result");
                        Glide.with(getActivity())
                                .load(sUri)
                                .into(binding.imgAddressProofSide1);

                        File file = new File(sUri);
                        byte[] buffer = new byte[(int) file.length() + 100];
                        @SuppressWarnings("resource")
                        int length = new FileInputStream(file).read(buffer);
                        String base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
                        viewModel.addressProofsideOne64.set("data:image/png;base64," + base64);
                        binding.tvAddressProofSide1Size.setText("image size= "+ Services.getImageSize(base64));
                        Log.e("ID 64 encoded: ", ConstSaveDataClass.SAVE_Id_proof_IMG);
                    } catch (Exception e) {
                    }
                }
                break;

            case ADDRESS_PROOF_SIDE_2:
                if (resultCode == Activity.RESULT_OK) {

                    try {
                        String sUri = data.getStringExtra("img_result");
                        Glide.with(getActivity())
                                .load(sUri)
                                .into(binding.imgAddressProofSide2);

                        File file = new File(sUri);
                        byte[] buffer = new byte[(int) file.length() + 100];
                        @SuppressWarnings("resource")
                        int length = new FileInputStream(file).read(buffer);
                        String base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
                        viewModel.addressProofsideTwo64.set("data:image/png;base64," + base64);
                        binding.tvAddressProofSide2Size.setText("image size= "+ Services.getImageSize(base64));
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
