package com.finwin.payyanur.ckyc.home.customer_id_proof;

import androidx.lifecycle.MutableLiveData;

import com.finwin.payyanur.ckyc.home.customer_id_proof.action.CustomerIdProofAction;
import com.finwin.payyanur.ckyc.home.customer_id_proof.pojo.get_id_proof_details.GetIdProofDetailsResponse;
import com.finwin.payyanur.ckyc.home.customer_id_proof.update_user.UpdateIdproofResponse;
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

public class CustomerIdProofRepository {
    public static CustomerIdProofRepository instance;
    public static CustomerIdProofRepository getInstance()
    {
        if (instance==null)
        {
            instance=new CustomerIdProofRepository();
        }
        return instance;
    }

    CompositeDisposable disposable;
    MutableLiveData<CustomerIdProofAction> mAction;

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public void setDisposable(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    public MutableLiveData<CustomerIdProofAction> getmAction() {
        return mAction;
    }

    public void setmAction(MutableLiveData<CustomerIdProofAction> mAction) {
        this.mAction = mAction;
    }



    public void getIdProofDetails(ApiInterface apiInterface, RequestBody body) {
        Single<GetIdProofDetailsResponse> call = apiInterface.getIdProofDetails(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetIdProofDetailsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(GetIdProofDetailsResponse response) {



                        if (response.getData().getTable().size()>0)
                        {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.GET_ID_PROOF_DETAILS,response));
                        }
                        else
                        {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.INVALID_CUST_ID));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

    public void updateIdProof(ApiInterface apiInterface, RequestBody body) {
        Single<UpdateIdproofResponse> call = apiInterface.updateIdProof(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UpdateIdproofResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(UpdateIdproofResponse response) {



                        if (response.getData().getTable1().size()>0) {
                            if (response.getData().getTable1().get(0).getReturnStatus().equals("Y")) {
                                mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.UPDATE_ID_PROOF_SUCCESS, response));
                            }else {
                                mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR,response.getData().getTable1().get(0).getReturnMessage()));
                            }
                        }
                        else
                        {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new CustomerIdProofAction(CustomerIdProofAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

}
