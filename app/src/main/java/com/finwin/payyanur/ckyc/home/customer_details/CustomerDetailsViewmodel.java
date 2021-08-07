package com.finwin.payyanur.ckyc.home.customer_details;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.finwin.payyanur.ckyc.home.customer_details.action.CustomerDetailsAction;
import com.finwin.payyanur.ckyc.home.customer_details.model.AccountType;
import com.finwin.payyanur.ckyc.home.customer_details.model.CitizenShip;
import com.finwin.payyanur.ckyc.home.customer_details.model.ContryCode;
import com.finwin.payyanur.ckyc.home.customer_details.model.Gender;
import com.finwin.payyanur.ckyc.home.customer_details.model.MaritalStatus;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getBranch.Branch;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getCustomerDetails.CustomerData;
import com.finwin.payyanur.ckyc.retrofit.ApiInterface;
import com.finwin.payyanur.ckyc.retrofit.RetrofitClient;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;
import com.finwin.payyanur.ckyc.utils.Services;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CustomerDetailsViewmodel extends AndroidViewModel implements Observable {
    CustomerDetailsRepository repository;

    public CustomerDetailsViewmodel(@NonNull Application application) {
        super(application);
        this.application = application;
        setPrefix();
        setResident();
        // database=  CustomerDatabase.getInstance(application);
        repository = CustomerDetailsRepository.getInstance(application);
//        repository.setDatabase(database);
        repository.setDisposable(disposable);
        repository.setmAction(mAction);

        sharedPreferences = application.getSharedPreferences(ConstantClass.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private static final String TAG = "CustomerDetailsViewmode";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    MutableLiveData<Boolean> mVerifyCustId = new MutableLiveData<>(true);
    MutableLiveData<CustomerDetailsAction> mAction = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();
    Application application;

    public MutableLiveData<Boolean> getVerifyCustId() {
        return mVerifyCustId;
    }

    public ObservableField<String> custId = new ObservableField<>("");
    public ObservableField<String> flag = new ObservableField<>("SELECTONE");
    public ObservableField<String> dateOfBirth = new ObservableField<>("");
    public ObservableField<String> selectedDateOfBirth = new ObservableField<>("");
    public ObservableField<String> firstNameHint = new ObservableField<>("");
    public ObservableField<String> lastNameHint = new ObservableField<>("");
    public ObservableField<String> middleNameHint = new ObservableField<>("");
    public ObservableField<String> fatherOrSpouse = new ObservableField<>("");
    public ObservableField<String> fatherOrSpouseName = new ObservableField<>("");


    public ObservableArrayList<String> prefixList = new ObservableArrayList<>();
    public ObservableArrayList<String> residentList = new ObservableArrayList<>();

    public ObservableArrayList<String> genderList = new ObservableArrayList<>();
    public ObservableField<String> profile64 = new ObservableField<>("");
    public ObservableArrayList<Gender> genderListData = new ObservableArrayList<>();

    public ObservableArrayList<String> maritalStatusList = new ObservableArrayList<>();
    public ObservableArrayList<MaritalStatus> maritalStatusListData = new ObservableArrayList<>();

    public ObservableArrayList<String> citizenshipList = new ObservableArrayList<>();
    public ObservableArrayList<CitizenShip> citizenshipListData = new ObservableArrayList<>();
    public ObservableArrayList<ContryCode> contryCodeList = new ObservableArrayList<>();
    public ObservableArrayList<String> contryList = new ObservableArrayList<>();
    public ObservableArrayList<String> accountTypeList = new ObservableArrayList<>();
    public ObservableArrayList<AccountType> accountTypeListData = new ObservableArrayList<>();
    public ObservableArrayList<String> branchList = new ObservableArrayList<>();
    public ObservableArrayList<Branch> branchListData = new ObservableArrayList<>();


    public ObservableField<String> prefixFathersName = new ObservableField<>("");
    public ObservableField<String> prefixMothersName = new ObservableField<>("");
    public ObservableField<String> prefixApplicantName = new ObservableField<>("");

    public ObservableField<String> applicantFirstName = new ObservableField<>("");
    public ObservableField<String> applicantMiddleName = new ObservableField<>("");
    public ObservableField<String> applicantLastName = new ObservableField<>("");

    public ObservableField<String> fathersFirstName = new ObservableField<>("");
    public ObservableField<String> fathersMiddleName = new ObservableField<>("");
    public ObservableField<String> fathersLastName = new ObservableField<>("");

    public ObservableField<String> mothersFirstName = new ObservableField<>("");
    public ObservableField<String> mothersMiddleName = new ObservableField<>("");
    public ObservableField<String> mothersLastName = new ObservableField<>("");

    public ObservableField<String> selectedGender = new ObservableField<>("");
    public ObservableField<String> selectedMaritalStatus = new ObservableField<>("");
    public ObservableField<String> selectedResidentialStatus = new ObservableField<>("");
    public ObservableField<String> selectedContryCode = new ObservableField<>("");
    public ObservableField<String> selectedCitizenship = new ObservableField<>("");
    public ObservableField<String> selectedAccountType = new ObservableField<>("");
    public ObservableField<String> selectedBranchId = new ObservableField<>("");
    public ObservableField<String> insertFlag = new ObservableField<>("");
    public ObservableField<String> etPan = new ObservableField<>("");

    public ObservableField<Boolean> isVerifyCustId = new ObservableField<>(false);
    public ObservableField<Boolean> rbtnFather = new ObservableField<>(true);
    public ObservableField<Boolean> rbtnSpouse = new ObservableField<>(false);
    public ObservableField<Boolean> rbtnOldCustomer = new ObservableField<>(false);
    public ObservableField<Boolean> rbtnNewCustomer = new ObservableField<>(true);
    public ObservableField<Integer> custIdVisibility = new ObservableField<>(View.GONE);


    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    private int fathersNamePrefix = 0;
    private int mothersNamePrefix = 0;
    private int applicantNamePrefix = 0;
    private int gender = 0;
    private int maritalStatus = 0;
    private int residentialStatus = 0;
    private int citizenship = 0;
    private int occupationType = 0;
    private int contryCode = 0;
    private int accountType = 0;
    private int branch = 0;

    public void onTextChanged(CharSequence text) {

        mVerifyCustId.setValue(false);

    }


    private void setResident() {
        residentList.add("--Select--");
        residentList.add("Resident Individual");
        residentList.add("Non Resident Indian");
        residentList.add("Foreign National");
        residentList.add("Person of Indian Origin");
    }

    private void setPrefix() {
        prefixList.add("--Select--");
        prefixList.add("Mr");
        prefixList.add("Ms");
        prefixList.add("Mrs");
        prefixList.add("Master");
        prefixList.add("Baby");
        prefixList.add("Kumari");
        prefixList.add("Rev");
        prefixList.add("Prof");
        prefixList.add("Sister");
        prefixList.add("Dr");
        prefixList.add("Others");

        citizenshipList.add("--Select--");
        citizenshipList.add("Indian");
        citizenshipList.add("Others");

        citizenshipListData.add(new CitizenShip("--Select--",""));
        citizenshipListData.add(new CitizenShip("Indian","IN"));
        citizenshipListData.add(new CitizenShip("Others","OTHERS"));

        contryList.add("India");
        contryCodeList.add(new ContryCode("IN", "India"));

        maritalStatusList.add("--Select--");
        maritalStatusList.add("Married");
        maritalStatusList.add("Unmarried");
        maritalStatusList.add("Others");
        maritalStatusListData.add(new MaritalStatus("--Select--", "-1"));
        maritalStatusListData.add(new MaritalStatus("Married", "01"));
        maritalStatusListData.add(new MaritalStatus("Unmarried", "02"));
        maritalStatusListData.add(new MaritalStatus("Others", "03"));

        genderList.add("--Select--");
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Transgender");
        genderListData.add(new Gender("--Select--", "-1"));
        genderListData.add(new Gender("Male", "M"));
        genderListData.add(new Gender("Female", "F"));
        genderListData.add(new Gender("Transgender", "T"));


        accountTypeList.add("--Select--");
        accountTypeList.add("Normal");
        accountTypeList.add("Small");
        accountTypeList.add("Simplified");
        accountTypeList.add("OTP Based E-KYC");
        accountTypeList.add("Minor");

        accountTypeListData.add(new AccountType("--Select--", "-1"));
        accountTypeListData.add(new AccountType("Normal", "01"));
        accountTypeListData.add(new AccountType("Small", "02"));
        accountTypeListData.add(new AccountType("Simplified", "03"));
        accountTypeListData.add(new AccountType("OTP Based E-KYC", "04"));
        accountTypeListData.add(new AccountType("Minor", "05"));

        branchList.add("--Select--");
        branchListData.add(new Branch("--Select--", "-1"));
    }

    @Bindable
    public int getFathersNamePrefix() {
        return fathersNamePrefix;
    }

    public void setFathersNamePrefix(int fathersNamePrefix) {
        this.fathersNamePrefix = fathersNamePrefix;
        registry.notifyChange(this, BR.fathersNamePrefix);
    }

    public void onSelectFatherNamePrefix(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            prefixFathersName.set(prefixList.get(position));
        }
    }

    @Bindable
    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
        registry.notifyChange(this, BR.branch);
    }

    public void onSelectBranch(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedBranchId.set(branchListData.get(position).getId());
        }

    }

    @Bindable
    public int getContryCode() {
        return contryCode;
    }

    public void setContryCode(int contryCode) {
        this.contryCode = contryCode;
        registry.notifyChange(this, BR.contryCode);
    }

    public void onSelectContryCode(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedContryCode.set(contryCodeList.get(position).getContryCode());
        }
    }

    @Bindable
    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
        registry.notifyChange(this, BR.accountType);
    }

    public void onSelectAccountType(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedAccountType.set(accountTypeListData.get(position).getAccountTypeCode());
        }
    }

    @Bindable
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
        registry.notifyChange(this, BR.gender);
    }

    public void onSelectGender(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedGender.set(genderListData.get(position).getGenderCode());
        }
    }

    @Bindable
    public int getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(int citizenship) {
        this.citizenship = citizenship;
        registry.notifyChange(this, BR.citizenship);
    }

    public void onSelectCitizenship(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedCitizenship.set(citizenshipListData.get(position).getContryCode());
        }
    }

    @Bindable
    public int getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(int residentialStatus) {
        this.residentialStatus = residentialStatus;
        registry.notifyChange(this, BR.residentialStatus);
    }

    public void onSelectResidentialStatus(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedResidentialStatus.set(prefixList.get(position));
        }
    }


    @Bindable
    public int getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(int residentialStatus) {
        this.occupationType = occupationType;
        registry.notifyChange(this, BR.occupationType);
    }

    public void onSelectOccupationType(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedResidentialStatus.set(prefixList.get(position));
        }
    }


    @Bindable
    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
        registry.notifyChange(this, BR.maritalStatus);
    }

    public void onSelectMaritalStatus(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedMaritalStatus.set(maritalStatusListData.get(position).getMaritalStatusCode());
        }
    }

    public MutableLiveData<CustomerDetailsAction> getmAction() {
        mAction = repository.getmAction();
        return mAction;
    }

    @Bindable
    public int getMothersNamePrefix() {
        return mothersNamePrefix;
    }

    public void setMothersNamePrefix(int mothersNamePrefix) {
        this.mothersNamePrefix = mothersNamePrefix;
        registry.notifyChange(this, BR.mothersNamePrefix);
    }

    public void onSelectMotherNamePrefix(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            prefixMothersName.set(prefixList.get(position));
        }
    }

    @Bindable
    public int getApplicantNamePrefix() {
        return applicantNamePrefix;
    }

    public void setApplicantNamePrefix(int fathersNamePrefix) {
        this.applicantNamePrefix = fathersNamePrefix;
        registry.notifyChange(this, BR.applicantNamePrefix);
    }

    public void onSelectApplicantNamePrefix(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            prefixApplicantName.set(prefixList.get(position));
        }
    }

