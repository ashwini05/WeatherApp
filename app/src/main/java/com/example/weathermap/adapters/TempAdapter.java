package com.example.weathermap.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weathermap.R;
import com.example.weathermap.activities.MonthlyWeatherActivity;
import com.example.weathermap.models.TempData;
import com.example.weathermap.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class TempAdapter extends RecyclerView.Adapter<TempAdapter.TempViewHolder> {

    private Context context;
    private List<TempData> tempDataList = new ArrayList<>();
    private int tabPos;

    public TempAdapter(Context context, int position) {
        this.context = context;
        this.tabPos = position;
    }

    @Override
    public TempViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_temp, parent, false);
        TempViewHolder tempViewHolder = new TempViewHolder(view);
        return tempViewHolder;
    }

    @Override
    public void onBindViewHolder(TempViewHolder holder, int position) {
        String unit = "";
        switch (tabPos) {
            case 0:
            case 1:
                unit = "\u00B0";
                break;
            case 2:
                unit = "h";
                break;
            case 3:
                unit = "mm";
                break;
        }
        final TempData tempData = tempDataList.get(position);

        holder.tvAnnual.setText(getValue(tempData.getAnnual(), unit));
        holder.tvWinter.setText(getValue(tempData.getWinter(), unit));
        holder.tvSpring.setText(getValue(tempData.getSpring(), unit));
        holder.tvSum.setText(getValue(tempData.getSummer(), unit));
        holder.tvAut.setText(getValue(tempData.getAutumn(), unit));
        holder.tvYear.setText("" + tempData.getYear());

        final String finalUnit = unit;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MonthlyWeatherActivity.class);
                intent.putExtra(Constants.UNIT, finalUnit);
                intent.putExtra(Constants.DATA,tempData);
                context.startActivity(intent);
            }
        });
    }

    public SpannableString getValue(String text, String unit) {
        if (text == null || text.equalsIgnoreCase("---"))
            return SpannableString.valueOf("N/A");
        else {
            SpannableString ss1 = new SpannableString(unit);
            ss1.setSpan(new RelativeSizeSpan(0.5f), 0, unit.length(), 0);
            return SpannableString.valueOf(text + ss1);
        }
    }

    public void setTempList(List<TempData> tempDataList) {
        this.tempDataList = tempDataList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tempDataList.size();
    }

    public static class TempViewHolder extends RecyclerView.ViewHolder {
        public TextView tvWinter;
        public TextView tvSpring;
        public TextView tvSum;
        public TextView tvAut;
        public TextView tvAnnual;
        public TextView tvYear;

        public TempViewHolder(View itemView) {
            super(itemView);
            tvWinter = (TextView) itemView.findViewById(R.id.item_temp_tv_winter);
            tvSpring = (TextView) itemView.findViewById(R.id.item_temp_tv_spring);
            tvSum = (TextView) itemView.findViewById(R.id.item_temp_tv_sum);
            tvAut = (TextView) itemView.findViewById(R.id.item_temp_tv_aut);
            tvYear = (TextView) itemView.findViewById(R.id.item_temp_tv_year);
            tvAnnual = (TextView) itemView.findViewById(R.id.item_temp_tv_annual);
        }

    }

}
