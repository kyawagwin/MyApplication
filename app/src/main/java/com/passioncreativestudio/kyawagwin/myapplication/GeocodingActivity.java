package com.passioncreativestudio.kyawagwin.myapplication;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class GeocodingActivity extends AppCompatActivity implements OnMapReadyCallback {

    EditText mPostalCodeEditText;
    MapView mMapView;

    Address mAddress;
    GoogleMap googleMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_geocoding);

        mMapView = (MapView) findViewById(R.id.activity_geocoding_mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);

        mPostalCodeEditText = (EditText) findViewById(R.id.activity_geocoding_postalEditText);
        mPostalCodeEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        mAddress = getLocationFromAddress(textView.getText().toString());

                        placeMapMarker(mAddress);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return true;
            }
        });
    }

    private void placeMapMarker(Address address) {
        LatLng location = new LatLng(address.getLatitude(), address.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(location).title(address.getAddressLine(0)));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 18.0f));
    }

    private Address getLocationFromAddress(String address) throws IOException {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;

        addressList = geocoder.getFromLocationName(address, 5);
        if(addressList == null) {
            return null;
        }

        return addressList.get(0);
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        googleMap = gMap;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.3521, 103.8198), 10.0f));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
}