//    public void clickNext(View view)
//    {
//        //createUser();
//    }


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

    public void clickVerifyCustId(View view) {
        if (!custId.get().equals("")) {
            initLoading(view.getContext());
            verifyCustId();
        } else {
            Toast.makeText(application, "Customer ID cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void verifyCustId() {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", custId.get());
        params.put("flag", "SELECTONE");


        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        //repository.verifyCustId(apiInterface, body);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.remove(callback);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        (repository.getDisposable()).dispose();
        mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.DEFAULT));
    }

    public void radioChanged(RadioGroup radioGroup, int id) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(id);
        String radio = radioButton.getText().toString();
        switch (radio) {
            case "Father":
                //01
                fatherOrSpouse.set("01");
                fatherOrSpouseName.set("Father");
                rbtnFather.set(true);
                rbtnSpouse.set(false);
                firstNameHint.set("Father First Name");
                middleNameHint.set("Father Middle Name");
                lastNameHint.set("Father Last Name");

                break;

            case "Spouse":
                //02
                fatherOrSpouseName.set("Spouse");
                fatherOrSpouse.set("02");
                rbtnSpouse.set(true);
                rbtnFather.set(false);
                firstNameHint.set("Spouse First Name");
                middleNameHint.set("Spouse Middle Name");
                lastNameHint.set("Spouse Last Name");

                break;
        }
    }

    public void radioChangedCustomerType(RadioGroup radioGroup, int id) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(id);
        String radio = radioButton.getText().toString();
        switch (radio) {
            case "Old Customer":
                //01

                rbtnOldCustomer.set(true);
                rbtnNewCustomer.set(false);
                custIdVisibility.set(View.VISIBLE);
                isVerifyCustId.set(false);
                insertFlag.set("UPDATE_APP");
                //etPan.set("");
                mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.SELECT_OLD_CUSTOMER));
                break;

            case "New Customer":
                //02
                editor.putString(ConstantClass.CUST_ID, "");
                editor.apply();
                rbtnNewCustomer.set(true);
                rbtnOldCustomer.set(false);
                custIdVisibility.set(View.GONE);
                isVerifyCustId.set(false);
                insertFlag.set("INSERT_MOBILEAPP");
                resetData();
                mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.SELECT_NEW_CUSTOMER));
                break;
        }
    }

    public void setCustomerDetailsData(CustomerData customerData) {

        //  viewmodel.setSelectedAccountNumber(viewmodel.listAccountNumbers.indexOf(( sharedPreferences.getString(ConstantClass.ACCOUNT_NUMBER,"") +" ("+ sharedPreferences.getString(ConstantClass.SCHEME,"")+" )")));
        int fatherIndex = prefixList.indexOf(customerData.getApplicantFatherSpouseNamePrefix());
        int applicantIndex = prefixList.indexOf(customerData.getApplicantNamePrefix());
        int motherIndex = prefixList.indexOf(customerData.getApplicantMotherNamePrefix());
        setFathersNamePrefix(fatherIndex);
        setApplicantNamePrefix(applicantIndex);
        setMothersNamePrefix(motherIndex);
        applicantFirstName.set(customerData.getApplicantFirstName());
        applicantLastName.set(customerData.getApplicantLastName());
        fathersFirstName.set(customerData.getFatherSpouseFirstName());
        fathersMiddleName.set(customerData.getFatherSpouseMiddleName());
        fathersLastName.set(customerData.getFatherSpouseLastName());
        mothersFirstName.set(customerData.getMotherSFirstName());
        mothersMiddleName.set(customerData.getMotherSMiddleName());
        mothersLastName.set(customerData.getMotherSLastName());
        //  (prefixList.indexOf(table.getApplicantMotherNamePrefix()));

    }

    public void clickDob(View view) {
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // fixDateViewModel.updateDate(myCalendar);
                dateOfBirth.set(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                selectedDateOfBirth.set(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        };

        new DatePickerDialog(view.getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void setBranchDetails(List<Branch> data) {
        for (Branch branch : data) {
            branchList.add(branch.getName());
            branchListData.add(branch);
        }
    }

    public void getBranchDetails() {

        Map<String, String> params = new HashMap<>();

        params.put("flag", "SELECTALL");

        ApiInterface apiInterface;
        apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.getBranchDetails(apiInterface, body);
    }

    public void createUser() {

        Map<String, String> params = new HashMap<>();

        params.put("Cust_Id", sharedPreferences.getString(ConstantClass.CUST_ID,""));
        params.put("flag", insertFlag.get());
        params.put("PAN", etPan.get());
        params.put("Account_Type", selectedAccountType.get());
        params.put("Applicant_Name_Prefix", prefixApplicantName.get());
        params.put("Applicant_First_Name", applicantFirstName.get());
        params.put("Applicant_Middle_Name", applicantMiddleName.get());
        params.put("Applicant_Last_Name", applicantLastName.get());
        params.put("Flag_indicating_Father_or_Spouse_Name", fatherOrSpouse.get());
        params.put("Applicant_Father_Or_Spouse_Name_Prefix", prefixFathersName.get());
        params.put("Father_Or_Spouse_First_Name", fathersFirstName.get());
        params.put("Father_Or_Spouse_Middle_Name", fathersMiddleName.get());
        params.put("Father_Or_Spouse_Last_Name", fathersLastName.get());
        params.put("Applicant_Mother_Name_Prefix", prefixMothersName.get());
        params.put("Mother_First_Name", mothersFirstName.get());
        params.put("Mother_Middle_Name", mothersMiddleName.get());
        params.put("Mother_Last_Name", mothersLastName.get());
        params.put("Gender", selectedGender.get());
        params.put("Marital_status", selectedMaritalStatus.get());
        params.put("Nationality", selectedCitizenship.get());
        //params.put("Occupation_Type", "");
        params.put("Date_of_Birth", selectedDateOfBirth.get());
        params.put("Applicant_Image", profile64.get());
        params.put("Residential_Status", "");
        params.put("Flag_Indian_NRI", "");
        params.put("Branch_Code", selectedBranchId.get());
        params.put("MakerId", sharedPreferences.getString(ConstantClass.MAKER_ID, ""));


        String request = new JSONObject(params).toString();
        Log.d(TAG, "create_request: " + request);

        ApiInterface apiInterface;
        apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.createUser(apiInterface, body);
    }

    public void clickFinwinCustId(View view) {
        if (custId.get().equals("")) {
            toast("Please enter Cust ID", view.getContext());
        } else {
            flag.set("SELECTONE");
            initLoading(view.getContext());
            getCustomerDetails();
        }
    }

    public void clickBankCustId(View view) {
        if (custId.get().equals("")) {
            toast("Please enter Cust ID", view.getContext());
        } else {
            flag.set("SELECTONE_BANCKCUSTID");
            initLoading(view.getContext());
            getCustomerDetails();
        }
    }


    public void getCustomerDetails() {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", custId.get());
        params.put("Flag", flag.get());


        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.getCustomerDetails(apiInterface, body);
    }

    public void toast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void setCustomerDetails(CustomerData customerData) {

        int fatherIndex = prefixList.indexOf(customerData.getApplicantFatherSpouseNamePrefix());
        int applicantIndex = prefixList.indexOf(customerData.getApplicantNamePrefix());
        int motherIndex = prefixList.indexOf(customerData.getApplicantMotherNamePrefix());
        setFathersNamePrefix(fatherIndex);
        setApplicantNamePrefix(applicantIndex);
        setMothersNamePrefix(motherIndex);
        etPan.set(customerData.getPan());
        applicantFirstName.set(customerData.getApplicantFirstName());
        applicantMiddleName.set(customerData.getApplicantMiddleName());
        applicantLastName.set(customerData.getApplicantLastName());
        fathersFirstName.set(customerData.getFatherSpouseFirstName());
        fathersMiddleName.set(customerData.getFatherSpouseMiddleName());
        fathersLastName.set(customerData.getFatherSpouseLastName());
        mothersFirstName.set(customerData.getMotherSFirstName());
        mothersMiddleName.set(customerData.getMotherSMiddleName());
        mothersLastName.set(customerData.getMotherSLastName());
        dateOfBirth.set(customerData.getDateOfBirthDateOfIncorporation());
        custId.set(customerData.getCustID());
        if (customerData.getFlagIndicatingFatherOrSpouseName().equals("01"))
        {
            rbtnFather.set(true);
            rbtnSpouse.set(false);
        }else if ((customerData.getFlagIndicatingFatherOrSpouseName().equals("02")))
        {
            rbtnFather.set(false);
            rbtnSpouse.set(true);
        }
        if (customerData.getNationality().equals("IN")) {
            setCitizenship(1);
        }
        setContryCode(0);
        for (int i = 0; i < accountTypeListData.size(); i++) {
            if (accountTypeListData.get(i).getAccountTypeCode().equals(customerData.getAccountType())) {
                int k = i;
                setAccountType(i);
            }
        }

        for (int i = 0; i < genderListData.size(); i++) {
            if (genderListData.get(i).getGenderCode().equals(customerData.getGender())) {
                int k = i;
                setGender(i);
            }
        }

        for (int i = 0; i < maritalStatusListData.size(); i++) {
            if (maritalStatusListData.get(i).getMaritalStatusCode().equals(customerData.getMaritalStatus())) {
                int k = i;
                setMaritalStatus(i);
            }
        }

        for (int i = 0; i < branchListData.size(); i++) {
            if (branchListData.get(i).getId().equals(customerData.getBranchCode())) {
                int k = i;
                setBranch(i);
            }
        }


    }

    public void resetData() {
        rbtnNewCustomer.set(true);
        rbtnOldCustomer.set(false);
        custId.set("");
        etPan.set("");
        setBranch(0);
        setApplicantNamePrefix(0);
        applicantFirstName.set("");
        applicantMiddleName.set("");
        applicantLastName.set("");
        rbtnFather.set(true);
        rbtnSpouse.set(false);

        setFathersNamePrefix(0);
        fathersFirstName.set("");
        fathersMiddleName.set("");
        fathersLastName.set("");

        setMothersNamePrefix(0);
        mothersFirstName.set("");
        mothersMiddleName.set("");
        mothersLastName.set("");
        setAccountType(0);
        dateOfBirth.set("Date of Birth");
        setGender(0);
        setMaritalStatus(0);
        setCitizenship(0);
        setContryCode(0);
        profile64.set("");
    }

    public void clickNext(View view) {
        if (rbtnOldCustomer.get() && !isVerifyCustId.get()) {
            toast("Please verify Customer ID", view.getContext());
        } else if (selectedBranchId.get().equals("")) {
            toast("Please Select Branch ID", view.getContext());
        } else if (prefixApplicantName.get().equals("")) {
            toast("Please Select Applicant Name Prefix", view.getContext());
        }else if (applicantFirstName.get().equals("")) {
            toast("Applicant First  Name cannot be empty", view.getContext());
        }else if (applicantLastName.get().equals("")) {
            toast("Applicant last name cannot be empty", view.getContext());
        }else if (prefixFathersName.get().equals("")) {
            toast("Please select "+fatherOrSpouseName.get()+"'s prefix", view.getContext());
        }else if (fathersFirstName.get().equals("")) {
            toast(fatherOrSpouseName.get()+"'s first name cannot be empty", view.getContext());
        }else if (fathersLastName.get().equals("")) {
            toast(fatherOrSpouseName.get() + "'s last name cannot be empty", view.getContext());
        }
//        } else if (prefixMothersName.get().equals("")) {
//            toast("Please select mother's name prefix", view.getContext());
//        }else if (mothersFirstName.get().equals("")) {
//            toast("Mother's First  Name cannot be empty", view.getContext());
//        }else if (mothersLastName.get().equals("")) {
//            toast("Mother's last  name cannot be empty", view.getContext());
//        }
        else if (selectedAccountType.get().equals("")) {
            toast("Please select an account type", view.getContext());
        }else if (dateOfBirth.get().equals("") || dateOfBirth.get().equals("Date of Birth")) {
            toast("Please select date of birth", view.getContext());
        }else if (selectedGender.get().equals("")) {
            toast("Please select a gender", view.getContext());
        }else if (selectedMaritalStatus.get().equals("")) {
            toast("Please select a marital status", view.getContext());
        }else if (selectedCitizenship.get().equals("")) {
            toast("Please select citizenship", view.getContext());
        }else if (profile64.get().equals("")) {
            toast("Please select profile image", view.getContext());
        }else
        {
            initLoading(view.getContext());
            createUser();
            //toast("submit",view.getContext());
        }

    }

}
