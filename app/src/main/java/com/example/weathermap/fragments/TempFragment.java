package com.example.weathermap.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weathermap.R;
import com.example.weathermap.adapters.TempAdapter;
import com.example.weathermap.database.WeatherContract;
import com.example.weathermap.database.WeatherDbHelper;
import com.example.weathermap.models.RegionData;
import com.example.weathermap.models.TempData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TempFragment extends Fragment {

    private static final String POSITION = "position";
    private int position;
    private RecyclerView rvTemps;
    private TempAdapter adapter;
    private String regionName;

    public TempFragment() {
        // Required empty public constructor
    }

    public static TempFragment newInstance(int pos, String regionName) {
        TempFragment tempFragment = new TempFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, pos);
        bundle.putString("region_name", regionName);
        tempFragment.setArguments(bundle);
        return tempFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments() != null ? getArguments().getInt(POSITION) : 0;
        regionName = getArguments() != null ? getArguments().getString("region_name") : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        init(view);
        initParams();
        return view;
    }

    private void initParams() {
        WeatherDbHelper dbHelper = new WeatherDbHelper(getActivity(), null, null, 0);
        dbHelper.open();
        List<TempData> tempDataList= new ArrayList<>();
        switch (position) {
            case 0:
                tempDataList = dbHelper.getTempData(WeatherContract.MaxTemp.TABLE_NAME,regionName);
                break;
            case 1:
                tempDataList = dbHelper.getTempData(WeatherContract.MinTemp.TABLE_NAME,regionName);
                break;
            case 2:
                tempDataList = dbHelper.getTempData(WeatherContract.Sunshine.TABLE_NAME,regionName);
                break;
            case 3:
                tempDataList = dbHelper.getTempData(WeatherContract.Rainfall.TABLE_NAME,regionName);
                break;

        }
        adapter.setTempList(tempDataList);
    }

    private void init(View view) {
        rvTemps = (RecyclerView) view.findViewById(R.id.frament_temp_rv_temp);
        rvTemps.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvTemps.setLayoutManager(layoutManager);
        adapter = new TempAdapter(getActivity(),position);
        rvTemps.setAdapter(adapter);
    }
}
