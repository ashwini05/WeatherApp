package com.example.weathermap.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TempData implements Parcelable{
    int id;
    int year;
    String janMonth;
    String febMonth;
    String marMonth;
    String aprMonth;
    String mayMonth;
    String junMonth;
    String julMonth;
    String augMonth;
    String sepMonth;
    String octMonth;
    String novMonth;
    String decMonth;
    String winter;
    String spring;
    String summer;
    String autumn;
    String annual;
    int regionId;

    public TempData(){};

    protected TempData(Parcel in) {
        id = in.readInt();
        year = in.readInt();
        janMonth = in.readString();
        febMonth = in.readString();
        marMonth = in.readString();
        aprMonth = in.readString();
        mayMonth = in.readString();
        junMonth = in.readString();
        julMonth = in.readString();
        augMonth = in.readString();
        sepMonth = in.readString();
        octMonth = in.readString();
        novMonth = in.readString();
        decMonth = in.readString();
        winter = in.readString();
        spring = in.readString();
        summer = in.readString();
        autumn = in.readString();
        annual = in.readString();
        regionId = in.readInt();
    }

    public static final Creator<TempData> CREATOR = new Creator<TempData>() {
        @Override
        public TempData createFromParcel(Parcel in) {
            return new TempData(in);
        }

        @Override
        public TempData[] newArray(int size) {
            return new TempData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getJanMonth() {
        return janMonth;
    }

    public void setJanMonth(String janMonth) {
        this.janMonth = janMonth;
    }

    public String getFebMonth() {
        return febMonth;
    }

    public void setFebMonth(String febMonth) {
        this.febMonth = febMonth;
    }

    public String getMarMonth() {
        return marMonth;
    }

    public void setMarMonth(String marMonth) {
        this.marMonth = marMonth;
    }

    public String getAprMonth() {
        return aprMonth;
    }

    public void setAprMonth(String aprMonth) {
        this.aprMonth = aprMonth;
    }

    public String getMayMonth() {
        return mayMonth;
    }

    public void setMayMonth(String mayMonth) {
        this.mayMonth = mayMonth;
    }

    public String getJunMonth() {
        return junMonth;
    }

    public void setJunMonth(String junMonth) {
        this.junMonth = junMonth;
    }

    public String getJulMonth() {
        return julMonth;
    }

    public void setJulMonth(String julMonth) {
        this.julMonth = julMonth;
    }

    public String getAugMonth() {
        return augMonth;
    }

    public void setAugMonth(String augMonth) {
        this.augMonth = augMonth;
    }

    public String getSepMonth() {
        return sepMonth;
    }

    public void setSepMonth(String sepMonth) {
        this.sepMonth = sepMonth;
    }

    public String getOctMonth() {
        return octMonth;
    }

    public void setOctMonth(String octMonth) {
        this.octMonth = octMonth;
    }

    public String getNovMonth() {
        return novMonth;
    }

    public void setNovMonth(String novMonth) {
        this.novMonth = novMonth;
    }

    public String getDecMonth() {
        return decMonth;
    }

    public void setDecMonth(String decMonth) {
        this.decMonth = decMonth;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getWinter() {
        return winter;
    }

    public void setWinter(String winter) {
        this.winter = winter;
    }

    public String getSpring() {
        return spring;
    }

    public void setSpring(String spring) {
        this.spring = spring;
    }

    public String getSummer() {
        return summer;
    }

    public void setSummer(String summer) {
        this.summer = summer;
    }

    public String getAutumn() {
        return autumn;
    }

    public void setAutumn(String autumn) {
        this.autumn = autumn;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(year);
        dest.writeString(janMonth);
        dest.writeString(febMonth);
        dest.writeString(marMonth);
        dest.writeString(aprMonth);
        dest.writeString(mayMonth);
        dest.writeString(junMonth);
        dest.writeString(julMonth);
        dest.writeString(augMonth);
        dest.writeString(sepMonth);
        dest.writeString(octMonth);
        dest.writeString(novMonth);
        dest.writeString(decMonth);
        dest.writeString(winter);
        dest.writeString(spring);
        dest.writeString(summer);
        dest.writeString(autumn);
        dest.writeString(annual);
        dest.writeInt(regionId);
    }
}
