package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_districts;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

@SerializedName("Table")
@Expose
private List<District> district = null;

public List<District> getDistrict() {
return district;
}

public void setDistrict(List<District> district) {
this.district = district;
}

}