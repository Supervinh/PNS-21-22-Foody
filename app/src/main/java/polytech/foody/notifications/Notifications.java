package polytech.foody.notifications;

import static polytech.foody.MainActivity.CHANNEL1_ID;
import static polytech.foody.MainActivity.CHANNEL2_ID;

import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import polytech.foody.R;

public abstract class Notifications {
    private static NotificationManager notificationManager;
    protected static int notificationId = 0;
    protected Context context;
    protected String channelId;
    protected NotificationCompat.Builder notification;

    public Notifications(Context context, int priority, String title) {
        this.context = context;
        this.channelId = getChannelId(priority);
        this.notification = buildNotification(context, title, priority);

    }

    private NotificationCompat.Builder buildNotification(Context context, String title, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setPriority(priority)
                .setSmallIcon(R.drawable.ic_launcher_foreground);


        return notification;
    }

    public void sendNotificationOnChannel() {
        NotificationManagerCompat.from(context).notify(notificationId, notification.build());
        notificationId++;
    }

    private String getChannelId(int priority) {
        switch (priority) {
            case NotificationCompat.PRIORITY_LOW:
                return CHANNEL1_ID;
            default:
                return CHANNEL2_ID;
        }
    }
}