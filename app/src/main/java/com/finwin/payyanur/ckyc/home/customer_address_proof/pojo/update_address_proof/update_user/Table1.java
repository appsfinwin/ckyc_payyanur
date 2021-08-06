package com.finwin.payyanur.ckyc.home.customer_address_proof.pojo.update_address_proof.update_user;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Table1 {

@SerializedName("ReturnMessage")
@Expose
private String returnMessage;
@SerializedName("ReturnID")
@Expose
private String returnID;
@SerializedName("ReturnStatus")
@Expose
private String returnStatus;

public String getReturnMessage() {
return returnMessage;
}

public void setReturnMessage(String returnMessage) {
this.returnMessage = returnMessage;
}

public String getReturnID() {
return returnID;
}

public void setReturnID(String returnID) {
this.returnID = returnID;
}

public String getReturnStatus() {
return returnStatus;
}

public void setReturnStatus(String returnStatus) {
this.returnStatus = returnStatus;
}

}