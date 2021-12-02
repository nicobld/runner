import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import java.time.*;

public abstract class AnimatedThing {
    protected double x,y;
    protected ImageView imageView;
    protected Integer attitude;

    protected Integer index;
    protected long frameDuration;
    protected Integer[] indexMax;
    protected Integer windowHeight;
    protected Integer windowLength;

    public AnimatedThing(String fileName,Integer x, Integer y,
                         Integer windowLength,Integer windowHeight,
                         Integer attitude, Integer[] indexMax,
                         long frameDuration){
        this.x=x;
        this.y=y;
        this.attitude=attitude;
        this.index=0;
        this.indexMax=indexMax;
        this.windowLength=windowLength;
        this.windowHeight=windowHeight;
        this.frameDuration=frameDuration;

        imageView=new ImageView(fileName);
        imageView.setX(x);
        imageView.setY(y);
    }

    public AnimatedThing(String fileName, Integer x, Integer y){
        this.x=x;
        this.y=y;
        imageView=new ImageView(fileName);
        imageView.setX(x);
        imageView.setY(y);
    }

    public ImageView getImage() {
        return imageView;
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

    public abstract Rectangle2D getHitBox();

    public abstract void update(long time);

}
