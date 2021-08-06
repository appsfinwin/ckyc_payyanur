package com.finwin.payyanur.ckyc.login;

import androidx.lifecycle.MutableLiveData;

import com.finwin.payyanur.ckyc.login.action.LoginAction;
import com.finwin.payyanur.ckyc.login.pojo.LoginResponse;
import com.finwin.payyanur.ckyc.retrofit.ApiInterface;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class LoginRepository {
    public static LoginRepository instance;
    public static LoginRepository getInstance()
    {
        if (instance==null)
        {
            instance=new LoginRepository();
        }
        return instance;
    }
    CompositeDisposable disposable;
    MutableLiveData<LoginAction> mAction;

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public void setDisposable(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    public MutableLiveData<LoginAction> getmAction() {
        return mAction;
    }

    public void setmAction(MutableLiveData<LoginAction> mAction) {
        this.mAction = mAction;
    }

    public void login(ApiInterface apiInterface, RequestBody body) {
        Single<LoginResponse> call = apiInterface.login(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(LoginResponse response) {

                        try {

                            if (response.getTable1()!=null)
                            {
                                mAction.setValue(new LoginAction(LoginAction.LOGIN_SUCCESS,response));
                            }
                            else
                            {
                                mAction.setValue(new LoginAction(LoginAction.API_ERROR,"Invalid Username or Password"));
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new LoginAction(LoginAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new LoginAction(LoginAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new LoginAction(LoginAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }
}
