package polytech.foody.notifications.factoryNotif;

import android.content.Context;

import polytech.foody.GpsTest;
import polytech.foody.notifications.PermissionGranted;
import polytech.foody.notifications.PermissionNotGranted;

public abstract class AbstractFactoryNotif {

    public abstract PermissionNotGranted buildNotGranted(Context context, String title);

    public abstract PermissionGranted buildGranted(Context context, String title);
}
