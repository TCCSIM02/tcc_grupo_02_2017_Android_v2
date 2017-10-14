package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctappo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import models.ActiveModels;
import models.BusinessModel;
import util.CommonClass;
import util.GPSTracker;

public class MapFragment extends Fragment   implements OnMapReadyCallback {
    CommonClass common;
    ArrayList<BusinessModel> postItems;
    String PREF_BUSINESS = "pref_business";
    GPSTracker gpsTracker;
    Double cur_latitude, cur_longitude;

    Activity act;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_map, container, false);
        act = getActivity();

        common = new CommonClass(act);


        gpsTracker = new GPSTracker(act);
        if (gpsTracker.canGetLocation())
        {
            if(gpsTracker.getLatitude()!=0.0)
                cur_latitude =	gpsTracker.getLatitude();
            if(gpsTracker.getLongitude()!=0.0)
                cur_longitude = gpsTracker.getLongitude();
        }else{
            gpsTracker.showSettingsAlert();
        }

        return  rootView;
    }

    @Override
    public void onResume() {
        postItems = ActiveModels.LIST_BUSINESS_MODEL;
        com.google.android.gms.maps.MapFragment mapFragment = (com.google.android.gms.maps.MapFragment) act.getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (cur_latitude != null && cur_longitude != null) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(cur_latitude, cur_longitude), 15));

            // You can customize the marker image using images bundled with
            // your app, or dynamically generated bitmaps.
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_icon))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .title("Sua posição")
                    .draggable(true)
                    .position(new LatLng(cur_latitude, cur_longitude)));
        }
    }
}
