package com.lucky.sweet.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lucky.sweet.R;


/**
 * Created by Qiuyue on 2017/9/15.
 */

/*
 ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
 {/ o o /} {/ . . /} {/ ︿︿ /}
 ( (oo) )  ( (oo) )  ( (oo) )
   ︶︶︶    ︶︶︶     ︶︶︶
*/

public class MyToast {
    private android.widget.Toast mToast;
    private MyToast(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_mytoast, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_toastContent);
        mToast = new android.widget.Toast(context);
        textView.setText(text);
        mToast.setDuration(duration);
        mToast.setView(v);
    }


    public static MyToast makeText(Context context, CharSequence text, int duration) {
        return new MyToast(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

}

