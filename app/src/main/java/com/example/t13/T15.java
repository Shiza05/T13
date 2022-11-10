package com.example.t13;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class T15 extends AppCompatActivity {
    private VideoView v1;
    private TextView timeText;
    private Button btnv_play, btnv_siguiente, btnv_anterior, btnv_stop, btn_sig, btn_ant;
    private SeekBar skVideo;
    private Handler skHandler = new Handler();
    private String videoss[] = new String[4];
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t15);
        btnv_play = (Button) findViewById(R.id.btnv_play);
        btnv_siguiente = (Button) findViewById(R.id.btnv_siguiente);
        btnv_stop = (Button) findViewById(R.id.btnv_stop);
        btnv_anterior = (Button) findViewById(R.id.btnv_anterior);
        btn_sig = (Button) findViewById(R.id.btn_sig);
        btn_ant = (Button) findViewById(R.id.btn_ant);
        skVideo = (SeekBar) findViewById(R.id.seek1);
        timeText = (TextView) findViewById(R.id.timeLapse);

        videoss[0] = "android.resource://"+getPackageName()+"/"+R.raw.v1;
        videoss[1] = "android.resource://"+getPackageName()+"/"+R.raw.v2;
        videoss[2] = "android.resource://"+getPackageName()+"/"+R.raw.v3;
        videoss[3] = "android.resource://"+getPackageName()+"/"+R.raw.v4;

        v1 = (VideoView) findViewById(R.id.vvv1);
        v1.setVideoURI(Uri.parse(videoss[position]));


        btnv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v1.isPlaying()){
                    v1.pause();
                    btnv_play.setBackgroundResource(R.drawable.reproducir);
                }
                else{
                    v1.start();
                    btnv_play.setBackgroundResource(R.drawable.pausa);
                }
            }
        });
        btnv_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v1.isPlaying() && v1 != null){
                    btnv_play.setBackgroundResource(R.drawable.reproducir);
                    v1.stopPlayback();
                    v1.setVideoURI(Uri.parse(videoss[position]));
                }
            }
        });

        btnv_anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.seekTo(v1.getCurrentPosition()-1000);
            }
        });

        btnv_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.seekTo(v1.getCurrentPosition()+1000);
            }
        });

        btn_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean si = false;
                int ant_position = position;
                if(v1.isPlaying()){
                    si = true;
                }
                if(position == 3){
                    position = 0;
                }
                else {
                    position++;
                }
                v1.stopPlayback();
                v1.setVideoURI(Uri.parse(videoss[position]));
                if(si){
                    v1.start();
                }

            }
        });

        btn_ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean si = false;
                int ant_position = position;
                if(v1.isPlaying()){
                    si = true;
                }
                if(position == 0){
                    position = 3;
                }
                else {
                    position--;
                }
                v1.stopPlayback();
                v1.setVideoURI(Uri.parse(videoss[position]));
                if(si){
                    v1.start();
                }
            }
        });
        timeText.setText(getRHM(v1.getDuration()));
        skVideo.setMax(v1.getDuration());
        skVideo.setProgress(v1.getCurrentPosition());
        skHandler.postDelayed(updateVideo, 1000);
    }

    private String getRHM(int milliseconds)
    {
        int seconds = (int) (milliseconds/1000) % 60;
        int minutes = (int) (milliseconds/(60*1000)) % 60;
        int hours = (int) (milliseconds/(1000*60*60)) % 24;

        return  ""  + ((hours < 10)?"0"+hours:hours) +
                ":" + ((minutes < 10)?"0"+minutes:minutes) +
                ":" + ((seconds < 10)?"0"+seconds:seconds);
    }

    //barra de avance de la pista
    Runnable updateVideo = new Runnable() {
        @Override
        public void run() {
            skVideo.setProgress(v1.getCurrentPosition());
            timeText.setText(getRHM(v1.getDuration()) + " - " + getRHM(v1.getCurrentPosition()));
            skHandler.postDelayed(updateVideo, 1000);

        }

    };
}