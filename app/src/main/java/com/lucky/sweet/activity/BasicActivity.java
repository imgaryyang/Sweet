package com.lucky.sweet.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;

public class BasicActivity extends AppCompatActivity implements TencentLocationListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    public AlertDialog showDialogBaseAct(String title, String message,
                                         String natureName,
                                         DialogInterface.OnClickListener
                                                 onClickNature,
                                         String positiveName, DialogInterface.OnClickListener onClickPositive,
                                         String negativeName,
                                         DialogInterface.OnClickListener
                                                 onClickNegative, Context
                                                 context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title)
                .setNegativeButton(natureName, onClickNature).setMessage
                        (message);
        if (null != positiveName)
            builder.setPositiveButton(positiveName, onClickPositive);
        if (null != negativeName)
            builder.setNegativeButton(negativeName, onClickNegative);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;

    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
      /*  if (requestCode == TencentLocation.ERROR_OK) {
            city = tencentLocation.getName().toString().trim();
        }
        stopLocation();*/
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    public void stopLocation() {
        //locationManager.removeUpdates(this);
    }
}
