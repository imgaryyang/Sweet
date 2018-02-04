package com.lucky.sweet.thread;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.utility.BlurBitmapUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chn on 2018/2/4.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public abstract class BlurBitmapThread extends Thread {

    private Activity activity;
    private View view;
    private int blurRadius;


    public BlurBitmapThread(Activity activity, View view, int blurRadius) {
        this.activity = activity;
        this.view = view;
        this.blurRadius = blurRadius;
    }

    @Override
    public void run() {
        Bitmap bitmap = BlurBitmapUtil.takeScreenshot((AppCompatActivity) activity, view);
        bitmap = BlurBitmapUtil.blurBitmap(activity, bitmap, 20);
        saveFile(bitmap, Properties.ORDER_SEAT_BACKGROUND_PATH);
        onBulerBitmapFinish();
    }

    public abstract void onBulerBitmapFinish();

    public static boolean saveFile(Bitmap bm, String path) {
        if (bm == null || path == null)
            return false;
        File myCaptureFile = new File(path);
        if (myCaptureFile.exists()) {
            myCaptureFile.delete();
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
