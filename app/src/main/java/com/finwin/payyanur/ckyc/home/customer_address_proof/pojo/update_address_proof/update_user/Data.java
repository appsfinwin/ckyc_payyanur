package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.update_address_proof.update_user;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

@SerializedName("Table1")
@Expose
private List<Table1> table1 = null;

    public List<Table1> getTable1() {
        return table1;
    }

    public void setTable1(List<Table1> table1) {
        this.table1 = table1;
    }
}