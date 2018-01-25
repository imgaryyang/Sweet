package com.lucky.sweet.widgets.dragload;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chn on 2018/1/25.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class RecycleViewRefresh extends mSwipeRefreshLayout {
    public RecycleViewRefresh(Context context) {
        super(context);
    }

    private RecyclerView mRecycleView;
    private mCircleImageView mCircleImageView;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mRecycleView == null && mCircleImageView == null)
            if (getChildCount() > 0) {

                View view = getChildAt(0);
                if (view instanceof RecyclerView) {
                    mRecycleView = (RecyclerView) view;
                    setRecyclerViewOnScroll();

                }
            }

    }


    private Boolean dragFlag = false;

    private void setRecyclerViewOnScroll() {

        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码

                        if (dragFlag) {
                            dragFlag = false;
                            return;
                        }
                        dragFlag = true;
                    }
                }
            }

            boolean isSlidingToLast;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dx > 0 || dy > 0) {
                    //大于0表示，正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0 表示停止或向左滚动
                    isSlidingToLast = false;
                }

            }
        });
        // 当不滚动时

    }

    public RecycleViewRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private OnRefreshListener listener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 移动的起点

                break;
            case MotionEvent.ACTION_MOVE:
                // 移动过程中判断时候能下拉加载更多
                if (dragFlag) {
                    // 加载数据
                    if (listener != null) {
                        listener.onRefresh();
                    }
                    setRefreshing(true);
                }

                break;
            case MotionEvent.ACTION_UP:
                // 移动的终点

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}
