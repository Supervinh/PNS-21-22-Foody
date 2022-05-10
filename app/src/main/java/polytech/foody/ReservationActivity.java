package polytech.foody;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static polytech.foody.MainActivity.CHANNEL1_ID;
import static polytech.foody.MainActivity.CHANNEL3_ID;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.telecom.Call;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import polytech.foody.notifications.PermissionGranted;

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
            sendNotificationOnChannel(title, date, nbPersonne, CHANNEL3_ID, NotificationCompat.PRIORITY_DEFAULT);


            final int callbackId = 42;
            checkPermission(callbackId, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR);

            long calID = 2; // Make sure to which calender you want to add event
            long startMillis = 0;
            long endMillis = 0;
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(Calendar.YEAR, 2022);
            beginTime.set(Calendar.MONTH, Calendar.MAY);
            beginTime.set(Calendar.DAY_OF_MONTH, 13);
            beginTime.set(Calendar.HOUR,9);
            startMillis = beginTime.getTimeInMillis();
            Calendar endTime = Calendar.getInstance();
            endTime.set(Calendar.YEAR, 2022);
            endTime.set(Calendar.MONTH, Calendar.MAY);
            endTime.set(Calendar.DAY_OF_MONTH, 13);
            endTime.set(Calendar.HOUR,10);
            endMillis = endTime.getTimeInMillis();
            Toast.makeText(ReservationActivity.this,startMillis+"_"+endMillis, Toast.LENGTH_SHORT ).show();
            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, "Hackathon");
            values.put(CalendarContract.Events.DESCRIPTION, "do some code");
            values.put(CalendarContract.Events.CALENDAR_ID, calID);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            //Toast.makeText(ReservationActivity.this,uri+"", Toast.LENGTH_SHORT ).show();
            // get the event ID that is the last element in the Uri
            //long eventID = Long.parseLong(uri.getLastPathSegment());

            sendNotificationOnChannel(title, date, nbPersonne, CHANNEL1_ID, NotificationCompat.PRIORITY_LOW);
        });
    }
    private void checkPermission(int callbackId, String... permissionsId) {
        boolean permissions = true;
        for (String p : permissionsId) {
            permissions = permissions && ContextCompat.checkSelfPermission(this, p) == PERMISSION_GRANTED;
        }

        if (!permissions)
            ActivityCompat.requestPermissions(this, permissionsId, callbackId);
    }

    private int notificationId = 0;

    private void sendNotificationOnChannel(String title, String date, String nbPersonne, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.reservation)
                .setContentTitle("Vous venez de reserver le restaurant " + title)
                .setContentText("Réservation le "+date+" pour "+nbPersonne+" personnes.")
                .setPriority(priority);
        switch (channelId){
            case CHANNEL3_ID: notification.setSmallIcon(R.drawable.reservation); break;
        }
        NotificationManagerCompat.from(this).notify(notificationId, notification.build());
    }
}
