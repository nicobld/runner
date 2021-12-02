import javafx.scene.image.ImageView;

public class StaticThing{
    private double x;
    private double y;


    private ImageView imageView;
    private String fileName;


    public StaticThing(double x, double y, String fileName) {
        this.x = x;
        this.y = y;
        this.fileName = fileName;
        imageView = new ImageView(fileName);
        imageView.setX(x);
        imageView.setY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }



    public ImageView getImage() {
        return imageView;
    }
}
