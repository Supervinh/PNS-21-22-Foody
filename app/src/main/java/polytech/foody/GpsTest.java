package polytech.foody;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class GpsTest extends AppCompatActivity implements LocationListener {

    EditText et_status;
    EditText et_position;
    LocationManager locationManager;
    private GpsFragment gpsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_test);

        et_status = (EditText) findViewById(R.id.et_status);
        et_position = (EditText) findViewById(R.id.et_position);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsFragment = (GpsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentGps);
        if (gpsFragment == null) {
            gpsFragment = new GpsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentGps, gpsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION},100);
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("status", Arrays.toString(grantResults));
        if (grantResults.length!=0){
            if(grantResults[0] == 0)Toast.makeText(GpsTest.this, "Permission GPS accordée", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        et_position.setText("Latitude: " + location.getLatitude() + ", longitude: " + location.getLongitude());

    }

    @Override
    public void onProviderDisabled(String provider) {
        et_status.setText("Disabled");
    }

    @Override
    public void onProviderEnabled(String provider) {
        et_status.setText("Enabled");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}