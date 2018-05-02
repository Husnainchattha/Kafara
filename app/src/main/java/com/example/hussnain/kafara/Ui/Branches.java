package com.example.hussnain.kafara.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hussnain.kafara.BackEnd.ApiHandler;
import com.example.hussnain.kafara.R;
import com.example.hussnain.kafara.ServerModel.Branch;
import com.example.hussnain.kafara.ServerModel.Page;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Branches extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        int pageno=0;
        ApiHandler.getInstance().getBranch(pageno, new ApiHandler.BranchCallback() {
            @Override
            public void onSuccess(Page<Branch> branch) {
                for (Branch branch1:branch.getData()) {
                    addMarker(branch1);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void addMarker(Branch branch1) {
        double[] location=branch1.getLocation();
        LatLng position=new LatLng(location[1],location[0]);
        if (googleMap!=null){
            googleMap.addMarker(new MarkerOptions()
                    .position(position).title(branch1.getName()));
        }

    }

    private GoogleMap googleMap;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney")
        .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
