package com.example.win.googlemaptest1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Button buttProcess;
    EditText etFrom, etTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Declare GUI Components
        buttProcess = (Button) findViewById(R.id.buttProcess);
        buttProcess.setOnClickListener(this);
        etFrom = (EditText) findViewById(R.id.etFrom);
        etTo = (EditText) findViewById(R.id.etTo);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
        MapFragment mapf = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapf.getMapAsync(this);

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
        //Toast.makeText(this,"ENTERING", Toast.LENGTH_LONG).show();
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //Toast.makeText(this,"ACCESS REQUIRED", Toast.LENGTH_LONG).show();
            return;
        }

        //Toast.makeText(this,"ACCESS OBTAINED", Toast.LENGTH_LONG).show();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /**
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));**/

        /**
        mMap = googleMap;
        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
**/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttProcess :
                try {
                    doIt();
                }
                catch (IOException e)
                {
                    showMessage(e.toString());
                }
                break;
        }
    }

    private void doIt() throws IOException
    {
        String descfr,descto;
        descfr = etFrom.getText().toString();
        descto = etTo.getText().toString();

        List<Double> coo1 = getCoordinates(descfr);
        LatLng locationfr = new LatLng(coo1.get(0), coo1.get(1));
        MarkerOptions moFr = new MarkerOptions()
                .title(descfr)
                .position(locationfr);
        mMap.addMarker(moFr);

        List<Double> coo2 = getCoordinates(descto);
        LatLng locationto = new LatLng(coo2.get(0), coo2.get(1));
        MarkerOptions moTo = new MarkerOptions()
                .title(descto)
                .position(locationto);
        mMap.addMarker(moTo);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(locationfr));

    }

    private List<Double> getCoordinates(String placename) throws IOException {
        List<Double> result = new ArrayList<Double>();
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(placename, 1);
        Address add = list.get(0);
        result.add(add.getLatitude());
        result.add(add.getLongitude());
        return result;
    }

    private void showMessage(String s1) {
        Toast.makeText(this, s1, Toast.LENGTH_LONG).show();
    }
/**
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
**/
}
