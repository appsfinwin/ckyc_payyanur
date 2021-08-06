package com.finwin.payyanur.ckyc.retrofit;


import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details.GetAddressProofResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_districts.GetDistrictResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_pincode.GetPincodeResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_states.GetStatesResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.update_address_proof.update_user.UpdateAddressProofResponse;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.create_user.CreateUserResponse;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getBranch.GetBranchDetailsResponse;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getCustomerDetails.GetCustomerDetailsResponse;
import com.finwin.payyanur.ckyc.home.customer_id_proof.pojo.get_id_proof_details.GetIdProofDetailsResponse;
import com.finwin.payyanur.ckyc.home.customer_id_proof.update_user.UpdateIdproofResponse;
import com.finwin.payyanur.ckyc.login.pojo.LoginResponse;
import com.finwin.payyanur.ckyc.upload.pojo.VerifyCustIdResponse;
import com.finwin.payyanur.ckyc.upload.pojo.update.UpdateImageResponse;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiInterface {

    @POST("LogIn_Api")
    Single<LoginResponse> login(@Body RequestBody body);

    @POST("CKYC_ManagerApi")
    Single<VerifyCustIdResponse> verifyCustId(@Body RequestBody body);

    @POST("CKYC_ManagerApi")
    Single<GetCustomerDetailsResponse> getCustomerDetails(@Body RequestBody body);

    @POST("CKYC_ManagerApi")
    Single<GetIdProofDetailsResponse> getIdProofDetails(@Body RequestBody body);

    @POST("CKYC_ManagerApi")
    Single<GetAddressProofResponse> getAddressProofDetails(@Body RequestBody body);

    @POST("CKYC_ManagerApi")
    Single<UpdateAddressProofResponse> updateAddressProofDetails(@Body RequestBody body);


    @POST("CKYC_ManagerApi")
    Single<UpdateImageResponse> uploadPhotos(@Body RequestBody body);

    @POST("CKYC_ManagerApi")
    Single<CreateUserResponse> createUser(@Body RequestBody body);

    @POST("CKYC_ManagerApi")
    Single<UpdateIdproofResponse> updateIdProof(@Body RequestBody body);

    @POST("BankSettings_ManagerApi")
    Single<GetBranchDetailsResponse> getBranchDetails(@Body RequestBody body);

    @POST("CKYC_DefaultValuesApi")
    Single<GetStatesResponse> getStates(@Body RequestBody body);

    @POST("CKYC_DefaultValuesApi")
    Single<GetDistrictResponse> getDistricts(@Body RequestBody body);

    @POST("CKYC_DefaultValuesApi")
    Single<GetPincodeResponse> getPincode(@Body RequestBody body);
}