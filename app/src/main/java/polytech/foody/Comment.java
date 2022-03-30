package polytech.foody;

import java.util.Date;

public class Comment {

    String content;
    Date date;
    Post commentedPost;
    User author;

    public Comment(String content, Post commentedPost, User author){
        this.content = content;
        this.commentedPost = commentedPost;
        this.date = new Date();
        this.author = author;
    }
}
