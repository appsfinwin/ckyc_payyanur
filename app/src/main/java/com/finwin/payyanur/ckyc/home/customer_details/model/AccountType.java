package com.finwin.payyanur.ckyc.home.customer_details.model;

public class AccountType {
    String accountType;
    String accountTypeCode;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public AccountType(String accountType, String accountTypeCode) {
        this.accountType = accountType;
        this.accountTypeCode = accountTypeCode;
    }
}
