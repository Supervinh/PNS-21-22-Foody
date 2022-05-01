package polytech.foody;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import polytech.foody.R;
import polytech.foody.Restaurant;

public class WebService extends AppCompatActivity {
    Button btn;
    TextView restaurantsList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webservice);
        btn = findViewById(R.id.buttonWebService);
        restaurantsList = findViewById(R.id.textViewWebService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://bluedays.com/data/web/restaurants.json";
                new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String str = new String(responseBody);
                        ArrayList<Restaurant> restaurants = new ArrayList<>();
                        StringBuilder txt = new StringBuilder();

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(str);
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonRoot = jsonArray.getJSONObject(i);
                                String name = jsonRoot.getString("name");
                                String description = jsonRoot.getString("description");
                                int image = jsonRoot.getInt("image");
                                String address = jsonRoot.getString("address");
                                int nutriPoints = jsonRoot.getInt("nutriPoints");
                                int visitors = jsonRoot.getInt("visitors");
                                double score = jsonRoot.getDouble("score");
                                int nombreDeNotes = jsonRoot.getInt("nombreDeNotes");
                                Restaurant r = new Restaurant(name, description, image, address, nutriPoints, visitors, score, nombreDeNotes);
                                txt.append(r.getName());
                                restaurants.add(r);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        restaurantsList.setText(txt);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        restaurantsList.setText("aled");
                    }
                });
            }
        });
    };
}