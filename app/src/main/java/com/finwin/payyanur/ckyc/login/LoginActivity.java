package com.finwin.payyanur.ckyc.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.finwin.payyanur.ckyc.R;
import com.finwin.payyanur.ckyc.databinding.ActivityLoginBinding;
import com.finwin.payyanur.ckyc.main_activity.MainActivity;
import com.finwin.payyanur.ckyc.login.action.LoginAction;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;


import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    SweetAlertDialog progressDialog;
   // RequestQueue requestQueue;
    String responseMsg, Message;
    EditText edtUsername, edtPassword;
    LoginViewModel viewModel;
    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);
        sharedPreferences=getSharedPreferences(ConstantClass.PREF_NAME,MODE_PRIVATE);
        editor= sharedPreferences.edit();

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        //requestQueue = Volley.newRequestQueue(this);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);


        viewModel.getmAction().observe(this, new Observer<LoginAction>() {
            @Override
            public void onChanged(LoginAction loginAction) {
                viewModel.cancelLoading();
                switch (loginAction.getAction()) {
                    case LoginAction.LOGIN_SUCCESS:

                        if (loginAction.getLoginResponse().getTable1().size()>0) {
                            ConstantClass.Cust_Id =loginAction.getLoginResponse().getTable1().get(0).getReturnID();
                            ConstantClass.Branch_Code = loginAction.getLoginResponse().getTable1().get(0).getBranchID();
                            ConstantClass.CNST_UserRole =loginAction.getLoginResponse().getTable1().get(0).getUserRole();
                            ConstantClass.CNST_ActiveSessionValue =loginAction.getLoginResponse().getTable1().get(0).getActiveSessionValue();
                            ConstantClass.CNST_IsDataEntryUser =loginAction.getLoginResponse().getTable1().get(0).getIsDataEntryUser();
                            ConstantClass.CNST_User_Name =loginAction.getLoginResponse().getTable1().get(0).getUserName();
                            ConstantClass.CNST_IsEnableBranch = loginAction.getLoginResponse().getTable1().get(0).getIsEnableBranch();

                            editor.putString(ConstantClass.MAKER_ID,loginAction.getLoginResponse().getTable1().get(0).getReturnID());
                            editor.apply();
//                            Intent intent = new Intent(LoginActivity.this, Upload.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                        break;

                    case LoginAction.API_ERROR:
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("ERROR")
                                .setContentText(loginAction.getError())
                                .show();
                        break;
                }

            }
        });

    }

//    private void loginProfile() {
//        progressDialog.setTitleText("Please wait..");
//        progressDialog.show();
//        String Username = edtUsername.getText().toString();
//        String Password = edtPassword.getText().toString();
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, ConstantClass.api_LogIn_Api,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("LogIn_Api rspns = >", response);
//                        try {
////                            JSONObject jObject = new JSONObject(response);
////                            responseMsg = jObject.getString("data");
//                            JSONArray jsonArray = new JSONObject(response).getJSONArray("Table1");
//                            if (jsonArray.length() > 0) {
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
////                                    HashMap<String, String> hashmap = new HashMap<String, String>();
//                                    ConstantClass.Cust_Id = jsonobject.getString("ReturnID");
//                                    ConstantClass.Branch_Code = jsonobject.getString("BranchID");
//                                    ConstantClass.CNST_UserRole = jsonobject.getString("UserRole");
//                                    ConstantClass.CNST_ActiveSessionValue = jsonobject.getString("ActiveSessionValue");
//                                    ConstantClass.CNST_IsDataEntryUser = jsonobject.getString("IsDataEntryUser");
//                                    ConstantClass.CNST_User_Name = jsonobject.getString("User_Name");
//                                    ConstantClass.CNST_IsEnableBranch = jsonobject.getString("IsEnableBranch");
//
//                                }
//                                responseMsg = "1";
//                            } else {
//                                Message = "Invalid Username/Password!!";
//                                responseMsg = "0";
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            responseMsg = "exception";
//                        }
//                        progressDialog.dismiss();
//                        switch (responseMsg) {
//                            case "1":
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();
//                                break;
//
//                            case "0":
//                                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
//                                        .setTitleText("Not Found")
//                                        .setContentText(Message)
//                                        .setCancelText("NO")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                                sweetAlertDialog.cancel();
//                                            }
//                                        })
//                                        .show();
//                                break;
//
//                            case "exception":
//                                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                        .setTitleText("ERROR")
//                                        .setContentText("Unexpected server error!")
//                                        .setCancelText("NO")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                                sweetAlertDialog.cancel();
//                                            }
//                                        })
//                                        .show();
//                                break;
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO Auto-generated method stub
//                        Log.d("ERROR", "error => " + error.toString());
//                        progressDialog.dismiss();
//                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                .setTitleText("ERROR")
//                                .setContentText("Server error occurred!!")
//                                .setCancelText("NO")
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                        sweetAlertDialog.cancel();
//                                    }
//                                })
//                                .show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                // the POST parameters:
//                Map<String, String> hashMap = new HashMap<>();
//                hashMap.put("Username", Username);
//                hashMap.put("Password", Password);
//
//                Log.e("login param", hashMap.toString());
//                return hashMap;
//            }
//        };
//
//        requestQueue.add(postRequest);
//
//    }

    //////========================

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
//            clearApplicationDataExternal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void clearApplicationDataExternal() {
//        File cache = getExternalCacheDir();
//        File appDir = new File(cache.getParent());
//        if (appDir.exists()) {
//            String[] children = appDir.list();
//            for (String s : children) {
//                if (!s.equals("lib")) {
//                    deleteDirExternal(new File(appDir, s));
//                    Log.e("Clear cache", "File /data/data/APP_PACKAGE/" + s + "DELETED...");
//                }
//            }
//        }
//    }
//
//    public static boolean deleteDirExternal(File dir) {
//        if (dir != null && dir.isDirectory()) {
//            String[] children = dir.list();
//            for (int i = 0; i < children.length; i++) {
//                boolean success = deleteDirExternal(new File(dir, children[i]));
//                if (!success) {
//                    return false;
//                }
//            }
//        }
//        return dir.delete();
//    }

    //////========================

}
