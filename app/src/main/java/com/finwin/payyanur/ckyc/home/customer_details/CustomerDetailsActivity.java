package com.finwin.payyanur.ckyc.home.customer_details;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.finwin.payyanur.ckyc.R;
import com.finwin.payyanur.ckyc.databinding.FragmentCustomerDetailsBinding;
import com.finwin.payyanur.ckyc.home.customer_details.action.CustomerDetailsAction;
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

public class CustomerDetailsActivity extends Fragment {

    CustomerDetailsViewmodel viewModel;
    FragmentCustomerDetailsBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    MainActivity mainActivity;


    public static CustomerDetailsActivity instance;

    public static CustomerDetailsActivity newInstance() {
        if (instance == null) {
            instance = new CustomerDetailsActivity();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences(ConstantClass.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_details, container, false);
        viewModel = new ViewModelProvider(this).get(CustomerDetailsViewmodel.class);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    public CustomerDetailsViewmodel getViewModel() {
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity= new MainActivity();
//        mainActivity.goToNextTab();
        viewModel.initLoading(view.getContext());
        viewModel.getBranchDetails();
        viewModel.getVerifyCustId().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    //  binding.imgCustidVerified.setImageResource(R.drawable.ic_not_verified);

                }
            }
        });

        viewModel.getmAction().observe(getViewLifecycleOwner(), new Observer<CustomerDetailsAction>() {
            @Override
            public void onChanged(CustomerDetailsAction customerDetailsAction) {

                viewModel.cancelLoading();
                switch (customerDetailsAction.getAction()) {
                    case CustomerDetailsAction.VERIFY_CUST_ID_SUCCESS:
                        //viewModel.setCustomerDetails(customerDetailsAction.getVerifyCustIdResponse().getData().getTable().get(0));
                        break;

                    case CustomerDetailsAction.API_ERROR:
                        Toast.makeText(view.getContext(), customerDetailsAction.getError(), Toast.LENGTH_SHORT).show();
                        break;

                    case CustomerDetailsAction.GET_BRANCH_DETAILS_SUCCESS:

                        viewModel.setBranchDetails(customerDetailsAction.getGetBranchDetailsResponse().getData().getBranch());
                        break;

                    case CustomerDetailsAction.CREATE_USER_SUCCESS:

                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setCancelable(false);
                        sweetAlertDialog.setTitle("Success");
                        sweetAlertDialog.setContentText("cust ID="+customerDetailsAction.getCreateUserResponse().getData().getCreateUserData().get(0).getReturnID());
                        sweetAlertDialog.setConfirmClickListener(view->{
                            viewModel.insertFlag.set("UPDATE_APP");
                            editor.putString(ConstantClass.CUST_ID,customerDetailsAction.getCreateUserResponse().getData().getCreateUserData().get(0).getReturnID());
                            editor.apply();

                            mainActivity.goToNextTab();
                            sweetAlertDialog.cancel();
                        })
                        .show();

                        //viewModel.setBranchDetails(customerDetailsAction.getGetBranchDetailsResponse().getData().getBranch());
                        break;


                    case CustomerDetailsAction.GET_CUSTOMER_DETAILS:
                        if (customerDetailsAction.getGetCustomerDetailsResponse().getData().getCustomerData().size() > 0) {
                            editor.putString(ConstantClass.CUST_ID, customerDetailsAction.getGetCustomerDetailsResponse().getData().getCustomerData().get(0).getCustID());
                            editor.apply();
                            viewModel.isVerifyCustId.set(true);
                            viewModel.setCustomerDetails(customerDetailsAction.getGetCustomerDetailsResponse().getData().getCustomerData().get(0));
                            String[] profileString = (customerDetailsAction.getGetCustomerDetailsResponse().getData().getCustomerData().get(0).getApplicantImage()).split("base64,");
                            String profile64 = profileString[1];
                            viewModel.profile64.set(customerDetailsAction.getGetCustomerDetailsResponse().getData().getCustomerData().get(0).getApplicantImage());
                            setCustomerImages(profile64);

                            binding.tvProfileImageSize.setText("Image size= "+Services.getImageSize(profile64));

                            ((MainActivity) getActivity()).enableAllTabs();
                        }
                        break;

                    case CustomerDetailsAction.DEFAULT:
                        binding.imgProfile.setImageResource(R.drawable.img_proof);
                        break;
                        case CustomerDetailsAction.SELECT_NEW_CUSTOMER:
                        binding.imgProfile.setImageResource(R.drawable.img_proof);

                            ((MainActivity) getActivity()).disableAllTab();
                        break;
                    case CustomerDetailsAction.SELECT_OLD_CUSTOMER:
                        binding.imgProfile.setImageResource(R.drawable.img_proof);
                        mainActivity = new MainActivity();

                        break;
                }
            }
        });

