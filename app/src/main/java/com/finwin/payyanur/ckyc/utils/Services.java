package com.finwin.payyanur.ckyc.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Services {

    public static SweetAlertDialog showProgressDialog(Context context) {


        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("Please wait")
                .show();

        return sweetAlertDialog;
    }

    public static Double getImageSize(String base64String) {
        Double result = -1.0;
        if (!base64String.isEmpty()) {
            Integer padding = 0;
            if (base64String.endsWith("==")) {
                padding = 2;
            } else {
                if (base64String.endsWith("=")) padding = 1;
            }
            result = (Math.ceil(base64String.length() / 4) * 3) - padding;
        }
        return result / 1000;
    }

    public static void saveImageToMemory(Bitmap finalBitmap, String file_name,String customerId)
    {

        //String root = Environment.getExternalStorageState(Environment.getExternalStorageState()).toString();
        String root = (Environment.getExternalStorageDirectory()+"/ckyc/").toString();
        File myPicDirectory = new File(root +customerId);
        myPicDirectory.mkdirs();



        File file = new File(myPicDirectory, customerId+"-"+file_name+".jpg");
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // 90 refers to quality of image
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
