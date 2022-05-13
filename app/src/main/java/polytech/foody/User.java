package polytech.foody;

import java.util.ArrayList;
import java.util.List;

public class User {
    String firstName;
    String lastName;
    int nutriPoints;
    int nbRestosVisites;

    List<Restaurant> favoriteRestaurants;
    List<Restaurant> recentRestaurants;

    List<User> subscriptions;

    List<Post> posts;


    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        nutriPoints = 0;
        nbRestosVisites = 0;
        favoriteRestaurants = new ArrayList<>();
        recentRestaurants = new ArrayList<>();
        subscriptions = new ArrayList<>();
        posts = new ArrayList<>();


    }


}
