import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {

    public static final Integer LENGTH=800;
    public static final Integer HEIGHT=300;

    public static Integer getLENGTH() {
        return LENGTH;
    }

    public static Integer getHEIGHT() {
        return HEIGHT;
    }

    public void start(Stage stage) throws MalformedURLException {
        Group root = new Group();
        GameScene gameScene = new GameScene(root, LENGTH, HEIGHT);


        for(StaticThing staticThing : gameScene.getBackground()){
            root.getChildren().add(staticThing.getImage());
        }

        root.getChildren().add(gameScene.getHero().getImage());
        for (Foe foe : gameScene.getFoes()) {
            root.getChildren().add(foe.getImage());
        }

        Rectangle rectt=new Rectangle(gameScene.getHero().getHitBox().getMinX(),gameScene.getHero().getHitBox().getMinY(),75,100);
        //root.getChildren().add(rectt);

        stage.setTitle("Runner");
        stage.setScene(gameScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}