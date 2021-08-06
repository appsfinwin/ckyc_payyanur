package com.finwin.payyanur.ckyc.home.customer_details.action;

import com.finwin.payyanur.ckyc.home.customer_details.pojo.create_user.CreateUserResponse;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getBranch.GetBranchDetailsResponse;
import com.finwin.payyanur.ckyc.home.customer_details.pojo.getCustomerDetails.GetCustomerDetailsResponse;
import com.finwin.payyanur.ckyc.upload.pojo.VerifyCustIdResponse;

public class CustomerDetailsAction {

    public static final int DEFAULT=-1;
    public static final int API_ERROR=1;
    public static final int VERIFY_CUST_ID_SUCCESS=2;
    public static final int INVALID_CUST_ID=3;
    public static final int GET_BRANCH_DETAILS_SUCCESS=4;
    public static final int CREATE_USER_SUCCESS=5;
    public static final int GET_CUSTOMER_DETAILS=6;
    public static final int SELECT_NEW_CUSTOMER=7;
    public static final int SELECT_OLD_CUSTOMER=8;

    String error;
    int action;
    VerifyCustIdResponse verifyCustIdResponse;
    GetBranchDetailsResponse getBranchDetailsResponse;
    GetCustomerDetailsResponse getCustomerDetailsResponse;
    CreateUserResponse createUserResponse;

    public CustomerDetailsAction(int action, CreateUserResponse createUserResponse) {
        this.action = action;
        this.createUserResponse = createUserResponse;
    }

    public CustomerDetailsAction(int action, GetCustomerDetailsResponse getCustomerDetailsResponse) {
        this.action = action;
        this.getCustomerDetailsResponse = getCustomerDetailsResponse;
    }

    public CustomerDetailsAction(int action, GetBranchDetailsResponse getBranchDetailsResponse) {
        this.action = action;
        this.getBranchDetailsResponse = getBranchDetailsResponse;
    }

    public CustomerDetailsAction(int action, VerifyCustIdResponse verifyCustIdResponse) {
        this.action = action;
        this.verifyCustIdResponse = verifyCustIdResponse;
    }

    public CustomerDetailsAction(int action) {
        this.action = action;
    }

    public CustomerDetailsAction( int action,String error) {
        this.error = error;
        this.action = action;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public VerifyCustIdResponse getVerifyCustIdResponse() {
        return verifyCustIdResponse;
    }

    public void setVerifyCustIdResponse(VerifyCustIdResponse verifyCustIdResponse) {
        this.verifyCustIdResponse = verifyCustIdResponse;
    }

    public GetBranchDetailsResponse getGetBranchDetailsResponse() {
        return getBranchDetailsResponse;
    }

    public void setGetBranchDetailsResponse(GetBranchDetailsResponse getBranchDetailsResponse) {
        this.getBranchDetailsResponse = getBranchDetailsResponse;
    }

    public GetCustomerDetailsResponse getGetCustomerDetailsResponse() {
        return getCustomerDetailsResponse;
    }

    public void setGetCustomerDetailsResponse(GetCustomerDetailsResponse getCustomerDetailsResponse) {
        this.getCustomerDetailsResponse = getCustomerDetailsResponse;
    }

    public CreateUserResponse getCreateUserResponse() {
        return createUserResponse;
    }

    public void setCreateUserResponse(CreateUserResponse createUserResponse) {
        this.createUserResponse = createUserResponse;
    }
}
