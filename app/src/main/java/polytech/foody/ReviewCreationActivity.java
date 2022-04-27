package polytech.foody;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_creation);

        String txt = "Avis";
        TextView textView = findViewById(R.id.textHeader);
        textView.setText(txt);

    }
}
