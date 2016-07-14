package com.janidham.nearestkrispykreme.model;

import android.location.*;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by janidham on 7/13/16.
 */
public class LocationMX {

    @SerializedName("dealerid")
    String id;

    @SerializedName("dealer")
    String name;

    @SerializedName("dealer_calle")
    String street;

    @SerializedName("dealer_colonia")
    String colonia;

    @SerializedName("dealercp")
    String zipcode;

    @SerializedName("dealertel")
    String phone;

    @SerializedName("dealerhorario")
    String schedule;

    @SerializedName("latitud")
    String latitud;

    @SerializedName("longitud")
    String longitud;

    android.location.Location location;
    float lt, lg;

    public LocationMX(String id, String name, String street, String colonia, String zipcode, String phone, String schedule, String latitud, String longitud) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.colonia = colonia;
        this.zipcode = zipcode;
        this.phone = phone;
        this.schedule = schedule;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        String tel = !phone.isEmpty() ? phone : "no tiene";
        return "Teléfono: " + tel;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLatitud() {
        return latitud.replace(",", "");
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud.replace(",", "");
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public LatLng getPosition() {

        lt = this.latitud.isEmpty() ? 0 : Float.parseFloat(getLatitud());
        lg = this.latitud.isEmpty() ? 0 : Float.parseFloat(getLongitud());

        return new LatLng(lt, lg);
    }

    public float comparePosition(Location currentPosition) {
        return getLocation().distanceTo(currentPosition);
    }

    public Location getLocation() {
        this.location = this.location == null ? new Location(this.name) : this.location;
        this.location.setLatitude(lt);
        this.location.setLongitude(lg);

        return this.location;
    }
}
