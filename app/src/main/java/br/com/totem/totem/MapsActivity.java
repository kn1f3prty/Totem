package br.com.totem.totem;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.totem.totem.model.Cliente;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Address> addresses;
    private double latitude = 0;
    private double longitude = 0;
    public static final String CLIENTE_SELECIONADO = "clienteSelecionado";
    private String nomeCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Cliente clienteSelecionado = (Cliente) getIntent().getSerializableExtra(CLIENTE_SELECIONADO);

        nomeCliente = clienteSelecionado.getNome();

        Geocoder geocoder = new Geocoder(this);


        while(addresses==null){
            try {
                addresses = geocoder.getFromLocationName(clienteSelecionado.getEndereco(), 1);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Address address = addresses.get(0);
        if (addresses.size() > 0) {
            latitude = address.getLatitude();
            longitude = address.getLongitude();
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng local = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(local).title(nomeCliente));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(local,18));
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }
}
