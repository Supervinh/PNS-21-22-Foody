package polytech.foody;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentRestaurant extends Fragment {
    View rootView;
    Restaurant restaurant;

    public FragmentRestaurant() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);


        ImageView picture = rootView.findViewById(R.id.restaurantPicture);
        picture.setImageResource(restaurant.image);

        TextView nutriPoints = rootView.findViewById(R.id.restaurantNutriPoints);
        nutriPoints.setText(restaurant.nutriPoints);

        TextView score = rootView.findViewById(R.id.restaurantScore);
        score.setText(String.valueOf(restaurant.score));

        TextView visitors = rootView.findViewById(R.id.restaurantVisitors);
        visitors.setText(restaurant.visitors);

        TextView description = rootView.findViewById(R.id.restaurantDescription);
        description.setText(restaurant.description);

        ImageView favorite = rootView.findViewById(R.id.favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeFavorite(restaurant);
            }
        });

        ImageView comment = rootView.findViewById(R.id.comment);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeaveComment(restaurant);
            }
        });

        Button reservation = rootView.findViewById(R.id.reservation);
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeReservation(restaurant);
            }
        });

        return rootView;
    }

    private void makeReservation(Restaurant restaurant) {
        Intent intent = new Intent( getParentFragment().getContext(), Reservation.class);
        intent.putExtra(restaurant);
        startActivity(intent);
    }

    private void LeaveComment(Restaurant restaurant) {
    }

    private void makeFavorite(Restaurant restaurant) {
    }

}
