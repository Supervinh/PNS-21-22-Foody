package polytech.foody;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class RestaurantActivity extends FragmentActivity implements IGPSActivity, OnMapReadyCallback {
    private FragmentMap gpsFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intentRestaurant = getIntent();
        String name = intentRestaurant.getStringExtra("name");
        String description = intentRestaurant.getStringExtra("description");
        int image = intentRestaurant.getIntExtra( "image", 0);
        String adress = intentRestaurant.getStringExtra("address");
        //adress = "235 chemin du Béal, 06480";
        int nutriPoint = intentRestaurant.getIntExtra("nutriPoints", 10 );
        int visitors = intentRestaurant.getIntExtra("visitors", 110);
        double score = intentRestaurant.getDoubleExtra("score", 4.5);
        int nombreDeNotes = intentRestaurant.getIntExtra("nombreDeNotes", 43);

        Restaurant restaurant = new Restaurant(name, description, image, adress, nutriPoint, visitors, score, nombreDeNotes);
        String txt = restaurant.name;
        TextView textView = findViewById(R.id.textHeader);
        textView.setText(txt);

       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        gpsFragment = (FragmentMap) getSupportFragmentManager().findFragmentById(R.id.restaurantLocation);
        if (gpsFragment == null) {
            gpsFragment = new FragmentMap(this);
            FragmentTransaction gpsTransaction = getSupportFragmentManager().beginTransaction();
            gpsTransaction.replace(R.id.restaurantLocation, gpsFragment);
            gpsTransaction.addToBackStack(null);
            gpsTransaction.commit();
        }*/
        FragmentMap mapFrag = new FragmentMap(restaurant);
        getSupportFragmentManager().beginTransaction().replace(R.id.map, mapFrag).commit();

        Fragment restaurantFrag = new FragmentRestaurant(restaurant);
        getSupportFragmentManager().beginTransaction().replace(R.id.restaurantLocation, restaurantFrag).commit();
        //Fragment restaurantFrag = new FragmentRestaurant();
        //getSupportFragmentManager().beginTransaction().replace(R.id.restaurantLocation, restaurantFrag).commit();

        findViewById(R.id.btn_add_post).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_home).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_profile).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_search).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);

                });

        findViewById( R.id.btn_back ).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), GpsTest.class);
                    startActivity(intent);

                });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LinearLayout linearLayout = findViewById(R.id.myLinearLayout);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void moveCamera() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gpsFragment.getPosition(), 15f));
    }
}
