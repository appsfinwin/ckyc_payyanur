package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_states;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

@SerializedName("Table")
@Expose
private List<States> states = null;

public List<States> getStates() {
return states;
}

public void setStates(List<States> states) {
this.states = states;
}

}