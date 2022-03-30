package polytech.foody;

public class Restaurant {
    String name;
    String description;
    String image;
    //todo map
    int nutriPoints;
    int Visitors;
    double score;

    public Restaurant (String name, String description, String image, int nutriPoints){ //todo implement map in constructor
        this.name = name;
        this.description = description;
        this.image = image;
        this.nutriPoints = nutriPoints;
        this.Visitors = 0;
        this.score = 0.0;
    }
}
