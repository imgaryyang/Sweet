package com.lucky.sweet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.AlterOrderInfo;

import java.util.List;

/**
 * Created by Qiuyue on 2018/3/2.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class HisOrderAdapter extends BaseAdapter {

    private List<AlterOrderInfo.UnfinishIndentListBean> datas;
    private Context context;

    public HisOrderAdapter(List<AlterOrderInfo.UnfinishIndentListBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    static public class ViewHolder {
        TextView shopName;
        TextView orderAttr;
        TextView time;
        TextView cost;
        TextView data;

        public void updata(AlterOrderInfo.UnfinishIndentListBean info) {
            shopName.setText(info.getMer_name());
            String[] split = info.getCreate_time().split(" ");
            if (split.length==2) {
                time.setText(split[0]);
                data.setText(split[1]);
            }
            cost.setText(info.getMoney() + "");
            String attr;
            switch (info.getType_sign()) {
                case "0":
                    attr = "自动取消订单";
                    break;
                case "1":
                    attr = "客户取消订单";

                    break;
                case "2":
                    attr = "店铺取消订单";
                    break;
                case "3":
                    attr = "已确认等待客户前往";
                    break;
                case "4":
                    attr = "完成订单";
                    break;
                case "5":
                    attr = "等待接单";
                    break;
                default:
                    attr = "等待";
                    break;
            }
            orderAttr.setText(attr);

        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public AlterOrderInfo.UnfinishIndentListBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HisOrderAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hisorder, null);
            viewHolder = new HisOrderAdapter.ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.shopName = convertView.findViewById(R.id.tv_order_alter_shopname);
            viewHolder.time = convertView.findViewById(R.id.tv_order_alter_time);
            viewHolder.orderAttr = convertView.findViewById(R.id.tv_order_alter_order_attr);
            viewHolder.cost = convertView.findViewById(R.id.tv_order_alter_order_cost);
            viewHolder.data = convertView.findViewById(R.id.tv_order_alter_order_data);
        } else {
            viewHolder = (HisOrderAdapter.ViewHolder) convertView.getTag();
        }


        viewHolder.updata(datas.get(position));

        return convertView;
    }
}
