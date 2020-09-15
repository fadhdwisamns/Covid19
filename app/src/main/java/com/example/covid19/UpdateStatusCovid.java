package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.covid19.model.dummy.DataDumy;
import com.example.covid19.service.APIClient;
import com.example.covid19.service.APPIInterfacesRest;
import com.example.covid19.tool.SharedPrefUtil;
import com.example.covid19.update.Update;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStatusCovid extends AppCompatActivity {
    DataDumy dataDumy;
    LinearLayout kembali;
    CheckBox box1 , box2 , box3 , box4 , box5;
    Button btnUpdate;
    String cb1 = "0", cb2 = "0", cb3 = "0", cb4 = "0", cb5 = "0";
    APPIInterfacesRest appiInterfacesRest;
    ProgressDialog progressDialog;
    public static final int RESULT_CODE = 110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status_covid);

        kembali = findViewById(R.id.linlayKembali);
        box1 = findViewById(R.id.cb1);
        box2 = findViewById(R.id.cb2);
        box3 = findViewById(R.id.cb3);
        box4 = findViewById(R.id.cb4);
        box5 = findViewById(R.id.cb5);
        btnUpdate = findViewById(R.id.btnUpdate);

        String json = SharedPrefUtil.getInstance(UpdateStatusCovid.this).getString("data_input");
        dataDumy = new Gson().fromJson(json, DataDumy.class);

        String string = dataDumy.getStatus();
        String arr[] =string.split(" ", 5);
        String word = arr[0];

       if (arr[0].equals("1")){
            box1.setChecked(true);
       }
        if (arr[1].equals("2")){
            box2.setChecked(true);
        }
        if (arr[2].equals("3")){
            box3.setChecked(true);
        }
        if (arr[3].equals("4")){
            box4.setChecked(true);
        }
        if (arr[4].equals("5")){
            box5.setChecked(true);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();

            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
        public void update(){
            appiInterfacesRest = APIClient.getClient().create(APPIInterfacesRest.class);
            progressDialog = new ProgressDialog(UpdateStatusCovid.this);
            progressDialog.setTitle("Loading");
            progressDialog.show();

            if (box1.isChecked()){
                cb1 = "1";
            }
            if (box2.isChecked()){
                cb2 = "2";
            }
            if (box3.isChecked()){
                cb3 = "3";
            }
            if (box4.isChecked()){
                cb4 = "4";
            }
            if (box5.isChecked()){
                cb5 = "5";
            }
            String status =  cb1+" "+cb2+" "+cb3+" "+cb4+" "+cb5;

            String kondisi ;

            if (status.equals("0 0 0 0 0")){
                kondisi = "sehat";

            } else if (status.equals("0 1 0 1 1") || status.equals("0 1 1 1 1") || status.equals("1 1 1 1 1")){
                kondisi = "covid";
            } else {
                kondisi = "sakit";
            }

            Long time = System.currentTimeMillis()/1000;
            String stamp =time.toString();

            final Call<Update> updateCall = appiInterfacesRest.updateData(
                    toRequestBody(dataDumy.getId()),
                    toRequestBody(dataDumy.getUsername()),
                    toRequestBody(kondisi),
                    toRequestBody(dataDumy.getLat()),
                    toRequestBody(dataDumy.getLon()),
                    toRequestBody(stamp),
                    toRequestBody(status),
                    toRequestBody(dataDumy.getNama_lengkap()),
                    toRequestBody(dataDumy.getUmur()),
                    toRequestBody(dataDumy.getJenis_kelamin()),
                    toRequestBody(dataDumy.getKota_domisili()),
                    toRequestBody(dataDumy.getNo_telepon()),
                    toRequestBody("")
            );
            updateCall.enqueue(new Callback<Update>() {
                @Override
                public void onResponse(Call<Update> call, Response<Update> response) {
                    progressDialog.dismiss();
                    Update status = response.body();
                    if (status != null) {

                        if (status.getStatus()) {
                            Toast.makeText(UpdateStatusCovid.this, "Update status Berhasil", Toast.LENGTH_LONG).show();

                            Gson gson = new Gson();
                            String json = gson.toJson(status.getStatus());
                            SharedPrefUtil.getInstance(UpdateStatusCovid.this).put("data_input", json);

                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("kondisi", status.getStatus().toString());
                            resultIntent.putExtra("timeStamp", status.getStatus().toString());
                            setResult(RESULT_CODE, resultIntent);
                            finish();
                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.body().toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String error = jObjError.get("message").toString();
                            Toast.makeText(UpdateStatusCovid.this, error, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            try {
                                Toast.makeText(UpdateStatusCovid.this, "Send Failed, " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                }

                @Override
                public void onFailure(Call<Update> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                    call.cancel();
                }
            });

        }

    private RequestBody toRequestBody(String s) {
        if (s == null) {
            s = "";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), s);
        return body;
    }
}