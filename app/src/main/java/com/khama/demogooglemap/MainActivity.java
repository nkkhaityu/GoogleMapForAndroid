package com.khama.demogooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    GoogleMap map;
    Button btnAddMarker, btnPolyline, btnPolygon, btnCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnAddMarker = findViewById(R.id.btnAddMarker);
        btnPolyline = findViewById(R.id.btnAddPolyline);
        btnPolygon = findViewById(R.id.btnAddPolygon);
        btnCircle = findViewById(R.id.btnAddCircle);

        btnAddMarker.setOnClickListener(this);
        btnPolyline.setOnClickListener(this);
        btnPolygon.setOnClickListener(this);
        btnCircle.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAddMarker:
                AddMarker();
                break;
            case R.id.btnAddPolyline:
                LatLng tranhungdao = new LatLng(10.7598442, 106.6886641);
                LatLng nearTranhungdao = new LatLng(10.7598, 106.689);

                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add(tranhungdao, nearTranhungdao);

                map.addPolyline(polylineOptions);
                break;
            case R.id.btnAddPolygon:
                Polygon polygon = map.addPolygon(new PolygonOptions()
                    .add(new LatLng(0, 0), new LatLng(3,5), new LatLng(0, 0))
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE));
                break;
            case R.id.btnAddCircle:
                LatLng tranhungdaoC = new LatLng(10.7598442, 106.6886641);
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.center(tranhungdaoC);
                circleOptions.radius(1000);
                map.addCircle(circleOptions);
                break;
        }

    }

    public void AddMarker() {
        LatLng tranhungdao = new LatLng(10.7598442, 106.6886641);
        MarkerOptions vitri1 = new MarkerOptions();
        vitri1.draggable(true);
        vitri1.position(tranhungdao);
        vitri1.title("393 Tran Hung Dao, Quan 1");
        vitri1.snippet("Day la tru so Cong An");
        Marker marker = map.addMarker(vitri1);
        marker.showInfoWindow();

//        CameraUpdate moveCamera = CameraUpdateFactory.newLatLng(tranhungdao);
//        map.moveCamera(moveCamera);
//
//        CameraUpdate zoomCamera = CameraUpdateFactory.zoomBy(10);
//        map.animateCamera(zoomCamera, 3000, null);

//        CameraUpdate moveAndZoomCamera = CameraUpdateFactory.newLatLngZoom(tranhungdao, 15);
//        map.animateCamera(moveAndZoomCamera, 3000, null);

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(tranhungdao)
                .bearing(4.3f)
                .tilt(45)
                .zoom(15).build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate, 3000, null);
    }
}
