package com.finwin.payyanur.ckyc.home.customer_address_proof.action;

import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details.GetAddressProofResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_districts.GetDistrictResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_pincode.GetPincodeResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_states.GetStatesResponse;
import com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.update_address_proof.update_user.UpdateAddressProofResponse;

public class CustomerAddressProofAction {
    public static final int Default = -1;
    public static final int API_ERROR = 1;
    public static final int GET_STATES_SUCCESS = 2;
    public static final int GET_DISTRICTS_SUCCESS = 3;
    public static final int GET_PINCODE_SUCCESS = 4;
    public static final int GET_ADDRESS_DETAILS_SUCCESS = 5;
    public static final int UPDATE_ADDRESS_DETAILS_SUCCESS = 6;
    public static final int CLICK_ADDRESS_PROOF_1 = 7;
    public static final int CLICK_ADDRESS_PROOF_2 = 8;

    int action;
    String error;
    GetStatesResponse getStatesResponse;
    GetDistrictResponse getDistrictResponse;
    GetPincodeResponse getPincodeResponse;
    GetAddressProofResponse getAddressProofResponse;
    UpdateAddressProofResponse updateAddressProofResponse;

    public CustomerAddressProofAction(int action, UpdateAddressProofResponse updateAddressProofResponse) {
        this.action = action;
        this.updateAddressProofResponse = updateAddressProofResponse;
    }

    public CustomerAddressProofAction(int action, GetAddressProofResponse getAddressProofResponse) {
        this.action = action;
        this.getAddressProofResponse = getAddressProofResponse;
    }

    public CustomerAddressProofAction(int action, GetPincodeResponse getPincodeResponse) {
        this.action = action;
        this.getPincodeResponse = getPincodeResponse;
    }

    public CustomerAddressProofAction(int action, GetDistrictResponse getDistrictResponse) {
        this.action = action;
        this.getDistrictResponse = getDistrictResponse;
    }

    public CustomerAddressProofAction(int action, GetStatesResponse getStatesResponse) {
        this.action = action;
        this.getStatesResponse = getStatesResponse;
    }

    public CustomerAddressProofAction(int action) {
        this.action = action;
    }

    public CustomerAddressProofAction(int action, String error) {
        this.action = action;
        this.error = error;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public GetStatesResponse getGetStatesResponse() {
        return getStatesResponse;
    }

    public void setGetStatesResponse(GetStatesResponse getStatesResponse) {
        this.getStatesResponse = getStatesResponse;
    }

    public GetDistrictResponse getGetDistrictResponse() {
        return getDistrictResponse;
    }

    public void setGetDistrictResponse(GetDistrictResponse getDistrictResponse) {
        this.getDistrictResponse = getDistrictResponse;
    }

    public GetPincodeResponse getGetPincodeResponse() {
        return getPincodeResponse;
    }

    public void setGetPincodeResponse(GetPincodeResponse getPincodeResponse) {
        this.getPincodeResponse = getPincodeResponse;
    }

    public GetAddressProofResponse getGetAddressProofResponse() {
        return getAddressProofResponse;
    }

    public void setGetAddressProofResponse(GetAddressProofResponse getAddressProofResponse) {
        this.getAddressProofResponse = getAddressProofResponse;
    }

    public UpdateAddressProofResponse getUpdateAddressProofResponse() {
        return updateAddressProofResponse;
    }

    public void setUpdateAddressProofResponse(UpdateAddressProofResponse updateAddressProofResponse) {
        this.updateAddressProofResponse = updateAddressProofResponse;
    }
}
