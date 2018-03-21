package com.example.weathermap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapData {
    @SerializedName("farms")
    @Expose
    private List<Farm> farms = null;

    @SerializedName("fields")
    @Expose
    private List<Fields> fields = null;

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public class Farm {

        @SerializedName("geometry")
        @Expose
        private Geometry geometry;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("properties")
        @Expose
        private FarmProperties properties;

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public FarmProperties getProperties() {
            return properties;
        }

        public void setProperties(FarmProperties properties) {
            this.properties = properties;
        }
    }

    public class Fields {

        @SerializedName("geometry")
        @Expose
        private FieldGeometry geometry;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("properties")
        @Expose
        private FarmProperties properties;

        public FieldGeometry getGeometry() {
            return geometry;
        }

        public void setGeometry(FieldGeometry geometry) {
            this.geometry = geometry;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public FarmProperties getProperties() {
            return properties;
        }

        public void setProperties(FarmProperties properties) {
            this.properties = properties;
        }
    }
}

