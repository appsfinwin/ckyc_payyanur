package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_pincode;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetPincodeResponse {

@SerializedName("data")
@Expose
private Data data;

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

}