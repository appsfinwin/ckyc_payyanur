package com.finwin.payyanur.ckyc.home.customer_id_proof.pojo.get_id_proof_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Table {

@SerializedName("Id_Proof_Image_Side1")
@Expose
private String Id_Proof_Image_Side1;

@SerializedName("Id_Proof_Image_Side2")
@Expose
private String Id_Proof_Image_Side2;

@SerializedName("Identification_Type")
@Expose
private String Identification_Type;

@SerializedName("Identification_Number")
@Expose
private String Identification_Number;

@SerializedName("Id_Expiry_Date")
@Expose
private String Id_Expiry_Date;

    public String getId_Proof_Image_Side1() {
        return Id_Proof_Image_Side1;
    }

    public void setId_Proof_Image_Side1(String id_Proof_Image_Side1) {
        Id_Proof_Image_Side1 = id_Proof_Image_Side1;
    }

    public String getId_Proof_Image_Side2() {
        return Id_Proof_Image_Side2;
    }

    public void setId_Proof_Image_Side2(String id_Proof_Image_Side2) {
        Id_Proof_Image_Side2 = id_Proof_Image_Side2;
    }

    public String getIdentification_Type() {
        return Identification_Type;
    }

    public void setIdentification_Type(String identification_Type) {
        Identification_Type = identification_Type;
    }

    public String getIdentification_Number() {
        return Identification_Number;
    }

    public void setIdentification_Number(String identification_Number) {
        Identification_Number = identification_Number;
    }

    public String getId_Expiry_Date() {
        return Id_Expiry_Date;
    }

    public void setId_Expiry_Date(String id_Expiry_Date) {
        Id_Expiry_Date = id_Expiry_Date;
    }
}