package com.lucky.sweet.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.OrderTimeDialogListAdapater;

/**
 * Created by chn on 2017/12/23.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class DishesOrderDialog {

    int selectDate = -1;
    private AlertDialog dialog;

    public DishesOrderDialog showDialog(final Context context, final String[]
            leftStr, final String[][] rightStr) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_dishes_order, null);

        ListView lv_left = inflate.findViewById(R.id.lv_left);
        final ListView lv_right = inflate.findViewById(R.id.lv_right);
        final OrderTimeDialogListAdapater leftAdapater = new OrderTimeDialogListAdapater(leftStr, context);
        lv_left.setAdapter(leftAdapater);
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lv_right.setAdapter(new OrderTimeDialogListAdapater(rightStr[position], context));
                selectDate = position;
                leftAdapater.setSelectedItem(position);
                leftAdapater.notifyDataSetChanged();

            }
        });

        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (onDateSelectListener != null)
                    onDateSelectListener.onDateSelected(leftStr[selectDate]+ rightStr[selectDate][position]);
                dialog.cancel();
            }
        });


        builder.setView(inflate);
        dialog = builder.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        WindowManager m = ((Activity) context).getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        p.width = d.getWidth();
        dialog.getWindow().setAttributes(p);
        return this;
    }

    public interface OnDateSelectListener {
        void onDateSelected(String dates);
    }

    private OnDateSelectListener onDateSelectListener;

    public void setDateSelectListener(OnDateSelectListener onDateSelectListener) {

        this.onDateSelectListener = onDateSelectListener;

    }
}
