package polytech.foody;


import java.util.ArrayList;
import java.util.List;

public class Post extends Comment {
    Restaurant place;
    String image;
    int nbLikes;
    List<Comment> comments;


    public Post(String content, Post commentedPost, User author, Restaurant place, String image) {
        super(content, commentedPost, author);
        this.place = place;
        this.image = image;
        this.nbLikes = 0;
        this.comments = new ArrayList<>();
    }
}
