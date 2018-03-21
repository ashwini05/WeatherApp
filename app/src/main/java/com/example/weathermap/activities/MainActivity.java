package com.example.weathermap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.weathermap.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        Button btnWeather = (Button) findViewById(R.id.activity_main_btn_weather);
        Button btnMap = (Button) findViewById(R.id.activity_main_btn_map);

        btnWeather.setOnClickListener(this);
        btnMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.activity_main_btn_weather:
                intent = new Intent(this, WeatherActivity.class);
                break;

            case R.id.activity_main_btn_map:
                intent = new Intent(this, MapsActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}
