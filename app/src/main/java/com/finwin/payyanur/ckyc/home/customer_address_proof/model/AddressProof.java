package com.finwin.payyanur.ckyc.home.customer_address_proof.model;

public class AddressProof {
    String addressProof,addressProofCode;

    public AddressProof(String addressProof, String addressProofCode) {
        this.addressProof = addressProof;
        this.addressProofCode = addressProofCode;
    }

    public String getAddressProof() {
        return addressProof;
    }

    public void setAddressProof(String addressProof) {
        this.addressProof = addressProof;
    }

    public String getAddressProofCode() {
        return addressProofCode;
    }

    public void setAddressProofCode(String addressProofCode) {
        this.addressProofCode = addressProofCode;
    }
}
