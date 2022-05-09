package polytech.foody;

import java.util.ArrayList;
import java.util.List;

public class Restaurants {
    private static Restaurants instance;
    List<Restaurant> list;

    public Restaurants(List<Restaurant> list) {
        this.list = list;
    }

    public Restaurants(){
        list = new ArrayList<>();
        list.add(new Restaurant("Taco Bell",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ornare auctor orci, id venenatis felis tincidunt in. Ut eu ante molestie, pharetra ligula at, facilisis neque. Mauris laoreet dictum nunc non tempor. Donec vitae felis non dolor tempor pharetra. Morbi massa mi, vestibulum sed tortor ut, lacinia mollis tortor. Praesent facilisis tortor mi, ac vehicula est varius et. Suspendisse potenti. Aenean a tempus ligula. Mauris massa sem, venenatis non lobortis non, fermentum id enim. Vestibulum blandit eros non scelerisque convallis. Maecenas fermentum bibendum arcu vitae feugiat. Vivamus non tellus dui. Duis ullamcorper libero arcu, eget malesuada libero tempor et. Maecenas id nibh aliquet, malesuada magna non, imperdiet nunc. Proin eu aliquam nulla. Donec et finibus leo.",
                R.drawable.tacos,2, "Nice"));
    }

    public int size(){
        return list.size();
    }

    public Restaurant get(int pos){
        return list.get(pos);
    }

    public void add(Restaurant score){
        list.add(score);
    }

    public List<Restaurant> getList() {
        return list;
    }

    public void setList(List<Restaurant> list) {
        this.list = list;
    }

    public static Restaurants getInstance(){
        if(Restaurants.instance == null){
            synchronized (Restaurants.class){
                if (Restaurants.instance == null)
                    Restaurants.instance = new Restaurants();
            }
        }
        return instance;
    }

}
