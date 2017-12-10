package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qiuyue on 2017/12/8.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class StoreDisplatActivity extends BasicActivity {

    private Title title = null;
    @Bind(R.id.main_sp1)
    Spinner mMainSp1;
    @Bind(R.id.main_sp2)
    Spinner mMainSp2;
    @Bind(R.id.main_sp3)
    Spinner mMainSp3;
    private List<String> data_list1;
    private List<String> data_list2;
    private List<String> data_list3;

    private BaseAdapter arr_adapter;//适配器
    private ListView lv_storeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedisplay);
        ButterKnife.bind(this);
        initData();
        initAdapter();
        setListener();
        initViews();

        initTitle();

    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);

        Intent intent = getIntent();
        if (intent.getStringExtra("tv_moreFood") != null && intent.getStringExtra("tv_moreFood").equals("Food")) {
            title.setTitleNameStr("餐厅");
        }
        else if (intent.getStringExtra("tv_moreRelax") != null && intent.getStringExtra("tv_moreRelax").equals("Relax")){
            title.setTitleNameStr("休闲");
        }
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        finish();
                        break;
                }
            }
        });
        title.mSetButtonInfo(buttonLeft);
    }

    private void initViews(){
        lv_storeInfo = (ListView)findViewById(R.id.lv_storeInfo);
    }


    //设置监听事件，将来商家列表的排序都在这里面处理
    private void setListener() {
        mMainSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StoreDisplatActivity.this, "点击了"+data_list1.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mMainSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StoreDisplatActivity.this, "点击了"+data_list2.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mMainSp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StoreDisplatActivity.this, "点击了"+data_list3.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initAdapter() {
        //适配器
        arr_adapter = new MyAdapter(data_list1);
        mMainSp1.setAdapter(arr_adapter);

        //适配器
        arr_adapter = new MyAdapter(data_list2);
        mMainSp2.setAdapter(arr_adapter);

        //适配器
        arr_adapter = new MyAdapter(data_list3);
        mMainSp3.setAdapter(arr_adapter);

    }

    //设置数据来源，这个要在网络获取。由于数据不会太多，不再考虑性能优化问题
    private void initData() {
        //数据
        data_list1 = new ArrayList<>();
        data_list1.add("全部商圈");
        data_list1.add("兴工街");
        data_list1.add("机场");
        data_list1.add("火车站");
        data_list1.add("旅顺南路");

        //数据
        data_list2 = new ArrayList<>();
        data_list2.add("餐厅");
        data_list2.add("咖啡店");
        data_list2.add("按摩院");
        data_list2.add("面包店");
        data_list2.add("艺术展");

        //数据
        data_list3 = new ArrayList<>();
        data_list3.add("评分最高");
        data_list3.add("离我最近");
        data_list3.add("最新收录");
        data_list3.add("消费最低");
        data_list3.add("消费最高");
    }

    //适配器
    class MyAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private List<String> mDataList;

        MyAdapter(List<String> dataList) {
            this.mDataList = dataList;
            mLayoutInflater = LayoutInflater.from(StoreDisplatActivity.this);
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //数据不太多，没有使用ViewHolder进行处理。
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(StoreDisplatActivity.this, R.layout.item_storedisplay, null);
            TextView textView = (TextView) convertView.findViewById(R.id.item_mainx_tv);
            textView.setText(mDataList.get(position));
            textView.setTextColor(getResources().getColorStateList(R.color.selector_spinner_item));
            return convertView;
        }
    }


}
