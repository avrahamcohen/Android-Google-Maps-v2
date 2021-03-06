package com.domain.example.checkbox;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {

    private boolean isFragmentMap = false;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        if (isFragmentMap)
            setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFragmentMap)
            setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        
        if (isFragmentMap && (id == R.id.action_maps)) return true;
        else if (isFragmentMap && (id != R.id.action_maps)) {
            removeMapFragment();
            isFragmentMap = false;
        }

        if (id == R.id.action_welcome) {
            setContentView(R.layout.fragment_main);
            return true;
        }
        if (id == R.id.action_maps) {
            setContentView(R.layout.fragment_map);
            setUpMapIfNeeded();
            isFragmentMap = true;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
                mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void removeMapFragment() {
        getSupportFragmentManager().beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.map)).commit();
    }
}
