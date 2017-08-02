package com.edu.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.edu.wmhxa.kuaishou.R;
import com.edu.wmhxa.kuaishou.activity.home.fragment.AcceptHallFragment;
import com.edu.wmhxa.kuaishou.model.BeanThing;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */

public class NearTaskAdapter extends BaseAdapter {
    Context context;
    List<Map<String, Object>> list = null;

    public NearTaskAdapter(Context context, List<Map<String, Object>> list) {
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

        Map<String, Object> map = list.get(position);
        List<BeanThing> thinglist = (List<BeanThing>) map.get("thinglist");
        LatLng thingXY = new LatLng(thinglist.get(0).getLatitude(), thinglist.get(0).getLongitude());
        LatLng userXY = AcceptHallFragment.xy;
        taskNmae.setText((String) map.get("ordername"));
//        taskDis.setText((String) map.get("distance"));//距离
//        int distance = (int)DistanceUtil.getDistance(userXY, thingXY);
        double distance = (int) DistanceUtil.getDistance(userXY, thingXY) / 1000.0;
        DecimalFormat df = new DecimalFormat("0.00");

        taskDis.setText(df.format(distance) + " km");
        taskText.setText((String) map.get("ordertext"));
        taskMoney.setText(String.valueOf(map.get("orderbounty")));


        return view;
    }
}
