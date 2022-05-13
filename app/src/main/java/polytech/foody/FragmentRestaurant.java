package polytech.foody;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FragmentRestaurant extends Fragment {
    View rootView;
    Restaurant restaurant;
    boolean isPressed = false;

    public FragmentRestaurant() {

    }

    public FragmentRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);

        TextView restaurantName = rootView.findViewById(R.id.restaurantName);
        restaurantName.setText(restaurant.name);

        //ImageView picture = rootView.findViewById(R.id.restaurantPicture);
        //picture.setImageResource(restaurant.image);

        TextView nutriPoints = rootView.findViewById(R.id.restaurantNutriPoints);
        String nutriPointsString = "NutriPoints : " + restaurant.nutriPoints;
        nutriPoints.setText(nutriPointsString);

        TextView score = rootView.findViewById(R.id.restaurantScore);
        String scoreString = "Score : " + restaurant.score;
        score.setText(scoreString);

        TextView visitors = rootView.findViewById(R.id.restaurantVisitors);
        String visitorsString = restaurant.visitors + " visiteurs";
        visitors.setText(visitorsString);

        TextView description = rootView.findViewById(R.id.restaurantDescription);
        description.setText(restaurant.description);

        ImageButton favorite = rootView.findViewById(R.id.favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("draw" + favorite.getBackground());
                if (isPressed) {
                    favorite.setBackgroundResource(R.drawable.starempty);
                    isPressed = false;
                } else {
                    favorite.setBackgroundResource(R.drawable.star);
                    isPressed = true;
                }
                makeFavorite(restaurant);
            }
        });

        ImageView comment = rootView.findViewById(R.id.comment);
        comment.setOnClickListener(click -> {
            Intent intent = new Intent(getContext(), ReviewCreationActivity.class);
            startActivity(intent);
        });

        Button reservation = rootView.findViewById(R.id.reservation);
        reservation.setOnClickListener(
                click -> {
                    Intent intent = new Intent(getContext(), ReservationActivity.class);
                    intent.putExtra("name", restaurant.name);
                    startActivity(intent);
                });

        return rootView;
    }


    private void LeaveComment(Restaurant restaurant) {
    }

    private void makeFavorite(Restaurant restaurant) {
    }
}
