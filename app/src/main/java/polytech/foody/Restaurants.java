package polytech.foody;

import java.util.ArrayList;
import java.util.List;

public class Restaurants {
    List<Restaurant> list;

    public Restaurants(List<Restaurant> list) {
        this.list = list;
    }

    public Restaurants(){
        list = new ArrayList<>();
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


}
