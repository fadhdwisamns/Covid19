package com.example.covid19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covid19.model.create.AddData;
import com.example.covid19.service.APIClient;
import com.example.covid19.service.APPIInterfacesRest;
import com.example.covid19.tool.SharedPrefUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btndaftar;
    EditText nama , umur , jenisKelamin , kota , telepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nama = findViewById(R.id.editnama);
        umur = findViewById(R.id.editumur);
        jenisKelamin = findViewById(R.id.editjl);
        kota = findViewById(R.id.editkota);
        telepon = findViewById(R.id.edittelepon);
        btndaftar = findViewById(R.id.btndaftar);

        btndaftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,Main_Menu.class);
        startActivity(intent);
    }
    APPIInterfacesRest appiInterfacesRest;
         ProgressDialog progressDialog;
         public void addData(){
             progressDialog = new ProgressDialog(MainActivity.this);
             appiInterfacesRest = APIClient.getClient().create(APPIInterfacesRest.class);
             progressDialog.setTitle("Loading");
             progressDialog.show();

             String string = nama.getText().toString();
             String arr[] = string.split(" " , 2);
             String word = arr[0];

             Long timeLong = System.currentTimeMillis()/1000;
             String stamp = timeLong.toString();

             Call<AddData> addDataCall = appiInterfacesRest.addData(
                     toRequestBody(word),
                     toRequestBody("sehat"),
                     toRequestBody(stamp),
                     toRequestBody("0 0 0 0 0"),
                     toRequestBody(nama.getText().toString()),
                     toRequestBody(umur.getText().toString()),
                     toRequestBody(jenisKelamin.getText().toString()),
                     toRequestBody(kota.getText().toString()),
                     toRequestBody(telepon.getText().toString()),
                     toRequestBody("")
             );
            addDataCall.enqueue(new Callback<AddData>() {
                @Override
                public void onResponse(Call<AddData> call, Response<AddData> response) {
                    progressDialog.dismiss();
                    AddData status = response.body();

                    if (status != null){
                        if (status.getStatus()){
                            Toast.makeText(MainActivity.this , "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                            Gson gson = new Gson();
                            String json = gson.toJson(status.getStatus());
                            SharedPrefUtil.getInstance(MainActivity.this).put("data_input", json);
                            Intent intent = new Intent(MainActivity.this, Main_Menu.class);
                            startActivity(intent);
                            finish();
                        }else {
                            try {
                                JSONObject jObjError = new JSONObject(response.body().toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String error = jObjError.get("status_detail").toString();
                            Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            try {
                                Toast.makeText(MainActivity.this, "Send Failed, " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddData> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                    call.cancel();
                }
            });
         }

    private RequestBody toRequestBody(String word) {
            if (word == null){
                word = " ";
            }
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"), word);
            return body;
         }
    public void showErrorDialog(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Peringatan");
        alertDialog.setMessage("Mohon isi field yang mandatory")
                .setIcon(R.drawable.circle)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Cancel ditekan", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}