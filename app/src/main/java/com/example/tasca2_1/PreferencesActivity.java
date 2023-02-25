package com.example.tasca2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreferencesActivity extends AppCompatActivity {

    private Button bEnable;
    private Button bDisable;
    public DBHelper dbh = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        setTitle("Preferencies");

        bEnable = findViewById(R.id.buttonEnableMusica);
        bDisable = findViewById(R.id.buttonDisableMusica);

        if (dbh.isMusicaEnabled()) {
            bEnable.setVisibility(View.GONE);
            bDisable.setVisibility(View.VISIBLE);
        } else {
            bDisable.setVisibility(View.GONE);
            bEnable.setVisibility(View.VISIBLE);
        }

        bEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("musica", "true");
                setResult(RESULT_OK, intent);
                finish();
                dbh.enableMusica();
            }
        });

        bDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("musica", "false");
                setResult(RESULT_OK, intent);
                finish();
                dbh.disableMusica();
            }
        });
    }
}