package com.finwin.payyanur.ckyc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivitySearch extends Activity {


    String responseMsg, Message, StrBrnchId = "";

    TextView tvwrng_msg;
    ListView acc_details_listView;
    private static ArrayList<HashMap<String, String>> list;
    Button btnFromDate, btnToDate, btnSearch;
    DatePickerDialog pickerFrom, pickerTo;
    SweetAlertDialog progressDialog;
    Spinner spinnerBrnch;
    ArrayAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);
        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
       // requestQueue = Volley.newRequestQueue(this);

        list = new ArrayList<HashMap<String, String>>();
        acc_details_listView = (ListView) findViewById(R.id.list_search);
        tvwrng_msg = (TextView) findViewById(R.id.tv_wrng_msg);

        spinnerBrnch = (Spinner) findViewById(R.id.spnr_branch);
        if (ConstantClass.ArrayBrnchName != null && ConstantClass.ArrayBrnchName.length > 0) {
            dataAdapter = new ArrayAdapter<String>(ActivitySearch.this, android.R.layout.simple_spinner_item,
                    ConstantClass.ArrayBrnchName);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBrnch.setAdapter(dataAdapter);
        }
        spinnerBrnch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    StrBrnchId = ConstantClass.ArrayBrnchId[position];
//                    Log.e("StrBrnchId: ", StrBrnchId);
                } catch (Exception ignore) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //searchProfile();
            }
        });

        btnFromDate = findViewById(R.id.btn_from_date);
        btnFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerFrom = new DatePickerDialog(ActivitySearch.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                cldr.set(year, monthOfYear + 1, dayOfMonth);
                                @SuppressLint("SimpleDateFormat")
                                String date = new SimpleDateFormat("yyyy-MM-dd").format(cldr.getTime());
                                btnFromDate.setText(date);
                            }
                        }, year, month, day);
                pickerFrom.show();
            }
        });

        btnToDate = findViewById(R.id.btn_to_date);
        btnToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerTo = new DatePickerDialog(ActivitySearch.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                cldr.set(year, monthOfYear + 1, dayOfMonth);
                                @SuppressLint("SimpleDateFormat")
                                String date = new SimpleDateFormat("yyyy-MM-dd").format(cldr.getTime());
                                btnToDate.setText(date);
                            }
                        }, year, month, day);
                pickerTo.show();
            }
        });

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//"dd-MM-yyyy"
        String CurrentDate = df.format(c.getTime());
        btnToDate.setText(CurrentDate);

        c.add(Calendar.YEAR, -1);
        String preDate = df.format(c.getTime()); // formatted date
        btnFromDate.setText(preDate);
    }

