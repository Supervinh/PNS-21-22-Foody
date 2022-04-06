package polytech.foody;

import java.util.Date;

public abstract class Comment {

    String content;
    Date date;
    User author;

    public Comment(String content, User author){
        this.content = content;
        this.date = new Date();
        this.author = author;
    }
}
