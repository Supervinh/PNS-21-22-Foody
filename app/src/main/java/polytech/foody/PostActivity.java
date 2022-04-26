package polytech.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

public class PostActivity extends AppCompatActivity implements IPictureActivity {


    private Bitmap picture;
    private PictureFragment pictureFragment;


    public PostActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        String txt = "Foody";
        TextView textView = findViewById(R.id.textHeader);
        textView.setText(txt);

        pictureFragment = (PictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPicture);
        if (pictureFragment == null) {
            pictureFragment = new PictureFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentPicture, pictureFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

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
    }

    /**
     * callback from requestPermission
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(), "CAMERA authorization granted", Toast.LENGTH_LONG);
                    toast.show();
                    pictureFragment.takePicture();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "CAMERA authorization not granted", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            break;
        }
    }

    /**
     * callback from startActivity
     * @param requesCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requesCode, int resultCode, Intent data) {
        super.onActivityResult(requesCode, resultCode, data);
        if (requesCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                picture = (Bitmap) data.getExtras().get("data");
                pictureFragment.setImage(picture);
            } else if (resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(), "Picture canceled", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "action failed", Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }
}