package com.finwin.payyanur.ckyc.upload.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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