package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.get_address_proof_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddressProof {

    @SerializedName("Current_Permanent_Overseas_Address_Type")
    @Expose
    private String addressType;

    @SerializedName("Current_Permanent_Overseas_Address_Line_1")
    @Expose
    private String addressLine1;

    @SerializedName("Current_Permanent_Overseas_Address_Line_2")
    @Expose
    private String addressLine2;

    @SerializedName("Current_Permanent_Overseas_Address_Line_3")
    @Expose
    private String addressLine3;

    @SerializedName("Current_Permanent_Overseas_Address_City_Town_Village")
    @Expose
    private String townOrVillage;

    @SerializedName("Current_Permanent_Overseas_Address_District")
    @Expose
    private String district;

    @SerializedName("Current_Permanent_Overseas_Address_State_U_T")
    @Expose
    private String state;

    @SerializedName("Current_Permanent_Overseas_Address_Country")
    @Expose
    private String contry;

    @SerializedName("Current_Permanent_Overseas_Address_PIN_Code")
    @Expose
    private String pinCode;

    @SerializedName("Proof_of_Address_submitted_for_Current_Permanent_Overseas_Address")
    @Expose
    private String proofOfAddress;

    @SerializedName("Address_Proof_Image_Side1")
    @Expose
    private String addressProofImageSide1;

    @SerializedName("Address_Proof_Image_Side2")
    @Expose
    private String addressProofImageSide2;

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getTownOrVillage() {
        return townOrVillage;
    }

    public void setTownOrVillage(String townOrVillage) {
        this.townOrVillage = townOrVillage;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getProofOfAddress() {
        return proofOfAddress;
    }

    public void setProofOfAddress(String proofOfAddress) {
        this.proofOfAddress = proofOfAddress;
    }

    public String getAddressProofImageSide1() {
        return addressProofImageSide1;
    }

    public void setAddressProofImageSide1(String addressProofImageSide1) {
        this.addressProofImageSide1 = addressProofImageSide1;
    }

    public String getAddressProofImageSide2() {
        return addressProofImageSide2;
    }

    public void setAddressProofImageSide2(String addressProofImageSide2) {
        this.addressProofImageSide2 = addressProofImageSide2;
    }
}