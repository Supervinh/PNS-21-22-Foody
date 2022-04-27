package polytech.foody;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements IPictureActivity {

    public static final String CHANNEL1_ID = "channel low";
    public static final String CHANNEL2_ID = "channel default";
    private static NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String txt = "Foody";
        TextView textView = findViewById(R.id.textHeader);
        //textView.setText(txt);


        findViewById(R.id.btn_add_post).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_home).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_profile).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_search).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);

                });

        findViewById(R.id.btn_back).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), GpsTest.class);
                    startActivity(intent);

                });

        createNotificationChannels();
    }


    private void createNotificationChannels() {
        //for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //verifie API26+
            NotificationChannel channel1 = new NotificationChannel(CHANNEL1_ID, "channel 1", NotificationManager.IMPORTANCE_LOW);
            NotificationChannel channel2 = new NotificationChannel(CHANNEL2_ID, "channel 2", NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("Channel has low priority");
            channel2.setDescription("Channel has default priority");
            //cannot be changed after
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel1);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel2);

        }
    }

}
