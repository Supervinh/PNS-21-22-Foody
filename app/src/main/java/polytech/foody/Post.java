package polytech.foody;


import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post extends AppCompatActivity implements IPictureActivity {
    Restaurant place;
    Bitmap image;
    int nbLikes;
    List<Comment> comments;
    String content;
    Date date;
    User author;


    public Post(String content, User author, Restaurant place, Bitmap image) {
        this.place = place;
        this.image = image;
        this.nbLikes = 0;
        this.comments = new ArrayList<>();
        this.content = content;
        this.date = new Date();
        this.author = author;
    }
    public Post(String content, User author, Bitmap image) {
        this.place = null;
        this.image = image;
        this.nbLikes = 0;
        this.comments = new ArrayList<>();
        this.content = content;
        this.date = new Date();
        this.author = author;
    }

}
