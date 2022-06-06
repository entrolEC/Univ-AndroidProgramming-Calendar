package com.example.univ_androidprogramming_calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class AddScheduleActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap = null;
    LatLng latLng = new LatLng(37.5817891, 127.009854);;
    private String searchPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        EditText editText = findViewById(R.id.search_editText);
        EditText titleEditText = findViewById(R.id.title_editText);
        EditText contentEditText = findViewById(R.id.memo_editText);
        TimePicker startTimePicker = findViewById(R.id.datePicker_start);
        TimePicker endTimePicker = findViewById(R.id.datePicker_end);

        if (getIntent().getStringExtra("title") != null) {
            titleEditText.setText(getIntent().getStringExtra("title"));
            contentEditText.setText(getIntent().getStringExtra("content"));
            startTimePicker.setHour(Integer.parseInt(getIntent().getStringExtra("start_time").split(":")[0]));
            startTimePicker.setMinute(Integer.parseInt(getIntent().getStringExtra("start_time").split(":")[1]));
            endTimePicker.setHour(Integer.parseInt(getIntent().getStringExtra("end_time").split(":")[0]));
            endTimePicker.setHour(Integer.parseInt(getIntent().getStringExtra("end_time").split(":")[0]));
            latLng = new LatLng(Double.parseDouble(getIntent().getStringExtra("location_latitude")), Double.parseDouble(getIntent().getStringExtra("location_longitude")));
        }

        if (getIntent().getStringExtra("start_time") != null) {
            startTimePicker.setHour(Integer.parseInt(getIntent().getStringExtra("start_time")));
            endTimePicker.setHour(Integer.parseInt(getIntent().getStringExtra("start_time")));
        }
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPlace = editText.getText().toString();

                Geocoder geocoder = new Geocoder(getBaseContext()); //https://salix97.tistory.com/124
                List<Address> addresses = null;

                try {
                    addresses = geocoder.getFromLocationName(searchPlace, 3);
                    if (addresses != null && !addresses.equals(" ")) {
                        search(addresses);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = getIntent().getStringExtra("selectedDate");
                ScheduleItem si = new ScheduleItem(titleEditText.getText().toString(), contentEditText.getText().toString(),
                        date, startTimePicker.getHour() + ":" + startTimePicker.getMinute(),
                        endTimePicker.getHour() + ":" + endTimePicker.getMinute(),
                        Double.toString(latLng.latitude), Double.toString(latLng.longitude)
                );

                DBHelper dbHelper = new DBHelper(getBaseContext());
                dbHelper.insertScheduleBySQL(si);
            }
        });
    }

    protected void search(List<Address> addresses) {
        Address address = addresses.get(0);
        latLng = new LatLng(address.getLatitude(), address.getLongitude()); // 주소

        String addressText = String.format(
                "%s, %s",
                address.getMaxAddressLineIndex() > 0 ? address
                        .getAddressLine(0) : " ", address.getFeatureName());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(addressText);

        mGoogleMap.clear();
        mGoogleMap.addMarker(markerOptions);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;

        googleMap.addMarker(
                new MarkerOptions().
                        position(latLng).
                        title("한성대학교"));

        // move the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

        mGoogleMap.setOnMarkerClickListener(new MyMarkerClickListener());
    }

    class MyMarkerClickListener implements GoogleMap.OnMarkerClickListener {

        @Override
        public boolean onMarkerClick(Marker marker) {
            if (marker.getTitle().equals(searchPlace)) {
                Toast.makeText(getApplicationContext(),"한성대입구역을 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }
}