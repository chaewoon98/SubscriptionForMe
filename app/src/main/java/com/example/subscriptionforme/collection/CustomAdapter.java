package com.example.subscriptionforme.collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
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
            //convertView = layoutInflater.inflate(R.layout.expandation_group, null);
            convertView = layoutInflater.inflate(R.layout.expandation_group,viewGroup,false);
        }

        TextView textView = convertView.findViewById(R.id.list_parent);
        textView.setText(group);

        ImageView imageView = convertView.findViewById(R.id.icon_list_parent);
        switch (groupPosition){
            case 0:
                imageView.setImageResource(R.drawable.restuarant);
                break;

            case 1:
                imageView.setImageResource(R.drawable.culture);
                break;

            case 2:
                imageView.setImageResource(R.drawable.coffee);
                break;

            case 3:
                imageView.setImageResource(R.drawable.delivery);
                break;

            case 4:
                imageView.setImageResource(R.drawable.clothes);
                break;

            case 5:
                imageView.setImageResource(R.drawable.living);
                break;

            case 6:
                imageView.setImageResource(R.drawable.the_rest);
                break;
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPostion, int childPostion, boolean isLastChild, View convertView, ViewGroup parent) {

        String child = (String) getChild(groupPostion, childPostion);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandation_child,null);
        }

        TextView textView = convertView.findViewById(R.id.name_list_child);
        TextView descriptionTextView = convertView.findViewById(R.id.description_list_child);
        ImageView logoImageView = convertView.findViewById(R.id.image_list_child);
        textView.setText(child);
        descriptionTextView.setText(getDescription(child,logoImageView));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) { return true; }

    //개 하드코딩 메소드입니다^ㅡ^ 추후 수정하죠
    public String getDescription(String name,ImageView logoImageView){
        switch (name){

            case "버거킹":
                logoImageView.setImageResource(R.drawable.ic_burgerking);
                return "저렴한 가격으로 만나는 버거킹 구독 서비스";

            case "GS25":
                logoImageView.setImageResource(R.drawable.ic_gs25);
                return "GS 25만의 든든하고 편리한 구독 서비스";

            case "밀리의 서재":
                logoImageView.setImageResource(R.drawable.logo_milli);
                return "시간을 가치있게 채워 주는 현명한 서재";

            case "Youtube Premium":
                logoImageView.setImageResource(R.drawable.logo_yotube);
                return "Preminum 으로 누리는 편리한 Youtube";

            case "넷플릭스":
                logoImageView.setImageResource(R.drawable.nexflix_logo);
                return "영화, TV 프로그램을 무제한으로!";

            case "멜론":
                logoImageView.setImageResource(R.drawable.melon_logo);
                return "No.1 뮤직플랫폼 멜론!";

            case "지니":
                logoImageView.setImageResource(R.drawable.logo_geni);
                return "가격은 가볍게, 추천은 확실하게! 프리미어 사운드!";

            case "뚜레쥬르":
                logoImageView.setImageResource(R.drawable.touslesjours_logo);
                return "뚜레쥬르가 드리는 최고의 혜택";

            case "커피 리브레":
                logoImageView.setImageResource(R.drawable.logo_coffee_libre);
                return "고급 커피의 품격";

            case "커피 플리즈":
                logoImageView.setImageResource(R.drawable.ic_coffeeplease);
                return "매주 받아보는 즐거움, 커피 구독 서비스";

            case "리얼 플루트":
                logoImageView.setImageResource(R.drawable.logo_real_fruit);
                return "맛있고 신선한 제철과일 구독 서비스";

            case "STREAMINGWEAR":
                logoImageView.setImageResource(R.drawable.logo_streaming_wear);
                return "라이프 스타일에 맞는 의류 구독 서비스";

            case "TRENDY":
                logoImageView.setImageResource(R.drawable.logo_trendy);
                return "매주 당신의 집 앞으로 배달되는 새로운 옷";

            case "LION CLEANERS":
                logoImageView.setImageResource(R.drawable.logo_lion_cleaner);
                return "최고의 품질, 착한 가격 세탁,수선 전문 서비스";

            case "쿠팡 로켓와우":
                logoImageView.setImageResource(R.drawable.coupang_logo);
                return "쿠팡을 200% 즐기는 최고의 구독 서비스";

            case "네이버 플러스 맴버쉽":
                logoImageView.setImageResource(R.drawable.naver_logo);
                return "네이버 페이 쇼핑할 때마다 다양한 혜택과 서비스";

            case "스마일 클럽":
                logoImageView.setImageResource(R.drawable.smileclub_logo);
                return "어디서나 최고의 혜택을 받는 최고의 쇼핑 멤버쉽";

            case "요기요 슈퍼 클럽":
                logoImageView.setImageResource(R.drawable.yogiyo_logo);
                return "주문할 때마다 누리는 다양한 할인";

            case "티몬 슈퍼세이브":
                logoImageView.setImageResource(R.drawable.tmon_logo);
                return "하루 약 82원으로 누리는 티몬 멤버십";

            default:
                return "";
        }

    }

}