package br.com.totem.totem;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;


@SuppressLint("Registered")
public class ApiGeocoding extends Activity implements LocationListener {

    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apigeocoding);

        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvDescricao = (TextView) findViewById(R.id.tvDescricao);

        LocationManager lm = (LocationManager)
                this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        tvLatitude.setText(String.valueOf(latitude));
        tvLongitude.setText(String.valueOf(longitude));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void btBuscarDescricaoOnClick(View v) {
        new Conexao().execute(tvLatitude.getText().toString(),
                tvLongitude.getText().toString());
    }

    class Conexao extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String url = "http://maps.googleapis.com/maps/api/geocode/xml?latlng="
                        + params[0] + "," + params[1]
                        + "&sensor=false";
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet request = new HttpGet(url);
                InputStream in = httpclient.execute(request).getEntity()
                        .getContent();

                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();

                br = new BufferedReader(new InputStreamReader(in));

                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();

                }

                String resposta = sb.toString();

                return resposta.substring(
                        resposta.indexOf( "<formatted_address>" ) + 19,
                        resposta.indexOf( "</formatted_address>" )  );

            } catch ( Exception e ) {
                return "Erro: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String msg) {
            tvDescricao.setText(msg);
        }

    }
}