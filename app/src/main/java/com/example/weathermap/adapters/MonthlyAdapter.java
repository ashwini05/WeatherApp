package com.example.weathermap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weathermap.R;
import com.example.weathermap.models.TempData;

public class MonthlyAdapter extends RecyclerView.Adapter<MonthlyAdapter.MonthlyViewHolder> {

    private Context context;
    private TempData tempData = new TempData();
    private String[] monthTitles = new String[]{
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"};
    private String unit;

    public MonthlyAdapter(Context context, TempData tempData, String unit) {
        this.context = context;
        this.tempData = tempData;
        this.unit = unit;
    }

    @Override
    public MonthlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_monthwise, parent, false);
        MonthlyViewHolder regionViewHolder = new MonthlyViewHolder(view);
        return regionViewHolder;
    }

    @Override
    public void onBindViewHolder(MonthlyViewHolder holder, int position) {
        holder.tvTitle.setText(monthTitles[position]);
        String value = "";
        switch (position) {
            case 0:
                value = tempData.getJanMonth();
                break;
            case 1:
                value = tempData.getFebMonth();
                break;
            case 2:
                value = tempData.getMarMonth();
                break;
            case 3:
                value = tempData.getAprMonth();
                break;
            case 4:
                value = tempData.getMayMonth();
                break;
            case 5:
                value = tempData.getJunMonth();
                break;
            case 6:
                value = tempData.getJulMonth();
                break;
            case 7:
                value = tempData.getAugMonth();
                break;
            case 8:
                value = tempData.getSepMonth();
                break;
            case 9:
                value = tempData.getOctMonth();
                break;
            case 10:
                value = tempData.getNovMonth();
                break;
            case 11:
                value = tempData.getDecMonth();
                break;
        }
        holder.tvValue.setText(value + unit);
    }

    @Override
    public int getItemCount() {
        return monthTitles.length;
    }

    public static class MonthlyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvValue;

        public MonthlyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.item_monthwise_tv_title);
            tvValue = (TextView) itemView.findViewById(R.id.item_monthwise_tv_value);
        }
    }
}

