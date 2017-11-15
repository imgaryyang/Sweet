package com.lucky.sweet.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lucky.sweet.R;


/**
 * Created by lenovo on 2016/2/18.
 */
public class TabButton extends RelativeLayout {

    public static final int TYPE_ALL = 1;//同时显示图片文字
    public static final int TYPE_IMG = 2;//仅显示图片
    public static final int TYPE_TEXT = 3;//仅显示文字

    private int positionTag;//TabButton唯一标识

    /**
     * 按钮类型
     * TYPE_IMG_TEXT,TYPE_IMG,TYPE_TEXT
     */
    private int mButtonType = TYPE_ALL;//按钮类型
    private boolean mSelectAble = true;//是否支持选中
    private TabButtonData parmas = null;//按钮配置参数

    private boolean checked = false;  //是否处于选中状态
    private int noteNum = 0;             //新提示数量
    private boolean notePrompt = false; //是否显示小圆点提示

    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mSelectAble) {
                checked = !checked;
                refreshView();
            }
            if (null != mOnTabClickListener) {
                mOnTabClickListener.onTabClick(TabButton.this, positionTag, parmas == null ? null : parmas.tag);
            }
        }
    };

    private OnTabClickListener mOnTabClickListener = null;


    //布局、图片、文本
    private View mCellFront = null;
    private ImageView mCellFrontImg = null;
    private TextView mCellFrontText = null;

    public TabButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
    }

    private void initViews() {
        mCellFront = findViewById(R.id.tabFrontCell);
        mCellFrontImg = (ImageView) findViewById(R.id.tabFrontImg);
        mCellFrontText = (TextView) findViewById(R.id.tabFrontTxt);
        this.setOnClickListener(mClickListener);
    }

    public void setButtonData(TabButtonData parmas) {
        this.parmas = parmas;
        this.mSelectAble = parmas.selectAble;
        this.mButtonType = parmas.buttonType;
        this.checked = parmas.checked;
        refreshView();
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.mOnTabClickListener = onTabClickListener;
    }

    public void setPositionTag(int positionTag) {
        this.positionTag = positionTag;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean is) {
        this.checked = is;
        refreshView();
    }

    public void refreshView() {
        if (checked) {
            mCellFrontText.setText(parmas.textChecked);
            mCellFrontText.setTextColor(parmas.colorChecked);
            mCellFrontImg.setImageResource(parmas.imgChecked);
        } else {
            mCellFrontText.setText(parmas.textNormal);
            mCellFrontText.setTextColor(parmas.colorNormal);
            mCellFrontImg.setImageResource(parmas.imgNormal);
        }
    }

    /**
     * Tab点击监听事件
     */
    public static abstract class OnTabClickListener {
        public abstract void onTabClick(View view, int positionTag, String tag);
    }

    public static class TabButtonData {
        public boolean checked = false;
        public String tag = null;
        public boolean selectAble = true;//是否支持选中
        public int buttonType = TYPE_ALL;//按钮类型

        public String textNormal;
        public String textChecked;
        public int colorNormal;
        public int colorChecked;
        public int imgNormal = 0;//图片资源ID:背景
        public int imgChecked = 0;//图片资源ID：前景
    }
}
