package com.finwin.payyanur.ckyc.home.customer_details.model;

public class Gender {
    String gender;
    String genderCode;

    public Gender(String gender, String genderCode) {
        this.gender = gender;
        this.genderCode = genderCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }
}