//    private void searchProfile() {
//        progressDialog.setTitleText("Please wait..");
//        progressDialog.show();
//        final String FromDate = btnFromDate.getText().toString();
//        final String ToDate = btnToDate.getText().toString();
//
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, ConstantClass.api_CKYC_ManagerApi,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("ManagerApi response = >", response);
//                        try {
//                            JSONObject jObject = new JSONObject(response);
//                            responseMsg = jObject.getString("data");
////                            JSONObject jsonObject = new JSONObject(responseMsg);
////                            if (responseMsg.equals("1")) {
////                                JSONArray jsonArray = new JSONArray(jsonObject.getString("Table"));
//                            JSONArray jsonArray = new JSONObject(responseMsg).getJSONArray("Table");
//                            Log.e("CKYC_ManagerApi = >", jsonArray.toString());
//                            if (jsonArray.length() > 0) {
//                                list.clear();
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
//                                    HashMap<String, String> hashmap = new HashMap<String, String>();
//                                    hashmap.put(ConstantClass.CNS_CUST_ID, jsonobject.getString("Cust_ID"));
//                                    hashmap.put(ConstantClass.CNS_CKYC_NO, jsonobject.getString("CKYC_NO"));
//                                    hashmap.put(ConstantClass.CNS_NAME, jsonobject.getString("Applicant_Name"));
//                                    hashmap.put(ConstantClass.CNS_DOB, jsonobject.getString("DOB"));
//                                    hashmap.put(ConstantClass.CNS_AADHAR, jsonobject.getString("Aadhaar"));
//                                    hashmap.put(ConstantClass.CNS_MSG, jsonobject.getString("Message"));
//                                    list.add(hashmap);
//                                }
//
//                                responseMsg = "1";
//                            } else {
//                                Message = "No results found";
//                                responseMsg = "0";
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            responseMsg = "exception";
//                        }
//                        progressDialog.dismiss();
//                        switch (responseMsg) {
//                            case "1":
//                                final SearchAdapter adapterSearch = new SearchAdapter(ActivitySearch.this, list);
//                                acc_details_listView.setAdapter(adapterSearch);
//                                acc_details_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                        //Toast.makeText(getBaseContext(), adapterSearch.getItem(position).toString(), Toast.LENGTH_SHORT).show();
//                                        String cus_id = adapterSearch.getCusId(position).toString();
//                                        String acc_nme = adapterSearch.getAccName(position).toString();
//                                        String acc_dob = adapterSearch.getDob(position).toString();
//                                        Intent resultIntent = new Intent();
//                                        resultIntent.putExtra("result_cus_id", cus_id);
//                                        resultIntent.putExtra("result_acc_name", acc_nme);
//                                        resultIntent.putExtra("result_dob", acc_dob);
//                                        setResult(RESULT_OK, resultIntent);
//                                        finish();
//                                    }
//                                });
//
//                                tvwrng_msg.setVisibility(View.INVISIBLE);
//                                acc_details_listView.setVisibility(VISIBLE);
//                                break;
//
//                            case "0":
//
//                                acc_details_listView.setVisibility(View.INVISIBLE);
//                                tvwrng_msg.setVisibility(VISIBLE);
//                                tvwrng_msg.setText(Message);
//                                break;
//
//                            case "exception":
//                                new SweetAlertDialog(ActivitySearch.this, SweetAlertDialog.ERROR_TYPE)
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
//                        new SweetAlertDialog(ActivitySearch.this, SweetAlertDialog.ERROR_TYPE)
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
//                hashMap.put("Record_Type", "string");
//                hashMap.put("Line_Number", "string");
//                hashMap.put("Application_Type", "string");
//                hashMap.put("Constitution_Type", "string");
//                hashMap.put("Account_Type", "string");
//                hashMap.put("CKYC_No", "string");
//                hashMap.put("Applicant_Name_Prefix", "string");
//                hashMap.put("Applicant_First_Name", "string");
//                hashMap.put("Applicant_Middle_Name", "string");
//                hashMap.put("Applicant_Last_Name", "string");
//                hashMap.put("Flag_indicating_Father_or_Spouse_Name", "string");
//                hashMap.put("Applicant_Father_Or_Spouse_Name_Prefix", "string");
//                hashMap.put("Father_Or_Spouse_First_Name", "string");
//                hashMap.put("Father_Or_Spouse_Middle_Name", "string");
//                hashMap.put("Father_Or_Spouse_Last_Name", "string");
//                hashMap.put("Applicant_Mother_Name_Prefix", "string");
//                hashMap.put("Mother_First_Name", "string");
//                hashMap.put("Mother_Middle_Name", "string");
//                hashMap.put("Mother_Last_Name", "string");
//                hashMap.put("Gender", "string");
//                hashMap.put("Marital_status", "string");
//                hashMap.put("Nationality", "string");
//                hashMap.put("Occupation_Type", "string");
//                hashMap.put("Date_of_Birth", "2020-01-07T09:24:49.484Z");
//                hashMap.put("Residential_Status", "string");
//                hashMap.put("Flag_Indian_NRI", "string");
//                hashMap.put("Current_Address_Type", "string");
//                hashMap.put("Current_Line_1", "string");
//                hashMap.put("Current_Line_2", "string");
//                hashMap.put("Current_Line_3", "string");
//                hashMap.put("Current_City_Town_Village", "string");
//                hashMap.put("Current_District", "string");
//                hashMap.put("Current_State", "string");
//                hashMap.put("Current_Country", "string");
//                hashMap.put("Current_PIN_Code", "string");
//                hashMap.put("Proof_of_Address", "string");
//                hashMap.put("Flag_IsSamelocal_Address", "string");
//                hashMap.put("Date_of_Declaration", "2020-01-07T09:24:49.484Z");
//                hashMap.put("Place_of_Declaration", "string");
//                hashMap.put("KYC_Verification_Date", "2020-01-07T09:24:49.484Z");
//                hashMap.put("Type_of_Document_Submitted", "string");
//                hashMap.put("KYC_Verification_Name", "string");
//                hashMap.put("KYC_Verification_Designation", "string");
//                hashMap.put("KYC_Verification_Branch", "string");
//                hashMap.put("KYC_Verification_EMP_code", "string");
//                hashMap.put("Organisation_Name", "string");
//                hashMap.put("Organisation_Code", "string");
//                hashMap.put("Number_of_Identity_Details", "string");
//                hashMap.put("Number_of_related_people", "string");
//                hashMap.put("Number_of_Local_Address_Details", "string");
//                hashMap.put("Number_of_Images", "string");
//                hashMap.put("Number_of_controlling_persons_resident_outside_India", "string");
//                hashMap.put("Image_Details_Update_Flag", "string");
//                hashMap.put("Account_Holder_Type", "string");
//                hashMap.put("Tax_Identification_Number_TIN", "string");
//                hashMap.put("TIN_Issuing_Country", "string");
//                hashMap.put("PAN", "string");
//                hashMap.put("Aadhaar", "string");
//                hashMap.put("Identification_Type", "string");
//                hashMap.put("Identification_Number", "string");
//                hashMap.put("Id_Expiry_Date", "2020-01-07T09:24:49.484Z");
//                hashMap.put("Id_Proof_Image_Side1", "string");
//                hashMap.put("Id_Proof_Image_Side2", "string");
//                hashMap.put("Applicant_Image", "string");
//                hashMap.put("Address_Proof_Image_Side1", "string");
//                hashMap.put("Address_Proof_Image_Side2", "string");
//                hashMap.put("Applicant_Signature_Image", "string");
//                hashMap.put("Occupation", "string");
//                hashMap.put("Note_Employee", "string");
//                hashMap.put("Note_Institution", "string");
//                hashMap.put("FromDate", FromDate);
//                hashMap.put("ToDate", ToDate);
//                hashMap.put("Cust_Id", ConstantClass.Cust_Id);
//                hashMap.put("Branch_Code", TextUtils.isEmpty(StrBrnchId) ? ConstantClass.Branch_Code : StrBrnchId);
//                hashMap.put("Flag", "SELECT_CUSTOMERLIST");
//                hashMap.put("MakerId", "string");
//                hashMap.put("RegionName", "string");
//                hashMap.put("RegionCode", "string");
//                hashMap.put("RegionId", "0");
//                hashMap.put("EmployeeId", "string");
//                hashMap.put("EmpCode", "string");
//                hashMap.put("EmpDesignation", "string");
//                hashMap.put("EmpBranch", "string");
//
//                Log.e("srch_CKYC_ManagerApi", hashMap.toString());
//                return hashMap;
//            }
//        };
//
//        int socketTimeout = 5000;//30000msec = 30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        postRequest.setRetryPolicy(policy);
//
////        postRequest.setRetryPolicy(new RetryPolicy() {
////            @Override
////            public int getCurrentTimeout() {
////                return 50000;
////            }
////
////            @Override
////            public int getCurrentRetryCount() {
////                return 50000;
////            }
////
////            @Override
////            public void retry(VolleyError error) throws VolleyError {
////
////            }
////        });
//        requestQueue.add(postRequest);
//
//    }

}

