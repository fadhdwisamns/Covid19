package com.example.covid19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid19.model.dummy.DataDumy;
import com.example.covid19.tool.SharedPrefUtil;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Locale;

public class Main_Menu extends AppCompatActivity implements View.OnClickListener {
    CardView cvStatus,cvLihatKondisi,cvUpdateStatus;
    TextView txtKondisi,txtKet;
    ImageView imgKondisi;
    private int REQUEST_CODE = 100;
    String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        cvStatus =findViewById(R.id.cvStatus);
        cvLihatKondisi =findViewById(R.id.cvLihatKondisi);
        cvUpdateStatus =findViewById(R.id.cvUpdateStatus);
        txtKondisi =findViewById(R.id.txtKondisi);
        txtKet =findViewById(R.id.txtKet);
        imgKondisi = findViewById(R.id.imgKondisi);

        String json = SharedPrefUtil.getInstance(Main_Menu.this).getString("data_input");
        DataDumy dataDumy = new Gson().fromJson(json, DataDumy.class);

        time = getTimestamps(dataDumy.getTimestamp());

        rubahSatus(dataDumy.getKondisi());

        cvUpdateStatus.setOnClickListener(this);




    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Main_Menu.this, UpdateStatusCovid.class);
        startActivity(intent);
    }

    private void rubahSatus(String kondisi) {
        switch (kondisi){
            case "sehat":
                cvStatus.setCardBackgroundColor(Color.parseColor("#FFC1F486"));
                txtKondisi.setText("SEHAT");
                imgKondisi.setImageResource(R.drawable.ic_done_24);
                txtKet.setText("Terakhir update "+time);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent sehat = new Intent(Main_Menu.this, DeskripsiCovid.class);
                        startActivity(sehat);
                    }
                },1000);

                break;
            case "covid":
                cvStatus.setCardBackgroundColor(Color.parseColor("#FFFF9A9A"));
                txtKondisi.setText("COVID");
                imgKondisi.setImageResource(R.drawable.ic_report_24);
                txtKet.setText("Terakhir update "+time);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent covid = new Intent(Main_Menu.this, Covid.class);
                        startActivity(covid);
                    }
                },1000);
                break;
            default:
                break;
        }
    }

    private String getTimestamps(String timestamp) {
        Long time = Long.parseLong(timestamp);
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time * 1000L);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss",calendar).toString();

        return date;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == UpdateStatusCovid.RESULT_CODE){
                time = getTimestamps(data.getStringExtra("timeStamp"));
                rubahSatus(data.getStringExtra("kondisi"));
            }
        }
    }
}