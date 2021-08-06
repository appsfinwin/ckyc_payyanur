package com.finwin.payyanur.ckyc.home.customer_details.pojo.create_user;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

@SerializedName("Table1")
@Expose
private List<CreateUserData> createUserData = null;

public List<CreateUserData> getCreateUserData() {
return createUserData;
}

public void setCreateUserData(List<CreateUserData> createUserData) {
this.createUserData = createUserData;
}

}