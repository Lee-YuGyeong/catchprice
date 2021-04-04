package com.market.catchprice.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutionResponse {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("text_upper")
    @Expose
    private String text_upper;

    @SerializedName("text_lower")
    @Expose
    private String text_lower;

    public AutionResponse(String title, String time, String text_upper, String text_lower) {
        this.title = title;
        this.time = time;
        this.text_upper = text_upper;
        this.text_lower = text_lower;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText_upper() {
        return text_upper;
    }

    public void setText_upper(String text_upper) {
        this.text_upper = text_upper;
    }

    public String getText_lower() {
        return text_lower;
    }

    public void setText_lower(String text_lower) {
        this.text_lower = text_lower;
    }
}
