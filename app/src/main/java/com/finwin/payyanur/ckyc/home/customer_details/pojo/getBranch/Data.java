package com.finwin.payyanur.ckyc.home.customer_details.pojo.getBranch;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("Table")
@Expose
private List<Branch> branch = null;

public List<Branch> getBranch() {
return branch;
}

public void setBranch(List<Branch> branch) {
this.branch = branch;
}

}