package com.janidham.nearestkrispykreme.rest;

import com.janidham.nearestkrispykreme.model.LocationMX;
import com.janidham.nearestkrispykreme.rest.modelresponse.LocationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by janidham on 7/13/16.
 */
public interface KrispyKremeInterface {

    @GET("?responseType=Full&search=%7B%22Where%22%3A%7B%22LocationTypes%22%3A%5B%22Store%22%2C%22Commissary%22%2C%22Franchise%22%5D%2C%22OpeningDate%22%3A%7B%22ComparisonType%22%3A0%7D%7D%2C%22Take%22%3A%7B%22Min%22%3A3%2C%22DistanceRadius%22%3A100%7D%2C%22PropertyFilters%22%3A%7B%22Attributes%22%3A%5B%22FoursquareVenueId%22%2C%22OpeningType%22%5D%7D%7D&lat=19.3589611&lng=-99.28158710000002")
    Call<List<LocationResponse>> nearestKrispyKreme();

    @FormUrlEncoded
    @POST("centros.php")
    Call<List<LocationMX>> nearestKrispyKremeMX(@Field("type") String centros, @Field("param1") int param);

}
