package com.finwin.payyanur.ckyc.home.customer_details.model;

public class CitizenShip {
    String contry,contryCode;

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public String getContryCode() {
        return contryCode;
    }

    public void setContryCode(String contryCode) {
        this.contryCode = contryCode;
    }

    public CitizenShip(String contry, String contryCode) {
        this.contry = contry;
        this.contryCode = contryCode;
    }
}
