package com.finwin.payyanur.ckyc.main_activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.finwin.payyanur.ckyc.R;
import com.finwin.payyanur.ckyc.databinding.ActivityMainBinding;
import com.finwin.payyanur.ckyc.home.customer_address_proof.CustomerAddressProof;
import com.finwin.payyanur.ckyc.home.customer_details.CustomerDetailsActivity;
import com.finwin.payyanur.ckyc.home.customer_id_proof.CustomerIdProofFragment;
import com.finwin.payyanur.ckyc.login.LoginActivity;
import com.finwin.payyanur.ckyc.supportClass.ConstantClass;
import com.finwin.payyanur.ckyc.utils.CustomViewPager;
import com.finwin.payyanur.ckyc.utils.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity  {

    SweetAlertDialog progressDialog;
    RequestQueue requestQueue;
    String responseMsg, Message;
    static CustomViewPager viewPager;
    TabLayout tabs;

    MainActivityViewmodel viewModel;
    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel= new ViewModelProvider(this).get(MainActivityViewmodel.class);
        binding.setViewModel(viewModel);
        sharedPreferences = getSharedPreferences(ConstantClass.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString(ConstantClass.CUST_ID,"");
        editor.apply();
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
                getApplicationContext(), getSupportFragmentManager());
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(3);

        tabs.setupWithViewPager(viewPager);

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        requestQueue = Volley.newRequestQueue(this);


        tabs.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //String custId=sharedPreferences.getString(ConstantClass.CUST_ID,"");
                if (position==0)
                {
//                    editor.putString(ConstantClass.CUST_ID,"");
//                    editor.apply();
                    CustomerDetailsActivity fragment = CustomerDetailsActivity.newInstance();
                    String custId=sharedPreferences.getString(ConstantClass.CUST_ID,"");
                    fragment.getViewModel().resetData();
                    if (!custId.equals("")) {
                        fragment.getViewModel().rbtnNewCustomer.set(false);
                        fragment.getViewModel().rbtnOldCustomer.set(true);
                        fragment.getViewModel().custId.set(custId);
                        fragment.getViewModel().custId.set(custId);
                        fragment.getViewModel().initLoading(MainActivity.this);
                        fragment.getViewModel().getCustomerDetails();
                    }
                }
              else if (position==1) {
                  CustomerIdProofFragment fragment = CustomerIdProofFragment.newInstance();
                  String custId=sharedPreferences.getString(ConstantClass.CUST_ID,"");
                    fragment.reset();
                  if (!custId.equals("")) {
                      fragment.getViewModel().initLoading(MainActivity.this);
                      fragment.getViewModel().getCustomerDetails(custId);
                  }
              } else if (position==2) {
                  CustomerAddressProof fragment = CustomerAddressProof.newInstance();
                  String custId=sharedPreferences.getString(ConstantClass.CUST_ID,"");
                    fragment.reset();
                  if (!custId.equals("")) {
                      //fragment.getViewModel().initLoading(MainActivity.this,"fetch cust id");
                      fragment.getViewModel().getCustomerDetails(custId);
                  }
              }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        disableTab(0);
        disableTab(1);
        disableTab(2);
        ImageButton btnLogout = findViewById(R.id.imgbtn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    public void disableTab(int tabNumber) {
        ViewGroup vg = (ViewGroup) tabs.getChildAt(0);
        ViewGroup vgTab = (ViewGroup) vg.getChildAt(tabNumber);
        vgTab.setEnabled(false);
        viewPager.setPagingEnabled(false);
    }

    public void enableAllTabs()
    {
        enableTab(0);
        enableTab(1);
        enableTab(2);
    }

    public void disableAllTab()
    {
        disableTab(0);
        disableTab(1);
        disableTab(2);
    }
    public void enableTab(int tabNumber) {
        ViewGroup vg = (ViewGroup) tabs.getChildAt(0);
        ViewGroup vgTab = (ViewGroup) vg.getChildAt(tabNumber);
        vgTab.setEnabled(true);
        viewPager.setPagingEnabled(true);
    }



    public void goToNextTab() {
        viewPager.setCurrentItem(getItem(+1), true);
    }public void goToFirst() {
        viewPager.setCurrentItem(0, true);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void logout() {
        new AlertDialog.Builder(this)
//                .setIcon(R.drawable.ic_logout)
                .setTitle("Logout")
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            try {
                String result_cus_id = data.getStringExtra("result_cus_id");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = Objects.requireNonNull(cm).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //////========================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//         MenuItem item=(MenuItem)findViewById(R.id.action_logout);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

}