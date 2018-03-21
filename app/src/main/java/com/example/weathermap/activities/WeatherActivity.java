package com.example.weathermap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weathermap.Api;
import com.example.weathermap.R;
import com.example.weathermap.adapters.RegionAdapter;
import com.example.weathermap.database.WeatherDbHelper;
import com.example.weathermap.interfaces.WeatherListener;
import com.example.weathermap.models.RegionData;
import com.example.weathermap.utils.Constants;
import com.example.weathermap.utils.NetworkUtil;

import java.util.List;

public class WeatherActivity extends BaseActivity implements WeatherListener, RegionAdapter.OnRegionItemClickListener {

    private RecyclerView rvRegions;
    private RegionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        init();
    }

    protected void init() {
        super.init(true);
        setToolbarTitle(getString(R.string.regions));
        rvRegions = (RecyclerView) findViewById(R.id.activity_weather_rv_regions);
        rvRegions.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvRegions.setLayoutManager(layoutManager);
        rvRegions.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvRegions.setItemAnimator(new DefaultItemAnimator());
        adapter = new RegionAdapter(this, this);
        rvRegions.setAdapter(adapter);

        if (!NetworkUtil.isNetworkAvailable(this)) {
            Snackbar.make(getWindow().getDecorView().getRootView(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
            return;
        }

        Api api = new Api(this, this);
        String[] ukUrls = getResources().getStringArray(R.array.uk_urls);
        String[] englandUrls = getResources().getStringArray(R.array.england_urls);
        String[] walesUrls = getResources().getStringArray(R.array.wales_urls);
        String[] scotlandUrls = getResources().getStringArray(R.array.scotland_urls);
        String[] regions = getResources().getStringArray(R.array.regions);
        api.execute(regions, ukUrls, englandUrls, walesUrls, scotlandUrls);
    }

    @Override
    public void onDownloadFinish() {
        WeatherDbHelper dbHelper = new WeatherDbHelper(this, null, null, 0);
        dbHelper.open();
        List<RegionData> regionDataList = dbHelper.getRegionData();
        adapter.setRegionDataList(regionDataList);
    }

    @Override
    public void onDeleteComplete() {

    }

    @Override
    public void onItemClick(RegionData regionData) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.REGION_NAME, regionData.getRegionName());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
