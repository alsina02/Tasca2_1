package com.example.tasca2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ConcertActivity extends AppCompatActivity {

    // Array of strings...
    private ListView simpleList;
    private String concerts[] = {"Barcelona, ES", "Paris, FR", "Nuremberg, AL", "Milan, IT", "Amsterdam, PB"};
    private String dates[] = {"19 Juny", "24 Juny", "30 Juny", "4 Juliol", "10 Juliol"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert);
        setTitle("Concerts");

        simpleList = (ListView)findViewById(R.id.concertListView);
        // ArrayAdapter<String> customAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, concerts);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), concerts, dates);
        simpleList.setAdapter(customAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ConcertActivity.this, MapsActivity.class);
                String sgls;
                switch (position) {
                    case 1: sgls = "PAR";
                        break;
                    case 2: sgls = "NUB";
                        break;
                    case 3: sgls = "MIL";
                        break;
                    case 4: sgls = "AMS";
                        break;
                    default: sgls = "BCN";
                }

                intent.putExtra("locaiton", sgls);

                ConcertActivity.this.startActivity(intent);
                // Toast.makeText(ConcertActivity.this, "Has pulsado: "+ position, Toast.LENGTH_LONG).show();
            }
        });
    }
}