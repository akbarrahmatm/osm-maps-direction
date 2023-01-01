package com.akbarrahmatm.osm_maps_direction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RoadManager roadManager = new OSRMRoadManager(this, "MyOwnUserAgent/1.0");

        // bisa diubah ke mode car / bike / foot
        ((OSRMRoadManager)roadManager).setMean(OSRMRoadManager.MEAN_BY_CAR);

        MapView map = (MapView) findViewById(R.id.map);
        map.setMultiTouchControls(true);
        map.invalidate();



        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();

        // titik start
        GeoPoint startPoint = new GeoPoint(-6.2363587, 106.747343);
        waypoints.add(startPoint);

        //titik stop
        GeoPoint endPoint = new GeoPoint(-6.2434495, 106.797386);
        waypoints.add(endPoint);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);

        Marker endMarker = new Marker(map);
        endMarker.setPosition(endPoint);
        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(endMarker);

        Road road = roadManager.getRoad(waypoints);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
        map.getOverlays().remove(roadOverlay);
        map.getOverlays().add(roadOverlay);


        IMapController mapController = map.getController();
        mapController.setZoom(19);

        mapController.setCenter(startPoint);
    }
}