package polytech.foody;

import static polytech.foody.MainActivity.CHANNEL1_ID;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReservationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        String txt = "Réservation";
        TextView textView = findViewById(R.id.textHeader);
        textView.setText(txt);

        findViewById( R.id.btn_add_post ).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                    startActivity(intent);
                });

        findViewById( R.id.btn_home ).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });

        findViewById( R.id.btn_profile ).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                    startActivity(intent);
                });

        findViewById(R.id.buttonReserver).setOnClickListener(click -> {
            //recupere le titre
            String title = ((EditText)findViewById(R.id.textInputNom)).getText().toString();
            String date = ((EditText)findViewById(R.id.textInputDate)).getText().toString();
            String nbPersonne = ((EditText)findViewById(R.id.textInputNombre)).getText().toString();
            sendNotificationOnChannel(title, date, nbPersonne, CHANNEL1_ID, NotificationCompat.PRIORITY_LOW);
        });
    }

    private int notificationId = 0;

    private void sendNotificationOnChannel(String title, String date, String nbPersonne, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.uploadpicture)
                .setContentTitle("Vous venez de reserver le restaurant " + title)
                .setContentText("Réservation le "+date+" pour "+nbPersonne+" personnes.")
                .setPriority(priority);
        switch (channelId){
            case CHANNEL1_ID: notification.setSmallIcon(R.drawable.uploadpicture); break;
        }
        NotificationManagerCompat.from(this).notify(notificationId, notification.build());
    }
}
