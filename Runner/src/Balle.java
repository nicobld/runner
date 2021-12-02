import javafx.geometry.Rectangle2D;

import java.io.File;
import java.net.MalformedURLException;

public class Balle extends AnimatedThing{
    public Balle() throws MalformedURLException {
        super(new File("images/balle.png").toURI().toURL().toString(),0,0);
    }

    @Override
    public void update(long time) {

    }

    @Override
    public Rectangle2D getHitBox() {
        return null;
    }
}
