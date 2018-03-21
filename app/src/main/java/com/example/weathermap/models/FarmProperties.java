package com.example.weathermap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmProperties {
    @SerializedName("farm_latitude")
    @Expose
    private Double farmLatitude;
    @SerializedName("permission")
    @Expose
    private Boolean permission;
    @SerializedName("farm_longitude")
    @Expose
    private Double farmLongitude;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("farm_slug")
    @Expose
    private String farmSlug;
    @SerializedName("team_id")
    @Expose
    private Integer teamId;
    @SerializedName("farm_location")
    @Expose
    private String farmLocation;
    @SerializedName("ownership")
    @Expose
    private Boolean ownership;
    @SerializedName("location_slug")
    @Expose
    private String locationSlug;

    public Double getFarmLatitude() {
        return farmLatitude;
    }

    public void setFarmLatitude(Double farmLatitude) {
        this.farmLatitude = farmLatitude;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    public Double getFarmLongitude() {
        return farmLongitude;
    }

    public void setFarmLongitude(Double farmLongitude) {
        this.farmLongitude = farmLongitude;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmSlug() {
        return farmSlug;
    }

    public void setFarmSlug(String farmSlug) {
        this.farmSlug = farmSlug;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation;
    }

    public Boolean getOwnership() {
        return ownership;
    }

    public void setOwnership(Boolean ownership) {
        this.ownership = ownership;
    }

    public String getLocationSlug() {
        return locationSlug;
    }

    public void setLocationSlug(String locationSlug) {
        this.locationSlug = locationSlug;
    }

}
