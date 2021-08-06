package com.finwin.payyanur.ckyc.home.customer_details.model;

public class ContryCode {
    String contryCode;
    String contry;

    public String getContryCode() {
        return contryCode;
    }

    public void setContryCode(String contryCode) {
        this.contryCode = contryCode;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public ContryCode(String contryCode, String contry) {
        this.contryCode = contryCode;
        this.contry = contry;
    }
}
