package com.finwin.payyanur.ckyc.home.customer_details.pojo.getBranch;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branch {

@SerializedName("name")
@Expose
private String name;
@SerializedName("id")
@Expose
private String id;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

    public Branch(String name, String id) {
        this.name = name;
        this.id = id;
    }
}