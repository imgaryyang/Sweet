package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.SearchSpinnerAdapter;
import com.lucky.sweet.adapter.ShowInfoListViewAdapter;
import com.lucky.sweet.entity.StoreDisplayInfo;
import com.lucky.sweet.entity.StoreDisplaySearchEntity;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private SwipyRefreshLayout sw_store_info;
    private ShowInfoListViewAdapter adapter;
    private CommunicationService.MyBinder myBinder;
    private List<StoreDisplayInfo.MerListBean> displayList;
    private String businessArea;
    private String rankType;
    private String recreationType;
    private List<String> circle;
    private List<String> classify;
    private List<String> order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedisplay);
        ButterKnife.bind(this);


        initViews();

        setListener();

        initTitle();

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

        myBinder.requestStoreDisplayInfo("聚餐宴请", "全部", MyApplication.CURRENT_CITY, "最新搜录", "0");
        this.myBinder = myBinder;
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

        intent.getStringExtra("city");
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


    private void sendSearChRequest() {

    }

    //设置监听事件，将来商家列表的排序都在这里面处理
    private void setListener() {
        sp_BusinessArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                businessArea = circle.get(position);
                sendSearChRequest();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_RecreationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recreationType = classify.get(position);
                sendSearChRequest();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_RankType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rankType = order.get(position);
                sendSearChRequest();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lv_storeInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(StoreDisplatActivity.this, StoreParticularInfoActivity.class);
                intent.putExtra("shopid", displayList.get(position).getMer_id());
                startActivity(intent);
                goNextAnim();
            }
        });
        sw_store_info.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {

            }
        });


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(StoreDisplayInfo info) {

        displayList = info.getMer_list();

        adapter = new ShowInfoListViewAdapter(displayList, this);
        lv_storeInfo.setAdapter(adapter);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(StoreDisplaySearchEntity info) {

        StoreDisplaySearchEntity.LiistBean list = info.getLiist();

        circle = list.getCircle();
        classify = list.getClassify();
        order = list.getOrder();

        businessArea = circle.get(0);
        rankType = classify.get(0);
        recreationType = order.get(0);

        sp_BusinessArea.setAdapter(new SearchSpinnerAdapter(circle, this));

        sp_RecreationType.setAdapter(new SearchSpinnerAdapter(classify, this));

        sp_RankType.setAdapter(new SearchSpinnerAdapter(order, this));
    }


}
