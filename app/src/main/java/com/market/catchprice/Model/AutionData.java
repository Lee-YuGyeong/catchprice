package com.market.catchprice.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutionData {

    @SerializedName("lng")
    @Expose
    private int lng;

    @SerializedName("lat")
    @Expose
    private int lat;

    public AutionData(int lng, int lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }
}
