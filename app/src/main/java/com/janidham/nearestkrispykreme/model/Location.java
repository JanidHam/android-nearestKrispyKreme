package com.janidham.nearestkrispykreme.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by janidham on 7/13/16.
 */
public class Location {


    @SerializedName("id")
    int id;

    @SerializedName("LocationNumber")
    int locationNumber;

    @SerializedName("Name")
    String name;

    @SerializedName("DetailUrl")
    String url;

    @SerializedName("LocationType")
    String locationType;

    @SerializedName("Address1")
    String address;

    @SerializedName("Latitude")
    float latitude;

    @SerializedName("Longitude")
    float longitude;

    public Location(int id, int locationNumber, String name, String url, String locationType, String address, float latitude, float longitude) {
        this.id = id;
        this.locationNumber = locationNumber;
        this.name = name;
        this.url = url;
        this.locationType = locationType;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public LatLng getPosition() {
        return new LatLng(this.latitude, this.longitude);
    }
}
