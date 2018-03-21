package com.example.weathermap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.weathermap.R;
import com.example.weathermap.adapters.MonthlyAdapter;
import com.example.weathermap.models.TempData;
import com.example.weathermap.utils.Constants;

public class MonthlyWeatherActivity extends BaseActivity {

    private RecyclerView rvTemps;
    private MonthlyAdapter adapter;
    private TempData tempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_weather);

        init();
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

    protected void init() {
        super.init(true);
        Intent intent = getIntent();
        String unit = intent.getStringExtra(Constants.UNIT);
        tempData = intent.getParcelableExtra(Constants.DATA);

        setToolbarTitle("For the Year " + tempData.getYear());
        rvTemps = (RecyclerView) findViewById(R.id.activity_monthly_rv_temp);
        rvTemps.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTemps.setLayoutManager(layoutManager);

        adapter = new MonthlyAdapter(this, tempData, unit);
        rvTemps.setAdapter(adapter);
    }
}
