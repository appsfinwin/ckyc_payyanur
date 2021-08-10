package com.finwin.payyanur.ckyc.home.customer_address_proof;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.finwin.payyanur.ckyc.BR;
import com.finwin.payyanur.ckyc.home.customer_address_proof.action.CustomerAddressProofAction;

import com.finwin.payyanur.ckyc.home.customer_address_proof.model.AddressProof;
import com.finwin.payyanur.ckyc.home.customer_address_proof.model.AddressType;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_districts.District;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_pincode.Pincode;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_states.States;
import com.finwin.payyanur.ckyc.retrofit.ApiInterface;
import com.finwin.payyanur.ckyc.retrofit.RetrofitClient;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;
import com.finwin.payyanur.ckyc.utils.Services;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CustomerAddressProofViewModel extends AndroidViewModel implements Observable {
    public CustomerAddressProofViewModel(@NonNull Application application) {
        super(application);
        repository = CustomerAddressProofRepository.getInstance();
        repository.setmAction(mAction);
        repository.setDisposable(disposable);
        sharedPreferences = application.getSharedPreferences(ConstantClass.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setSpinnerData();
    }
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private void setSpinnerData() {
        addressTypeList.add("--Select--");
        addressTypeList.add("Resident/Business");
        addressTypeList.add("Residential");
        addressTypeList.add("Business");
        addressTypeList.add("Registered Office");
        addressTypeList.add("Unspecified");
        contryList.add("India");


        addressTypeListData.add(new AddressType("--Select--", "-1"));
        addressTypeListData.add(new AddressType("Resident/Business", "01"));
        addressTypeListData.add(new AddressType("Residential", "02"));
        addressTypeListData.add(new AddressType("Business", "03"));
        addressTypeListData.add(new AddressType("Registered Office", "04"));
        addressTypeListData.add(new AddressType("Unspecified", "05"));

        addressProofList.add("--Select--");
        addressProofList.add("UID (Aadhar Card)");
        addressProofList.add("Passport");
        addressProofList.add("Driving License");
        addressProofList.add("Voters Identity Card");
        addressProofList.add("NREGA Job Card");
        addressProofList.add("Other");

        addressProofListData.add(new AddressProof("--Select--", "-1"));
        addressProofListData.add(new AddressProof("UID (Aadhar Card)", "01"));
        addressProofListData.add(new AddressProof("Passport", "02"));
        addressProofListData.add(new AddressProof("Driving License", "03"));
        addressProofListData.add(new AddressProof("Voters Identity Card", "04"));
        addressProofListData.add(new AddressProof("NREGA Job Card", "05"));
        addressProofListData.add(new AddressProof("Other", "99"));

        stateList.add("--Select State--");
        stateListData.add(new States("-1","--Select State--"));

        districtList.add("--Select District--");
        districtListData.add(new District("--Select District--","-1"));

        pincodeList.add("--Select Pincode--");
        pincodeListData.add(new Pincode("-1","--Select Pincode--"));
    }

    public void resetDistrict()
    {
        districtList.clear();
        districtListData.clear();
        districtList.add("--Select District--");
        districtListData.add(new District("--Select District--","-1"));
    }

    public void resetPincode()
    {
        pincodeList.clear();
        pincodeListData.clear();
        pincodeList.add("--Select Pincode--");
        pincodeListData.add(new Pincode("-1","--Select Pincode--"));
    }

    MutableLiveData<CustomerAddressProofAction> mAction = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();
    CustomerAddressProofRepository repository;

    public ObservableArrayList<AddressType> addressTypeListData = new ObservableArrayList<>();
    public ObservableArrayList<String> addressTypeList = new ObservableArrayList<>();
    public ObservableArrayList<String> contryList = new ObservableArrayList<>();
    public ObservableArrayList<String> addressProofList = new ObservableArrayList<>();
    public ObservableArrayList<AddressProof> addressProofListData = new ObservableArrayList<>();

    public ObservableArrayList<String> pincodeList = new ObservableArrayList<>();
    public ObservableArrayList<Pincode> pincodeListData = new ObservableArrayList<>();

    public ObservableArrayList<String> stateList = new ObservableArrayList<>();
    public ObservableArrayList<States> stateListData = new ObservableArrayList<>();

    public ObservableArrayList<String> districtList = new ObservableArrayList<>();
    public ObservableArrayList<District> districtListData = new ObservableArrayList<>();

    public ObservableField<String> stateCode = new ObservableField<>("");
    public ObservableField<String> selectedAddressType = new ObservableField<>("");
    public ObservableField<String> selectedAddressProof = new ObservableField<>("");
    public ObservableField<String> selectedState = new ObservableField<>("");
    public ObservableField<String> selectedDistrict = new ObservableField<>("");
    public ObservableField<String> selectedPincode = new ObservableField<>("");
    public ObservableField<String> selectedContryCode = new ObservableField<>("");
    public ObservableField<String> etAddressLineOne = new ObservableField<>("");
    public ObservableField<String> etAddressLineTwo = new ObservableField<>("");
    public ObservableField<String> etAddressLineThree = new ObservableField<>("");
    public ObservableField<String> etCityOrTown = new ObservableField<>("");
    public ObservableField<String> addressProofsideOne64 = new ObservableField<>("");
    public ObservableField<String> addressProofsideTwo64 = new ObservableField<>("");

    public int addressType = 0;
    public int addressProof = 0;
    public int state = 0;
    public int district = 0;
    public int pincode=0;

    SweetAlertDialog loading;

    public void initLoading(Context context,String tag) {
        loading = Services.showProgressDialog(context);
        loading.setContentText(tag);
    }

    public void cancelLoading() {
        if (loading != null) {
            loading.cancel();
            loading = null;
        }
    }


    public MutableLiveData<CustomerAddressProofAction> getmAction() {
        mAction=repository.getmAction();
        return mAction;
    }

    public PropertyChangeRegistry registry = new PropertyChangeRegistry();

    @Bindable
    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
        registry.notifyChange(this, BR.addressType);
    }

    public void onSelectAddressType(AdapterView<?> parent, View view, int position, long id) {

        {
            selectedAddressType.set(addressTypeListData.get(position).getAddressTypeCode());
        }
    }

    @Bindable
    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
        registry.notifyChange(this, BR.pincode);
    }

    public void onSelectPincode(AdapterView<?> parent, View view, int position, long id) {


        {
            selectedPincode.set(pincodeListData.get(position).getId());
        }
    }

    @Bindable
    public int getAddressProof() {
        return addressProof;
    }

    public void setAddressProof(int addressProof) {
        this.addressProof = addressProof;
        registry.notifyChange(this, BR.addressProof);
    }

    public void onSelectAddressProof(AdapterView<?> parent, View view, int position, long id) {


        {
            selectedAddressProof.set(addressProofListData.get(position).getAddressProofCode());
        }
    }

    @Bindable
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        registry.notifyChange(this, BR.state);
    }

    public void onSelectState(AdapterView<?> parent, View view, int position, long id) {

        setDistrict(0);
        setPincode(0);
        if (position!=0)
        {
            resetDistrict();
            resetPincode();
            initLoading(view.getContext(),"get district");
            getDistricts(stateListData.get(position).getId());
            selectedState.set(stateListData.get(position).getId());
        }
    }

    public void setDistrict(int district) {
        this.district = district;
        registry.notifyChange(this, BR.district);
    }

    @Bindable
    public int getDistrict() {
        return district;
    }

    public void onSelectDistrict(AdapterView<?> parent, View view, int position, long id) {

        setPincode(0);
        {
            resetPincode();
            if (position!=0) {
                initLoading(view.getContext(), "get pincode");
                getPincode(districtListData.get(position).getId());
                selectedDistrict.set(districtListData.get(position).getId());
            }
        }
    }

    public void getStates() {

        Map<String, String> params = new HashMap<>();
        //params.put("Cust_Id", custId.get());
        params.put("Flag", "STATE");


        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.getStates(apiInterface, body);
    }

    public void getDistricts(String stateCode) {

        Map<String, String> params = new HashMap<>();
        params.put("StateCode", stateCode);
        params.put("Flag", "DISTRICT");


        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.getDistricts(apiInterface, body);
    }

    public void getPincode(String district) {

        Map<String, String> params = new HashMap<>();
        params.put("District", district);
        params.put("Flag", "PIN");


        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.getPincode(apiInterface, body);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

        registry.remove(callback);
    }

    public void setStatesData(List<States> states) {
        if (states.size()>0)
        {
            for (States data:states)
            {
                stateList.add(data.getText());
                stateListData.add(data);
            }
        }
    }

    public void setDistrictsData(List<District> district) {
        if (district.size()>0)
        {
            for (District data: district)
            {
                districtList.add(data.getText());
                districtListData.add(data);
            }
        }
    }

    public void setPincodeData(List<Pincode> pincode) {
        for (Pincode data:pincode)
        {
            pincodeList.add(data.getText());
            pincodeListData.add(data);
        }
    }

    public void getCustomerDetails(String custId) {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", custId);
        params.put("flag", "SELECTONE");


        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.getAddressProofDetails(apiInterface, body);
    }

    public void updateAddressProof() {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", sharedPreferences.getString(ConstantClass.CUST_ID,""));
        params.put("Flag", "UPDATE_ADDR_MOBILEAPP");
        params.put("Current_Address_Type", selectedAddressType.get());
        params.put("Proof_of_Address", selectedAddressProof.get());
        params.put("Current_Line_1", etAddressLineOne.get());
        params.put("Current_Line_2", etAddressLineTwo.get());
        params.put("Current_Line_3", etAddressLineThree.get());
        params.put("Current_City_Town_Village", etCityOrTown.get());
        params.put("Current_State", selectedState.get());
        params.put("Current_District", selectedDistrict.get());
        params.put("Current_Country", "IN");
        params.put("Current_PIN_Code", selectedPincode.get());
        params.put("Address_Proof_Image_Side1", addressProofsideOne64.get());
        params.put("Address_Proof_Image_Side2", addressProofsideTwo64.get());



        String request=new JSONObject(params).toString();
        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.updateAddressProofDetails(apiInterface, body);
    }

    public void setAddressProofDetails(List< com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details.AddressProof> addressProofData) {
        if (addressProofData.size()>0)
        {try {


            com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details.AddressProof addressProof= addressProofData.get(0);
            addressProofsideOne64.set(addressProof.getAddressProofImageSide1());
            addressProofsideTwo64.set(addressProof.getAddressProofImageSide2());
            if (addressProof.getAddressType()!=null) {
                for (int i = 0; i < addressTypeListData.size(); i++) {
                    if (addressTypeListData.get(i).getAddressTypeCode().equals(addressProof.getAddressType())) {
                        setAddressType(i);
                    }
                }
            }

            if (addressProof.getAddressType()!=null){
            for(int i=0;i<addressProofListData.size();i++)
            {
                if (addressProofListData.get(i).getAddressProofCode().equals(addressProof.getAddressType()))
                {
                    setAddressProof(i);
                }
            }}

            if (addressProof.getState()!=null){
            if (addressProof.getState()!=null) {
                for (int i = 0; i < stateListData.size(); i++) {
                    if (stateListData.get(i).getId().equals(addressProof.getState())) {
                        setState(i);
                    }
                }}
            }
           etAddressLineOne.set(addressProof.getAddressLine1()==null ? "" : addressProof.getAddressLine1());
           etAddressLineTwo.set(addressProof.getAddressLine2()==null ? "":addressProof.getAddressLine2());
           etAddressLineThree.set(addressProof.getAddressLine3()==null ? "": addressProof.getAddressLine3());
           etCityOrTown.set(addressProof.getTownOrVillage()==null ? "": addressProof.getTownOrVillage());

        }catch (Exception e)
        {

        }
        }
    }

    public void setDistricts( com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details.AddressProof addressProof)
    {
       try {
           if (addressProof.getDistrict()!= null) {
               for (int i = 0; i < districtListData.size(); i++) {
                   if (districtListData.get(i).getId().equals(addressProof.getDistrict())) {
                       setDistrict(i);
                   }
               }
           }
       }catch (Exception e)
       {

       }
    }

    public void setPin( com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details.AddressProof addressProof)
    {
       try {
           if (addressProof.getPinCode()!=null){
           for(int i=0;i<pincodeListData.size();i++) {
               if (pincodeListData.get(i).getId().equals(addressProof.getPinCode())) {
                   setPincode(i);
               }
           }
           }
       }catch (Exception e)
       {

       }
    }

    public void clickSubmit(View view)
    {
        if (selectedAddressType.get().equals("-1"))
        {
            toast(view,"Please select address type");
        }else if (selectedAddressProof.get().equals("-1"))
        {
            toast(view,"Please select address proof");
        }else if (etAddressLineOne.get().equals(""))
        {
            toast(view,"Please fill address");
        }else if (etCityOrTown.get().equals(""))
        {
            toast(view,"Please Enter city/Town/Village");
        }else if (selectedState.get().equals("-1"))
        {
            toast(view,"Please select state");
        }else if (selectedDistrict.get().equals("-1"))
        {
            toast(view,"Please select a district");
        }else if (selectedPincode.get().equals("-1"))
        {
            toast(view,"Please select pincode");
        }else if (addressProofsideOne64.get().equals(""))
        {
            toast(view,"Please select address proof ");
        }else if (addressProofsideTwo64.get().equals(""))
        {
            toast(view,"Please select address proof ");
        } else if (sharedPreferences.getString(ConstantClass.CUST_ID,"").equals("")) {
            toast(view, "Please select a customer id in customer details page");
        }else {
            initLoading(view.getContext(),"submit address");
            updateAddressProof();
        }
    }

    private void toast(View context, String message) {
        Toast.makeText(context.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public  void clickAddrressProof1(View view)
    {
        mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.CLICK_ADDRESS_PROOF_1));
    }

    public  void clickAddrressProof2(View view)
    {
        mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.CLICK_ADDRESS_PROOF_2));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.Default));
        (repository.getDisposable()).dispose();
    }
}
