package com.finwin.payyanur.ckyc.utils;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.finwin.payyanur.ckyc.home.customer_address_proof.CustomerAddressProof;
import com.finwin.payyanur.ckyc.home.customer_details.CustomerDetailsActivity;
import com.finwin.payyanur.ckyc.home.customer_id_proof.CustomerIdProofFragment;
import com.finwin.payyanur.ckyc.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.customer_details,R.string.customer_id_proof, R.string.customer_address_proof};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm
            //, MutableLiveData<AddMemberActionModel> mutableAction
    ) {
        super(fm);

        customerIdProofFragment = CustomerIdProofFragment.newInstance();
        customerAddressProofFragment = CustomerAddressProof.newInstance();
        customerDetailsActivityFragment = CustomerDetailsActivity.newInstance();



        mContext = context;

    }

    CustomerDetailsActivity customerDetailsActivityFragment;
    CustomerAddressProof customerAddressProofFragment;
    CustomerIdProofFragment customerIdProofFragment;


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PersonalFragment (defined as a static inner class below).
//        return PersonalFragment.newInstance(position + 1);
       // SharedPreferences sharedPreferences = mContext.getApplicationContext().getSharedPreferences(SharedPrefConstants.PREF_CONST, Context.MODE_PRIVATE);

//        int status_Completed = sharedPreferences.getInt(SharedPrefConstants.ADD_MEMBER_STATUS, 1);

        int status_Completed = 1;
        switch (position) {
            case 0:
                return customerDetailsActivityFragment;
            case 1:


                    return customerIdProofFragment;
            case 2:

                    return customerAddressProofFragment;

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }

}