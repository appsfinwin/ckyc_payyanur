package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_districts;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class District {

@SerializedName("text")
@Expose
private String text;
@SerializedName("id")
@Expose
private String id;

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

    public District(String text, String id) {
        this.text = text;
        this.id = id;
    }
}