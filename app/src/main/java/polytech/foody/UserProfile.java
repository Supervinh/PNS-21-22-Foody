package polytech.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);



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
                    Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                    startActivity(intent);

                });

        findViewById( R.id.btn_back ).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), GpsTest.class);
                    startActivity(intent);

                });
    }

}
