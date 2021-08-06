package com.finwin.payyanur.ckyc.home.customer_details.pojo.getCustomerDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CustomerData {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("Cust_ID")
    @Expose
    private String custID;
    @SerializedName("CKYC_NO")
    @Expose
    private String ckycNo;
    @SerializedName("Record_Type")
    @Expose
    private String recordType;
    @SerializedName("Application_Type")
    @Expose
    private String applicationType;
    @SerializedName("Branch_Code")
    @Expose
    private String branchCode;
    @SerializedName("Account_Holder_Type_Flag")
    @Expose
    private String accountHolderTypeFlag;
    @SerializedName("Account_Holder_Type")
    @Expose
    private String accountHolderType;
    @SerializedName("Account_Type")
    @Expose
    private String accountType;
    @SerializedName("Applicant_Name_Prefix")
    @Expose
    private String applicantNamePrefix;
    @SerializedName("Applicant_First_Name")
    @Expose
    private String applicantFirstName;
    @SerializedName("Applicant_Middle_Name")
    @Expose
    private String applicantMiddleName;
    @SerializedName("_Applicant_Last_Name")
    @Expose
    private String applicantLastName;
    @SerializedName("Applicant_Image")
    @Expose
    private String applicantImage;
    @SerializedName("Flag_indicating_Father_or_Spouse_Name")
    @Expose
    private String flagIndicatingFatherOrSpouseName;
    @SerializedName("Applicant_Father_Spouse_Name_Prefix")
    @Expose
    private String applicantFatherSpouseNamePrefix;
    @SerializedName("Father_Spouse_First_Name")
    @Expose
    private String fatherSpouseFirstName;
    @SerializedName("Father_Spouse_Middle_Name")
    @Expose
    private String fatherSpouseMiddleName;
    @SerializedName("Father_Spouse_Last_Name")
    @Expose
    private String fatherSpouseLastName;
    @SerializedName("Father_Spouse_Full_Name")
    @Expose
    private String fatherSpouseFullName;
    @SerializedName("Applicant_Mother_Name_Prefix")
    @Expose
    private String applicantMotherNamePrefix;
    @SerializedName("Mother_s_First_Name")
    @Expose
    private String motherSFirstName;
    @SerializedName("Mother_s_Middle_Name")
    @Expose
    private String motherSMiddleName;
    @SerializedName("Mother_s_Last_Name")
    @Expose
    private String motherSLastName;
    @SerializedName("Mother_s_Full_Name")
    @Expose
    private String motherSFullName;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Marital_status")
    @Expose
    private String maritalStatus;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("Occupation")
    @Expose
    private String occupation;
    @SerializedName("Occupation_Type")
    @Expose
    private String occupationType;
    @SerializedName("Date_of_Birth_Date_of_Incorporation")
    @Expose
    private String dateOfBirthDateOfIncorporation;
    @SerializedName("PAN")
    @Expose
    private String pan;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
//@SerializedName("Flag_indicating_Father_or_Spouse_Name")
//@Expose
//private String fatherOrSpouse;
//
//    public String getFatherOrSpouse() {
//        return fatherOrSpouse;
//    }
//
//    public void setFatherOrSpouse(String fatherOrSpouse) {
//        this.fatherOrSpouse = fatherOrSpouse;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCkycNo() {
        return ckycNo;
    }

    public void setCkycNo(String ckycNo) {
        this.ckycNo = ckycNo;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountHolderTypeFlag() {
        return accountHolderTypeFlag;
    }

    public void setAccountHolderTypeFlag(String accountHolderTypeFlag) {
        this.accountHolderTypeFlag = accountHolderTypeFlag;
    }

    public String getAccountHolderType() {
        return accountHolderType;
    }

    public void setAccountHolderType(String accountHolderType) {
        this.accountHolderType = accountHolderType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getApplicantNamePrefix() {
        return applicantNamePrefix;
    }

    public void setApplicantNamePrefix(String applicantNamePrefix) {
        this.applicantNamePrefix = applicantNamePrefix;
    }

    public String getApplicantFirstName() {
        return applicantFirstName;
    }

    public void setApplicantFirstName(String applicantFirstName) {
        this.applicantFirstName = applicantFirstName;
    }

    public String getApplicantMiddleName() {
        return applicantMiddleName;
    }

    public void setApplicantMiddleName(String applicantMiddleName) {
        this.applicantMiddleName = applicantMiddleName;
    }

    public String getApplicantLastName() {
        return applicantLastName;
    }

    public void setApplicantLastName(String applicantLastName) {
        this.applicantLastName = applicantLastName;
    }

    public String getApplicantImage() {
        return applicantImage;
    }

    public void setApplicantImage(String applicantImage) {
        this.applicantImage = applicantImage;
    }

    public String getFlagIndicatingFatherOrSpouseName() {
        return flagIndicatingFatherOrSpouseName;
    }

    public void setFlagIndicatingFatherOrSpouseName(String flagIndicatingFatherOrSpouseName) {
        this.flagIndicatingFatherOrSpouseName = flagIndicatingFatherOrSpouseName;
    }

    public String getApplicantFatherSpouseNamePrefix() {
        return applicantFatherSpouseNamePrefix;
    }

    public void setApplicantFatherSpouseNamePrefix(String applicantFatherSpouseNamePrefix) {
        this.applicantFatherSpouseNamePrefix = applicantFatherSpouseNamePrefix;
    }

    public String getFatherSpouseFirstName() {
        return fatherSpouseFirstName;
    }

    public void setFatherSpouseFirstName(String fatherSpouseFirstName) {
        this.fatherSpouseFirstName = fatherSpouseFirstName;
    }

    public String getFatherSpouseMiddleName() {
        return fatherSpouseMiddleName;
    }

    public void setFatherSpouseMiddleName(String fatherSpouseMiddleName) {
        this.fatherSpouseMiddleName = fatherSpouseMiddleName;
    }

    public String getFatherSpouseLastName() {
        return fatherSpouseLastName;
    }

    public void setFatherSpouseLastName(String fatherSpouseLastName) {
        this.fatherSpouseLastName = fatherSpouseLastName;
    }

    public String getFatherSpouseFullName() {
        return fatherSpouseFullName;
    }

    public void setFatherSpouseFullName(String fatherSpouseFullName) {
        this.fatherSpouseFullName = fatherSpouseFullName;
    }

    public String getApplicantMotherNamePrefix() {
        return applicantMotherNamePrefix;
    }

    public void setApplicantMotherNamePrefix(String applicantMotherNamePrefix) {
        this.applicantMotherNamePrefix = applicantMotherNamePrefix;
    }

    public String getMotherSFirstName() {
        return motherSFirstName;
    }

    public void setMotherSFirstName(String motherSFirstName) {
        this.motherSFirstName = motherSFirstName;
    }

    public String getMotherSMiddleName() {
        return motherSMiddleName;
    }

    public void setMotherSMiddleName(String motherSMiddleName) {
        this.motherSMiddleName = motherSMiddleName;
    }

    public String getMotherSLastName() {
        return motherSLastName;
    }

    public void setMotherSLastName(String motherSLastName) {
        this.motherSLastName = motherSLastName;
    }

    public String getMotherSFullName() {
        return motherSFullName;
    }

    public void setMotherSFullName(String motherSFullName) {
        this.motherSFullName = motherSFullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getDateOfBirthDateOfIncorporation() {
        return dateOfBirthDateOfIncorporation;
    }

    public void setDateOfBirthDateOfIncorporation(String dateOfBirthDateOfIncorporation) {
        this.dateOfBirthDateOfIncorporation = dateOfBirthDateOfIncorporation;
    }

}