package com.lucky.sweet.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.lucky.sweet.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/2/18.
 */
public class Tab extends RelativeLayout {

    //Tab按钮设置数据
    public ArrayList<TabButton.TabButtonData> dataLst = new ArrayList<TabButton.TabButtonData>();

    //Tab按钮布局
    private LinearLayout buttonsLayout = null;

    //Tab按钮对象
    private TabButton[] tabButtons = null;

    //Tab变化监听事件
    private OnTabChangeListener onTabChangeListener = null;

    //Tab按钮点击效果
    private TabButton.OnTabClickLinstener onTabClickLinstener = new TabButton.OnTabClickLinstener(){

        @Override
        public void onTabClick(View view, int positionTag, String tag) {
            int oldCheckedPos = positionTag;
            for(int i=0; i<tabButtons.length; i++){
                if(positionTag != i && tabButtons[i].isChecked()){
                    oldCheckedPos = i;
                    tabButtons[i].setChecked(false);
                }
            }
            if(oldCheckedPos == positionTag){
                tabButtons[positionTag].setChecked(true);
            }

            if(null != onTabChangeListener && oldCheckedPos != positionTag){
                onTabChangeListener.onTabChange(positionTag, tag);
            }
        }
    };

    public Tab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        buttonsLayout = (LinearLayout)findViewById(R.id.tabButtons);
        int tabButtonNum = 0;
        int n = buttonsLayout.getChildCount();
        for(int i=0; i<n; i++){
            if(buttonsLayout.getChildAt(i) instanceof TabButton){
                tabButtonNum++;
            }
        }
        tabButtons = new TabButton[tabButtonNum];
    }

    /**
     * 设置Tab的初始化数据
     * @param dataLst
     */
    public void mSetDataList(ArrayList<TabButton.TabButtonData> dataLst){
        this.dataLst = dataLst;
        if(tabButtons.length == dataLst.size()){
            int checkPos = -1;
            int tabPosition = 0;
            for(int i=0; i<buttonsLayout.getChildCount(); i++){
                if(buttonsLayout.getChildAt(i) instanceof TabButton){
                    tabButtons[tabPosition] = (TabButton)buttonsLayout.getChildAt(i);
                    tabButtons[tabPosition].setPositionTag(tabPosition);
                    tabButtons[tabPosition].setOnTabClickLinstener(onTabClickLinstener);
                    tabButtons[tabPosition].setButtonData(dataLst.get(tabPosition));
                    if(tabButtons[tabPosition].isChecked()){
                        checkPos = tabPosition;
                    }
                    tabPosition++;
                }
            }
            if(checkPos >= 0){
                if(null != onTabChangeListener){
                    onTabChangeListener.onTabChange(checkPos, dataLst.get(checkPos).tag);
                }
            }
        }
    }

    /**
     * 设置Tab变化监听事件
     * @param onTabChangeListener
     */
    public void setOnTabChangeListener(OnTabChangeListener onTabChangeListener){
        this.onTabChangeListener = onTabChangeListener;
    }

    public interface OnTabChangeListener {
        public void onTabChange(int position, String tag);
    }

    public void setSeletionByPos(int position){

    }

    public void setSeletionByTag(String tag){

    }
}
