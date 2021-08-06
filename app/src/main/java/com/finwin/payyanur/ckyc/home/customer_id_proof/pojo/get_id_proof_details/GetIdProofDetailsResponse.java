package com.finwin.payyanur.ckyc.home.customer_id_proof.pojo.get_id_proof_details;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetIdProofDetailsResponse {

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