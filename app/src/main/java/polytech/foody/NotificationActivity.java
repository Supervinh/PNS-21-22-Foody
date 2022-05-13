package polytech.foody;


import static polytech.foody.MainActivity.CHANNEL1_ID;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        String txt = "Notifications";
        TextView textView = findViewById(R.id.textHeader);
        textView.setText(txt);

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

        findViewById(R.id.textView2).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.imageView9).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.textView3).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), CommentCreationActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.imageView10).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), CommentCreationActivity.class);
                    startActivity(intent);
                });


        findViewById(R.id.btn_search).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);

                });

        findViewById(R.id.btn_back).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                });
    }
}
