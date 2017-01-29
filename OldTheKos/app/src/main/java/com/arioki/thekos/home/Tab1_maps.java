package com.arioki.thekos.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arioki.thekos.DetailKos;
import com.arioki.thekos.R;
import com.arioki.thekos.helper.Http;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1_maps extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private String[] nama, alamat, harga, jam, update, alatmusik, call, id,
            gambar, ratingalatmusik, ratingrecording, ratingtempat, distance;
    int numData;
    private SwipeRefreshLayout swipe;
    LatLng latLng[];
    Boolean markerD[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        swipe = (SwipeRefreshLayout) rootView.findViewById(R.id.tab1_swipe);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        swipe.setEnabled(false);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.getUiSettings().setCompassEnabled(true);
                googleMap.getUiSettings().setMapToolbarEnabled(true);
                // LatLng sydney = new LatLng(getLatitude(), getLongitude());
                LatLng sydney = new LatLng(-2.996788, 104.776956);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                getJsonLokasi();
            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void getJsonLokasi() {
        // String url = "http://cloudofoasis.com/api/Ivan/getStudio.php";
        String url = Http.server + "home_maps";
        swipe.setRefreshing(true);
        Log.d("Data", url);
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //parsing json
                        numData = response.length();
                        id = new String[numData];
                        latLng = new LatLng[numData];
                        markerD = new Boolean[numData];
                        nama = new String[numData];
                        alamat = new String[numData];
                        Log.d("Json.respon", String.valueOf(numData));
                        try {
                            for (int i = 0; i < numData; i++) {
                                JSONObject item = response.getJSONObject(i);
                                id[i] = item.getString("id");
                                latLng[i] = new LatLng(item.getDouble("latitude"),
                                        item.getDouble("longitude"));
                                nama[i] = item.getString("nama");
                                alamat[i] = item.getString("alamat");

                                markerD[i] = false;
                                googleMap.addMarker(new MarkerOptions()
                                        .position(latLng[i])
                                        .title(nama[i])
                                        .snippet(alamat[i])
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapslogo)));


                                Log.d("jsonMapid", String.valueOf(id[i]));
                                Log.d("jsonMapLatlang", String.valueOf(latLng[i]));
                                Log.d("jsonMapnama", String.valueOf(nama[i]));
                                Log.d("jsonMapalamat", String.valueOf(alamat[i]));

                                //marker klik
                                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        for (int i = 0; i < numData; i++) {

                                            if (marker.getTitle().equals(nama[i])) {
                                                if (markerD[i]) {
                                                    Log.d("DEBUG_", "panggil activity");
                                                    DetailKos.id = id[i];
                                                    DetailKos.nama = nama[i];
                                                    DetailKos.alamat = alamat[i];

                                                    Intent intent = new Intent(getActivity(), DetailKos.class);
                                                    startActivity(intent);
                                                    markerD[i] = false;
                                                } else {
                                                    Log.d("DEBUG_", "show info");
                                                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15.5f));
                                                    markerD[i] = true;
                                                    marker.showInfoWindow();
                                                }
                                            } else {
                                                markerD[i] = false;
                                            }
                                        }
                                    }
                                });

                            }
                            swipe.setRefreshing(false);
                        } catch (JSONException e) {
                            getJsonLokasi();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               getJsonLokasi();
            }
        });

        Volley.newRequestQueue(getActivity()).add(request);
    }

    @Override
    public void onRefresh() {
        googleMap.clear();
        getJsonLokasi();
    }
}
