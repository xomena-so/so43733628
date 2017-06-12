package com.xomena.so43733628;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private GoogleMap map;
    private LatLng requestedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in Sydney and move the camera
        requestedPosition = new LatLng(41.385692,2.163953);
        float zoom = 16.0f;
        map.addMarker(new MarkerOptions().position(requestedPosition).title("Marker in Barcelona"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(requestedPosition, zoom));

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(requestedPosition, zoom));
        // Disable all Ui interaction except for zoom
        map.getUiSettings().setAllGesturesEnabled(false);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.setOnCameraIdleListener(this);
    }

    @Override
    public void onCameraIdle() {
        float zoom = map.getCameraPosition().zoom;
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(requestedPosition, zoom));
    }
}
