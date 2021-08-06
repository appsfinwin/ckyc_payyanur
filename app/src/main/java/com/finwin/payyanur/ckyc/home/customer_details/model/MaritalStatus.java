package com.finwin.payyanur.ckyc.home.customer_details.model;

public class MaritalStatus {
    String maritalStatus;
    String maritalStatusCode;

    public MaritalStatus(String maritalStatus, String maritalStatusCode) {
        this.maritalStatus = maritalStatus;
        this.maritalStatusCode = maritalStatusCode;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setMaritalStatusCode(String maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }
}
