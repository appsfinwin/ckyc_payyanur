package com.finwin.payyanur.ckyc.home.customer_id_proof;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
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
import com.finwin.payyanur.ckyc.home.customer_id_proof.action.CustomerIdProofAction;
import com.finwin.payyanur.ckyc.home.customer_id_proof.model.IdProofType;
import com.finwin.payyanur.ckyc.home.customer_id_proof.pojo.get_id_proof_details.Table;
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

public class CustomerIdProofViewModel extends AndroidViewModel implements Observable {


    public CustomerIdProofViewModel(@NonNull Application application) {
        super(application);
        initSpinnerData();

        disposable = new CompositeDisposable();
        repository = CustomerIdProofRepository.getInstance();
        repository.setDisposable(disposable);
        repository.setmAction(mAction);

        sharedPreferences = application.getSharedPreferences(ConstantClass.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void initSpinnerData() {
        idProofList.add("--Select--");
        idProofList.add("Passport");
        idProofList.add("Voter ID");
        idProofList.add("PAN");
        idProofList.add("Driving Licence");
        idProofList.add("UID");

        idProofListData.add(new IdProofType("--Select--", "-1"));
        idProofListData.add(new IdProofType("Passport", "A"));
        idProofListData.add(new IdProofType("Voter ID", "B"));
        idProofListData.add(new IdProofType("PAN", "C"));
        idProofListData.add(new IdProofType("Driving Licence", "D"));
        idProofListData.add(new IdProofType("UID", "E"));

    }

    CustomerIdProofRepository repository;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CompositeDisposable disposable;
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();
    public ObservableArrayList<String> idProofList = new ObservableArrayList<>();
    public ObservableArrayList<IdProofType> idProofListData = new ObservableArrayList<>();
    public ObservableField<String> selectedIdProof = new ObservableField<>("-1");
    public ObservableField<String> selectedExpiryDate = new ObservableField<>("");
    public ObservableField<String> etIdProofNumber = new ObservableField<>("");
    public ObservableField<String> idProofSideOne64 = new ObservableField<>("");
    public ObservableField<String> idProofSideTwo64 = new ObservableField<>("");
    public ObservableField<Boolean> isExpiryMandatory = new ObservableField<>(false);
    public ObservableField<String> expiryDate = new ObservableField<>("select date");

    public ObservableField<Integer> expiryDateVisibility = new ObservableField<>(View.GONE);
    public MutableLiveData<CustomerIdProofAction> mAction = new MutableLiveData<>();

    public MutableLiveData<CustomerIdProofAction> getmAction() {
        mAction = repository.getmAction();
        return mAction;
    }

    private int idProof = 0;

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


    @Bindable
    public int getIdProof() {
        return idProof;
    }

    public void setIdProof(int idProof) {
        this.idProof = idProof;
        registry.notifyChange(this, BR.idProof);
    }

    public void clickIdProofSideOne(View view) {
        mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.CLICK_ID_PROOF_SIDE1));
    }


    public void clickIdProofSideTwo(View view) {
        mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.CLICK_ID_PROOF_SIDE2));
    }

    public void onSelectedIdProof(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            selectedIdProof.set(idProofListData.get(position).getIdProofCode());
            switch (idProofListData.get(position).getIdProofCode()) {
                case "A":
                case "D":
                    expiryDateVisibility.set(View.VISIBLE);
                    isExpiryMandatory.set(true);
                   // expiryDate.set("select date");
                    break;
                case "B":
                case "E":
                case "C":
                    expiryDateVisibility.set(View.GONE);
                    isExpiryMandatory.set(false);
                    expiryDate.set("");
                    break;


            }
        }
    }

    public void clickExpiryDate(View view) {
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
                expiryDate.set(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                selectedExpiryDate.set(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        };

        new DatePickerDialog(view.getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void clickNext(View view) {
        if (selectedIdProof.get().equals("-1")) {
            toast(view, "Please select an ID proof!");
        } else if (etIdProofNumber.get().equals("")) {
            toast(view, "ID proof number cannot be empty!");
        } else if ((isExpiryMandatory.get() == true) && expiryDate.get().equals("select date")) {
            toast(view, "Expiry date cannot be empty");
        } else if (idProofSideOne64.get().equals("")) {
            toast(view, "Please select id proof image");
        } else if (idProofSideTwo64.get().equals("")) {
            toast(view, "Please select id proof image");
        }else if (sharedPreferences.getString(ConstantClass.CUST_ID,"").equals("")) {
            toast(view, "Please select a customer id in customer details page");
        } else {
            initLoading(view.getContext());
            updateIdProof();
            //toast(view,"submit");

        }
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.remove(callback);
    }

    public void toast(View view, String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void getCustomerDetails(String custId) {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", custId);
        params.put("Flag", "SELECTONE");


        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.getIdProofDetails(apiInterface, body);
    }

    public void updateIdProof() {

        Map<String, String> params = new HashMap<>();
        params.put("Cust_Id", sharedPreferences.getString(ConstantClass.CUST_ID,""));
        params.put("Flag", "UPDATE_ID_MOBILEAPP");
        params.put("Id_Proof_Image_Side1", idProofSideOne64.get());
        params.put("Id_Proof_Image_Side2", idProofSideTwo64.get());
        params.put("Identification_Type", selectedIdProof.get());
        params.put("Identification_Number", etIdProofNumber.get());
        params.put("Id_Expiry_Date", selectedExpiryDate.get());


        String request= new JSONObject(params).toString();
        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.updateIdProof(apiInterface, body);
    }

    public void setIdProofData(List<Table> table) {
        if (table.size() > 0) {

            expiryDate.set(table.get(0).getId_Expiry_Date());
            etIdProofNumber.set(table.get(0).getIdentification_Number());
            for (int i = 0; i < idProofListData.size(); i++) {
                if (idProofListData.get(i).getIdProofCode().equals(table.get(0).getIdentification_Type())) {
                    int k = i;
                    setIdProof(i);
                }
            }
        }
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        (repository.getDisposable()).dispose();
        mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.DEFAULT));
    }

}
