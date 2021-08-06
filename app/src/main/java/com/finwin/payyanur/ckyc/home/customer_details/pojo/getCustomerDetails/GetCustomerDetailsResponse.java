package com.finwin.payyanur.ckyc.home.customer_details.pojo.getCustomerDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCustomerDetailsResponse {

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