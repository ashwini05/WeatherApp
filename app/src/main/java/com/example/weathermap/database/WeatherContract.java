package com.example.weathermap.database;

import android.provider.BaseColumns;

public final class WeatherContract {

    private WeatherContract() {
    }

    public static class Region implements BaseColumns {
        public static final String TABLE_NAME = "region";
        public static final String COLUMN_REGION_NAME = "region_name";
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_REGION_NAME + " TEXT)";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class MaxTemp implements BaseColumns, Temp {
        public static final String TABLE_NAME = "maxtemp";
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_YEAR + " INTEGER," +
                        COLUMN_JAN + " TEXT," +
                        COLUMN_FEB + " TEXT," +
                        COLUMN_MAR + " TEXT," +
                        COLUMN_APR + " TEXT," +
                        COLUMN_MAY + " TEXT," +
                        COLUMN_JUN + " TEXT," +
                        COLUMN_JUL + " TEXT," +
                        COLUMN_AUG + " TEXT," +
                        COLUMN_SEP + " TEXT," +
                        COLUMN_OCT + " TEXT," +
                        COLUMN_NOV + " TEXT," +
                        COLUMN_DEC + " TEXT," +
                        COLUMN_WINTER+ " TEXT," +
                        COLUMN_SPRING+ " TEXT," +
                        COLUMN_SUMMER+ " TEXT," +
                        COLUMN_AUTUMN+ " TEXT," +
                        COLUMN_ANNUAL+ " TEXT," +
                        COLUMN_REGION_ID + " INTEGER," +
                        " FOREIGN KEY(" + COLUMN_REGION_ID + ") REFERENCES " + Region.TABLE_NAME + "(" + _ID + ")" +
                        ")";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class MinTemp implements BaseColumns, Temp {
        public static final String TABLE_NAME = "mintemp";
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_YEAR + " INTEGER," +
                        COLUMN_JAN + " TEXT," +
                        COLUMN_FEB + " TEXT," +
                        COLUMN_MAR + " TEXT," +
                        COLUMN_APR + " TEXT," +
                        COLUMN_MAY + " TEXT," +
                        COLUMN_JUN + " TEXT," +
                        COLUMN_JUL + " TEXT," +
                        COLUMN_AUG + " TEXT," +
                        COLUMN_SEP + " TEXT," +
                        COLUMN_OCT + " TEXT," +
                        COLUMN_NOV + " TEXT," +
                        COLUMN_DEC + " TEXT," +
                        COLUMN_WINTER+ " TEXT," +
                        COLUMN_SPRING+ " TEXT," +
                        COLUMN_SUMMER+ " TEXT," +
                        COLUMN_AUTUMN+ " TEXT," +
                        COLUMN_ANNUAL+ " TEXT," +
                        COLUMN_REGION_ID + " INTEGER," +
                        " FOREIGN KEY(" + COLUMN_REGION_ID + ") REFERENCES " + Region.TABLE_NAME + "(" + _ID + ")" +
                        ")";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Sunshine implements BaseColumns, Temp {
        public static final String TABLE_NAME = "sunshine";
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_YEAR + " INTEGER," +
                        COLUMN_JAN + " TEXT," +
                        COLUMN_FEB + " TEXT," +
                        COLUMN_MAR + " TEXT," +
                        COLUMN_APR + " TEXT," +
                        COLUMN_MAY + " TEXT," +
                        COLUMN_JUN + " TEXT," +
                        COLUMN_JUL + " TEXT," +
                        COLUMN_AUG + " TEXT," +
                        COLUMN_SEP + " TEXT," +
                        COLUMN_OCT + " TEXT," +
                        COLUMN_NOV + " TEXT," +
                        COLUMN_DEC + " TEXT," +
                        COLUMN_WINTER+ " TEXT," +
                        COLUMN_SPRING+ " TEXT," +
                        COLUMN_SUMMER+ " TEXT," +
                        COLUMN_AUTUMN+ " TEXT," +
                        COLUMN_ANNUAL+ " TEXT," +
                        COLUMN_REGION_ID + " INTEGER," +
                        " FOREIGN KEY(" + COLUMN_REGION_ID + ") REFERENCES " + Region.TABLE_NAME + "(" + _ID + ")" +
                        ")";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Rainfall implements BaseColumns, Temp {
        public static final String TABLE_NAME = "rainfall";
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_YEAR + " INTEGER," +
                        COLUMN_JAN + " TEXT," +
                        COLUMN_FEB + " TEXT," +
                        COLUMN_MAR + " TEXT," +
                        COLUMN_APR + " TEXT," +
                        COLUMN_MAY + " TEXT," +
                        COLUMN_JUN + " TEXT," +
                        COLUMN_JUL + " TEXT," +
                        COLUMN_AUG + " TEXT," +
                        COLUMN_SEP + " TEXT," +
                        COLUMN_OCT + " TEXT," +
                        COLUMN_NOV + " TEXT," +
                        COLUMN_DEC + " TEXT," +
                        COLUMN_WINTER+ " TEXT," +
                        COLUMN_SPRING+ " TEXT," +
                        COLUMN_SUMMER+ " TEXT," +
                        COLUMN_AUTUMN+ " TEXT," +
                        COLUMN_ANNUAL+ " TEXT," +
                        COLUMN_REGION_ID + " INTEGER," +
                        " FOREIGN KEY(" + COLUMN_REGION_ID + ") REFERENCES " + Region.TABLE_NAME + "(" + _ID + ")" +
                        ")";
        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public interface Temp {
        String COLUMN_REGION_ID = "region_id";
        String COLUMN_YEAR = "year";
        String COLUMN_JAN = "jan";
        String COLUMN_FEB = "feb";
        String COLUMN_MAR = "mar";
        String COLUMN_APR = "apr";
        String COLUMN_MAY = "may";
        String COLUMN_JUN = "jun";
        String COLUMN_JUL = "jul";
        String COLUMN_AUG = "aug";
        String COLUMN_SEP = "sep";
        String COLUMN_OCT = "oct";
        String COLUMN_NOV = "nov";
        String COLUMN_DEC = "dec";
        String COLUMN_WINTER = "winter";
        String COLUMN_SPRING = "spring";
        String COLUMN_SUMMER = "summer";
        String COLUMN_AUTUMN = "autumn";
        String COLUMN_ANNUAL = "annual";
    }
}