package com.lucky.sweet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.adapter.ProductComentAdapter;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;

public class DetailedDishesActivity extends BaseActivity {

    private static final String EXTRA_TRAVEL = "DETAILED_DISHES";
    private ListView lv_productcoment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_dishes);
        initViews();
    }

    public void initViews(){
        ToolBar toolBar = new ToolBar(this);
        toolBar.setImmersionBar();
        ImageButton fl_back=(ImageButton)findViewById(R.id.fl_back);
        fl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                goPreAnim();
            }
        });
        lv_productcoment = (ListView)findViewById(R.id.lv_productcoment);
        ArrayList<String> objects = new ArrayList<String>();
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        lv_productcoment.setAdapter(new ProductComentAdapter(objects, this));
        setListViewHeightBasedOnChildren(lv_productcoment);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, DetailedDishesActivity.class);
        //intent.putExtra(EXTRA_TRAVEL, travel);
        return intent;
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        //获得adapter
        ProductComentAdapter adapter = (ProductComentAdapter) listView.getAdapter();
        if (adapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            //计算总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //计算分割线高度
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        //给listview设置高度
        listView.setLayoutParams(params);
    }
}
