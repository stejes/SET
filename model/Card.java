package model;

/**
 * Created by fed on 1/31/15.
 */
public class Card {
    public Number number;
    public Shape shape;
    public Color color;
    public Shading shading;
    private Enum[] features = new Enum[4];
    private String image;
    private boolean isHint = false;

    public Card(Color color, Shape shape, Shading shading, Number number, String image) {
        this.color = color;
        this.shape = shape;
        this.shading = shading;
        this.number = number;
        features[0] = color;
        features[1] = shape;
        features[2] = shading;
        features[3] = number;
        this.image = image;
    }

    public Enum[] getFeatures() {
        return features;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "model.Card{" +
                "number=" + number +
                ", shape=" + shape +
                ", color=" + color +
                ", shading=" + shading +
                '}';
    }

    public void setHint(){
        isHint = true;
    }

    public void unsetHint(){
        isHint = false;
    }

    public boolean isHint(){
        return isHint;
    }
}
