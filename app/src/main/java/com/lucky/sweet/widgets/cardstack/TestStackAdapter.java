package com.lucky.sweet.widgets.cardstack;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucky.sweet.R;

import java.util.ArrayList;

public class TestStackAdapter extends StackAdapter<Integer> {

    private ArrayList arrayList;

    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.bg_vip_card_color_first,
            R.color.bg_vip_card_color_sec,
            R.color.bg_vip_card_color_third,
            R.color.bg_vip_card_color_fourth,
            R.color.bg_vip_card_color_fifth,
    };

    public TestStackAdapter(Context context, ArrayList arrayList) {
        super(context);
        this.arrayList = arrayList;
        updateData(arrayList.size());
    }

    @Override
    public void bindView(int position, CardStackView.ViewHolder holder) {

        ((viewHolder) holder).onBind(TEST_DATAS[position % TEST_DATAS.length],
                position);
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        return new viewHolder(getLayoutInflater().inflate(R
                        .layout.item_card_list_with_no_header,
                parent, false));
    }



    static class viewHolder extends CardStackView.ViewHolder {
        View mLayout;
        View mContainerContent;
        TextView mTextTitle;

        public viewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = view.findViewById(R.id.text_list_card_title);
        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));
        }

    }


}
