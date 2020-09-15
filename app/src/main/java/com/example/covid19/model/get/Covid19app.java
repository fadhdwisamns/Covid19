
package com.example.covid19.model.get;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Covid19app implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("kondisi")
    @Expose
    private String kondisi;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("umur")
    @Expose
    private String umur;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("picture")
    @Expose
    private String picture;
    public final static Creator<Covid19app> CREATOR = new Creator<Covid19app>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Covid19app createFromParcel(Parcel in) {
            return new Covid19app(in);
        }

        public Covid19app[] newArray(int size) {
            return (new Covid19app[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1714113579223883946L;

    protected Covid19app(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.lon = ((String) in.readValue((String.class.getClassLoader())));
        this.kondisi = ((String) in.readValue((String.class.getClassLoader())));
        this.timestamp = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.namaLengkap = ((String) in.readValue((String.class.getClassLoader())));
        this.umur = ((String) in.readValue((String.class.getClassLoader())));
        this.jenisKelamin = ((String) in.readValue((String.class.getClassLoader())));
        this.kota = ((String) in.readValue((String.class.getClassLoader())));
        this.telepon = ((String) in.readValue((String.class.getClassLoader())));
        this.picture = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Covid19app() {
    }

    /**
     * 
     * @param umur
     * @param kota
     * @param kondisi
     * @param telepon
     * @param lon
     * @param picture
     * @param jenisKelamin
     * @param id
     * @param lat
     * @param namaLengkap
     * @param username
     * @param timestamp
     * @param status
     */
    public Covid19app(String id, String username, String lat, String lon, String kondisi, String timestamp, String status, String namaLengkap, String umur, String jenisKelamin, String kota, String telepon, String picture) {
        super();
        this.id = id;
        this.username = username;
        this.lat = lat;
        this.lon = lon;
        this.kondisi = kondisi;
        this.timestamp = timestamp;
        this.status = status;
        this.namaLengkap = namaLengkap;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.kota = kota;
        this.telepon = telepon;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(username);
        dest.writeValue(lat);
        dest.writeValue(lon);
        dest.writeValue(kondisi);
        dest.writeValue(timestamp);
        dest.writeValue(status);
        dest.writeValue(namaLengkap);
        dest.writeValue(umur);
        dest.writeValue(jenisKelamin);
        dest.writeValue(kota);
        dest.writeValue(telepon);
        dest.writeValue(picture);
    }

    public int describeContents() {
        return  0;
    }

}
