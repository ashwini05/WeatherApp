package com.example.weathermap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.weathermap.database.WeatherContract.MaxTemp;
import com.example.weathermap.database.WeatherContract.MinTemp;
import com.example.weathermap.database.WeatherContract.Rainfall;
import com.example.weathermap.database.WeatherContract.Region;
import com.example.weathermap.database.WeatherContract.Sunshine;
import com.example.weathermap.database.WeatherContract.Temp;
import com.example.weathermap.interfaces.WeatherListener;
import com.example.weathermap.models.RegionData;
import com.example.weathermap.models.TempData;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;

public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weatherapp.db";
    private SQLiteDatabase db;

    public WeatherDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Region.CREATE_TABLE);
        db.execSQL(MaxTemp.CREATE_TABLE);
        db.execSQL(MinTemp.CREATE_TABLE);
        db.execSQL(Sunshine.CREATE_TABLE);
        db.execSQL(Rainfall.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Region.DELETE_TABLE);
        db.execSQL(MaxTemp.DELETE_TABLE);
        db.execSQL(MinTemp.DELETE_TABLE);
        db.execSQL(Sunshine.DELETE_TABLE);
        db.execSQL(Rainfall.DELETE_TABLE);
    }

    public long insertRegionData(String regionName) {
        String sql = "SELECT * FROM " + Region.TABLE_NAME + " WHERE " + Region.COLUMN_REGION_NAME + " =?";
        Cursor cursor = db.rawQuery(sql, new String[]{regionName});
        if (cursor.moveToFirst())
            return -1;
        ContentValues values = new ContentValues();
        values.put(Region.COLUMN_REGION_NAME, regionName);
        long newRowId = db.insert(Region.TABLE_NAME, null, values);
        return newRowId;
    }

    public long insertTempData(String tableName, TempData tempData) {
        ContentValues values = new ContentValues();
        values.put(Temp.COLUMN_YEAR, tempData.getYear());
        values.put(Temp.COLUMN_JAN, tempData.getJanMonth());
        values.put(Temp.COLUMN_FEB, tempData.getFebMonth());
        values.put(Temp.COLUMN_MAR, tempData.getMarMonth());
        values.put(Temp.COLUMN_APR, tempData.getAprMonth());
        values.put(Temp.COLUMN_MAY, tempData.getMayMonth());
        values.put(Temp.COLUMN_JUN, tempData.getJunMonth());
        values.put(Temp.COLUMN_JUL, tempData.getJulMonth());
        values.put(Temp.COLUMN_AUG, tempData.getAugMonth());
        values.put(Temp.COLUMN_SEP, tempData.getSepMonth());
        values.put(Temp.COLUMN_OCT, tempData.getOctMonth());
        values.put(Temp.COLUMN_NOV, tempData.getNovMonth());
        values.put(Temp.COLUMN_DEC, tempData.getDecMonth());
        values.put(Temp.COLUMN_WINTER, tempData.getWinter());
        values.put(Temp.COLUMN_SPRING, tempData.getSpring());
        values.put(Temp.COLUMN_SUMMER, tempData.getSummer());
        values.put(Temp.COLUMN_AUTUMN, tempData.getAutumn());
        values.put(Temp.COLUMN_ANNUAL, tempData.getAnnual());
        values.put(Temp.COLUMN_REGION_ID, tempData.getRegionId());
        long newRowId = db.insert(tableName, null, values);
        return newRowId;
    }

    public List<RegionData> getRegionData() {
        List<RegionData> regionDataList = new ArrayList<>();
        String sql = "SELECT * FROM " + Region.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            RegionData regionData = new RegionData();
            regionData.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
            regionData.setRegionName(cursor.getString(cursor.getColumnIndexOrThrow(Region.COLUMN_REGION_NAME)));
            regionDataList.add(regionData);
        }
        return regionDataList;
    }

    public List<TempData> getTempData(String tableName, String regionName) {
        List<TempData> tempDataList = new ArrayList<>();
        String[] selectionArgs = {regionName};
        String sql = "SELECT mt.* FROM " + Region.TABLE_NAME + " rm join " + tableName + " mt on rm._id = mt.region_id where rm.region_name = ?";
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            TempData tempData = new TempData();
            tempData.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
            tempData.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(Temp.COLUMN_YEAR)));
            tempData.setJanMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_JAN)));
            tempData.setFebMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_FEB)));
            tempData.setMarMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_MAR)));
            tempData.setAprMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_APR)));
            tempData.setMayMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_MAY)));
            tempData.setJunMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_JUN)));
            tempData.setJulMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_JUL)));
            tempData.setAugMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_AUG)));
            tempData.setSepMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_SEP)));
            tempData.setOctMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_OCT)));
            tempData.setNovMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_NOV)));
            tempData.setDecMonth(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_DEC)));
            tempData.setWinter(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_WINTER)));
            tempData.setSpring(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_SPRING)));
            tempData.setSummer(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_SUMMER)));
            tempData.setAutumn(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_AUTUMN)));
            tempData.setRegionId(cursor.getInt(cursor.getColumnIndexOrThrow(Temp.COLUMN_REGION_ID)));
            tempData.setAnnual(cursor.getString(cursor.getColumnIndexOrThrow(Temp.COLUMN_ANNUAL)));
            tempDataList.add(tempData);
        }
        cursor.close();
        return tempDataList;
    }

    public void deleteAll(WeatherListener weatherListener) {
        String[] tableNames = new String[]{MaxTemp.TABLE_NAME, MinTemp.TABLE_NAME, Sunshine.TABLE_NAME, Rainfall.TABLE_NAME, Region.TABLE_NAME};
        for (String tableName : tableNames) {
            db.execSQL("DELETE FROM " + tableName);
        }
        weatherListener.onDeleteComplete();
    }

    public SQLiteDatabase open() {
        db = getWritableDatabase();
        return db;
    }

    public void close() {
        db.close();
    }
}
