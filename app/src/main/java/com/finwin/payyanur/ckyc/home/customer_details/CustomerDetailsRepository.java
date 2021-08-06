package com.finwin.payyanur.ckyc.home.customer_details;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.finwin.payyanur.ckyc.home.customer_details.action.CustomerDetailsAction;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.create_user.CreateUserResponse;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getBranch.GetBranchDetailsResponse;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getCustomerDetails.GetCustomerDetailsResponse;
import com.finwin.payyanur.ckyc.retrofit.ApiInterface;
import com.finwin.payyanur.ckyc.upload.pojo.VerifyCustIdResponse;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class CustomerDetailsRepository {
    Application application;
    CompositeDisposable disposable;
    public static CustomerDetailsRepository instance;
    MutableLiveData<CustomerDetailsAction> mAction;
    //CustomerDatabase database;
   // SaveDataDao saveDataDao;
    private static final String TAG = "CustomerDetailsReposito";

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public void setDisposable(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    public MutableLiveData<CustomerDetailsAction> getmAction() {
        return mAction;
    }

    public void setmAction(MutableLiveData<CustomerDetailsAction> mAction) {
        this.mAction = mAction;
    }

    public CustomerDetailsRepository(Application application) {
        this.application = application;

//        CustomerDatabase database= CustomerDatabase.getInstance(application);
//        saveDataDao =database.saveDataDao();
//        setCustId();
    }

    public static CustomerDetailsRepository getInstance(Application application)
    {

        if (instance==null)
        {
            instance=new CustomerDetailsRepository(application);
        }
        return instance;
    }

    public void getBranchDetails(ApiInterface apiInterface, RequestBody body) {
        Single<GetBranchDetailsResponse> call = apiInterface.getBranchDetails(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetBranchDetailsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(GetBranchDetailsResponse response) {



                        if (response.getData().getBranch().size()>0)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.GET_BRANCH_DETAILS_SUCCESS,response));
                        }
                        else
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR));
                        }



                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

    public void createUser(ApiInterface apiInterface, RequestBody body) {
        Single<CreateUserResponse> call = apiInterface.createUser(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CreateUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(CreateUserResponse response) {

                        if (response.getData().getCreateUserData().size()>0) {
                            if (response.getData().getCreateUserData().get(0).getReturnStatus().equals("Y")) {
                                mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.CREATE_USER_SUCCESS, response));
                            } else {
                                mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,response.getData().getCreateUserData().get(0).getReturnMessage()));
                            }
                        }



                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
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
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.VERIFY_CUST_ID_SUCCESS,response));
                        }
                        else
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.INVALID_CUST_ID));
                        }



                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

    public void getCustomerDetails(ApiInterface apiInterface, RequestBody body) {
        Single<GetCustomerDetailsResponse> call = apiInterface.getCustomerDetails(body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetCustomerDetailsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(GetCustomerDetailsResponse response) {



                        if (response.getData().getCustomerData().size()>0)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.GET_CUSTOMER_DETAILS,response));
                        }
                        else
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"Invalid Customer ID"));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"Timeout! Please try again later"));
                        }else if (e instanceof UnknownHostException)
                        {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR,"No Internet"));
                        }else {
                            mAction.setValue(new CustomerDetailsAction(CustomerDetailsAction.API_ERROR, e.getMessage()));
                        }
                    }
                });
    }

//    public CustomerDatabase getDatabase() {
//        return database;
//    }
//
//    public void setDatabase(CustomerDatabase database) {
//        this.database = database;
//    }
//
//    public void getUserDetails(){
//        CustomerDatabase.databaseWriteExecutor.execute(() -> {
//            saveDataDao.getBranchId("1052");
//        });
//    }
//
//    public void setCustId(){
//        CustomerDatabase.databaseWriteExecutor.execute(() -> {
//            saveDataDao.setCustID(1052);
//        });
//    }

//    public void getUserDetails()
//    {
//        database.saveDataDao().getBranchId("1052")
//                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<SaveData>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(SaveData saveData) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
//    }

//    public void setCustId(int i) {
//        database.saveDataDao().setCustID(i)
//                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<SaveData>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(SaveData saveData) {
//
//                        Log.d(TAG, "onSuccess: "+saveData.toString());
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
//    }
}
