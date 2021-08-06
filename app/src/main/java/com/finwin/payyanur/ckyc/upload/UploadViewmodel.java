package com.finwin.payyanur.ckyc.upload;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.finwin.payyanur.ckyc.R;
import com.finwin.payyanur.ckyc.databinding.ActivityUploadBinding;
import com.finwin.payyanur.ckyc.retrofit.ApiInterface;
import com.finwin.payyanur.ckyc.retrofit.RetrofitClient;
import com.finwin.payyanur.ckyc.upload.pojo.Table;
import com.finwin.payyanur.ckyc.utils.Services;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UploadViewmodel extends AndroidViewModel {

    MutableLiveData<UploadAction> mAction;
    CompositeDisposable disposable;
    UploadRepository repository;
    ActivityUploadBinding binding;
    ApiInterface apiInterface;
    Application application;
    String firstName,lastName,middleName;
    public ObservableField<String> custId = new ObservableField<>("");
    public ObservableField<String> addressProof64 = new ObservableField<>("");
    public ObservableField<String> idProof64 = new ObservableField<>("");
    public ObservableField<String> profilePhoto64 = new ObservableField<>("");
    public ObservableField<Boolean> isCustIdVerified = new ObservableField<>(false);
    public ObservableField<String> customerId = new ObservableField<>("");
    public ObservableField<String> customerName = new ObservableField<>("");
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> fathersName = new ObservableField<>("");
    public ObservableField<String> mothersName = new ObservableField<>("");
    public ObservableField<Integer> currentImage = new ObservableField<>(-1);
    public ObservableField<Long> profileSize = new ObservableField<>();
    public ObservableField<Long> idProofSize = new ObservableField<>();
    public ObservableField<Long> addressProofSize = new ObservableField<>();
    MutableLiveData<Boolean> verifyCustId=new MutableLiveData<>(true);

    public MutableLiveData<Boolean> getVerifyCustId() {
        return verifyCustId;
    }

    public ObservableField<Boolean> getIsCustIdVerified() {
        return isCustIdVerified;
    }

    public UploadViewmodel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = UploadRepository.getInstance();
        mAction = new MutableLiveData<>();
        disposable = new CompositeDisposable();

        repository.setDisposable(disposable);
        repository.setmAction(mAction);
    }

    SweetAlertDialog loading;

    public void initLoading(Context context) {
        loading = Services.showProgressDialog(context);
    }

    public void cancelLoading() {
        if (loading != null) {
            loading.cancel();
            loading = null;
        }
    }

    public MutableLiveData<UploadAction> getmAction() {
        mAction = repository.getmAction();
        return mAction;
    }

    public void clickProfilePic(View view) {
        if (isCustIdVerified.get()) {
            currentImage.set(1);
            mAction.setValue(new UploadAction(UploadAction.CLICK_PROFILE_PIC));
        } else {
            pleaseVerifyCustId();
        }
    }

    private void pleaseVerifyCustId() {
        Toast.makeText(application, "Please Verify Customer ID", Toast.LENGTH_SHORT).show();
    }

    public void clickIdProof(View view) {

        if (isCustIdVerified.get()) {
            currentImage.set(2);
            mAction.setValue(new UploadAction(UploadAction.CLICK_ID_PROOF));
        } else {
            pleaseVerifyCustId();
        }

    }

    public void clickAddressProof(View view) {
        if (isCustIdVerified.get()) {
            currentImage.set(3);
            mAction.setValue(new UploadAction(UploadAction.CLICK_ADDRESS_PROOF));
        } else {
            pleaseVerifyCustId();
        }

    }

    public void clickSubmit(View view) {



            {
            if (!isCustIdVerified.get()) {
                Toast.makeText(application, "Please verify customer ID", Toast.LENGTH_SHORT).show();
            } else if (profilePhoto64.get().equals("")) {
                Toast.makeText(application, "Please select a profile photo", Toast.LENGTH_SHORT).show();
            }

//            else if (profileSize.get()>50  || (profileSize.get())<20  )
//            {
//                Toast.makeText(application, "Please retake profile photo", Toast.LENGTH_SHORT).show();
//            }

            else if (idProof64.get().equals("")) {
                Toast.makeText(application, "Please select an ID proof", Toast.LENGTH_SHORT).show();
            }
//            else if (idProofSize.get()>40  || idProofSize.get()<20  )
//            {
//                Toast.makeText(application, "Please retake ID proof", Toast.LENGTH_SHORT).show();
//            }

            else if (addressProof64.get().equals("")) {
                Toast.makeText(application, "Please select an address proof", Toast.LENGTH_SHORT).show();
            }
            else if (addressProof64.get().equals("")) {
                Toast.makeText(application, "Please select an ID proof", Toast.LENGTH_SHORT).show();
            }
//            else if (addressProofSize.get()>80  || addressProofSize.get()<20  )
//            {
//                Toast.makeText(application, "Please retake Address proof", Toast.LENGTH_SHORT).show();
//            }

            else
            {
                initLoading(view.getContext());
                uploadPhotos();
                //mAction.setValue(new UploadAction( UploadAction.UPLOAD_PHOTOS_SUCCESS));
            }
        }

    }

    public void clickVerifyCustId(View view) {
        if (!custId.get().equals("")) {
            initLoading(view.getContext());
            verifyCustId();
        } else {
            Toast.makeText(application, "Customer ID cannot be empty!", Toast.LENGTH_SHORT).show();
        }
        //mAction.setValue(new UploadAction(UploadAction.CLICK_VERIFY_CUST_ID));
    }

    public void ssetBinding(ActivityUploadBinding binding) {
        this.binding = binding;
    }

    public void uploadPhotos() {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", custId.get());
        params.put("flag", "UPDATE_CUST_IMG");
        params.put("Id_Proof_Image_Side1", idProof64.get());
        params.put("Applicant_Image", profilePhoto64.get());
        params.put("Address_Proof_Image_Side1", addressProof64.get());


        apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.uploadPhotos(apiInterface, body);
    }

    public void verifyCustId() {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", custId.get());
        params.put("flag", "SELECTONE");


        apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.verifyCustId(apiInterface, body);
    }

    public void verifyCustidSuccess() {
        isCustIdVerified.set(true);
        binding.imgCustidVerified.setImageResource(R.drawable.ic_verified);
    }

    public void verifyCustidError() {
        isCustIdVerified.set(false);
        binding.imgCustidVerified.setImageResource(R.drawable.ic_not_verified);
    }

    public void clearData() {
        binding.layoutCustomerDetails.setVisibility(View.GONE);
        isCustIdVerified.set(false);
        binding.imgCustidVerified.setImageResource(R.drawable.ic_not_verified);
        custId.set("");
        idProof64.set("");
        addressProof64.set("");
        profilePhoto64.set("");

        binding.imgAddressProof.setVisibility(View.GONE);
        binding.imgProfilePhoto.setVisibility(View.GONE);
        binding.imgIdProof.setVisibility(View.GONE);
    }

    public void onTextChanged(CharSequence text) {
        // TODO do something with text
//        binding.imgCustidVerified.setImageResource(R.drawable.ic_not_verified);
//        isCustIdVerified.set(false);
//        binding.layoutCustomerDetails.setVisibility(View.GONE);

        verifyCustId.setValue(false);

    }

    public void setCustomerDetails(Table table) {
        firstName = ((table.getApplicantFirstName() == null) ? "" : table.getApplicantFirstName());
        middleName = ((table.getApplicantMiddleName() == null) ? "" : table.getApplicantMiddleName());
        lastName = ((table.getApplicantLastName() == null) ? "" : table.getApplicantLastName());

        binding.layoutCustomerDetails.setVisibility(View.VISIBLE);
        customerId.set(table.getCustID());
        customerName.set(firstName + " " + middleName + " " + lastName);
        phone.set(table.getMobileNoISDCode() + " " + table.getMobileNo());
        fathersName.set(table.getFatherSpouseFullName());
        mothersName.set(table.getMotherSFullName());

//        try {
//            binding.imgIdProof.setImageBitmap(compressBitmap(table.getApplicantIdProof()));
//            idProof64.set(getBase64String(compressBitmap(table.getApplicantIdProof())));
//
//            binding.imgAddressProof.setImageBitmap(compressBitmap(table.getApplicantAddressProof()));
//            addressProof64.set(getBase64String(compressBitmap(table.getApplicantAddressProof())));
//
//            binding.imgProfilePhoto.setImageBitmap(compressBitmap(table.getApplicantImage()));
//            profilePhoto64.set(getBase64String(compressBitmap(table.getApplicantImage())));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    public void clickLogout(View view) {
        mAction.setValue(new UploadAction(UploadAction.CLICK_BACK));
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

}
