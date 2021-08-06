package com.finwin.payyanur.ckyc.home.customer_details.pojo.getBranch;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBranchDetailsResponse {

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