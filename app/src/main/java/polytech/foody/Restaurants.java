package polytech.foody;

import java.util.ArrayList;

public class Restaurants {

    private static ArrayList<Restaurant> RestaurantArrayList = new ArrayList<>();

    static {
        RestaurantArrayList.add(new Restaurant("taco Bell",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ornare auctor orci, id venenatis felis tincidunt in. Ut eu ante molestie, pharetra ligula at, facilisis neque. Mauris laoreet dictum nunc non tempor. Donec vitae felis non dolor tempor pharetra. Morbi massa mi, vestibulum sed tortor ut, lacinia mollis tortor. Praesent facilisis tortor mi, ac vehicula est varius et. Suspendisse potenti. Aenean a tempus ligula. Mauris massa sem, venenatis non lobortis non, fermentum id enim. Vestibulum blandit eros non scelerisque convallis. Maecenas fermentum bibendum arcu vitae feugiat. Vivamus non tellus dui. Duis ullamcorper libero arcu, eget malesuada libero tempor et. Maecenas id nibh aliquet, malesuada magna non, imperdiet nunc. Proin eu aliquam nulla. Donec et finibus leo.",
                R.drawable.cloche,4, "taco bell"));
    }

    public static Restaurant get(int index){
        return RestaurantArrayList.get(index);
    }

    public static void remove(int index){
        RestaurantArrayList.remove(index);
    }

    public static int size(){
        return RestaurantArrayList.size();
    }
}
