package polytech.foody;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Restaurant restaurant = Restaurants.get(getIntent().getIntExtra(getString(R.string.RESTAURANT), 0));

        FragmentMap mapFrag = new FragmentMap();
        getSupportFragmentManager().beginTransaction().replace(R.id.map, mapFrag).commit();

        Fragment restaurantFrag = new FragmentRestaurant();
        getSupportFragmentManager().beginTransaction().replace(R.id.restaurant, restaurantFrag).commit();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LinearLayout linearLayout = findViewById(R.id.myLinearLayout);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
    }
}
