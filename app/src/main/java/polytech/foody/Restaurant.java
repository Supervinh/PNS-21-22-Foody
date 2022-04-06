package polytech.foody;

public class Restaurant {
    String name;
    String description;
    String image;
    //todo map
    int nutriPoints;
    int Visitors;
    double score;
    int nombreDeNotes;

    public Restaurant (String name, String description, String image, int nutriPoints){ //todo implement map in constructor
        this.name = name;
        this.description = description;
        this.image = image;
        this.nutriPoints = nutriPoints;
        this.Visitors = 0;
        this.score = 0.0;
        this.nombreDeNotes=0;
    }

    public void ajouterScore(int note) {
        this.score *= this.nombreDeNotes;
        this.score += note;
        this.nombreDeNotes++;
        this.score /= nombreDeNotes;
    }
}
