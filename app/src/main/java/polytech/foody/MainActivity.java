package polytech.foody;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL1_ID = "channel post";
    public static final String CHANNEL2_ID = "channel default";
    public static final String CHANNEL3_ID = "channel reservation";
    private static NotificationManager notificationManager;
    boolean isStarred = false;
    boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String txt = "Feed";
        TextView textView = findViewById(R.id.textHeader);
        //textView.setText(txt);

        PostList listS = PostList.getInstance();

        //RestaurantAdapter adapter = new RestaurantAdapter(this, listS);

        ListView list = findViewById(R.id.listViewMain);

        ImageButton favorite = findViewById(R.id.imageView14);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("draw" + favorite.getBackground());
                if(isStarred){
                    favorite.setBackgroundResource(R.drawable.starempty);
                    isStarred = false;}
                else{
                    favorite.setBackgroundResource(R.drawable.star);
                    isStarred = true;}
            }
        });

        ImageButton like = findViewById(R.id.imageView13);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLiked){
                    like.setBackgroundResource(R.drawable.coeur);
                    isLiked = false;}
                else{
                    like.setBackgroundResource(R.drawable.coeurrempli);
                    isLiked = true;}
            }
        });

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

        findViewById(R.id.imageView11).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), FriendProfil.class);
                    startActivity(intent);
                });

        findViewById(R.id.imageView8).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.imageView12).setOnClickListener(
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
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);

                });

        findViewById(R.id.bell).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                    startActivity(intent);
                });

        createNotificationChannels();
    }


    private void createNotificationChannels() {
        //for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //verifie API26+
            NotificationChannel channel1 = new NotificationChannel(CHANNEL1_ID, "channel 1", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channel2 = new NotificationChannel(CHANNEL2_ID, "channel 2", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channel3 = new NotificationChannel(CHANNEL3_ID, "channel 3", NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("Channel for post");
            channel1.setDescription("Channel has low priority");
            channel2.setDescription("Channel for reservation");
            //cannot be changed after
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel1);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel2);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel3);

        }
    }

}
