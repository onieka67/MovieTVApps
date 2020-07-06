package com.example.drawnav;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class DetailTv extends AppCompatActivity {
    Tv tv;
    ImageView id_image;
    TextView id_name;
    TextView id_ori;
    TextView id_lg;
    TextView id_date;
    TextView id_over;
    ProgressBar progressBar;

    String idName;
    String idOri;
    String idDate;
    String idLg;
    String idOver;

    int progressStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        progressBar = findViewById(R.id.simpleProgressBar);

        id_image = findViewById(R.id.id_image);
        id_name = findViewById(R.id.id_name);
        id_ori = findViewById(R.id.id_ori);
        id_date = findViewById(R.id.id_date);
        id_lg = findViewById(R.id.id_lg);
        id_over = findViewById(R.id.id_over);
        progressBar.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 10;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent in = getIntent();
                        tv = in.getParcelableExtra("tv");
                        idName = tv.getName();
                        id_name.setText(idName);

                        RequestOptions requestOptions = new RequestOptions();
                        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(40));

                        Glide.with(DetailTv.this)
                                .load("https://image.tmdb.org/t/p/w500" + tv.getPoster_path())
                                .apply(requestOptions)
                                .placeholder(R.drawable.rectangle_image)
                                .into(id_image);

                        idOri = tv.getOriginal_name();
                        id_ori.setText(idOri);
                        idDate = tv.getFirst_air_date();
                        id_date.setText(idDate);
                        idLg = tv.getOriginal_language();
                        id_lg.setText(idLg);
                        idOver = tv.getOverview();
                        id_over.setText(idOver);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}
