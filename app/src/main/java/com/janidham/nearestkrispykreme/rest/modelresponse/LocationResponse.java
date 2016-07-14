package com.janidham.nearestkrispykreme.rest.modelresponse;

import com.google.gson.annotations.SerializedName;
import com.janidham.nearestkrispykreme.model.Location;

/**
 * Created by janidham on 7/13/16.
 */
public class LocationResponse {

    @SerializedName("Location")
    Location location;

    @SerializedName("Distance")
    float distance;

    public LocationResponse(Location location, float distance) {
        this.location = location;
        this.distance = distance;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
