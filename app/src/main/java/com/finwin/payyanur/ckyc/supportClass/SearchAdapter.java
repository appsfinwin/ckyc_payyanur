package com.finwin.payyanur.ckyc.supportClass;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finwin.payyanur.ckyc.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchAdapter extends BaseAdapter {

    Activity activity;
    public ArrayList<HashMap<String, String>> list;
    private ViewHolder holder;

//    private String[] acc_name, cus_id, acc_no, acc_phone;
//    private static LayoutInflater layoutInflater = null;

    public SearchAdapter(Activity activity, ArrayList<HashMap<String, String>> _list) {
        this.activity = activity;
        this.list = _list;
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
//        return acc_name.length;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
//        return acc_no[position];
    }

    public Object getCusId(int position) {
        HashMap<String, String> map = list.get(position);
        return map.get(ConstantClass.CNS_CUST_ID);
        //        return list.get(position);
//        return cus_id[position];
    }

    public Object getAccName(int position) {
        HashMap<String, String> map = list.get(position);
        return map.get(ConstantClass.CNS_NAME);
    }

    public Object getDob(int position) {
        HashMap<String, String> map = list.get(position);
        return map.get(ConstantClass.CNS_DOB);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        //        TextView tvslno;
        TextView tvCusid;
        TextView tvName;
        TextView tvDob;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_list_view, null);
            holder = new SearchAdapter.ViewHolder();
//            holder.tvslno = convertView.findViewById(R.id.txt_list_slno);
            holder.tvCusid = convertView.findViewById(R.id.tv_row_cus_id);
            holder.tvName = convertView.findViewById(R.id.tv_row_name);
            holder.tvDob = convertView.findViewById(R.id.tv_row_dob);

            convertView.setTag(holder);
        } else {
            holder = (SearchAdapter.ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map = list.get(position);
        holder.tvCusid.setText(map.get(ConstantClass.CNS_CUST_ID));
        holder.tvName.setText(map.get(ConstantClass.CNS_NAME));
        holder.tvDob.setText(map.get(ConstantClass.CNS_DOB));

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#e6e6e6"));
        }

        return convertView;
    }
}
