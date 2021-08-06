package com.finwin.payyanur.ckyc.login;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.finwin.payyanur.ckyc.login.action.LoginAction;
import com.finwin.payyanur.ckyc.retrofit.ApiInterface;
import com.finwin.payyanur.ckyc.retrofit.RetrofitClient;
import com.finwin.payyanur.ckyc.utils.Services;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginViewModel extends AndroidViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository= LoginRepository.getInstance();
        mAction=new MutableLiveData<>();
        disposable=new CompositeDisposable();
        repository.setDisposable(disposable);
        repository.setmAction(mAction);
        this.application=application;
    }
    Application application;
    ApiInterface apiInterface;
    LoginRepository repository;
    CompositeDisposable disposable;
    MutableLiveData<LoginAction> mAction;
    public ObservableField<String> ob_userName=new ObservableField<>("");
    public ObservableField<String> ob_password=new ObservableField<>("");

    public MutableLiveData<LoginAction> getmAction() {
        mAction=repository.getmAction();
        return mAction;
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
    public void login() {

        Map<String, String> params = new HashMap<>();
        params.put("Username", ob_userName.get());
        params.put("Password", ob_password.get());

        apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());
        repository.login(apiInterface, body);
    }

    public void clickLogin(View view)
    {
        if (ob_userName.get().equals(""))
        {
            Toast.makeText(application, "username cannot be empty", Toast.LENGTH_SHORT).show();
        }else if (ob_password.get().equals(""))
        {
            Toast.makeText(application, "password cannot be empty", Toast.LENGTH_SHORT).show();
        }else {
            initLoading(view.getContext());
            login();
        }
    }
}
