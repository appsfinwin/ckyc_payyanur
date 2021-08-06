package com.finwin.payyanur.ckyc.upload;

import com.finwin.payyanur.ckyc.upload.pojo.VerifyCustIdResponse;
import com.finwin.payyanur.ckyc.upload.pojo.update.UpdateImageResponse;

public class UploadAction {
    public static final int DEFAULT=-1;
    public static final int CLICK_PROFILE_PIC=1;
    public static final int CLICK_ID_PROOF=2;
    public static final int CLICK_ADDRESS_PROOF=3;
    public static final int CLICK_SUBMIT=4;
    public static final int CLICK_VERIFY_CUST_ID=5;
    public static final int VERIFY_CUST_ID_SUCCESS=6;
    public static final int API_ERROR=7;
    public static final int INVALID_CUST_ID=8;
    public static final int UPLOAD_PHOTOS_SUCCESS=9;
    public static final int UPLOAD_PHOTOS_ERROR=10;
    public static final int CLICK_BACK=11;
    String error;
    int action;
    VerifyCustIdResponse verifyCustIdResponse;
    UpdateImageResponse updateImageResponse;

    public UploadAction(int action, UpdateImageResponse updateImageResponse) {
        this.action = action;
        this.updateImageResponse = updateImageResponse;
    }

    public UploadAction(int action, VerifyCustIdResponse verifyCustIdResponse) {
        this.action = action;
        this.verifyCustIdResponse = verifyCustIdResponse;
    }

    public UploadAction(int action) {
        this.action = action;
    }

    public UploadAction(int action,String error) {
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

    public UpdateImageResponse getUpdateImageResponse() {
        return updateImageResponse;
    }

    public void setUpdateImageResponse(UpdateImageResponse updateImageResponse) {
        this.updateImageResponse = updateImageResponse;
    }
}
