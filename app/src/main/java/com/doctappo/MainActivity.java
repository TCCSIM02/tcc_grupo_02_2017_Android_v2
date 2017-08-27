package com.doctappo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Config.ApiParams;
import adapters.CategoryAdapter;
import configfcm.MyFirebaseRegister;
import models.ActiveModels;
import models.BusinessModel;
import models.CategoryModel;
import util.GPSTracker;
import util.VJsonRequest;

public class MainActivity extends CommonActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    ArrayList<CategoryModel> categoryArray;
    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    ProgressBar progressBar1;
    Toolbar toolbar;
    BusinessModel selected_salon;
    GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setHeaderTitle(getString(R.string.app_name));
        selected_salon = ActiveModels.BUSINESS_MODEL;


        com.google.android.gms.maps.MapFragment mapFragment = (com.google.android.gms.maps.MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        categoryArray = new ArrayList<>();

        if (!common.getSessionBool("fcm_registered") && common.is_user_login()){
            MyFirebaseRegister fireReg = new MyFirebaseRegister(this);
            fireReg.RegisterUser(common.get_user_id());
        }

/*        TextView textView1 = (TextView)findViewById(R.id.textView);
        textView1.setTypeface(getCustomFont());*/
        //bindView();
        //loadData();

        //AutoCOmpletetextView
        String[] language ={"Odontologista","Cardiologista","Oftamologista","Ginecologista","Radiologista","Otorrinolaringologista","Pediatra","Geriatra"};

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        //actv.setTextColor(Color.RED);
    }

    public void bindView(){
        categoryRecyclerView = (RecyclerView) findViewById(R.id.rv_artist);
        GridLayoutManager layoutManager
                = new GridLayoutManager(this, 3);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryArray);
        categoryRecyclerView.setAdapter(categoryAdapter);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        setProgressBarAnimation(progressBar1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onResume() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View navHeader = navigationView.getHeaderView(0);
        ((TextView)navHeader.findViewById(R.id.menu_title)).setTypeface(common.getCustomFont());


        navigationView.setNavigationItemSelectedListener(this);
        Menu nav_Menu = navigationView.getMenu();
        /*if (!common.is_user_login()){

            nav_Menu.findItem(R.id.nav_appointment).setVisible(false);
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
            nav_Menu.findItem(R.id.nav_password).setVisible(false);
            nav_Menu.findItem(R.id.nav_profile).setVisible(false);
            nav_Menu.findItem(R.id.nav_login).setVisible(true);
            navHeader.findViewById(R.id.txtFullName).setVisibility(View.GONE);
            navHeader.findViewById(R.id.textEmailId).setVisibility(View.GONE);
        }else{*/
            nav_Menu.findItem(R.id.nav_appointment).setVisible(true);
            nav_Menu.findItem(R.id.nav_logout).setVisible(true);
            nav_Menu.findItem(R.id.nav_password).setVisible(true);
            nav_Menu.findItem(R.id.nav_profile).setVisible(true);
            nav_Menu.findItem(R.id.nav_login).setVisible(false);
            navHeader.findViewById(R.id.txtFullName).setVisibility(View.VISIBLE);
            navHeader.findViewById(R.id.textEmailId).setVisibility(View.VISIBLE);
            ((TextView)navHeader.findViewById(R.id.txtFullName)).setText(common.getSession(ApiParams.USER_FULLNAME));
            ((TextView)navHeader.findViewById(R.id.textEmailId)).setText(common.getSession(ApiParams.USER_EMAIL));
        //}
        super.onResume();
    }
    /* TESTE COMMIT*/
    public void loadData(){
        VJsonRequest vJsonRequest = new VJsonRequest(MainActivity.this, ApiParams.CATEGORY_LIST,
                new VJsonRequest.VJsonResponce(){
                    @Override
                    public void VResponce(String responce) {

                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<CategoryModel>>() {
                        }.getType();
                        categoryArray.clear();
                        categoryArray.addAll((Collection<? extends CategoryModel>) gson.fromJson(responce, listType));
                        categoryAdapter.notifyDataSetChanged();
                        progressBar1.setVisibility(View.GONE);

                    }
                    @Override
                    public void VError(String responce) {
                        progressBar1.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_location:

                return true;
           
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this,UpdateProfileActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_password) {
            Intent intent = new Intent(MainActivity.this,ChangePasswordActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_appointment) {
            Intent intent = new Intent(MainActivity.this,MyAppointmentsActivity.class);
            startActivity(intent);

        }else if(id == R.id.nav_logout){
            common.logOut();
        }else if(id == R.id.nav_login){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void searchViewClick(View view){
        /*Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        startActivity(intent);*/
    }

    public void onMapReady(GoogleMap map) {
        Double lat = Double.parseDouble("-23.5453814");/*selected_salon.getBus_latitude()*/
        Double lon = Double.parseDouble("-46.4339783");/*selected_salon.getBus_longitude()*/
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(lat, lon), 12));

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_icon))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .title(/*selected_salon.getBus_title()*/"Nilton")
                .position(new LatLng(lat, lon)));

        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.doctors))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .title(/*selected_salon.getBus_title()*/"Cardiologista 1")
                .position(new LatLng(-23.5443763, -46.4395297)));

        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.doctors))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .title(/*selected_salon.getBus_title()*/"Médico 2")
                .position(new LatLng(-23.5432787, -46.434249)));

        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dental))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .title(/*selected_salon.getBus_title()*/"Odontologista 3")
                .position(new LatLng(-23.5456743, -46.4371517)));

    }
}
