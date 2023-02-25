package com.example.tasca2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private Button bWT;
    private Button bM;
    public DBHelper dbh = new DBHelper(this);
    private GestureLibrary libreria;

    MediaPlayer mp;
    SoundPool soundPool;
    int idEfecteBoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Kanye West");

        dbh.cargarDades();

        //Carreguem la llibreria
        libreria = GestureLibraries.fromRawResource(this,R.raw.gestures);

        if(!libreria.load()){
            finish();
        }

        GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gestures);
        gestureView.addOnGesturePerformedListener(this);

        bWT = findViewById(R.id.buttonWT);
        bM = findViewById(R.id.buttonMusica);

        mp = MediaPlayer.create(this, R.raw.canco_fons);
        mp.setLooping(true);

        soundPool = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        idEfecteBoto = soundPool.load(this, R.raw.effect, 0);

        if (dbh.isMusicaEnabled()) {
            mp.start();
        }

        bWT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbh.isMusicaEnabled()) {
                    soundPool.play(idEfecteBoto, 1, 1, 1, 0, 1);
                }
                Intent intent = new Intent(MainActivity.this, ConcertActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        bM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("musica");

                switch (strEditText) {
                    case "true": mp.start();
                        break;
                    case "false": mp.pause();
                        break;
                }
            }
        }
    }

    //Comportament a aplicar segons el 'gesture'
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = libreria.recognize(gesture);
        if (predictions.size() > 0) {
            String comando = predictions.get(0).name;
            System.out.println(comando);
            switch(comando)
            {
                case "salir": //'Gesture' associat a la sortida de l'aplicació
                    finish();
                    System.exit(0);
                    break;
            }
        }
    }

}