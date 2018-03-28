package com.lucky.sweet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.SearchFriendInfo;

import java.util.List;

public class DetaileSearchFriendAdapter extends BaseAdapter {
    private List<SearchFriendInfo.NicknameListBean> listBean;
    private Context context;
    private DetaileSearchFriendAdapter.OnInvitationClick onInvitationClick;

    public DetaileSearchFriendAdapter(List<SearchFriendInfo.NicknameListBean> listBean, Context context) {
        this.listBean = listBean;
        this.context = context;
    }

    @Override

    public int getCount() {
        return listBean.size();
    }

    @Override
    public Object getItem(int position) {
        return listBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        SearchFriendInfo.NicknameListBean nicknameListBean = listBean.get(position);
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_flow_friend, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_flow_friend);
            viewHolder.invitation=convertView.findViewById(R.id.btn_in_frinde);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(nicknameListBean.getNickname());

        viewHolder.invitation.setOnClickListener(v -> {
            if (onInvitationClick != null) {
                onInvitationClick.onClick(nicknameListBean.getUser());
            }
        });
        return convertView;
    }

    public interface OnInvitationClick {
        void onClick(String userId);
    }


    public void setOnInvitationClick(OnInvitationClick onInvitationClick) {
        this.onInvitationClick = onInvitationClick;
    }

    static public class ViewHolder {
        TextView name;
        ImageView invitation;

    }

}
