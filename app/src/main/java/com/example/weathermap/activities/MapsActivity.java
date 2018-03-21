package com.example.weathermap.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.weathermap.R;
import com.example.weathermap.models.FarmProperties;
import com.example.weathermap.models.MapData;
import com.example.weathermap.models.MapData.Farm;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String POINT = "Point";
    private static final String POLYGON = "Polygon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            MapData mapData = new Gson().fromJson(jsonArray.getJSONObject(0).toString(), MapData.class);
            List<Farm> farmList = mapData.getFarms();
            for (Farm farm : farmList) {
                if (farm.getGeometry().getType().equalsIgnoreCase(POINT)) {
                    List<Double> coordinatesList = farm.getGeometry().getCoordinates();
                    setMapMarker(googleMap, coordinatesList.get(1), coordinatesList.get(0), farm.getProperties());
                }
            }

            List<MapData.Fields> fieldsList = mapData.getFields();
            for (MapData.Fields fields : fieldsList) {
                if (fields.getGeometry().getType().equalsIgnoreCase(POLYGON)) {
                    List<List<List<Double>>> coordinatesList = fields.getGeometry().getCoordinates();
                    setPolygon(googleMap, coordinatesList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setMapMarker(GoogleMap googleMap, double latitude, double longitude, FarmProperties properties) {
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title(properties.getFarmName() + " in " + properties.getFarmLocation()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void setPolygon(GoogleMap googleMap, List<List<List<Double>>> coordinatesList) {
        List<LatLng> latLngList = new ArrayList<>();
        for (List<List<Double>> lists : coordinatesList) {
            for (List<Double> doubleList : lists) {
                LatLng latLng = new LatLng(doubleList.get(1), doubleList.get(0));
                latLngList.add(latLng);
            }
        }
        Polygon polygon = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .addAll(latLngList));
        polygon.setFillColor(Color.RED);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLngList.get(0).latitude, latLngList.get(0).longitude), 10));
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("geodata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
