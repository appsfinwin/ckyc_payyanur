package com.finwin.payyanur.ckyc.home.customer_id_proof.update_user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateIdproofResponse {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
