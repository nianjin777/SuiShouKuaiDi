package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.activity.home.fragment.AcceptHallFragment;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.model.BeanThing;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by wssb on 2017/7/21.
 */

public class NearTaskAdapter extends BaseAdapter {
    Context context;
    List<BeanOrder> list = null;

    public NearTaskAdapter(Context context, List<BeanOrder> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_neartask, null);
        } else {
            view = convertView;
        }
        TextView taskNmae = (TextView) view.findViewById(R.id.neartask_tv_task);
        TextView taskDis = (TextView) view.findViewById(R.id.neartask_tv_dis);
        TextView taskText = (TextView) view.findViewById(R.id.neartask_tv_text);
        TextView taskMoney = (TextView) view.findViewById(R.id.neartask_tv_money);

        BeanOrder beanOrder = list.get(position);
        List<BeanThing> thinglist = (List<BeanThing>) beanOrder.getThingList();
//        LatLng thingXY = new LatLng(thinglist.get(0).getLatitude(), thinglist.get(0).getLongitude());
//        LatLng userXY = AcceptHallFragment.xy;
        taskNmae.setText((String) beanOrder.getOrderName());
//        taskDis.setText((String) map.get("distance"));//距离
//        int distance = (int)DistanceUtil.getDistance(userXY, thingXY);
        double distance = beanOrder.getDistence();

        taskDis.setText(String.valueOf(distance) + " km");
        taskText.setText((String) beanOrder.getOrderText());
        taskMoney.setText(String.valueOf(beanOrder.getBounty()));

        return view;
    }
}
