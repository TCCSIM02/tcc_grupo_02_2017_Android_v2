package com.doctappo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import Config.ApiParams;
import models.CategoryModel;
import models.ModelUnidade;
import to.TOUnidade;

public class MainActivity extends CommonActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    ArrayList<CategoryModel> categoryArray;
    Toolbar toolbar;

    protected Context context;

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        com.google.android.gms.maps.MapFragment mapFragment = (com.google.android.gms.maps.MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setHeaderTitle(getString(R.string.app_name));

        categoryArray = new ArrayList<>();

        //AutoCOmpletetextView
        String[] busca = {"Odontologista", "Cardiologista", "Oftamologista", "Ginecologista", "Radiologista", "Otorrinolaringologista", "Pediatra", "Geriatra"};

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, busca);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        //actv.setTextColor(Color.RED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    protected void onResume() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View navHeader = navigationView.getHeaderView(0);
        ((TextView) navHeader.findViewById(R.id.menu_title)).setTypeface(common.getCustomFont());

        navigationView.setNavigationItemSelectedListener(this);
        Menu nav_Menu = navigationView.getMenu();
        if (!common.is_user_login()) {
            nav_Menu.findItem(R.id.nav_novo_usuario).setVisible(true);
            nav_Menu.findItem(R.id.nav_appointment).setVisible(false);
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
            nav_Menu.findItem(R.id.nav_password).setVisible(false);
            nav_Menu.findItem(R.id.nav_profile).setVisible(false);
            nav_Menu.findItem(R.id.nav_login).setVisible(true);
            navHeader.findViewById(R.id.txtFullName).setVisibility(View.GONE);
            navHeader.findViewById(R.id.textEmailId).setVisibility(View.GONE);
        } else {
            nav_Menu.findItem(R.id.nav_appointment).setVisible(true);
            nav_Menu.findItem(R.id.nav_logout).setVisible(true);
            nav_Menu.findItem(R.id.nav_password).setVisible(true);
            nav_Menu.findItem(R.id.nav_profile).setVisible(true);
            nav_Menu.findItem(R.id.nav_login).setVisible(false);
            navHeader.findViewById(R.id.txtFullName).setVisibility(View.VISIBLE);
            navHeader.findViewById(R.id.textEmailId).setVisibility(View.VISIBLE);
            ((TextView) navHeader.findViewById(R.id.txtFullName)).setText(common.getSession(ApiParams.USER_FULLNAME));
            ((TextView) navHeader.findViewById(R.id.textEmailId)).setText(common.getSession(ApiParams.USER_EMAIL));
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_location:
                onCoracaoIconClick();
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
            Intent intent = new Intent(MainActivity.this, UpdateProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_password) {
            Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_novo_usuario) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_info_historica) {
            Intent intent = new Intent(MainActivity.this,   CadastroActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_appointment) {
            Intent intent = new Intent(MainActivity.this, MyAppointmentsActivity.class);
            //Intent intent = new Intent(MainActivity.this,TimeSlotActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            common.logOut();
        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onMapReady(GoogleMap map) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            common.setToastMessage("Conceda as permissões de localização");
            return;
        }
        map.setMyLocationEnabled(true);


        /* BUSCA UNIDADES */
        ModelUnidade modelUnidade = new ModelUnidade();

        ArrayList<TOUnidade> listaUnidade = null;
        try {
            listaUnidade = modelUnidade.listarUnidadesMap();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < listaUnidade.size();i++){

            Marker markerUnidade = map.addMarker(new MarkerOptions().position(new LatLng(listaUnidade.get(i).getLatitude(), listaUnidade.get(i).getLongitude()))
                    .title(listaUnidade.get(i).getNomeFantasia())
                    .snippet("Endereço: " + listaUnidade.get(i).getEndereco() +", "+listaUnidade.get(i).getNumeroEndereco() +
                            "\nCEP: " + listaUnidade.get(i).getCep() +
                            "\nTelefone: " + listaUnidade.get(i).getTel1() + "\n")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_icon_coracao))
                    );

            markerUnidade.setTag(listaUnidade.get(i));

            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                @Override
                public View getInfoWindow(Marker arg0) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {

                    Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.

                    LinearLayout info = new LinearLayout(context);
                    info.setOrientation(LinearLayout.VERTICAL);

                    TextView title = new TextView(context);
                    title.setTextColor(Color.BLACK);
                    title.setGravity(Gravity.CENTER);
                    title.setTypeface(null, Typeface.BOLD);
                    title.setText(marker.getTitle());

                    TextView snippet = new TextView(context);
                    snippet.setTextColor(Color.GRAY);
                    snippet.setText(marker.getSnippet());

                    Button btnUnidade = new Button(context);
                    btnUnidade.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    btnUnidade.setTextColor(Color.WHITE);
                    btnUnidade.setText("Agende conosco ;)");

                    info.addView(title);
                    info.addView(snippet);

                    if(!marker.getTitle().equals("Sua posição")) {
                        info.addView(btnUnidade);
                    }
                    return info;
                }
            });

            final ArrayList<TOUnidade> finalListaUnidade = listaUnidade;
            map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                public void onInfoWindowClick(Marker marker)
                {
                    Intent intent = new Intent(MainActivity.this, CriarAgendamentoActivity.class);

                    TOUnidade unidade = (TOUnidade) marker.getTag();

                    intent.putExtra("codUnidade",unidade.getCodUnidade());
                    intent.putExtra("nomeFantasiaUnidade", unidade.getNomeFantasia());
                    startActivity(intent);

                }
            });
        }
    }

    public void onCoracaoIconClick(){
        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}

