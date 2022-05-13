package polytech.foody;

import java.util.ArrayList;
import java.util.List;

public class PostList {
    private static PostList instance;
    User author;
    List<Post> list;


    public PostList() {
        list = new ArrayList<>();
        author = new User("Rick", "Sanchez");
    }

    public static PostList getInstance() {
        if (PostList.instance == null) {
            synchronized (Restaurants.class) {
                if (PostList.instance == null)
                    PostList.instance = new PostList();
            }
        }
        return instance;
    }

    public int size() {
        return list.size();
    }

    public Post get(int pos) {
        return list.get(pos);
    }

    public List<Post> getPostByAuthor(User author) {
        List<Post> authorPost = new ArrayList<>();
        for (Post post : list) {
            if (post.author.equals(author))
                authorPost.add(post);
        }
        return authorPost;
    }

    public List<Post> getPostByRestaurant(Restaurant restaurant) {
        List<Post> restaurantPost = new ArrayList<>();
        for (Post post : list) {
            if (post.place.equals(restaurant))
                restaurantPost.add(post);
        }
        return restaurantPost;
    }

    public void addPost(Post post) {
        list.add(post);
    }

    public List<Post> getAllPosts() {
        return list;
    }
}
