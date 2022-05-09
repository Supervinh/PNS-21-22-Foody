package polytech.foody;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends BaseAdapter {

    private final Restaurants listRestaurant;
    private final LayoutInflater inflater;
    private final Activity context;

    public RestaurantAdapter(Activity context, Restaurants listS){
        this.listRestaurant = listS;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listRestaurant.size();
    }

    @Override
    public Object getItem(int i) {
        return listRestaurant.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "restaurant_layout.xml"
            layoutItem = (LinearLayout) inflater.inflate(R.layout.restaurant_layout, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_Name = layoutItem.findViewById(R.id.TV_Name);
        TextView tv_Score = layoutItem.findViewById(R.id.TV_Score);
        TextView tv_NutriPoints = layoutItem.findViewById(R.id.TV_NutriPoints);

        //(3) : Renseignement des valeurs
        tv_Name.setText(listRestaurant.get(position).getName());
        tv_Score.setText(String.valueOf("Score : " + listRestaurant.get(position).getScore()));
        tv_NutriPoints.setText(String.valueOf("NutriPoints : " + listRestaurant.get(position).getNutriPoints()));

        //On retourne l'item créé.
        return layoutItem;

    }
}
