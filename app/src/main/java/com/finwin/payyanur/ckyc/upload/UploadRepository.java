package com.finwin.payyanur.ckyc.upload;

import androidx.lifecycle.MutableLiveData;


import com.finwin.payyanur.ckyc.retrofit.ApiInterface;
import com.finwin.payyanur.ckyc.upload.pojo.VerifyCustIdResponse;
import com.finwin.payyanur.ckyc.upload.pojo.update.UpdateImageResponse;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class UploadRepository
{
    public static UploadRepository instance;
    public static UploadRepository getInstance()
    {
        if (instance==null)
        {
            instance= new UploadRepository();
        }
        return instance;
    }

    MutableLiveData<UploadAction> mAction;
    CompositeDisposable disposable;

    public MutableLiveData<UploadAction> getmAction() {
        return mAction;
    }

    public void setmAction(MutableLiveData<UploadAction> mAction) {
        this.mAction = mAction;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public void setDisposable(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    public void verifyCustId(ApiInterface apiInterface, RequestBody body) {
        Single<VerifyCustIdResponse> call = apiInterface.verifyCustId(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<VerifyCustIdResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(VerifyCustIdResponse response) {



                            if (response.getData().getTable().size()>0)
                            {
                                mAction.setValue(new UploadAction(UploadAction.VERIFY_CUST_ID_SUCCESS,response));
                            }
                            else
                            {
                                mAction.setValue(new UploadAction(UploadAction.INVALID_CUST_ID));
                            }



                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new UploadAction(UploadAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new UploadAction(UploadAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new UploadAction(UploadAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

    public void uploadPhotos(ApiInterface apiInterface, RequestBody body) {
        Single<UpdateImageResponse> call = apiInterface.uploadPhotos(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UpdateImageResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(UpdateImageResponse response) {

                        if (response.getData().getTable1().size()>0)
                        {
                            mAction.setValue(new UploadAction(UploadAction.UPLOAD_PHOTOS_SUCCESS,response));
                        }
                        else
                        {
                            mAction.setValue(new UploadAction(UploadAction.UPLOAD_PHOTOS_ERROR));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new UploadAction(UploadAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new UploadAction(UploadAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new UploadAction(UploadAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

}
