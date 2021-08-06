package com.finwin.payyanur.ckyc.login.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table1 {

@SerializedName("ReturnID")
@Expose
private String returnID;
@SerializedName("BranchID")
@Expose
private String branchID;
@SerializedName("UserRole")
@Expose
private String userRole;
@SerializedName("ActiveSessionValue")
@Expose
private String activeSessionValue;
@SerializedName("IsDataEntryUser")
@Expose
private String isDataEntryUser;
@SerializedName("User_Name")
@Expose
private String userName;
@SerializedName("IsEnableBranch")
@Expose
private String isEnableBranch;

public String getReturnID() {
return returnID;
}

public void setReturnID(String returnID) {
this.returnID = returnID;
}

public String getBranchID() {
return branchID;
}

public void setBranchID(String branchID) {
this.branchID = branchID;
}

public String getUserRole() {
return userRole;
}

public void setUserRole(String userRole) {
this.userRole = userRole;
}

public String getActiveSessionValue() {
return activeSessionValue;
}

public void setActiveSessionValue(String activeSessionValue) {
this.activeSessionValue = activeSessionValue;
}

public String getIsDataEntryUser() {
return isDataEntryUser;
}

public void setIsDataEntryUser(String isDataEntryUser) {
this.isDataEntryUser = isDataEntryUser;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getIsEnableBranch() {
return isEnableBranch;
}

public void setIsEnableBranch(String isEnableBranch) {
this.isEnableBranch = isEnableBranch;
}

}