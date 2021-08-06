package com.finwin.payyanur.ckyc.home.customer_id_proof.action;

import com.finwin.payyanur.ckyc.home.customer_id_proof.pojo.get_id_proof_details.GetIdProofDetailsResponse;
import com.finwin.payyanur.ckyc.home.customer_id_proof.update_user.UpdateIdproofResponse;

public class CustomerIdProofAction {
    public static final int DEFAULT = -1;
    public static final int CLICK_ID_PROOF_SIDE1 = 1;
    public static final int CLICK_ID_PROOF_SIDE2 = 2;
    public static final int API_ERROR = 3;
    public static final int UPDATE_USER_SUCCESS = 4;
    public static final int INVALID_CUST_ID = 5;
    public static final int GET_ID_PROOF_DETAILS = 6;
    public static final int UPDATE_ID_PROOF_SUCCESS = 7;
    int action;
    String error;
    GetIdProofDetailsResponse getIdProofDetailsResponse;
    UpdateIdproofResponse updateIdproofResponse;

    public CustomerIdProofAction(int action, UpdateIdproofResponse updateIdproofResponse) {
        this.action = action;
        this.updateIdproofResponse = updateIdproofResponse;
    }

    public CustomerIdProofAction(int action, GetIdProofDetailsResponse getIdProofDetailsResponse) {
        this.action = action;
        this.getIdProofDetailsResponse = getIdProofDetailsResponse;
    }

    public CustomerIdProofAction(int action) {
        this.action = action;
    }

    public CustomerIdProofAction(int action, String error) {
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

    public GetIdProofDetailsResponse getGetIdProofDetailsResponse() {
        return getIdProofDetailsResponse;
    }

    public void setGetIdProofDetailsResponse(GetIdProofDetailsResponse getIdProofDetailsResponse) {
        this.getIdProofDetailsResponse = getIdProofDetailsResponse;
    }

    public UpdateIdproofResponse getUpdateIdproofResponse() {
        return updateIdproofResponse;
    }

    public void setUpdateIdproofResponse(UpdateIdproofResponse updateIdproofResponse) {
        this.updateIdproofResponse = updateIdproofResponse;
    }
}