//        binding.btnNext.setOnClickListener(v -> {
//            mainActivity = new MainActivity();
//            mainActivity.goToNextTab();
//        });

        binding.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermissions()) {
                    selectPic();
                }
            }
        });
//

        binding.imgClearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgProfile.setImageResource(R.drawable.img_proof);
                viewModel.profile64.set("");

            }
        });

    }


    private void selectPic() {
        AlertDialog.Builder b = new AlertDialog.Builder(requireContext());
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
                id.putExtra(ConstantClass.CROP_TYPE, ConstantClass.CROP_PROFILE);
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_CAMERA);
                startActivityForResult(id, 101);
            }
        });

        linGalry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent id = new Intent(getContext(), ImageActivity.class);
                id.putExtra(ConstantClass.CROP_TYPE, ConstantClass.CROP_PROFILE);
                id.putExtra(ConstantClass.IMG_SELECTION_TYPE, ConstantClass.IMG_SELECT_GALRY);
                startActivityForResult(id, 101);
            }
        });
        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("Cust Details Result", requestCode + "-" + resultCode + "-" + data);
        if (requestCode == 101) {
            try {
                String sUri = data.getStringExtra("img_result");
                Glide.with(requireContext())
                        .load(sUri)
                        .into(binding.imgProfile);

                File file = new File(sUri);
                byte[] buffer = new byte[(int) file.length() + 100];
                @SuppressWarnings("resource")
                int length = new FileInputStream(file).read(buffer);
                String base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
                viewModel.profile64.set("data:image/png;base64," + base64);

                binding.tvProfileImageSize.setText("Image size= "+Services.getImageSize(base64));

                byte[] decodedString = Base64.decode(sUri, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Bitmap bm = Bitmap.createScaledBitmap(bitmap, 200, 230, true);

                Services.saveImageToMemory(bm,"profile_image",viewModel.custId.get());
                binding.tvProfileImageSize.setText("Image size= "+Services.getImageSize(base64));
                //ConstSaveDataClass.SAVE_Profile_IMG = "data:image/png;base64," + base64;
                Log.e("base64 encoded: ", ConstSaveDataClass.SAVE_Profile_IMG);
            } catch (Exception e) {
            }
        }
    }

    //-------------------------------------------------------------------------

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
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
                        Log.e("onRequestPer", "Camera and Storage permission granted");
                        selectPic();
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.e("onRequestPer", "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) ||
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

    //========================================================================================================


    private void setCustomerImages(String profile64) {
        try {

            if (profile64 != "") {
                binding.imgProfile.setVisibility(View.VISIBLE);
                byte[] decodedProfile = Base64.decode(profile64, Base64.DEFAULT);
                Bitmap bitmapProfile = BitmapFactory.decodeByteArray(decodedProfile, 0, decodedProfile.length);

                Glide.with(Objects.requireNonNull(this))
                        .load(bitmapProfile)
                        .into(binding.imgProfile);
                Services.saveImageToMemory(bitmapProfile,"profile_image",viewModel.custId.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

