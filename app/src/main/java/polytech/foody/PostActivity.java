package polytech.foody;

import static polytech.foody.MainActivity.CHANNEL1_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentTransaction;

public class PostActivity extends AppCompatActivity implements IPictureActivity, IStorageActivity {

    private Bitmap picture;
    private PictureFragment pictureFragment;
    private StorageFragment storageFragment;

    public PostActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        String txt = "Post";
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

        storageFragment = (StorageFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentStorage);
        if (storageFragment == null) {
            storageFragment = new StorageFragment(this);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentStorage, storageFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }


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

        findViewById(R.id.buttonPost).setOnClickListener(click -> {
            //recupere le titre
            String title = ((EditText)findViewById(R.id.textInputLayout)).getText().toString();
            String message = ((EditText)findViewById(R.id.textInputLayout2)).getText().toString();
            sendNotificationOnChannel(title, message, CHANNEL1_ID, NotificationCompat.PRIORITY_LOW);
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
                    Toast toast = Toast.makeText(getApplicationContext(), "Accès à la caméra autorisé", Toast.LENGTH_LONG);
                    toast.show();
                    pictureFragment.takePicture();
                    storageFragment.setEnableSaveButton();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Accès à la caméra NON autoriséd", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            break;
            case REQUEST_MEDIA_WRITE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    storageFragment.saveToInternalStorage(picture);
                    Toast toast = Toast.makeText(getApplicationContext(), "Accès au stockage autorisé", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Accès au stockage NON autorisé", Toast.LENGTH_LONG);
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
                storageFragment.setEnableSaveButton();
            } else if (resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(), "Picture canceled", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "action failed", Toast.LENGTH_LONG);
                toast.show();
            }
        }


    }

    private int notificationId = 0;

    private void sendNotificationOnChannel(String title, String message, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.uploadpicture)
                .setContentTitle("Vous venez de poster une photo de " + title)
                .setContentText("avec le message: " + message)
                .setPriority(priority);
        switch (channelId){
            case CHANNEL1_ID: notification.setSmallIcon(R.drawable.uploadpicture); break;
        }
        NotificationManagerCompat.from(this).notify(notificationId, notification.build());
    }

    @Override
    public void onPictureLoad(Bitmap bitmap) {

    }

    @Override
    public Bitmap getPictureToSave() {
        return picture;
    }
}