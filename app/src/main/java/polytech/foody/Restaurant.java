package polytech.foody;

public class Restaurant {
    String name;
    String description;
    int image;
    String address;
    int nutriPoints;
    int visitors;
    double score;
    int nombreDeNotes;

    public Restaurant (String name, String description, int image, int nutriPoints, String address ){
        this.name = name;
        this.description = description;
        this.image = image;
        this.nutriPoints = nutriPoints;
        this.visitors = 0;
        this.score = 0.0;
        this.nombreDeNotes=0;
        this.address = address;
    }



    public void ajouterScore(int note) {
        this.score *= this.nombreDeNotes;
        this.score += note;
        this.nombreDeNotes++;
        this.score /= nombreDeNotes;
    }
}
