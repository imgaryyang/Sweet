package com.lucky.sweet.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.SearchSpinnerAdapter;
import com.lucky.sweet.adapter.ShowInfoListViewAdapter;
import com.lucky.sweet.entity.CollectStoreEntitiy;
import com.lucky.sweet.entity.StoreDisplayInfo;
import com.lucky.sweet.entity.StoreDisplaySearchEntity;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.views.MyToast;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

public class StoreDisplayActivity extends BaseActivity {

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
    private String businessArea = "";
    private String rankType = "";
    private String recreationType = "";
    private List<String> circle;
    private List<String> classify;
    private List<String> order;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedisplay);
        setIsBindEventBus();
        ButterKnife.bind(this);

        initViews();

        initTitle();

    }


    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
        myBinder.requestStoreSearch();
    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = findViewById(R.id.title);

        Intent intent = getIntent();
        if (intent.getStringExtra("tv_moreFood") != null && intent.getStringExtra("tv_moreFood").equals("Food")) {
            title.setTitleNameStr("餐厅");
        } else if (intent.getStringExtra("tv_moreRelax") != null && intent.getStringExtra("tv_moreRelax").equals("Relax")) {
            title.setTitleNameStr("休闲");
        }

        intent.getStringExtra("city");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        title.setOnTitleButtonClickListener((id, viewHolder) -> {
            switch (id) {
                case Title.BUTTON_LEFT:
                    finish();
                    goPreAnim();
                    break;
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
            Boolean flag = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!flag) {
                    if (position == 0) {

                        flag = true;
                        return;
                    }
                }

                businessArea = circle.get(position);

                requestSearchItem(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_RecreationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean flag = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!flag) {
                    if (position == 0) {

                        flag = true;
                        return;
                    }
                }

                recreationType = classify.get(position);
                requestSearchItem(false);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_RankType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean flag = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!flag) {
                    if (position == 0) {

                        flag = true;
                        return;
                    }
                }

                rankType = order.get(position);
                requestSearchItem(false);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lv_storeInfo.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(StoreDisplayActivity.this, StoreParticularInfoActivity.class);
            intent.putExtra("shopid", displayList.get(position).getMer_id());
            startActivity(intent);
            goNextAnim();
        });
        sw_store_info.setOnRefreshListener(direction -> {
            switch (direction.name()) {
                case "TOP":
                    num = 0;
                    requestSearchItem(true);
                    break;
                case "BOTTOM":
                    requestSearchItem(false);
                    num++;
                    myBinder.requestStoreDisplayInfo(recreationType, businessArea, rankType, num + "");
                    MyToast.showShort("加载更多");
                    break;
            }

        });


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(StoreDisplayInfo info) {
        if (sw_store_info.isRefreshing()) {
            sw_store_info.setRefreshing(false);
        }
        if (adapter == null) {
            displayList = info.getMer_list();
            adapter = new ShowInfoListViewAdapter(displayList, this);
            lv_storeInfo.setAdapter(adapter);
        } else if (info != null) {
            displayList.addAll(info.getMer_list());
            adapter.notifyDataSetChanged();
        } else {
            MyToast.showShort("暂时无数据");

        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(CollectStoreEntitiy collectStoreEntitiy) {
        if (collectStoreEntitiy.getAttr()) {
            String log;
            if (collectStoreEntitiy.isCollect()) {
                log = "收藏成功";
            } else {
                log = "取关成功";

            }

            MyToast.showShort(log);

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(StoreDisplaySearchEntity info) {

        StoreDisplaySearchEntity.LiistBean list = info.getLiist();
        circle = new ArrayList<>();
        ArrayList<StoreDisplaySearchEntity.LiistBean.SelectBean.ListBean> type = new ArrayList<>();
        for (StoreDisplaySearchEntity.LiistBean.SelectBean selectBean : list.getSelect()) {
            circle.add(selectBean.getName());
            for (StoreDisplaySearchEntity.LiistBean.SelectBean.ListBean bean : selectBean.getList()) type.add(bean);

        }


        this.order = list.getOrder();
        businessArea = circle.get(0);
        rankType = classify.get(0);
        recreationType = this.order.get(0);
        sp_BusinessArea.setAdapter(new SearchSpinnerAdapter(circle, this));

        sp_RecreationType.setAdapter(new SearchSpinnerAdapter(classify, this));

        sp_RankType.setAdapter(new SearchSpinnerAdapter(this.order, this));

        setListener();

        myBinder.requestStoreDisplayInfo(businessArea, rankType, recreationType, num + "");

    }

    private int CURRENT_PAGE_NUM = 0;

    private void requestSearchItem(boolean isRefresh) {
        String page = "0";
        if (!isRefresh) {
            CURRENT_PAGE_NUM++;
            page = String.valueOf(CURRENT_PAGE_NUM);
        }
        myBinder.requestStoreDisplayInfo(recreationType, businessArea, rankType, page);

    }


}
