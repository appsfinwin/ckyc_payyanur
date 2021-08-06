package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

@SerializedName("Table")
@Expose
private List<AddressProof> addressProof = null;

public List<AddressProof> getAddressProof() {
return addressProof;
}

public void setAddressProof(List<AddressProof> addressProof) {
this.addressProof = addressProof;
}

}