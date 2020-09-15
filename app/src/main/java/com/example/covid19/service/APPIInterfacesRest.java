package com.example.covid19.service;

import com.example.covid19.model.create.AddData;
import com.example.covid19.model.get.GetData;
import com.example.covid19.update.Update;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APPIInterfacesRest {

    @GET("covid19apps/all")
    Call<GetData> getDetailData(@Query("id") int id);

    @Multipart
    @POST("covid19apps/add")
    Call<AddData> addData(
            @Part("username") RequestBody username,
            @Part("kondisi") RequestBody kondisi,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon,
            @Part("timestamp") RequestBody timestamp,
            @Part("status") RequestBody status,
            @Part("nama_lengkap") RequestBody nama_lengkap,
            @Part("umur") RequestBody umur,
            @Part("jenis_kelamin") RequestBody jenis_kelamin,
            @Part("kota_domisili") RequestBody kota_domisili
    );
    @Multipart
    @POST("covid19apps/update")
    Call<Update> updateData(
            @Part("id") RequestBody id,
            @Part("username") RequestBody username,
            @Part("kondisi") RequestBody kondisi,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon,
            @Part("timestamp") RequestBody timestamp,
            @Part("status") RequestBody status,
            @Part("nama_lengkap") RequestBody nama_lengkap,
            @Part("umur") RequestBody umur,
            @Part("jenis_kelamin") RequestBody jenis_kelamin,
            @Part("kota_domisili") RequestBody kota_domisili,
            @Part("no_telepon") RequestBody no_telepon,
            @Part("picture") RequestBody picture
    );
}
