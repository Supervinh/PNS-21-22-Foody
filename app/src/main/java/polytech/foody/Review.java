package polytech.foody;

import android.graphics.Bitmap;

public class Review extends Post{
    int score;

    public Review(String content, Post commentedPost, User author, Restaurant place, Bitmap image, int score) {
        super(content, author, place, image);
        this.score = score;
    }
}
