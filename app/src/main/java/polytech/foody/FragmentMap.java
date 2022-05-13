package polytech.foody;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
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
import java.util.Locale;

/**
 * A styled map using JSON styles from a raw resource.
 */
public class FragmentMap extends Fragment implements OnMapReadyCallback {

    private static final String TAG = FragmentMap.class.getSimpleName();
    MapView mapView;
    private GoogleMap googleMap;
    private Restaurant restaurant;
    private IGPSActivity igpsActivity;
    private Location currentLocation;

    public FragmentMap() {
    }

    //public FragmentMap (IGPSActivity activity) {
    // igpsActivity = activity;
    //}

    public FragmentMap(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = rootView.findViewById(R.id.mapView);
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
    public void onMapReady(@NonNull GoogleMap googleMap) {

        MapsInitializer.initialize(this.requireActivity());

        String address = restaurant.address;

        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> RestaurantLocations = null;
        currentLocation = new Location(String.valueOf(0));

        try {
            RestaurantLocations = geocoder.getFromLocationName(address, 3);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ActivityCompat.checkSelfPermission(this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) (getActivity().getSystemService(Context.LOCATION_SERVICE));
            LocationListener listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    currentLocation = location;
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
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, listener);

            Address tmp = RestaurantLocations.get(0);
            Location tmpLocation = new Location("");
            tmpLocation.setLatitude(RestaurantLocations.get(0).getLatitude());
            tmpLocation.setLongitude(RestaurantLocations.get(0).getLongitude());


            float distance = tmpLocation.distanceTo(currentLocation);

            for (Address address1 : RestaurantLocations) {
                Location location = new Location("");
                location.setLatitude(address1.getLatitude());
                location.setLongitude(address1.getLongitude());
                if (location.distanceTo(currentLocation) < distance) {
                    distance = location.distanceTo(currentLocation);
                    tmp = address1;
                }
            }
            RestaurantLocations.set(0, tmp);
        }
        Address restaurantLocation = RestaurantLocations.get(0);
        LatLng restaurantMarker = new LatLng(restaurantLocation.getLatitude(), restaurantLocation.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .position(restaurantMarker)
                .title(restaurant.name)
                .visible(true));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantMarker, 15f));

    }

    LatLng getPosition() {
        return new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
    }

    String getPlaceName() throws IOException {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
        return addresses.get(0).getLocality();
    }

    void setPlaceName(String placeName) {
        //TODO
    }

}
