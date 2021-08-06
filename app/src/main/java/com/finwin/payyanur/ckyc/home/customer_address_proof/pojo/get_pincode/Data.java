package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_pincode;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

@SerializedName("Table")
@Expose
private List<Pincode> pincode = null;

public List<Pincode> getPincode() {
return pincode;
}

public void setPincode(List<Pincode> pincode) {
this.pincode = pincode;
}

}