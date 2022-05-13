package polytech.foody;

import static polytech.foody.MainActivity.CHANNEL3_ID;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.database.Cursor;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;






public class ReservationActivity extends AppCompatActivity {
    String name="Restaurant";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        Intent intentRestaurant = getIntent();
        name = intentRestaurant.getStringExtra("name");
        String txt = "Réservation";
        TextView textView = findViewById(R.id.textHeader);
        textView.setText(txt);

        TextView restaurantName = findViewById(R.id.textViewRestaurant);
        restaurantName.setText(name);

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

        findViewById( R.id.btn_back ).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_agenda).setOnClickListener(
                click -> {
                    if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_CALENDAR},
                                IAgendaActivity.REQUEST_CALENDAR_READ);
                    }else {
                        this.addToAgenda();
                        //Toast.makeText(this, "Ajouté à l'agenda" ,Toast.LENGTH_SHORT ).show();
                        //findViewById(R.id.btn_agenda).setEnabled(false);
                    }

                }
        );
        findViewById(R.id.buttonReserver).setOnClickListener(click -> {
            DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
            TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
            //recupere le titre
            String title = name;
            String date = datePicker.toString() + timePicker.toString();
            String nbPersonne = ((EditText)findViewById(R.id.textInputNombre)).getText().toString();
            sendNotificationOnChannel(title, datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+1+"/"+datePicker.getYear()+" à "+timePicker.getHour()+"h"+timePicker.getMinute(), nbPersonne, CHANNEL3_ID, NotificationCompat.PRIORITY_DEFAULT);

            //sendNotificationOnChannel(title, datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear()+" à "+timePicker.getHour()+":"+timePicker.getMinute(), nbPersonne, CHANNEL1_ID, NotificationCompat.PRIORITY_LOW);
        });

        findViewById(R.id.bell).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                    startActivity(intent);
                });
    }
    private void addToAgenda(){
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CALENDAR},
                    IAgendaActivity.REQUEST_CALENDAR_WRITE);
        }else{
            DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
            TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
            ContentResolver cr = this.getContentResolver();


            long calID = 2; // Make sure to which calender you want to add event
            long startMillis = 0;
            long endMillis = 0;
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(Calendar.YEAR, datePicker.getYear());
            beginTime.set(Calendar.MONTH, datePicker.getYear());
            beginTime.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
            beginTime.set(Calendar.HOUR, timePicker.getHour());
            beginTime.set(Calendar.MINUTE, timePicker.getMinute());
            Toast.makeText(this, timePicker.getHour() + "", Toast.LENGTH_SHORT).show();
            startMillis = beginTime.getTimeInMillis();

            Calendar endTime = Calendar.getInstance();
            endTime.set(Calendar.YEAR, datePicker.getYear());
            endTime.set(Calendar.MONTH, datePicker.getYear());
            endTime.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
            endTime.set(Calendar.HOUR, timePicker.getHour() + 1);
            endTime.set(Calendar.MINUTE, timePicker.getMinute());
            endMillis = endTime.getTimeInMillis();


            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, this.name);
            values.put(CalendarContract.Events.DESCRIPTION, "Reservation Foody");
            values.put(CalendarContract.Events.CALENDAR_ID, calID);
            values.put(CalendarContract.Events.ALL_DAY, false);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().toString());
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        }
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
