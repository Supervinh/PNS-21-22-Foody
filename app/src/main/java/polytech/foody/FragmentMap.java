package polytech.foody;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * A styled map using JSON styles from a raw resource.
 */
public class FragmentMap extends Fragment implements OnMapReadyCallback {

    private static final String TAG = FragmentMap.class.getSimpleName();
    MapView mapView;
    private GoogleMap googleMap;
    private Restaurant restaurant;

    public FragmentMap () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mapView =rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        mapView.onResume();

        mapView.getMapAsync(this);

        return rootView;
    }


    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready for use.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(this.getActivity());

        String address = restaurant.address;

        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> RestaurantLocations = null;

        try {
            RestaurantLocations = geocoder.getFromLocationName(address, 3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address restaurantLocation = RestaurantLocations.get(0);
        LatLng restaurantMarker = new LatLng(restaurantLocation.getLatitude(), restaurantLocation.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .position(restaurantMarker)
                .title("Restaurant Location"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(restaurantMarker));

    }




}
