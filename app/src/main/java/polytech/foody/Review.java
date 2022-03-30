package polytech.foody;

public class Review extends Post{
    int score;

    public Review(String content, Post commentedPost, User author, Restaurant place, String image, int score) {
        super(content, commentedPost, author, place, image);
        this.score = score;
    }
}
