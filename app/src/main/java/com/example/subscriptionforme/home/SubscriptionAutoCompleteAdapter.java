package com.example.subscriptionforme.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.SubscriptionModelData;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionAutoCompleteAdapter extends ArrayAdapter<SubscriptionModelData> {

    public ArrayList<SubscriptionModelData> subscriptionModelDataList;
    private SoundSearcher soundSearcher;

    public SubscriptionAutoCompleteAdapter(@NonNull Context context,ArrayList<SubscriptionModelData> subscriptionModelDataList){
        super(context,0,subscriptionModelDataList);
        this.subscriptionModelDataList = new ArrayList<>(subscriptionModelDataList);
        soundSearcher = new SoundSearcher();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_add_subscrption_activity, parent, false);

        TextView nameTextView = convertView.findViewById(R.id.name_subscription_activity);
        TextView paymentSystemTextView = convertView.findViewById(R.id.payment_system_subscription_activity);
        ImageView logoImageView = convertView.findViewById(R.id.image_logo_subscription_activity);

        SubscriptionModelData currentSubscriptionModelData = getItem(position);

        if(currentSubscriptionModelData != null){
            nameTextView.setText(currentSubscriptionModelData.getName());
            paymentSystemTextView.setText(currentSubscriptionModelData.getPaymentSystem());
            logoImageView.setImageResource(currentSubscriptionModelData.getImageID());
        }

        return convertView;
    }

    //---------아래로 자동 완성 찾아주는 코드

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            ArrayList<SubscriptionModelData> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(subscriptionModelDataList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (SubscriptionModelData item : subscriptionModelDataList) {
                    if (item.getName().toLowerCase().contains(filterPattern) || soundSearcher.matchString(item.getName().toLowerCase(),filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((SubscriptionModelData) resultValue).getName();
        }
    };
}
