package com.finwin.payyanur.ckyc.upload.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {


    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Cust_ID")
    @Expose
    private String custID;
    @SerializedName("CKYC_NO")
    @Expose
    private String ckycNo;
    @SerializedName("Record_Type")
    @Expose
    private String recordType;
    @SerializedName("Line_Number")
    @Expose
    private String lineNumber;
    @SerializedName("Application_Type")
    @Expose
    private String applicationType;
    @SerializedName("Branch_Code")
    @Expose
    private String branchCode;
    @SerializedName("Applicant_Name_Update_Flag")
    @Expose
    private String applicantNameUpdateFlag;
    @SerializedName("Personal_Entity_Details_Update_Flag")
    @Expose
    private String personalEntityDetailsUpdateFlag;
    @SerializedName("Address_Details_Update_Flag")
    @Expose
    private String addressDetailsUpdateFlag;
    @SerializedName("Contact_Details_Update_Flag")
    @Expose
    private String contactDetailsUpdateFlag;
    @SerializedName("Remarks_Update_Flag")
    @Expose
    private String remarksUpdateFlag;
    @SerializedName("KYC_Verification_Update_Flag")
    @Expose
    private String kYCVerificationUpdateFlag;
    @SerializedName("Identity_Details_Update_Flag")
    @Expose
    private String identityDetailsUpdateFlag;
    @SerializedName("Related_Person_Details_Flag")
    @Expose
    private String relatedPersonDetailsFlag;
    @SerializedName("Controlling_Person_Details_Flag")
    @Expose
    private String controllingPersonDetailsFlag;
    @SerializedName("Image_Details_Update_Flag")
    @Expose
    private String imageDetailsUpdateFlag;
    @SerializedName("Constitution_Type")
    @Expose
    private String constitutionType;
    @SerializedName("Account_Holder_Type_Flag")
    @Expose
    private String accountHolderTypeFlag;
    @SerializedName("Account_Holder_Type")
    @Expose
    private String accountHolderType;
    @SerializedName("Account_Type")
    @Expose
    private String accountType;
    @SerializedName("CKYC_no_FI_reference_No")
    @Expose
    private Object cKYCNoFIReferenceNo;
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
    @SerializedName("Applicant_Signature_Image")
    @Expose
    private String applicantSignatureImage;
    @SerializedName("Name_of_the_Applicant_Entity")
    @Expose
    private String nameOfTheApplicantEntity;
    @SerializedName("Applicant_Maiden_Name_Prefix")
    @Expose
    private String applicantMaidenNamePrefix;
    @SerializedName("Applicant_Maiden_First_Name")
    @Expose
    private String applicantMaidenFirstName;
    @SerializedName("Applicant_Maiden_Middle_Name")
    @Expose
    private String applicantMaidenMiddleName;
    @SerializedName("Applicant_Maiden_Last_Name")
    @Expose
    private String applicantMaidenLastName;
    @SerializedName("Applicant_Maiden_Full_Name")
    @Expose
    private String applicantMaidenFullName;
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
    @SerializedName("Place_of_Incorporation")
    @Expose
    private String placeOfIncorporation;
    @SerializedName("Date_of_Commencement_of_business")
    @Expose
    private String dateOfCommencementOfBusiness;
    @SerializedName("Country_of_Incorporation")
    @Expose
    private String countryOfIncorporation;
    @SerializedName("Country_of_residence_as_per_Tax_Laws")
    @Expose
    private String countryOfResidenceAsPerTaxLaws;
    @SerializedName("Identification_Type")
    @Expose
    private String identificationType;
    @SerializedName("Identification_Number")
    @Expose
    private String identificationNumber;
    @SerializedName("Id_Expiry_Date")
    @Expose
    private Object idExpiryDate;
    @SerializedName("Tax_Identification_Number_TIN_")
    @Expose
    private Object taxIdentificationNumberTIN;
    @SerializedName("TIN_Issuing_Country")
    @Expose
    private Object tINIssuingCountry;
    @SerializedName("PAN")
    @Expose
    private String pan;
    @SerializedName("Aadhaar")
    @Expose
    private String aadhaar;
    @SerializedName("Id_Proof_Image_Side1")
    @Expose
    private String idProofImageSide1;
    @SerializedName("Id_Proof_Image_Side2")
    @Expose
    private String idProofImageSide2;
    @SerializedName("Residential_Status")
    @Expose
    private String residentialStatus;
    @SerializedName("Flag_indicating_if_applicant_resident_for_tax_purposes_in_Jurisdiction_outside_India_")
    @Expose
    private String flagIndicatingIfApplicantResidentForTaxPurposesInJurisdictionOutsideIndia;
    @SerializedName("Jurisdiction_of_residence_")
    @Expose
    private String jurisdictionOfResidence;
    @SerializedName("Tax_Identification_Number_or_equivalent_If_issued_by_jurisdiction_")
    @Expose
    private String taxIdentificationNumberOrEquivalentIfIssuedByJurisdiction;
    @SerializedName("Country_of_Birth")
    @Expose
    private String countryOfBirth;
    @SerializedName("City_Place_of_Birth")
    @Expose
    private String cityPlaceOfBirth;
    @SerializedName("Current_Permanent_Overseas_Address_Type")
    @Expose
    private String currentPermanentOverseasAddressType;
    @SerializedName("Current_Permanent_Overseas_Address_Line_1")
    @Expose
    private String currentPermanentOverseasAddressLine1;
    @SerializedName("Current_Permanent_Overseas_Address_Line_2")
    @Expose
    private String currentPermanentOverseasAddressLine2;
    @SerializedName("Current_Permanent_Overseas_Address_Line_3")
    @Expose
    private String currentPermanentOverseasAddressLine3;
    @SerializedName("Current_Permanent_Overseas_Address_City_Town_Village")
    @Expose
    private String currentPermanentOverseasAddressCityTownVillage;
    @SerializedName("Current_Permanent_Overseas_Address_District")
    @Expose
    private String currentPermanentOverseasAddressDistrict;
    @SerializedName("Current_Permanent_Overseas_Address_State_U_T")
    @Expose
    private String currentPermanentOverseasAddressStateUT;
    @SerializedName("Current_Permanent_Overseas_Address_Country")
    @Expose
    private String currentPermanentOverseasAddressCountry;
    @SerializedName("Current_Permanent_Overseas_Address_PIN_Code")
    @Expose
    private String currentPermanentOverseasAddressPINCode;
    @SerializedName("Proof_of_Address_submitted_for_Current_Permanent_Overseas_Address")
    @Expose
    private String proofOfAddressSubmittedForCurrentPermanentOverseasAddress;
    @SerializedName("Address_Proof_Image_Side1")
    @Expose
    private String addressProofImageSide1;
    @SerializedName("Address_Proof_Image_Side2")
    @Expose
    private String addressProofImageSide2;
    @SerializedName("Proof_of_Address_submitted_for_Current_Permanent_Overseas_Address_Others_")
    @Expose
    private String proofOfAddressSubmittedForCurrentPermanentOverseasAddressOthers;
    @SerializedName("Flag_indicating_if_Current_Permanent_Overseas_Address_is_same_as_correspondence_local_Address")
    @Expose
    private String flagIndicatingIfCurrentPermanentOverseasAddressIsSameAsCorrespondenceLocalAddress;
    @SerializedName("Correspondence_Local_Address_Type")
    @Expose
    private String correspondenceLocalAddressType;
    @SerializedName("Correspondence_Local_Address_Line_1")
    @Expose
    private String correspondenceLocalAddressLine1;
    @SerializedName("Correspondence_Local_Address_Line_2")
    @Expose
    private String correspondenceLocalAddressLine2;
    @SerializedName("Correspondence_Local_Address_Line_3")
    @Expose
    private String correspondenceLocalAddressLine3;
    @SerializedName("Correspondence_Local_Address_City_Town_Village")
    @Expose
    private String correspondenceLocalAddressCityTownVillage;
    @SerializedName("Correspondence_Local_Address_District")
    @Expose
    private String correspondenceLocalAddressDistrict;
    @SerializedName("Correspondence_Local_Address_State")
    @Expose
    private String correspondenceLocalAddressState;
    @SerializedName("Correspondence_Local_Address_Country")
    @Expose
    private String correspondenceLocalAddressCountry;
    @SerializedName("Correspondence_Local_Address_PIN_Code")
    @Expose
    private String correspondenceLocalAddressPINCode;
    @SerializedName("Proof_of_Address_submitted_for_Correspondence_Local_Address_")
    @Expose
    private String proofOfAddressSubmittedForCorrespondenceLocalAddress;
    @SerializedName("Flag_indicating_if_address")
    @Expose
    private String flagIndicatingIfAddress;
    @SerializedName("Address_in_Jurisdiction_Type")
    @Expose
    private String addressInJurisdictionType;
    @SerializedName("Address_in_Jurisdiction_Line_1")
    @Expose
    private String addressInJurisdictionLine1;
    @SerializedName("Address_in_Jurisdiction_Line_2")
    @Expose
    private String addressInJurisdictionLine2;
    @SerializedName("Address_in_Jurisdiction_Line_3")
    @Expose
    private String addressInJurisdictionLine3;
    @SerializedName("Address_in_Jurisdiction_City_Town_Village")
    @Expose
    private String addressInJurisdictionCityTownVillage;
    @SerializedName("Address_in_Jurisdiction_State")
    @Expose
    private String addressInJurisdictionState;
    @SerializedName("Address_in_Jurisdiction_Country")
    @Expose
    private String addressInJurisdictionCountry;
    @SerializedName("Address_in_Jurisdiction_ZIP_Post_Code")
    @Expose
    private String addressInJurisdictionZIPPostCode;
    @SerializedName("Proof_of_Address_submitted_for_Address_in_Jurisdiction")
    @Expose
    private String proofOfAddressSubmittedForAddressInJurisdiction;
    @SerializedName("Residence_Telephone_No_STD_Code_")
    @Expose
    private String residenceTelephoneNoSTDCode;
    @SerializedName("Residence_Telephone_No")
    @Expose
    private String residenceTelephoneNo;
    @SerializedName("Office_Telephone_No_STD_Code_")
    @Expose
    private String officeTelephoneNoSTDCode;
    @SerializedName("Office_Telephone_No")
    @Expose
    private String officeTelephoneNo;
    @SerializedName("Mobile_No_ISD_Code_")
    @Expose
    private String mobileNoISDCode;
    @SerializedName("Mobile_No")
    @Expose
    private String mobileNo;
    @SerializedName("Fax_No_STD_Code_")
    @Expose
    private String faxNoSTDCode;
    @SerializedName("Fax_No")
    @Expose
    private String faxNo;
    @SerializedName("Email_ID")
    @Expose
    private String emailID;
    @SerializedName("Remarks_if_any")
    @Expose
    private String remarksIfAny;
    @SerializedName("Date_of_Declaration")
    @Expose
    private String dateOfDeclaration;
    @SerializedName("Place_of_Declaration")
    @Expose
    private String placeOfDeclaration;
    @SerializedName("KYC_Verification_Date")
    @Expose
    private String kYCVerificationDate;
    @SerializedName("Type_of_Document_Submitted_")
    @Expose
    private String typeOfDocumentSubmitted;
    @SerializedName("KYC_Verification_Name")
    @Expose
    private String kYCVerificationName;
    @SerializedName("KYC_Verification_Designation")
    @Expose
    private String kYCVerificationDesignation;
    @SerializedName("KYC_Verification_Branch")
    @Expose
    private String kYCVerificationBranch;
    @SerializedName("KYC_Verification_EMP_code")
    @Expose
    private String kYCVerificationEMPCode;
    @SerializedName("Organisation_Name")
    @Expose
    private String organisationName;
    @SerializedName("Organisation_Code")
    @Expose
    private String organisationCode;
    @SerializedName("Number_of_Identity_Details")
    @Expose
    private String numberOfIdentityDetails;
    @SerializedName("Number_of_related_people")
    @Expose
    private String numberOfRelatedPeople;
    @SerializedName("Number_of_controlling_persons_resident_outside_India")
    @Expose
    private String numberOfControllingPersonsResidentOutsideIndia;
    @SerializedName("Number_of_Local_Address_Details")
    @Expose
    private String numberOfLocalAddressDetails;
    @SerializedName("Number_of_Images")
    @Expose
    private String numberOfImages;
    @SerializedName("Error_Code")
    @Expose
    private String errorCode;
    @SerializedName("Filler_1")
    @Expose
    private String filler1;
    @SerializedName("Filler_2")
    @Expose
    private String filler2;
    @SerializedName("Filler_3")
    @Expose
    private String filler3;
    @SerializedName("Filler_4")
    @Expose
    private String filler4;
    @SerializedName("Note_Employee")
    @Expose
    private String noteEmployee;
    @SerializedName("Note_Institution")
    @Expose
    private String noteInstitution;
    @SerializedName("Related_Person_Details_Flag1")
    @Expose
    private String relatedPersonDetailsFlag1;
    @SerializedName("AdditionOrDeletion_RelatedPerson")
    @Expose
    private String additionOrDeletionRelatedPerson;
    @SerializedName("KYCNumber_RelatedPerson")
    @Expose
    private String kYCNumberRelatedPerson;
    @SerializedName("RelatedPerson_NamePrefix")
    @Expose
    private String relatedPersonNamePrefix;
    @SerializedName("RelatedPerson_FirstName")
    @Expose
    private String relatedPersonFirstName;
    @SerializedName("RelatedPerson_MiddleName")
    @Expose
    private String relatedPersonMiddleName;
    @SerializedName("RelatedPerson_LastName")
    @Expose
    private String relatedPersonLastName;
    @SerializedName("RelatedPerson_TypeOfIdentity")
    @Expose
    private String relatedPersonTypeOfIdentity;
    @SerializedName("RelatedPerson_Identification_Number")
    @Expose
    private String relatedPersonIdentificationNumber;
    @SerializedName("ReatedPerson_Id_ExpiryDate")
    @Expose
    private String reatedPersonIdExpiryDate;
    @SerializedName("Cust_Type")
    @Expose
    private String custType;
    @SerializedName("FlagOf_RelatedPerson_Father_Spouse")
    @Expose
    private String flagOfRelatedPersonFatherSpouse;
    @SerializedName("RelatedPerson_FatherOrSpouse_Prefix")
    @Expose
    private String relatedPersonFatherOrSpousePrefix;
    @SerializedName("RelatedPerson_FatherOrSpouse_FirstName")
    @Expose
    private String relatedPersonFatherOrSpouseFirstName;
    @SerializedName("RelatedPerson_PAN")
    @Expose
    private String relatedPersonPAN;
    @SerializedName("RelatedPerson_DOB")
    @Expose
    private String relatedPersonDOB;
    @SerializedName("RelatedPerson_Gender")
    @Expose
    private String relatedPersonGender;
    @SerializedName("RelatedPerson_Nationality")
    @Expose
    private String relatedPersonNationality;
    @SerializedName("RelatedPerson_AddressLine_1")
    @Expose
    private String relatedPersonAddressLine1;
    @SerializedName("RelatedPerson_AddressLine_2")
    @Expose
    private String relatedPersonAddressLine2;
    @SerializedName("RelatedPerson_AddressLine_3")
    @Expose
    private String relatedPersonAddressLine3;
    @SerializedName("RelatedPerson_CityTownVillage")
    @Expose
    private String relatedPersonCityTownVillage;
    @SerializedName("RelatedPerson_District")
    @Expose
    private String relatedPersonDistrict;
    @SerializedName("RelatedPerson_State")
    @Expose
    private String relatedPersonState;
    @SerializedName("RelatedPerson_Country")
    @Expose
    private String relatedPersonCountry;
    @SerializedName("RelatedPerson_PINCode")
    @Expose
    private String relatedPersonPINCode;
    @SerializedName("FlagOf_IdentificationAddressIsSame_RelaredPerson")
    @Expose
    private String flagOfIdentificationAddressIsSameRelaredPerson;
    @SerializedName("RelatedPerson_Image")
    @Expose
    private String relatedPersonImage;
    @SerializedName("RelatedPerson_Signature_Image")
    @Expose
    private String relatedPersonSignatureImage;
    @SerializedName("RelatedPerson_IdProof_Image_Side1")
    @Expose
    private String relatedPersonIdProofImageSide1;
    @SerializedName("RelatedPerson_IdProof_Image_Side2")
    @Expose
    private String relatedPersonIdProofImageSide2;
    @SerializedName("RelatedPerson_AddressProof_Image_Side1")
    @Expose
    private String relatedPersonAddressProofImageSide1;
    @SerializedName("RelatedPerson_AddressProof_Image_Side2")
    @Expose
    private String relatedPersonAddressProofImageSide2;
    @SerializedName("RelatedPerson_Mobile_ISD")
    @Expose
    private String relatedPersonMobileISD;
    @SerializedName("RelatedPerson_Mobile_Number")
    @Expose
    private String relatedPersonMobileNumber;
    @SerializedName("RelatedPerson_Email")
    @Expose
    private String relatedPersonEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
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

    public String getApplicantNameUpdateFlag() {
        return applicantNameUpdateFlag;
    }

    public void setApplicantNameUpdateFlag(String applicantNameUpdateFlag) {
        this.applicantNameUpdateFlag = applicantNameUpdateFlag;
    }

    public String getPersonalEntityDetailsUpdateFlag() {
        return personalEntityDetailsUpdateFlag;
    }

    public void setPersonalEntityDetailsUpdateFlag(String personalEntityDetailsUpdateFlag) {
        this.personalEntityDetailsUpdateFlag = personalEntityDetailsUpdateFlag;
    }

    public String getAddressDetailsUpdateFlag() {
        return addressDetailsUpdateFlag;
    }

    public void setAddressDetailsUpdateFlag(String addressDetailsUpdateFlag) {
        this.addressDetailsUpdateFlag = addressDetailsUpdateFlag;
    }

    public String getContactDetailsUpdateFlag() {
        return contactDetailsUpdateFlag;
    }

    public void setContactDetailsUpdateFlag(String contactDetailsUpdateFlag) {
        this.contactDetailsUpdateFlag = contactDetailsUpdateFlag;
    }

    public String getRemarksUpdateFlag() {
        return remarksUpdateFlag;
    }

    public void setRemarksUpdateFlag(String remarksUpdateFlag) {
        this.remarksUpdateFlag = remarksUpdateFlag;
    }

    public String getKYCVerificationUpdateFlag() {
        return kYCVerificationUpdateFlag;
    }

    public void setKYCVerificationUpdateFlag(String kYCVerificationUpdateFlag) {
        this.kYCVerificationUpdateFlag = kYCVerificationUpdateFlag;
    }

    public String getIdentityDetailsUpdateFlag() {
        return identityDetailsUpdateFlag;
    }

    public void setIdentityDetailsUpdateFlag(String identityDetailsUpdateFlag) {
        this.identityDetailsUpdateFlag = identityDetailsUpdateFlag;
    }

    public String getRelatedPersonDetailsFlag() {
        return relatedPersonDetailsFlag;
    }

    public void setRelatedPersonDetailsFlag(String relatedPersonDetailsFlag) {
        this.relatedPersonDetailsFlag = relatedPersonDetailsFlag;
    }

    public String getControllingPersonDetailsFlag() {
        return controllingPersonDetailsFlag;
    }

    public void setControllingPersonDetailsFlag(String controllingPersonDetailsFlag) {
        this.controllingPersonDetailsFlag = controllingPersonDetailsFlag;
    }

    public String getImageDetailsUpdateFlag() {
        return imageDetailsUpdateFlag;
    }

    public void setImageDetailsUpdateFlag(String imageDetailsUpdateFlag) {
        this.imageDetailsUpdateFlag = imageDetailsUpdateFlag;
    }

    public String getConstitutionType() {
        return constitutionType;
    }

    public void setConstitutionType(String constitutionType) {
        this.constitutionType = constitutionType;
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

    public Object getCKYCNoFIReferenceNo() {
        return cKYCNoFIReferenceNo;
    }

    public void setCKYCNoFIReferenceNo(Object cKYCNoFIReferenceNo) {
        this.cKYCNoFIReferenceNo = cKYCNoFIReferenceNo;
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

    public String getApplicantSignatureImage() {
        return applicantSignatureImage;
    }

    public void setApplicantSignatureImage(String applicantSignatureImage) {
        this.applicantSignatureImage = applicantSignatureImage;
    }

    public String getNameOfTheApplicantEntity() {
        return nameOfTheApplicantEntity;
    }

    public void setNameOfTheApplicantEntity(String nameOfTheApplicantEntity) {
        this.nameOfTheApplicantEntity = nameOfTheApplicantEntity;
    }

    public String getApplicantMaidenNamePrefix() {
        return applicantMaidenNamePrefix;
    }

    public void setApplicantMaidenNamePrefix(String applicantMaidenNamePrefix) {
        this.applicantMaidenNamePrefix = applicantMaidenNamePrefix;
    }

    public String getApplicantMaidenFirstName() {
        return applicantMaidenFirstName;
    }

    public void setApplicantMaidenFirstName(String applicantMaidenFirstName) {
        this.applicantMaidenFirstName = applicantMaidenFirstName;
    }

    public String getApplicantMaidenMiddleName() {
        return applicantMaidenMiddleName;
    }

    public void setApplicantMaidenMiddleName(String applicantMaidenMiddleName) {
        this.applicantMaidenMiddleName = applicantMaidenMiddleName;
    }

    public String getApplicantMaidenLastName() {
        return applicantMaidenLastName;
    }

    public void setApplicantMaidenLastName(String applicantMaidenLastName) {
        this.applicantMaidenLastName = applicantMaidenLastName;
    }

    public String getApplicantMaidenFullName() {
        return applicantMaidenFullName;
    }

    public void setApplicantMaidenFullName(String applicantMaidenFullName) {
        this.applicantMaidenFullName = applicantMaidenFullName;
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

    public String getPlaceOfIncorporation() {
        return placeOfIncorporation;
    }

    public void setPlaceOfIncorporation(String placeOfIncorporation) {
        this.placeOfIncorporation = placeOfIncorporation;
    }

    public String getDateOfCommencementOfBusiness() {
        return dateOfCommencementOfBusiness;
    }

    public void setDateOfCommencementOfBusiness(String dateOfCommencementOfBusiness) {
        this.dateOfCommencementOfBusiness = dateOfCommencementOfBusiness;
    }

    public String getCountryOfIncorporation() {
        return countryOfIncorporation;
    }

    public void setCountryOfIncorporation(String countryOfIncorporation) {
        this.countryOfIncorporation = countryOfIncorporation;
    }

    public String getCountryOfResidenceAsPerTaxLaws() {
        return countryOfResidenceAsPerTaxLaws;
    }

    public void setCountryOfResidenceAsPerTaxLaws(String countryOfResidenceAsPerTaxLaws) {
        this.countryOfResidenceAsPerTaxLaws = countryOfResidenceAsPerTaxLaws;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Object getIdExpiryDate() {
        return idExpiryDate;
    }

    public void setIdExpiryDate(Object idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
    }

    public Object getTaxIdentificationNumberTIN() {
        return taxIdentificationNumberTIN;
    }

    public void setTaxIdentificationNumberTIN(Object taxIdentificationNumberTIN) {
        this.taxIdentificationNumberTIN = taxIdentificationNumberTIN;
    }

    public Object getTINIssuingCountry() {
        return tINIssuingCountry;
    }

    public void setTINIssuingCountry(Object tINIssuingCountry) {
        this.tINIssuingCountry = tINIssuingCountry;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getIdProofImageSide1() {
        return idProofImageSide1;
    }

    public void setIdProofImageSide1(String idProofImageSide1) {
        this.idProofImageSide1 = idProofImageSide1;
    }

    public String getIdProofImageSide2() {
        return idProofImageSide2;
    }

    public void setIdProofImageSide2(String idProofImageSide2) {
        this.idProofImageSide2 = idProofImageSide2;
    }

    public String getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(String residentialStatus) {
        this.residentialStatus = residentialStatus;
    }

    public String getFlagIndicatingIfApplicantResidentForTaxPurposesInJurisdictionOutsideIndia() {
        return flagIndicatingIfApplicantResidentForTaxPurposesInJurisdictionOutsideIndia;
    }

    public void setFlagIndicatingIfApplicantResidentForTaxPurposesInJurisdictionOutsideIndia(String flagIndicatingIfApplicantResidentForTaxPurposesInJurisdictionOutsideIndia) {
        this.flagIndicatingIfApplicantResidentForTaxPurposesInJurisdictionOutsideIndia = flagIndicatingIfApplicantResidentForTaxPurposesInJurisdictionOutsideIndia;
    }

    public String getJurisdictionOfResidence() {
        return jurisdictionOfResidence;
    }

    public void setJurisdictionOfResidence(String jurisdictionOfResidence) {
        this.jurisdictionOfResidence = jurisdictionOfResidence;
    }

    public String getTaxIdentificationNumberOrEquivalentIfIssuedByJurisdiction() {
        return taxIdentificationNumberOrEquivalentIfIssuedByJurisdiction;
    }

    public void setTaxIdentificationNumberOrEquivalentIfIssuedByJurisdiction(String taxIdentificationNumberOrEquivalentIfIssuedByJurisdiction) {
        this.taxIdentificationNumberOrEquivalentIfIssuedByJurisdiction = taxIdentificationNumberOrEquivalentIfIssuedByJurisdiction;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getCityPlaceOfBirth() {
        return cityPlaceOfBirth;
    }

    public void setCityPlaceOfBirth(String cityPlaceOfBirth) {
        this.cityPlaceOfBirth = cityPlaceOfBirth;
    }

    public String getCurrentPermanentOverseasAddressType() {
        return currentPermanentOverseasAddressType;
    }

    public void setCurrentPermanentOverseasAddressType(String currentPermanentOverseasAddressType) {
        this.currentPermanentOverseasAddressType = currentPermanentOverseasAddressType;
    }

    public String getCurrentPermanentOverseasAddressLine1() {
        return currentPermanentOverseasAddressLine1;
    }

    public void setCurrentPermanentOverseasAddressLine1(String currentPermanentOverseasAddressLine1) {
        this.currentPermanentOverseasAddressLine1 = currentPermanentOverseasAddressLine1;
    }

    public String getCurrentPermanentOverseasAddressLine2() {
        return currentPermanentOverseasAddressLine2;
    }

    public void setCurrentPermanentOverseasAddressLine2(String currentPermanentOverseasAddressLine2) {
        this.currentPermanentOverseasAddressLine2 = currentPermanentOverseasAddressLine2;
    }

    public String getCurrentPermanentOverseasAddressLine3() {
        return currentPermanentOverseasAddressLine3;
    }

    public void setCurrentPermanentOverseasAddressLine3(String currentPermanentOverseasAddressLine3) {
        this.currentPermanentOverseasAddressLine3 = currentPermanentOverseasAddressLine3;
    }

    public String getCurrentPermanentOverseasAddressCityTownVillage() {
        return currentPermanentOverseasAddressCityTownVillage;
    }

    public void setCurrentPermanentOverseasAddressCityTownVillage(String currentPermanentOverseasAddressCityTownVillage) {
        this.currentPermanentOverseasAddressCityTownVillage = currentPermanentOverseasAddressCityTownVillage;
    }

    public String getCurrentPermanentOverseasAddressDistrict() {
        return currentPermanentOverseasAddressDistrict;
    }

    public void setCurrentPermanentOverseasAddressDistrict(String currentPermanentOverseasAddressDistrict) {
        this.currentPermanentOverseasAddressDistrict = currentPermanentOverseasAddressDistrict;
    }

    public String getCurrentPermanentOverseasAddressStateUT() {
        return currentPermanentOverseasAddressStateUT;
    }

    public void setCurrentPermanentOverseasAddressStateUT(String currentPermanentOverseasAddressStateUT) {
        this.currentPermanentOverseasAddressStateUT = currentPermanentOverseasAddressStateUT;
    }

    public String getCurrentPermanentOverseasAddressCountry() {
        return currentPermanentOverseasAddressCountry;
    }

    public void setCurrentPermanentOverseasAddressCountry(String currentPermanentOverseasAddressCountry) {
        this.currentPermanentOverseasAddressCountry = currentPermanentOverseasAddressCountry;
    }

    public String getCurrentPermanentOverseasAddressPINCode() {
        return currentPermanentOverseasAddressPINCode;
    }

    public void setCurrentPermanentOverseasAddressPINCode(String currentPermanentOverseasAddressPINCode) {
        this.currentPermanentOverseasAddressPINCode = currentPermanentOverseasAddressPINCode;
    }

    public String getProofOfAddressSubmittedForCurrentPermanentOverseasAddress() {
        return proofOfAddressSubmittedForCurrentPermanentOverseasAddress;
    }

    public void setProofOfAddressSubmittedForCurrentPermanentOverseasAddress(String proofOfAddressSubmittedForCurrentPermanentOverseasAddress) {
        this.proofOfAddressSubmittedForCurrentPermanentOverseasAddress = proofOfAddressSubmittedForCurrentPermanentOverseasAddress;
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

    public String getProofOfAddressSubmittedForCurrentPermanentOverseasAddressOthers() {
        return proofOfAddressSubmittedForCurrentPermanentOverseasAddressOthers;
    }

    public void setProofOfAddressSubmittedForCurrentPermanentOverseasAddressOthers(String proofOfAddressSubmittedForCurrentPermanentOverseasAddressOthers) {
        this.proofOfAddressSubmittedForCurrentPermanentOverseasAddressOthers = proofOfAddressSubmittedForCurrentPermanentOverseasAddressOthers;
    }

    public String getFlagIndicatingIfCurrentPermanentOverseasAddressIsSameAsCorrespondenceLocalAddress() {
        return flagIndicatingIfCurrentPermanentOverseasAddressIsSameAsCorrespondenceLocalAddress;
    }

    public void setFlagIndicatingIfCurrentPermanentOverseasAddressIsSameAsCorrespondenceLocalAddress(String flagIndicatingIfCurrentPermanentOverseasAddressIsSameAsCorrespondenceLocalAddress) {
        this.flagIndicatingIfCurrentPermanentOverseasAddressIsSameAsCorrespondenceLocalAddress = flagIndicatingIfCurrentPermanentOverseasAddressIsSameAsCorrespondenceLocalAddress;
    }

    public String getCorrespondenceLocalAddressType() {
        return correspondenceLocalAddressType;
    }

    public void setCorrespondenceLocalAddressType(String correspondenceLocalAddressType) {
        this.correspondenceLocalAddressType = correspondenceLocalAddressType;
    }

    public String getCorrespondenceLocalAddressLine1() {
        return correspondenceLocalAddressLine1;
    }

    public void setCorrespondenceLocalAddressLine1(String correspondenceLocalAddressLine1) {
        this.correspondenceLocalAddressLine1 = correspondenceLocalAddressLine1;
    }

    public String getCorrespondenceLocalAddressLine2() {
        return correspondenceLocalAddressLine2;
    }

    public void setCorrespondenceLocalAddressLine2(String correspondenceLocalAddressLine2) {
        this.correspondenceLocalAddressLine2 = correspondenceLocalAddressLine2;
    }

    public String getCorrespondenceLocalAddressLine3() {
        return correspondenceLocalAddressLine3;
    }

    public void setCorrespondenceLocalAddressLine3(String correspondenceLocalAddressLine3) {
        this.correspondenceLocalAddressLine3 = correspondenceLocalAddressLine3;
    }

    public String getCorrespondenceLocalAddressCityTownVillage() {
        return correspondenceLocalAddressCityTownVillage;
    }

    public void setCorrespondenceLocalAddressCityTownVillage(String correspondenceLocalAddressCityTownVillage) {
        this.correspondenceLocalAddressCityTownVillage = correspondenceLocalAddressCityTownVillage;
    }

    public String getCorrespondenceLocalAddressDistrict() {
        return correspondenceLocalAddressDistrict;
    }

    public void setCorrespondenceLocalAddressDistrict(String correspondenceLocalAddressDistrict) {
        this.correspondenceLocalAddressDistrict = correspondenceLocalAddressDistrict;
    }

    public String getCorrespondenceLocalAddressState() {
        return correspondenceLocalAddressState;
    }

    public void setCorrespondenceLocalAddressState(String correspondenceLocalAddressState) {
        this.correspondenceLocalAddressState = correspondenceLocalAddressState;
    }

    public String getCorrespondenceLocalAddressCountry() {
        return correspondenceLocalAddressCountry;
    }

    public void setCorrespondenceLocalAddressCountry(String correspondenceLocalAddressCountry) {
        this.correspondenceLocalAddressCountry = correspondenceLocalAddressCountry;
    }

    public String getCorrespondenceLocalAddressPINCode() {
        return correspondenceLocalAddressPINCode;
    }

    public void setCorrespondenceLocalAddressPINCode(String correspondenceLocalAddressPINCode) {
        this.correspondenceLocalAddressPINCode = correspondenceLocalAddressPINCode;
    }

    public String getProofOfAddressSubmittedForCorrespondenceLocalAddress() {
        return proofOfAddressSubmittedForCorrespondenceLocalAddress;
    }

    public void setProofOfAddressSubmittedForCorrespondenceLocalAddress(String proofOfAddressSubmittedForCorrespondenceLocalAddress) {
        this.proofOfAddressSubmittedForCorrespondenceLocalAddress = proofOfAddressSubmittedForCorrespondenceLocalAddress;
    }

    public String getFlagIndicatingIfAddress() {
        return flagIndicatingIfAddress;
    }

    public void setFlagIndicatingIfAddress(String flagIndicatingIfAddress) {
        this.flagIndicatingIfAddress = flagIndicatingIfAddress;
    }

    public String getAddressInJurisdictionType() {
        return addressInJurisdictionType;
    }

    public void setAddressInJurisdictionType(String addressInJurisdictionType) {
        this.addressInJurisdictionType = addressInJurisdictionType;
    }

    public String getAddressInJurisdictionLine1() {
        return addressInJurisdictionLine1;
    }

    public void setAddressInJurisdictionLine1(String addressInJurisdictionLine1) {
        this.addressInJurisdictionLine1 = addressInJurisdictionLine1;
    }

    public String getAddressInJurisdictionLine2() {
        return addressInJurisdictionLine2;
    }

    public void setAddressInJurisdictionLine2(String addressInJurisdictionLine2) {
        this.addressInJurisdictionLine2 = addressInJurisdictionLine2;
    }

    public String getAddressInJurisdictionLine3() {
        return addressInJurisdictionLine3;
    }

    public void setAddressInJurisdictionLine3(String addressInJurisdictionLine3) {
        this.addressInJurisdictionLine3 = addressInJurisdictionLine3;
    }

    public String getAddressInJurisdictionCityTownVillage() {
        return addressInJurisdictionCityTownVillage;
    }

    public void setAddressInJurisdictionCityTownVillage(String addressInJurisdictionCityTownVillage) {
        this.addressInJurisdictionCityTownVillage = addressInJurisdictionCityTownVillage;
    }

    public String getAddressInJurisdictionState() {
        return addressInJurisdictionState;
    }

    public void setAddressInJurisdictionState(String addressInJurisdictionState) {
        this.addressInJurisdictionState = addressInJurisdictionState;
    }

    public String getAddressInJurisdictionCountry() {
        return addressInJurisdictionCountry;
    }

    public void setAddressInJurisdictionCountry(String addressInJurisdictionCountry) {
        this.addressInJurisdictionCountry = addressInJurisdictionCountry;
    }

    public String getAddressInJurisdictionZIPPostCode() {
        return addressInJurisdictionZIPPostCode;
    }

    public void setAddressInJurisdictionZIPPostCode(String addressInJurisdictionZIPPostCode) {
        this.addressInJurisdictionZIPPostCode = addressInJurisdictionZIPPostCode;
    }

    public String getProofOfAddressSubmittedForAddressInJurisdiction() {
        return proofOfAddressSubmittedForAddressInJurisdiction;
    }

    public void setProofOfAddressSubmittedForAddressInJurisdiction(String proofOfAddressSubmittedForAddressInJurisdiction) {
        this.proofOfAddressSubmittedForAddressInJurisdiction = proofOfAddressSubmittedForAddressInJurisdiction;
    }

    public String getResidenceTelephoneNoSTDCode() {
        return residenceTelephoneNoSTDCode;
    }

    public void setResidenceTelephoneNoSTDCode(String residenceTelephoneNoSTDCode) {
        this.residenceTelephoneNoSTDCode = residenceTelephoneNoSTDCode;
    }

    public String getResidenceTelephoneNo() {
        return residenceTelephoneNo;
    }

    public void setResidenceTelephoneNo(String residenceTelephoneNo) {
        this.residenceTelephoneNo = residenceTelephoneNo;
    }

    public String getOfficeTelephoneNoSTDCode() {
        return officeTelephoneNoSTDCode;
    }

    public void setOfficeTelephoneNoSTDCode(String officeTelephoneNoSTDCode) {
        this.officeTelephoneNoSTDCode = officeTelephoneNoSTDCode;
    }

    public String getOfficeTelephoneNo() {
        return officeTelephoneNo;
    }

    public void setOfficeTelephoneNo(String officeTelephoneNo) {
        this.officeTelephoneNo = officeTelephoneNo;
    }

    public String getMobileNoISDCode() {
        return mobileNoISDCode;
    }

    public void setMobileNoISDCode(String mobileNoISDCode) {
        this.mobileNoISDCode = mobileNoISDCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFaxNoSTDCode() {
        return faxNoSTDCode;
    }

    public void setFaxNoSTDCode(String faxNoSTDCode) {
        this.faxNoSTDCode = faxNoSTDCode;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getRemarksIfAny() {
        return remarksIfAny;
    }

    public void setRemarksIfAny(String remarksIfAny) {
        this.remarksIfAny = remarksIfAny;
    }

    public String getDateOfDeclaration() {
        return dateOfDeclaration;
    }

    public void setDateOfDeclaration(String dateOfDeclaration) {
        this.dateOfDeclaration = dateOfDeclaration;
    }

    public String getPlaceOfDeclaration() {
        return placeOfDeclaration;
    }

    public void setPlaceOfDeclaration(String placeOfDeclaration) {
        this.placeOfDeclaration = placeOfDeclaration;
    }

    public String getKYCVerificationDate() {
        return kYCVerificationDate;
    }

    public void setKYCVerificationDate(String kYCVerificationDate) {
        this.kYCVerificationDate = kYCVerificationDate;
    }

    public String getTypeOfDocumentSubmitted() {
        return typeOfDocumentSubmitted;
    }

    public void setTypeOfDocumentSubmitted(String typeOfDocumentSubmitted) {
        this.typeOfDocumentSubmitted = typeOfDocumentSubmitted;
    }

    public String getKYCVerificationName() {
        return kYCVerificationName;
    }

    public void setKYCVerificationName(String kYCVerificationName) {
        this.kYCVerificationName = kYCVerificationName;
    }

    public String getKYCVerificationDesignation() {
        return kYCVerificationDesignation;
    }

    public void setKYCVerificationDesignation(String kYCVerificationDesignation) {
        this.kYCVerificationDesignation = kYCVerificationDesignation;
    }

    public String getKYCVerificationBranch() {
        return kYCVerificationBranch;
    }

    public void setKYCVerificationBranch(String kYCVerificationBranch) {
        this.kYCVerificationBranch = kYCVerificationBranch;
    }

    public String getKYCVerificationEMPCode() {
        return kYCVerificationEMPCode;
    }

    public void setKYCVerificationEMPCode(String kYCVerificationEMPCode) {
        this.kYCVerificationEMPCode = kYCVerificationEMPCode;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getOrganisationCode() {
        return organisationCode;
    }

    public void setOrganisationCode(String organisationCode) {
        this.organisationCode = organisationCode;
    }

    public String getNumberOfIdentityDetails() {
        return numberOfIdentityDetails;
    }

    public void setNumberOfIdentityDetails(String numberOfIdentityDetails) {
        this.numberOfIdentityDetails = numberOfIdentityDetails;
    }

    public String getNumberOfRelatedPeople() {
        return numberOfRelatedPeople;
    }

    public void setNumberOfRelatedPeople(String numberOfRelatedPeople) {
        this.numberOfRelatedPeople = numberOfRelatedPeople;
    }

    public String getNumberOfControllingPersonsResidentOutsideIndia() {
        return numberOfControllingPersonsResidentOutsideIndia;
    }

    public void setNumberOfControllingPersonsResidentOutsideIndia(String numberOfControllingPersonsResidentOutsideIndia) {
        this.numberOfControllingPersonsResidentOutsideIndia = numberOfControllingPersonsResidentOutsideIndia;
    }

    public String getNumberOfLocalAddressDetails() {
        return numberOfLocalAddressDetails;
    }

    public void setNumberOfLocalAddressDetails(String numberOfLocalAddressDetails) {
        this.numberOfLocalAddressDetails = numberOfLocalAddressDetails;
    }

    public String getNumberOfImages() {
        return numberOfImages;
    }

    public void setNumberOfImages(String numberOfImages) {
        this.numberOfImages = numberOfImages;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getFiller3() {
        return filler3;
    }

    public void setFiller3(String filler3) {
        this.filler3 = filler3;
    }

    public String getFiller4() {
        return filler4;
    }

    public void setFiller4(String filler4) {
        this.filler4 = filler4;
    }

    public String getNoteEmployee() {
        return noteEmployee;
    }

    public void setNoteEmployee(String noteEmployee) {
        this.noteEmployee = noteEmployee;
    }

    public String getNoteInstitution() {
        return noteInstitution;
    }

    public void setNoteInstitution(String noteInstitution) {
        this.noteInstitution = noteInstitution;
    }

    public String getRelatedPersonDetailsFlag1() {
        return relatedPersonDetailsFlag1;
    }

    public void setRelatedPersonDetailsFlag1(String relatedPersonDetailsFlag1) {
        this.relatedPersonDetailsFlag1 = relatedPersonDetailsFlag1;
    }

    public String getAdditionOrDeletionRelatedPerson() {
        return additionOrDeletionRelatedPerson;
    }

    public void setAdditionOrDeletionRelatedPerson(String additionOrDeletionRelatedPerson) {
        this.additionOrDeletionRelatedPerson = additionOrDeletionRelatedPerson;
    }

    public String getKYCNumberRelatedPerson() {
        return kYCNumberRelatedPerson;
    }

    public void setKYCNumberRelatedPerson(String kYCNumberRelatedPerson) {
        this.kYCNumberRelatedPerson = kYCNumberRelatedPerson;
    }

    public String getRelatedPersonNamePrefix() {
        return relatedPersonNamePrefix;
    }

    public void setRelatedPersonNamePrefix(String relatedPersonNamePrefix) {
        this.relatedPersonNamePrefix = relatedPersonNamePrefix;
    }

    public String getRelatedPersonFirstName() {
        return relatedPersonFirstName;
    }

    public void setRelatedPersonFirstName(String relatedPersonFirstName) {
        this.relatedPersonFirstName = relatedPersonFirstName;
    }

    public String getRelatedPersonMiddleName() {
        return relatedPersonMiddleName;
    }

    public void setRelatedPersonMiddleName(String relatedPersonMiddleName) {
        this.relatedPersonMiddleName = relatedPersonMiddleName;
    }

    public String getRelatedPersonLastName() {
        return relatedPersonLastName;
    }

    public void setRelatedPersonLastName(String relatedPersonLastName) {
        this.relatedPersonLastName = relatedPersonLastName;
    }

    public String getRelatedPersonTypeOfIdentity() {
        return relatedPersonTypeOfIdentity;
    }

    public void setRelatedPersonTypeOfIdentity(String relatedPersonTypeOfIdentity) {
        this.relatedPersonTypeOfIdentity = relatedPersonTypeOfIdentity;
    }

    public String getRelatedPersonIdentificationNumber() {
        return relatedPersonIdentificationNumber;
    }

    public void setRelatedPersonIdentificationNumber(String relatedPersonIdentificationNumber) {
        this.relatedPersonIdentificationNumber = relatedPersonIdentificationNumber;
    }

    public String getReatedPersonIdExpiryDate() {
        return reatedPersonIdExpiryDate;
    }

    public void setReatedPersonIdExpiryDate(String reatedPersonIdExpiryDate) {
        this.reatedPersonIdExpiryDate = reatedPersonIdExpiryDate;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getFlagOfRelatedPersonFatherSpouse() {
        return flagOfRelatedPersonFatherSpouse;
    }

    public void setFlagOfRelatedPersonFatherSpouse(String flagOfRelatedPersonFatherSpouse) {
        this.flagOfRelatedPersonFatherSpouse = flagOfRelatedPersonFatherSpouse;
    }

    public String getRelatedPersonFatherOrSpousePrefix() {
        return relatedPersonFatherOrSpousePrefix;
    }

    public void setRelatedPersonFatherOrSpousePrefix(String relatedPersonFatherOrSpousePrefix) {
        this.relatedPersonFatherOrSpousePrefix = relatedPersonFatherOrSpousePrefix;
    }

    public String getRelatedPersonFatherOrSpouseFirstName() {
        return relatedPersonFatherOrSpouseFirstName;
    }

    public void setRelatedPersonFatherOrSpouseFirstName(String relatedPersonFatherOrSpouseFirstName) {
        this.relatedPersonFatherOrSpouseFirstName = relatedPersonFatherOrSpouseFirstName;
    }

    public String getRelatedPersonPAN() {
        return relatedPersonPAN;
    }

    public void setRelatedPersonPAN(String relatedPersonPAN) {
        this.relatedPersonPAN = relatedPersonPAN;
    }

    public String getRelatedPersonDOB() {
        return relatedPersonDOB;
    }

    public void setRelatedPersonDOB(String relatedPersonDOB) {
        this.relatedPersonDOB = relatedPersonDOB;
    }

    public String getRelatedPersonGender() {
        return relatedPersonGender;
    }

    public void setRelatedPersonGender(String relatedPersonGender) {
        this.relatedPersonGender = relatedPersonGender;
    }

    public String getRelatedPersonNationality() {
        return relatedPersonNationality;
    }

    public void setRelatedPersonNationality(String relatedPersonNationality) {
        this.relatedPersonNationality = relatedPersonNationality;
    }

    public String getRelatedPersonAddressLine1() {
        return relatedPersonAddressLine1;
    }

    public void setRelatedPersonAddressLine1(String relatedPersonAddressLine1) {
        this.relatedPersonAddressLine1 = relatedPersonAddressLine1;
    }

    public String getRelatedPersonAddressLine2() {
        return relatedPersonAddressLine2;
    }

    public void setRelatedPersonAddressLine2(String relatedPersonAddressLine2) {
        this.relatedPersonAddressLine2 = relatedPersonAddressLine2;
    }

    public String getRelatedPersonAddressLine3() {
        return relatedPersonAddressLine3;
    }

    public void setRelatedPersonAddressLine3(String relatedPersonAddressLine3) {
        this.relatedPersonAddressLine3 = relatedPersonAddressLine3;
    }

    public String getRelatedPersonCityTownVillage() {
        return relatedPersonCityTownVillage;
    }

    public void setRelatedPersonCityTownVillage(String relatedPersonCityTownVillage) {
        this.relatedPersonCityTownVillage = relatedPersonCityTownVillage;
    }

    public String getRelatedPersonDistrict() {
        return relatedPersonDistrict;
    }

    public void setRelatedPersonDistrict(String relatedPersonDistrict) {
        this.relatedPersonDistrict = relatedPersonDistrict;
    }

    public String getRelatedPersonState() {
        return relatedPersonState;
    }

    public void setRelatedPersonState(String relatedPersonState) {
        this.relatedPersonState = relatedPersonState;
    }

    public String getRelatedPersonCountry() {
        return relatedPersonCountry;
    }

    public void setRelatedPersonCountry(String relatedPersonCountry) {
        this.relatedPersonCountry = relatedPersonCountry;
    }

    public String getRelatedPersonPINCode() {
        return relatedPersonPINCode;
    }

    public void setRelatedPersonPINCode(String relatedPersonPINCode) {
        this.relatedPersonPINCode = relatedPersonPINCode;
    }

    public String getFlagOfIdentificationAddressIsSameRelaredPerson() {
        return flagOfIdentificationAddressIsSameRelaredPerson;
    }

    public void setFlagOfIdentificationAddressIsSameRelaredPerson(String flagOfIdentificationAddressIsSameRelaredPerson) {
        this.flagOfIdentificationAddressIsSameRelaredPerson = flagOfIdentificationAddressIsSameRelaredPerson;
    }

    public String getRelatedPersonImage() {
        return relatedPersonImage;
    }

    public void setRelatedPersonImage(String relatedPersonImage) {
        this.relatedPersonImage = relatedPersonImage;
    }

    public String getRelatedPersonSignatureImage() {
        return relatedPersonSignatureImage;
    }

    public void setRelatedPersonSignatureImage(String relatedPersonSignatureImage) {
        this.relatedPersonSignatureImage = relatedPersonSignatureImage;
    }

    public String getRelatedPersonIdProofImageSide1() {
        return relatedPersonIdProofImageSide1;
    }

    public void setRelatedPersonIdProofImageSide1(String relatedPersonIdProofImageSide1) {
        this.relatedPersonIdProofImageSide1 = relatedPersonIdProofImageSide1;
    }

    public String getRelatedPersonIdProofImageSide2() {
        return relatedPersonIdProofImageSide2;
    }

    public void setRelatedPersonIdProofImageSide2(String relatedPersonIdProofImageSide2) {
        this.relatedPersonIdProofImageSide2 = relatedPersonIdProofImageSide2;
    }

    public String getRelatedPersonAddressProofImageSide1() {
        return relatedPersonAddressProofImageSide1;
    }

    public void setRelatedPersonAddressProofImageSide1(String relatedPersonAddressProofImageSide1) {
        this.relatedPersonAddressProofImageSide1 = relatedPersonAddressProofImageSide1;
    }

    public String getRelatedPersonAddressProofImageSide2() {
        return relatedPersonAddressProofImageSide2;
    }

    public void setRelatedPersonAddressProofImageSide2(String relatedPersonAddressProofImageSide2) {
        this.relatedPersonAddressProofImageSide2 = relatedPersonAddressProofImageSide2;
    }

    public String getRelatedPersonMobileISD() {
        return relatedPersonMobileISD;
    }

    public void setRelatedPersonMobileISD(String relatedPersonMobileISD) {
        this.relatedPersonMobileISD = relatedPersonMobileISD;
    }

    public String getRelatedPersonMobileNumber() {
        return relatedPersonMobileNumber;
    }

    public void setRelatedPersonMobileNumber(String relatedPersonMobileNumber) {
        this.relatedPersonMobileNumber = relatedPersonMobileNumber;
    }

    public String getRelatedPersonEmail() {
        return relatedPersonEmail;
    }

    public void setRelatedPersonEmail(String relatedPersonEmail) {
        this.relatedPersonEmail = relatedPersonEmail;
    }


}