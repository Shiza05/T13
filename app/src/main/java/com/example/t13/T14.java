package com.example.t13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class T14 extends AppCompatActivity {
    private TextView tvMsg, tvTime;
    private Button btnPlay, btnPause, btnStop;
    private SeekBar skSong;
    private MediaPlayer aPlayer = null;
    private Handler skHandler = new Handler();

    private Button btn_play, btn_siguiente, btn_anterior, btn_repetir, btn_stop;
    private SeekBar sk_song;
    private TextView tv1, tv2;
    private Handler sk_handler = new Handler();
    int posicion = 0; int repetir = 2;
    private MediaPlayer listamp3[] = new MediaPlayer[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t14);


        tvMsg = (TextView) findViewById(R.id.tvMsg);
        tvTime =(TextView) findViewById(R.id.tvTime);
        btnPlay = (Button) findViewById(R.id.buttonP);
        btnStop = (Button) findViewById(R.id.buttonS);
        skSong = (SeekBar) findViewById(R.id.skSong);
        aPlayer = MediaPlayer.create(T14.this, R.raw.smas);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!aPlayer.isPlaying())
                {
                    aPlayer.start();
                    tvMsg.setText("PLAY");
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aPlayer.isPlaying()){
                    aPlayer.pause();
                    tvMsg.setText("PAUSE");
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aPlayer != null){
                    aPlayer.stop();
                    aPlayer.release();
                    aPlayer = MediaPlayer.create(T14.this, R.raw.smas);
                    tvMsg.setText("Sin canción");
                }
            }
        });

        tvTime.setText(getRHM(aPlayer.getDuration()));
        skSong.setMax(aPlayer.getDuration());
        skSong.setProgress(aPlayer.getCurrentPosition());
        skHandler.postDelayed(updateSong, 1000);
        /////////////////////////////////////////////////
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_repetir = (Button) findViewById(R.id.btn_repetir);
        btn_siguiente = (Button) findViewById(R.id.btnv_siguiente);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_anterior = (Button) findViewById(R.id.btnv_anterior);
        sk_song = (SeekBar) findViewById(R.id.sk_song);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        listamp3[0] = MediaPlayer.create(T14.this, R.raw.kimigayo);
        listamp3[1] = MediaPlayer.create(T14.this, R.raw.smas);
        listamp3[2] = MediaPlayer.create(T14.this, R.raw.urss);
        listamp3[3] = MediaPlayer.create(T14.this, R.raw.fran);
        listamp3[4] = MediaPlayer.create(T14.this, R.raw.mace);

        tv2.setText(getRHM(listamp3[posicion].getDuration()));
        tv1.setText(getNames());
        sk_song.setMax(listamp3[posicion].getDuration());
        sk_song.setProgress(listamp3[posicion].getCurrentPosition());
        sk_handler.postDelayed(listaUpdateSong, 1000);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listamp3[posicion].isPlaying()){
                    listamp3[posicion].pause();
                    btn_play.setBackgroundResource(R.drawable.reproducir);
                }
                else {
                    listamp3[posicion].start();
                    btn_play.setBackgroundResource(R.drawable.pausa);
                }
                tv1.setText(getNames());
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listamp3 [posicion] != null){
                    listamp3[posicion].stop();
                    listamp3[posicion].release();
                    listamp3[0] = MediaPlayer.create(T14.this, R.raw.kimigayo);
                    listamp3[1] = MediaPlayer.create(T14.this, R.raw.smas);
                    listamp3[2] = MediaPlayer.create(T14.this, R.raw.urss);
                    listamp3[3] = MediaPlayer.create(T14.this, R.raw.fran);
                    listamp3[4] = MediaPlayer.create(T14.this, R.raw.mace);
                    btn_play.setBackgroundResource(R.drawable.reproducir);


                }
            }
        });
        btn_repetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listamp3[posicion].isLooping()) {
                    listamp3[posicion].setLooping(false);
                    btn_repetir.setBackgroundResource(R.drawable.no_repetir);
                    btn_repetir.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else {
                    listamp3[posicion].setLooping(true);
                    btn_repetir.setBackgroundResource(R.drawable.repetir);
                    btn_repetir.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                }
            }
        });

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int posicion_ant = posicion;
                if(posicion == 4){
                    posicion = 0;
                }
                else
                {
                    posicion++;
                }
                if(listamp3 [posicion_ant].isPlaying()){
                    listamp3[posicion_ant].stop();
                    listamp3[posicion_ant].release();
                    listamp3[0] = MediaPlayer.create(T14.this, R.raw.kimigayo);
                    listamp3[1] = MediaPlayer.create(T14.this, R.raw.smas);
                    listamp3[2] = MediaPlayer.create(T14.this, R.raw.urss);
                    listamp3[3] = MediaPlayer.create(T14.this, R.raw.fran);
                    listamp3[4] = MediaPlayer.create(T14.this, R.raw.mace);
                    listamp3[posicion].start();
                }
                else{
                    listamp3[posicion_ant].stop();
                    listamp3[posicion_ant].release();
                    listamp3[0] = MediaPlayer.create(T14.this, R.raw.kimigayo);
                    listamp3[1] = MediaPlayer.create(T14.this, R.raw.smas);
                    listamp3[2] = MediaPlayer.create(T14.this, R.raw.urss);
                    listamp3[3] = MediaPlayer.create(T14.this, R.raw.fran);
                    listamp3[4] = MediaPlayer.create(T14.this, R.raw.mace);
                }
                sk_song.setMax(listamp3[posicion].getDuration());
                tv1.setText(getNames());
            }
        });

        btn_anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion_ant = posicion;
                if(posicion == 0){
                    posicion = 4;
                }
                else
                {
                    posicion--;
                }
                if(listamp3 [posicion_ant].isPlaying()){
                    listamp3[posicion_ant].stop();
                    listamp3[posicion_ant].release();
                    listamp3[0] = MediaPlayer.create(T14.this, R.raw.kimigayo);
                    listamp3[1] = MediaPlayer.create(T14.this, R.raw.smas);
                    listamp3[2] = MediaPlayer.create(T14.this, R.raw.urss);
                    listamp3[3] = MediaPlayer.create(T14.this, R.raw.fran);
                    listamp3[4] = MediaPlayer.create(T14.this, R.raw.mace);
                    listamp3[posicion].start();
                }
                else{
                    listamp3[posicion_ant].stop();
                    listamp3[posicion_ant].release();
                    listamp3[0] = MediaPlayer.create(T14.this, R.raw.kimigayo);
                    listamp3[1] = MediaPlayer.create(T14.this, R.raw.smas);
                    listamp3[2] = MediaPlayer.create(T14.this, R.raw.urss);
                    listamp3[3] = MediaPlayer.create(T14.this, R.raw.fran);
                    listamp3[4] = MediaPlayer.create(T14.this, R.raw.mace);
                }
                sk_song.setMax(listamp3[posicion].getDuration());
                tv1.setText(getNames());
            }

        });
    }

    public String getNames(){
        String nombre = "";
        switch (posicion){
            case 0:
                nombre = "Himno nacional de Japón";
                break;
            case 1:
                nombre= "Smash mouth - All star";
                break;
            case 2:
                nombre = "Himno nacional de la URSS";
                break;
            case 3:
                nombre = "Himno nacional de Francia";
                break;
            case 4:
                nombre = "Himno nacional de Macedonia";
                break;
        }
        return nombre;
    }
    //hh:mm:ss
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
    Runnable updateSong = new Runnable() {
        @Override
        public void run() {
            skSong.setProgress(aPlayer.getCurrentPosition());
            tvTime.setText(getRHM(aPlayer.getDuration()) + " - " + getRHM(aPlayer.getCurrentPosition()));
            skHandler.postDelayed(updateSong, 1000);

        }

    };

    Runnable listaUpdateSong = new Runnable() {
        @Override
        public void run() {
            sk_song.setProgress(listamp3[posicion].getCurrentPosition());
            tv2.setText(getRHM(listamp3[posicion].getDuration()) + " - " + getRHM(listamp3[posicion].getCurrentPosition()));
            sk_handler.postDelayed(listaUpdateSong, 1000);

        }

    };
    @Override
    protected void onStop(){
        T14.super.onStop();
        if(aPlayer != null){
            aPlayer.pause();
            tvMsg.setText("PAUSE");
        }
    }

    @Override
    protected void onRestart(){
        T14.super.onRestart();
        if(!aPlayer.isPlaying()){
            aPlayer.start();
            tvMsg.setText("PLAY");
        }
    }
}