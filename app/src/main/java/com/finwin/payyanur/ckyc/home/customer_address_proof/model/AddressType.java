package com.finwin.payyanur.ckyc.home.customer_address_proof.model;

public class AddressType {
    String addressType,addressTypeCode;

    public AddressType(String addressType, String addressTypeCode) {
        this.addressType = addressType;
        this.addressTypeCode = addressTypeCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressTypeCode() {
        return addressTypeCode;
    }

    public void setAddressTypeCode(String addressTypeCode) {
        this.addressTypeCode = addressTypeCode;
    }
}
