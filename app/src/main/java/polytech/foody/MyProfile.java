package polytech.foody;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfile extends AppCompatActivity {

    public void afficherInformations(User Utilisateur){
        //peut être avec intent ?
        TextView nomUtilisateur = findViewById(R.id.nomUtilisateur);
        nomUtilisateur.setText(Utilisateur.firstName+Utilisateur.lastName);
        TextView nbRestos = findViewById(R.id.nbRestos);
        nbRestos.setText("Nombre de restaurants visités : " + Utilisateur.nbRestosVisites);
        TextView pointsUtilisateur = findViewById(R.id.pointsUtilisateur);
        pointsUtilisateur.setText("Nutripoints : " + Utilisateur.nutriPoints);
        //faire pour les restos favoris et récemment fréquentés

    }
}
