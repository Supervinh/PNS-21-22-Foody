package polytech.foody;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class UserProfile extends AppCompatActivity {
    boolean abonne;

    public UserProfile(boolean abonne){
        this.abonne = abonne;
    }

    public void afficherInformations(User autreUtilisateur){
        //peut être avec intent ?
        TextView nomUtilisateur = findViewById(R.id.nomUtilisateur);
        nomUtilisateur.setText(autreUtilisateur.firstName+autreUtilisateur.lastName);
        TextView nbRestos = findViewById(R.id.nbRestos);
        nbRestos.setText("Nombre de restaurants visités : " + autreUtilisateur.nbRestosVisites);
        TextView pointsUtilisateur = findViewById(R.id.pointsUtilisateur);
        pointsUtilisateur.setText("Nutripoints : " + autreUtilisateur.nutriPoints);
    }

}
