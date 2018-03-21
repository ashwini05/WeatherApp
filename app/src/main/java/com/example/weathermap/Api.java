package com.example.weathermap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.weathermap.database.WeatherContract.MaxTemp;
import com.example.weathermap.database.WeatherContract.MinTemp;
import com.example.weathermap.database.WeatherContract.Rainfall;
import com.example.weathermap.database.WeatherContract.Sunshine;
import com.example.weathermap.database.WeatherDbHelper;
import com.example.weathermap.interfaces.WeatherListener;
import com.example.weathermap.models.TempData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Api extends AsyncTask<Object, Void, Void> {
    private static final String TAG = "Api";
    private Context context;
    private WeatherListener weatherListener;
    private WeatherDbHelper dbHelper;
    private ProgressDialog dialog;

    public Api(Context context, WeatherListener weatherListener) {
        this.context = context;
        this.weatherListener = weatherListener;
        dialog = new ProgressDialog(context);
        dbHelper = new WeatherDbHelper(context, null, null, 0);
        dbHelper.open();
    }

    public void fetchData(String strUrl, long rowId, String tableName) {
        try {
            URL url = new URL(strUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            List<TempData> tempDataList = new ArrayList<>();
            int count = 0;
            while ((str = in.readLine()) != null) {
                count++;
                if (str.isEmpty())
                    continue;

                if (count > 8) {
                    str = str.replaceAll("\\s+", " ");
                    Log.i(TAG, "str:" + str);
                    String[] splitStr = str.split(" ");
                    TempData tempData = new TempData();
                    for (int i = 0; i < splitStr.length; i++) {
                        switch (i) {
                            case 0:
                                tempData.setYear(Integer.parseInt(splitStr[i]));
                                break;
                            case 1:
                                tempData.setJanMonth(splitStr[i]);
                                break;
                            case 2:
                                tempData.setFebMonth(splitStr[i]);
                                break;
                            case 3:
                                tempData.setMarMonth(splitStr[i]);
                                break;
                            case 4:
                                tempData.setAprMonth(splitStr[i]);
                                break;
                            case 5:
                                tempData.setMayMonth(splitStr[i]);
                                break;
                            case 6:
                                tempData.setJunMonth(splitStr[i]);
                                break;
                            case 7:
                                tempData.setJulMonth(splitStr[i]);
                                break;
                            case 8:
                                tempData.setAugMonth(splitStr[i]);
                                break;
                            case 9:
                                tempData.setSepMonth(splitStr[i]);
                                break;
                            case 10:
                                tempData.setOctMonth(splitStr[i]);
                                break;
                            case 11:
                                tempData.setNovMonth(splitStr[i]);
                                break;
                            case 12:
                                tempData.setDecMonth(splitStr[i]);
                                break;
                            case 13:
                                tempData.setWinter(splitStr[i]);
                                break;
                            case 14:
                                tempData.setSpring(splitStr[i]);
                                break;
                            case 15:
                                tempData.setSummer(splitStr[i]);
                                break;
                            case 16:
                                tempData.setAutumn(splitStr[i]);
                                break;
                            case 17:
                                tempData.setAnnual(splitStr[i]);
                                break;
                        }
                    }
                    tempData.setRegionId((int) rowId);
                    tempDataList.add(tempData);
                    dbHelper.insertTempData(tableName, tempData);
                }
            }
            Log.i(TAG, "" + str);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage(context.getString(R.string.downloading_data_message));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected Void doInBackground(Object... params) {
        final String[] regions = (String[]) params[0];
        final String[] ukUrls = (String[]) params[1];
        final String[] englandUrls = (String[]) params[2];
        final String[] walesUrls = (String[]) params[3];
        final String[] scotlandUrls = (String[]) params[4];
        final String[] tableNames = new String[]{MaxTemp.TABLE_NAME, MinTemp.TABLE_NAME, Sunshine.TABLE_NAME, Rainfall.TABLE_NAME};

        //First delete all rows- to fetch new data everytime
        dbHelper.deleteAll(new WeatherListener() {
            @Override
            public void onDownloadFinish() {

            }

            @Override
            public void onDeleteComplete() {
                //insert new downloaded data from server
                for (String region : regions) {
                    String[] urls = new String[0];
                    long rowId = dbHelper.insertRegionData(region);
                    switch ((int) rowId) {
                        case 1:
                            urls = ukUrls;
                            break;
                        case 2:
                            urls = englandUrls;
                            break;
                        case 3:
                            urls = walesUrls;
                            break;
                        case 4:
                            urls = scotlandUrls;
                            break;
                    }
                    for (int i = 0; i < urls.length; i++) {
                        fetchData(urls[i], rowId, tableNames[i]);
                    }
                }
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (dialog.isShowing())
            dialog.dismiss();
        dbHelper.close();
        weatherListener.onDownloadFinish();
    }
}
