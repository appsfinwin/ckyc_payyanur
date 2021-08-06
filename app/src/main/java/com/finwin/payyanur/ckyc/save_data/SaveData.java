package com.finwin.payyanur.ckyc.save_data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SaveData")
public class SaveData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
     Integer id;

    @ColumnInfo(name = "Cust_Id")
     Integer Cust_Id;

    @ColumnInfo(name = "Branch_Code")
     String Branch_Code;

    @ColumnInfo(name = "CKYC_NO")
     String CKYC_NO;

    @ColumnInfo(name = "Applicant_Name_Prefix")
    String Applicant_Name_Prefix;

    @ColumnInfo(name = "Applicant_First_Name")
     String Applicant_First_Name;

    @ColumnInfo(name = "Applicant_Middle_Name")
    String Applicant_Middle_Name;

    @ColumnInfo(name = "_Applicant_Last_Name")
    String _Applicant_Last_Name;

    @ColumnInfo(name = "Applicant_Image")
    String Applicant_Image;

    @ColumnInfo(name = "Applicant_Father_Spouse_Name_Prefix")
    String Applicant_Father_Spouse_Name_Prefix;

    @ColumnInfo(name = "Father_Spouse_First_Name")
    String Father_Spouse_First_Name;

    @ColumnInfo(name = "Father_Spouse_Middle_Name")
    String Father_Spouse_Middle_Name;

    @ColumnInfo(name = "Father_Spouse_Last_Name")
    String Father_Spouse_Last_Name;

    @ColumnInfo(name = "Father_Spouse_Full_Name")
    String Father_Spouse_Full_Name;

    @ColumnInfo(name = "Applicant_Mother_Name_Prefix")
    String Applicant_Mother_Name_Prefix;

    @ColumnInfo(name = "Mother_s_First_Name")
    String Mother_s_First_Name;

    @ColumnInfo(name = "Mother_s_Middle_Name")
    String Mother_s_Middle_Name;

    @ColumnInfo(name = "Mother_s_Last_Name")
    String Mother_s_Last_Name;

    @ColumnInfo(name = "Mother_s_Full_Name")
    String Mother_s_Full_Name;

    @ColumnInfo(name = "Gender")
    String Gender;

    @ColumnInfo(name = "Marital_status")
    String Marital_status;

    @ColumnInfo(name = "Nationality")
    String Nationality;

    @ColumnInfo(name = "Occupation")
    String Occupation;

    @ColumnInfo(name = "Occupation_Type")
    String Occupation_Type;

    @ColumnInfo(name = "Date_of_Birth_Date_of_Incorporation")
    String Date_of_Birth_Date_of_Incorporation;

    @ColumnInfo(name = "Identification_Type")
    String Identification_Type;

    @ColumnInfo(name = "TIN_Issuing_Country")
    String TIN_Issuing_Country;

    @ColumnInfo(name = "Id_Expiry_Date")
    String Id_Expiry_Date;

    @ColumnInfo(name = "Id_Proof_Image_Side1")
    String Id_Proof_Image_Side1;

    @ColumnInfo(name = "Residential_Status")
    String Residential_Status;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_Type")
    String Current_Permanent_Overseas_Address_Type;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_Line_1")
    String Current_Permanent_Overseas_Address_Line_1;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_Line_2")
    String Current_Permanent_Overseas_Address_Line_2;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_Line_3")
    String Current_Permanent_Overseas_Address_Line_3;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_City_Town_Village")
    String Current_Permanent_Overseas_Address_City_Town_Village;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_State_U_T")
    String Current_Permanent_Overseas_Address_State_U_T;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_Country")
    String Current_Permanent_Overseas_Address_Country;

    @ColumnInfo(name = "Current_Permanent_Overseas_Address_PIN_Code")
    String Current_Permanent_Overseas_Address_PIN_Code;

    @ColumnInfo(name = "Proof_of_Address_submitted_for_Current_Permanent_Overseas_Address")
    String Proof_of_Address_submitted_for_Current_Permanent_Overseas_Address;

    @ColumnInfo(name = "Proof_of_Address_submitted_for_Current_Permanent_Overseas_Address_Others_")
    String Proof_of_Address_submitted_for_Current_Permanent_Overseas_Address_Others_;

    @ColumnInfo(name = "Address_Proof_Image_Side1")
    String Address_Proof_Image_Side1;

}