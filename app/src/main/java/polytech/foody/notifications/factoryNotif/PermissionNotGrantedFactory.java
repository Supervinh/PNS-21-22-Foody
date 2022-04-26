package polytech.foody.notifications.factoryNotif;

import android.content.Context;

import polytech.foody.notifications.PermissionGranted;
import polytech.foody.notifications.PermissionNotGranted;

public class PermissionNotGrantedFactory extends AbstractFactoryNotif{
    @Override
    public PermissionNotGranted buildNotGranted(Context context, String title) {
        return new PermissionNotGranted(context, title);
    }

    @Override
    public PermissionGranted buildGranted(Context context, String title) {
        return null;
    }
}
