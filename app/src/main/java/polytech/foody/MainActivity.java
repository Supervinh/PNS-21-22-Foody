package polytech.foody;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity implements IPictureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String txt = "Foody";
        TextView textView = findViewById(R.id.textHeader);
        textView.setText(txt);


        findViewById( R.id.btn_add_post ).setOnClickListener(
            click -> {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);

        });
    }
}