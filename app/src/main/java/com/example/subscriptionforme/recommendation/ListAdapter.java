package com.example.subscriptionforme.recommendation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subscriptionforme.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<RecommendationList> list;

    public ListAdapter(Context context, ArrayList<RecommendationList> data){
        this.context = context;
        list = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }
//    public ListAdapter(Context context){
//        this.context = context;
//
//        layoutInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        return 3;
//    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.recommendation_list_view,null);

        TextView title = (TextView)view.findViewById(R.id.title_text);
        title.setText(list.get(position).getTitle());
        title.setTextColor(list.get(position).getColor());

        TextView name = (TextView)view.findViewById(R.id.name_text);
        name.setText(list.get(position).getName());
        name.setTextColor(list.get(position).getColor());

        ImageView icon = (ImageView)view.findViewById(R.id.icon_logo);
        icon.setImageResource(list.get(position).getIcon());

        TextView consumption = (TextView)view.findViewById(R.id.consumption_text);
        consumption.setText(list.get(position).getConsumption());

        TextView discount = (TextView)view.findViewById(R.id.discount_text);
        discount.setText(list.get(position).getDiscount());

        return view;
    }
}
