package polytech.foody;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    String responseText;
    StringBuffer response;
    URL url;
    Activity activity;
    Restaurants restaurants = new Restaurants();
    private ProgressDialog progressDialog;


    //Direct Web services URL
    String path = "http://bluedays.com/data/web/restaurants.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        activity = this;
        listView = (ListView) findViewById(R.id.listView);
        restaurants.getList().clear();
            //Call WebService
        new GetServerData().execute();

        findViewById(R.id.btn_add_post).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_home).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_profile).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                    startActivity(intent);
                });

        findViewById(R.id.btn_search).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);

                });

        findViewById(R.id.btn_back).setOnClickListener(
                click -> {
                    Intent intent = new Intent(getApplicationContext(), GpsTest.class);
                    startActivity(intent);

                });
    }

    // In case if you deploy rest web service, then use below link and replace below ip address with yours
    //http://192.168.2.22:8080/JAXRSJsonExample/rest/countries

    class GetServerData extends AsyncTask
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog = new ProgressDialog(SearchActivity.this);
            progressDialog.setMessage("Fetching restaurants data");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            return getWebServiceResponseData();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            // Dismiss the progress dialog
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            // For populating list data
            RestaurantAdapter restaurantAdapter = new RestaurantAdapter(activity, restaurants);
            listView.setAdapter(restaurantAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(activity.getApplicationContext(),"You Selected "+restaurants.get(position).getName()+ " as Restaurant",Toast.LENGTH_SHORT).show();
                    final Intent launchActivity = new Intent(SearchActivity.this, RestaurantActivity.class);
                    launchActivity.putExtra("Pos", position);
                    startActivity(launchActivity);
                    }
            });
        }
    }

    protected Void getWebServiceResponseData() {

        try {

            //Direct Web services URL
            url = new URL(path);
            Log.d(TAG, "ServerData: " + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            Log.d(TAG, "Response code: " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // Reading response from input Stream
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String output;
                response = new StringBuffer();

                while ((output = in.readLine()) != null) {
                    response.append(output);
                }
                in.close();
            }}
        catch(Exception e){
            e.printStackTrace();
        }

        responseText = response.toString();
        //Call ServerData() method to call webservice and store result in response
        //  response = service.ServerData(path, postDataParams);
        Log.d(TAG, "data:" + responseText);
        try {
            JSONArray jsonArray = new JSONArray(responseText);
            for(int i=0; i < jsonArray.length(); i++){
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
                restaurants.add(r);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}