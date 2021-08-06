package com.finwin.payyanur.ckyc.login.action;

import com.finwin.payyanur.ckyc.login.pojo.LoginResponse;

public class LoginAction {

    public static final int API_ERROR=2;
    public static final int LOGIN_SUCCESS=4;
    public int action;
    public String error;
    public LoginResponse loginResponse;

    public LoginAction(int action) {
        this.action = action;
    }

    public LoginAction(int action, String error) {
        this.action = action;
        this.error = error;
    }

    public LoginAction(int action, LoginResponse loginResponse) {
        this.action = action;
        this.loginResponse = loginResponse;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }
}
