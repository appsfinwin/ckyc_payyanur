package com.finwin.payyanur.ckyc.home.customer_address_proof;

import androidx.lifecycle.MutableLiveData;

import com.finwin.payyanur.ckyc.home.customer_address_proof.action.CustomerAddressProofAction;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details.GetAddressProofResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_districts.GetDistrictResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_pincode.GetPincodeResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_states.GetStatesResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.update_address_proof.update_user.UpdateAddressProofResponse;
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

public class CustomerAddressProofRepository {
    public static CustomerAddressProofRepository instance;

    public static CustomerAddressProofRepository getInstance() {
        if (instance == null) {
            instance = new CustomerAddressProofRepository();
        }
        return instance;
    }

    MutableLiveData<CustomerAddressProofAction> mAction;
    CompositeDisposable disposable;

    public MutableLiveData<CustomerAddressProofAction> getmAction() {
        return mAction;
    }

    public void setmAction(MutableLiveData<CustomerAddressProofAction> mAction) {
        this.mAction = mAction;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public void setDisposable(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    public void getDistricts(ApiInterface apiInterface, RequestBody body) {
        Single<GetDistrictResponse> call = apiInterface.getDistricts(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetDistrictResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(GetDistrictResponse response) {


                        if (response.getData().getDistrict().size() > 0) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.GET_DISTRICTS_SUCCESS, response));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR));
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "Timeout! Please try again later"));
                        } else if (e instanceof UnknownHostException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "No Internet"));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

    public void getPincode(ApiInterface apiInterface, RequestBody body) {
        Single<GetPincodeResponse> call = apiInterface.getPincode(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetPincodeResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(GetPincodeResponse response) {

                        if (response.getData().getPincode().size() > 0) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.GET_PINCODE_SUCCESS, response));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR));
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "Timeout! Please try again later"));
                        } else if (e instanceof UnknownHostException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "No Internet"));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

    public void getStates(ApiInterface apiInterface, RequestBody body) {
        Single<GetStatesResponse> call = apiInterface.getStates(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetStatesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(GetStatesResponse response) {


                        if (response.getData().getStates().size() > 0) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.GET_STATES_SUCCESS, response));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR));
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "Timeout! Please try again later"));
                        } else if (e instanceof UnknownHostException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "No Internet"));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

    public void updateAddressProofDetails (ApiInterface apiInterface, RequestBody body) {
        Single<UpdateAddressProofResponse> call = apiInterface.updateAddressProofDetails(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UpdateAddressProofResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(UpdateAddressProofResponse response) {

                        if (response.getData().getTable1().size() > 0) {
                            if (response.getData().getTable1().get(0).getReturnStatus().equals("Y")) {
                                mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.UPDATE_ADDRESS_DETAILS_SUCCESS, response));
                            } else {
                                mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR));
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "Timeout! Please try again later"));
                        } else if (e instanceof UnknownHostException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "No Internet"));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }


    public void getAddressProofDetails(ApiInterface apiInterface, RequestBody body) {
        Single<GetAddressProofResponse> call = apiInterface.getAddressProofDetails(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetAddressProofResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(GetAddressProofResponse response) {

                        if (response.getData().getAddressProof().size() > 0) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.GET_ADDRESS_DETAILS_SUCCESS, response));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "Timeout! Please try again later"));
                        } else if (e instanceof UnknownHostException) {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, "No Internet"));
                        } else {
                            mAction.setValue(new CustomerAddressProofAction(CustomerAddressProofAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }
}
