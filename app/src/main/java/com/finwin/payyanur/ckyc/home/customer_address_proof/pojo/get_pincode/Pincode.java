package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_pincode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pincode {

@SerializedName("id")
@Expose
private String id;
@SerializedName("text")
@Expose
private String text;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

    public Pincode(String id, String text) {
        this.id = id;
        this.text = text;
    }
}