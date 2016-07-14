package com.janidham.nearestkrispykreme.util;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by janidham on 7/13/16.
 */
public class Config {

    public static final String BASE_URL = "http://services.krispykreme.com/api/locationsearchresult/";


    public static final String BASE_URL_MX = "http://www.krispykreme.mx/services/";


    public static final double ARETELAT = 19.3579779;

    public static final double ARETELNG = -99.280355;

    public static final LatLng ARETEPOSITION = new LatLng(ARETELAT, ARETELNG);

    public static Location ARETELOCATION = new Location("ARETE");

    public static final int COLORLINESMAP = Color.parseColor("#3F51B5");

    public static final int REQUEST_CALL_PHONE = 101;
}
