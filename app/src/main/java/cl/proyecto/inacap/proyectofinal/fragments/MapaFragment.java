package cl.proyecto.inacap.proyectofinal.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cl.proyecto.inacap.proyectofinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    GoogleMap map;

    public MapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng puq = new LatLng(-53.1625446, -70.907785);
        map.addMarker(new MarkerOptions().position(puq).title("Punta Arenas"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(puq,16));

        map.getUiSettings().setZoomControlsEnabled(true);
    }
}
