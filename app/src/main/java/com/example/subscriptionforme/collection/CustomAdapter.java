package com.example.subscriptionforme.collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.subscriptionforme.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter {

    //private ArrayList<GroupData> groupDatas;
    //private ArrayList<ArrayList<ChildData>> childData;
    Context context;
    private LayoutInflater inflater;
    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;

    public CustomAdapter(Context context, List<String> listGroup, HashMap<String, List<String>> listItem){
        this.context = context;
        this.listGroup = listGroup;
        this.listItem = listItem;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listItem.get(this.listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listItem.get(this.listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPostion, int childPostition) {
        return childPostition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
        String group = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandation_group, null);
        }

        TextView textView = convertView.findViewById(R.id.list_parent);
        textView.setText(group);
        return convertView;
    }

    @Override
    public View getChildView(int groupPostion, int childPostion, boolean isLastChild, View convertView, ViewGroup parent) {

        String child = (String) getChild(groupPostion, childPostion);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandation_child,null);
        }

        TextView textView = convertView.findViewById(R.id.list_child);
        textView.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) { return true; }

}