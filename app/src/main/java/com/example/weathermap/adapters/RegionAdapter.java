package com.example.weathermap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weathermap.R;
import com.example.weathermap.models.RegionData;

import java.util.ArrayList;
import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionViewHolder> {

    private Context context;
    private List<RegionData> regionDataList = new ArrayList<>();
    private OnRegionItemClickListener clickListener;

    public RegionAdapter(Context context, OnRegionItemClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setRegionDataList(List<RegionData> regionDataList) {
        this.regionDataList = regionDataList;
        notifyDataSetChanged();
    }

    @Override
    public RegionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_region, parent, false);
        RegionViewHolder regionViewHolder = new RegionViewHolder(view, clickListener, regionDataList);
        return regionViewHolder;
    }

    @Override
    public void onBindViewHolder(RegionViewHolder holder, int position) {
        holder.tvRegionName.setText(regionDataList.get(position).getRegionName());
    }

    @Override
    public int getItemCount() {
        return regionDataList.size();
    }

    public static class RegionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvRegionName;
        private OnRegionItemClickListener clickListener;
        private List<RegionData> regionDataList = new ArrayList<>();

        public RegionViewHolder(View itemView, OnRegionItemClickListener clickListener, List<RegionData> regionDataList) {
            super(itemView);
            this.clickListener = clickListener;
            this.regionDataList= regionDataList;
            tvRegionName = (TextView) itemView.findViewById(R.id.item_region_tv_region_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(regionDataList.get(getAdapterPosition()));
        }
    }

    public interface OnRegionItemClickListener {
        public void onItemClick(RegionData regionData);
    }
}
