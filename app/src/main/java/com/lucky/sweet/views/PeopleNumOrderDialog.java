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
import com.lucky.sweet.adapter.OrderPeopleNumberListViewAdapter;

import java.util.List;

/**
 * Created by chn on 2017/12/23.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class PeopleNumOrderDialog {

    private AlertDialog dialog;
    private OnNumelectListener onNumelectListener;


    public PeopleNumOrderDialog(final Context context, final
    List<String> datas) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_people_number, null);

        ListView lv_left = inflate.findViewById(R.id.lv_people_num);
        lv_left.setAdapter(new OrderPeopleNumberListViewAdapter(datas, context));
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onNumelectListener != null) {
                    onNumelectListener.onNumSelected(datas.get(position));
                    dialog.cancel();
                }

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

    }

    public interface OnNumelectListener {
        void onNumSelected(String num);
    }


    public void setDateSelectListener(OnNumelectListener onNumelectListener) {
        this.onNumelectListener = onNumelectListener;

    }
}
