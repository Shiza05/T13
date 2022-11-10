package com.example.t13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Persona> listaPersonas;

    SoundPool play_c;
    Button play_l;
    int sonido_de_reproduccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPersonas = new ArrayList<Persona>();
        listaPersonas.add(new Persona("Juan", 'M'));
        listaPersonas.add(new Persona("Maria", 'F'));
        listaPersonas.add(new Persona ("Pedro", 'M'));
        listaPersonas.add(new Persona ("Martin", 'M'));
        listaPersonas.add(new Persona ("Jimena", 'F'));
        listaPersonas.add(new Persona ("Samanta", 'F'));
        listaPersonas.add(new Persona ("Carlos", 'M'));
        listaPersonas.add(new Persona ("David", 'M'));
        listaPersonas.add(new Persona ("Fatima", 'F'));
        listaPersonas.add(new Persona ("Daniela", 'F'));

        AdaptadorPersonas adaptador = new AdaptadorPersonas(this);
        ListView lv1 = findViewById(R.id.list1);
        lv1.setAdapter(adaptador);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, listaPersonas.get(i).getNombre() + ",  " + ((listaPersonas.get(i).getGenero()=='M')?"Hombre":"Mujer"), Toast.LENGTH_SHORT).show();
            }
        });

        play_c = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_de_reproduccion = play_c.load(this, R.raw.corto,1);
        play_l = (Button) findViewById(R.id.play_largo);



    }
    class AdaptadorPersonas extends ArrayAdapter<Persona>
    {
        AppCompatActivity appCompatActivity;
        AdaptadorPersonas (AppCompatActivity context)
        {
            super(context, R.layout.persona, listaPersonas);
            appCompatActivity = context;
        }

        public View getView(int position, View converView, ViewGroup parent)
        {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.persona, null);
            TextView textView1 = item.findViewById(R.id.textView);
            textView1.setText(listaPersonas.get(position).getNombre());

            ImageView imageView = item.findViewById(R.id.imageView);

            switch (listaPersonas.get(position).getNombre())
            {
                case "Juan":
                    imageView.setImageResource(R.drawable.juan);
                    break;
                case "Maria":
                    imageView.setImageResource(R.drawable.maria);
                    break;
                case "Pedro":
                    imageView.setImageResource(R.drawable.pedro);
                    break;
                case "Martin":
                    imageView.setImageResource(R.drawable.martin);
                    break;
                case "Jimena":
                    imageView.setImageResource(R.drawable.jimena);
                    break;
                case "Samanta":
                    imageView.setImageResource(R.drawable.samanta);
                    break;
                case "Carlos":
                    imageView.setImageResource(R.drawable.carlos);
                    break;
                case "David":
                    imageView.setImageResource(R.drawable.david);
                    break;
                case "Fatima":
                    imageView.setImageResource(R.drawable.fatima);
                    break;
                case "Daniela":
                    imageView.setImageResource(R.drawable.daniela);
                    break;
            }

            return item;

        }

    }

    public void audioCorto(View view)
    {
        play_c.play(sonido_de_reproduccion, 2, 2,1,0,0);
    }
    public void audioLargo(View view)
    {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.fran);
        mp.start();

    }

    public void Cambiar(View view){
        Intent a = new Intent(this, T15.class);
        startActivity(a);
    }

}

