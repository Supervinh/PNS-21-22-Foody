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

    public Restaurant(String name, String description, int image, int nutriPoints, String address) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.nutriPoints = nutriPoints;
        this.visitors = 0;
        this.score = 0.0;
        this.nombreDeNotes = 0;
        this.address = address;
    }

    public Restaurant(String name, String description, int image, String address, int nutriPoints, int visitors, double score, int nombreDeNotes) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.address = address;
        this.nutriPoints = nutriPoints;
        this.visitors = visitors;
        this.score = score;
        this.nombreDeNotes = nombreDeNotes;
    }

    public void ajouterScore(int note) {
        this.score *= this.nombreDeNotes;
        this.score += note;
        this.nombreDeNotes++;
        this.score /= nombreDeNotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNutriPoints() {
        return nutriPoints;
    }

    public void setNutriPoints(int nutriPoints) {
        this.nutriPoints = nutriPoints;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNombreDeNotes() {
        return nombreDeNotes;
    }

    public void setNombreDeNotes(int nombreDeNotes) {
        this.nombreDeNotes = nombreDeNotes;
    }
}
