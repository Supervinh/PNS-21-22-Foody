package polytech.foody.notifications;

import android.content.Context;

public class PermissionNotGranted extends Notifications {

    public PermissionNotGranted(Context context, String title) {
            super(context, 1, title);

    }
}
