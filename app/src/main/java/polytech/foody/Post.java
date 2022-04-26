package polytech.foody;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post extends AppCompatActivity implements IPictureActivity {
    Restaurant place;
    String image;
    int nbLikes;
    List<Comment> comments;
    String content;
    Date date;
    User author;

    public Post(String content, User author, Restaurant place, String image) {
        this.place = place;
        this.image = image;
        this.nbLikes = 0;
        this.comments = new ArrayList<>();
        this.content = content;
        this.date = new Date();
        this.author = author;
    }

}
