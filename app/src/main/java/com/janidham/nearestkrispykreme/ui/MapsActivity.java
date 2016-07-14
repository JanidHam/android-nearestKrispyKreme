package com.janidham.nearestkrispykreme.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.janidham.nearestkrispykreme.R;
import com.janidham.nearestkrispykreme.model.LocationMX;
import com.janidham.nearestkrispykreme.rest.KrispyKremeInterface;
import com.janidham.nearestkrispykreme.rest.modelresponse.LocationResponse;
import com.janidham.nearestkrispykreme.util.Config;
import com.janidham.nearestkrispykreme.util.GMapV2Direction;
import com.janidham.nearestkrispykreme.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.Manifest.permission.CALL_PHONE;

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private List<LocationMX> krispyLocations;
    private LocationMX nearestKrispy;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        Config.ARETELOCATION.setLatitude(Config.ARETELAT);
        Config.ARETELOCATION.setLongitude(Config.ARETELNG);

        utils = new Utils();

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnInfoWindowClickListener(this);

        mMap.addMarker(new MarkerOptions()
                .position(Config.ARETEPOSITION)
                .title("AretÈ")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        );

        getNearestKrispyKremeMX();

    }

    private void getNearestKrispyKremeMX() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL_MX)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<LocationMX>> locationResponseCall = retrofit
                .create(KrispyKremeInterface.class).nearestKrispyKremeMX("centros", 3);

        locationResponseCall.enqueue(new Callback<List<LocationMX>>() {
            @Override
            public void onResponse(Call<List<LocationMX>> call, Response<List<LocationMX>> response) {
                if (response.code() != 200) return;

                krispyLocations = response.body();

                if (krispyLocations == null || krispyLocations.size() == 0) return;

                LocationMX firstK = krispyLocations.get(0);

                nearestKrispy = firstK;

                double distance = utils.distance(
                        Config.ARETELAT, Config.ARETELNG,
                        firstK.getPosition().latitude, firstK.getPosition().longitude
                );

                for (LocationMX krispy : krispyLocations) {

                    double tmpDistance = utils.distance(
                            Config.ARETELAT, Config.ARETELNG,
                            krispy.getPosition().latitude, krispy.getPosition().longitude
                    );

                    if (distance > tmpDistance) {
                        nearestKrispy = krispy;
                        distance = tmpDistance;
                    }


                    mMap.addMarker(new MarkerOptions()
                            .position(krispy.getPosition())
                            .title(krispy.getName())
                            .snippet(krispy.getPhone())
                    );
                }

                new GMapV2Direction(Config.ARETEPOSITION, nearestKrispy.getPosition(), GMapV2Direction.MODE_WALKING, mMap)
                        .execute();
            }

            @Override
            public void onFailure(Call<List<LocationMX>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getNearestKrispyKreme() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<LocationResponse>> locationResponseCall = retrofit
                .create(KrispyKremeInterface.class).nearestKrispyKreme();

        locationResponseCall.enqueue(new Callback<List<LocationResponse>>() {
            @Override
            public void onResponse(Call<List<LocationResponse>> call, Response<List<LocationResponse>> response) {
                if (response.code() != 200) return;

                for (LocationResponse krispy : response.body()) {
                    mMap.addMarker(new MarkerOptions()
                            .position(krispy.getLocation().getPosition())
                            .title(krispy.getLocation().getName())
                    );
                }
            }

            @Override
            public void onFailure(Call<List<LocationResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String snippet = marker.getSnippet();

        String phone = snippet.replace("Teléfono: ", "");

        if (phone.isEmpty()) {
            utils.renderToask(this, "No cuenta con teléfono");
            return;
        }

        makeCall(phone);

    }

    public void makeCall(String phone) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
                else
                    requestPermissions(new String[]{CALL_PHONE}, Config.REQUEST_CALL_PHONE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == Config.REQUEST_CALL_PHONE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }
}
