package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.SearchSpinnerAdapter;
import com.lucky.sweet.adapter.ShowInfoListViewAdapter;
import com.lucky.sweet.entity.MainStoreInfo;
import com.lucky.sweet.model.StoreDisplatManager;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

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

public class StoreDisplatActivity extends BaseActivity {

    private Title title = null;
    @Bind(R.id.sp_businessarea)
    Spinner sp_BusinessArea;
    @Bind(R.id.sp_recreationtype)
    Spinner sp_RecreationType;
    @Bind(R.id.sp_rankyype)
    Spinner sp_RankType;

    private ListView lv_storeInfo;
    private StoreDisplatManager storeDisplatManager;
    private SwipyRefreshLayout sw_store_info;
    private ShowInfoListViewAdapter adapter;
    private List<MainStoreInfo> storeShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedisplay);
        ButterKnife.bind(this);

        storeDisplatManager = new StoreDisplatManager(this);

        storeDisplatManager.getDisInfo();


        initViews();

        initAdapter();

        setListener();

        initTitle();

    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);

        Intent intent = getIntent();
        if (intent.getStringExtra("tv_moreFood") != null && intent.getStringExtra("tv_moreFood").equals("Food")) {
            title.setTitleNameStr("餐厅");
        } else if (intent.getStringExtra("tv_moreRelax") != null && intent.getStringExtra("tv_moreRelax").equals("Relax")) {
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
                        goPreAnim();
                        break;
                }
            }
        });
        title.mSetButtonInfo(buttonLeft);
    }

    private void initViews() {
        lv_storeInfo = findViewById(R.id.lv_storeInfo);
        sw_store_info = findViewById(R.id.sw_store_info);
    }


    //设置监听事件，将来商家列表的排序都在这里面处理
    private void setListener() {
        sp_BusinessArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StoreDisplatActivity.this, "点击了" + storeDisplatManager.getBusinessAreaList().get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_RecreationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StoreDisplatActivity.this, "点击了" +
                        storeDisplatManager.getRecreationTypeList().get
                                (position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_RankType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StoreDisplatActivity.this, "点击了" + storeDisplatManager.getRankTypeList().get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lv_storeInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(StoreDisplatActivity.this, StoreParticularInfoActivity.class);
                intent.putExtra("shopid", "" + position);
                startActivity(intent);
                goNextAnim();
            }
        });
        sw_store_info.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                storeShowInfo.add(new MainStoreInfo());
                storeShowInfo.add(new MainStoreInfo());
                storeShowInfo.add(new MainStoreInfo());
                storeShowInfo.add(new MainStoreInfo());
                storeShowInfo.add(new MainStoreInfo());
                storeShowInfo.add(new MainStoreInfo());
                adapter.notifyDataSetChanged();
                sw_store_info.setRefreshing(false);
            }
        });


    }

    private void initAdapter() {

        sp_BusinessArea.setAdapter(new SearchSpinnerAdapter(storeDisplatManager.getBusinessAreaList(), this));

        sp_RecreationType.setAdapter(new SearchSpinnerAdapter(storeDisplatManager.getRecreationTypeList(), this));

        sp_RankType.setAdapter(new SearchSpinnerAdapter(storeDisplatManager.getRankTypeList(), this));
        storeShowInfo = storeDisplatManager.getStoreShowInfo();
        adapter = new ShowInfoListViewAdapter
                (storeShowInfo, this);

        lv_storeInfo.setAdapter(adapter);

    }


}
