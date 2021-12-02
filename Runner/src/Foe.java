import javafx.geometry.Rectangle2D;

import java.io.File;
import java.net.MalformedURLException;

public class Foe extends AnimatedThing{
    private double xInit;
    private double yInit;

    public Foe(Integer xInit, Integer yInit) throws MalformedURLException {
        super(new File("images/foe4.png").toURI().toURL().toString(),
                xInit,yInit);
        this.xInit=xInit;
        this.yInit=yInit;
    }

    public double getxInit() {
        return xInit;
    }

    public double getyInit() {
        return yInit;
    }

    @Override
    public Rectangle2D getHitBox() {
        return new Rectangle2D(xInit,yInit,75,100);
    }

    public void update(long time){

    }
}
