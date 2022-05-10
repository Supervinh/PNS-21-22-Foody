package polytech.foody;


import static polytech.foody.MainActivity.CHANNEL1_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notifications extends AppCompatActivity {

    private int notificationId = 0;

    private void sendNotificationOnChannel(String title, String message, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.upload)
                .setContentTitle("Vous venez de poster une photo de " + title)
                .setContentText("avec le message: " + message)
                .setPriority(priority);
        switch (channelId){
            case CHANNEL1_ID: notification.setSmallIcon(R.drawable.upload); break;
        }
        NotificationManagerCompat.from(this).notify(notificationId, notification.build());
    }
}
