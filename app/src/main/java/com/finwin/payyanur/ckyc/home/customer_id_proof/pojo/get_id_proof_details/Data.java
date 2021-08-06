package com.finwin.payyanur.ckyc.home.customer_id_proof.pojo.get_id_proof_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

@SerializedName("Table")
@Expose
private List<Table> table = null;

public List<Table> getTable() {
return table;
}

public void setTable(List<Table> table) {
this.table = table;
}

}