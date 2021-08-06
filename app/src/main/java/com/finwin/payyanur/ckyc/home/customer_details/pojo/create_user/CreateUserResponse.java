package com.finwin.payyanur.ckyc.home.customer_details.pojo.create_user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CreateUserResponse {

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