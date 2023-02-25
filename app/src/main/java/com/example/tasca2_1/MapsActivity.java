package com.example.tasca2_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.tasca2_1.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private String option = null;
    private LatLng ciutat;
    private TextView city;
    private TextView datetime;
    private TextView location;
    private Button compraTicket;
    private Uri url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        city = findViewById(R.id.textViewCity);
        datetime = findViewById(R.id.textViewDateTime);
        location = findViewById(R.id.textViewLocation);
        compraTicket = findViewById(R.id.buttonComprarTickets);

        compraTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, url);
                startActivity(launchBrowser);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String title;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            option = extras.getString("locaiton");
        }

        switch (option) {
            case "PAR":
                ciutat = new LatLng(48.852737, 2.350699);
                title = "Catedral de Notre Dame";
                city.setText("Paris");
                location.setText(title);
                datetime.setText("24 Juny 20:30 CET");
                url = Uri.parse("https://www.ticketmaster.es");
                break;
            case "NUB":
                ciutat = new LatLng(49.43225, 11.11243);
                title = "Kongresshalle";
                city.setText("Nürnberg");
                location.setText(title);
                datetime.setText("30 Juny 18:30 CET");
                url = Uri.parse("https://www.ticketmaster.es");
                break;
            case "MIL":
                ciutat = new LatLng(45.464211, 9.191383);
                title = "Duomo di Milano";
                city.setText("Milano");
                location.setText(title);
                datetime.setText("4 Juliol 20:30 CET");
                url = Uri.parse("https://www.ticketmaster.es");
                break;
            case "AMS":
                ciutat = new LatLng(52.374355, 4.897960);
                title = "Oude Kerk";
                city.setText("Amsterdam");
                location.setText(title);
                datetime.setText("10 Juliol 18:30 CET");
                url = Uri.parse("https://www.ticketmaster.es");
                break;
            case "BCN":
                ciutat = new LatLng(41.387027, 2.170024);
                title = "Palau de la Música";
                city.setText("Barcelona");
                location.setText(title);
                datetime.setText("19 Juny 21:00 CET");
                url = Uri.parse("https://www.ticketmaster.es");
                break;
            default:
                ciutat = new LatLng(41.3943798, 2.1616566);
                title = "Titol";
                break;
        }

        mMap.addMarker(new MarkerOptions().position(ciutat).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ciutat, 11));
    }
}