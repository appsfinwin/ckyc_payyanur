package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_states;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStatesResponse {

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