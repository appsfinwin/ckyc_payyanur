package com.finwin.payyanur.ckyc.home.customer_details.pojo.getCustomerDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

@SerializedName("Table")
@Expose
private List<CustomerData> customerData = null;

public List<CustomerData> getCustomerData() {
return customerData;
}

public void setCustomerData(List<CustomerData> customerData) {
this.customerData = customerData;
}

}