package polytech.foody;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import polytech.foody.notifications.PermissionGranted;
import polytech.foody.notifications.PermissionNotGranted;
import polytech.foody.notifications.factoryNotif.AbstractFactoryNotif;
import polytech.foody.notifications.factoryNotif.PermissionGrantedFactory;
import polytech.foody.notifications.factoryNotif.PermissionNotGrantedFactory;

public class GpsFragment extends Fragment {
    LocationManager locationManager;

    public GpsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gps_fragment, container, false);
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            100);
                }
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                PermissionGranted notification;
                AbstractFactoryNotif factory = new PermissionGrantedFactory();
                notification = factory.buildGranted(getActivity(), "Permission accordée");
                notification.sendNotificationOnChannel();
            }
            else {
                PermissionNotGranted notification;
                AbstractFactoryNotif factory = new PermissionNotGrantedFactory();
                notification = factory.buildNotGranted(getActivity(), "Permission non accordée");
                notification.sendNotificationOnChannel();
            }

        return rootView;
    }

}
