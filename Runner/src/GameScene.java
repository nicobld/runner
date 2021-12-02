import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

import java.util.ArrayList;


public class GameScene extends Scene {
    private static Camera camera = new Camera(0,0);

    private static StaticThing backgroundLeft;
    private static StaticThing backgroundRight;

    private static StaticThing livesImage;
    private static StaticThing startImage;
    private static StaticThing youLose;

    private static Hero hero;
    private static ArrayList<Foe> foeList;
    private static Balle balle;

    private static boolean finished=false;
    private static boolean start=false;


    public GameScene(Parent root, double v, double v1) throws MalformedURLException {
        super(root, v, v1);
        backgroundLeft = new StaticThing(0,0,new File("images/desert.png").toURI().toURL().toString());
        backgroundRight = new StaticThing(800,0,new File("images/desert.png").toURI().toURL().toString());
        livesImage = new StaticThing(0,0,new File("images/coeurs.png").toURI().toURL().toString());
        hero = new Hero();
        startImage = new StaticThing(0,0,new File("images/start_button.png").toURI().toURL().toString());
        youLose = new StaticThing(0,0,new File("images/you_lose.png").toURI().toURL().toString());

        Random random = new Random();
        int next = random.nextInt(100);

        foeList = new ArrayList<Foe>(next);
        foeList.add(new Foe(1000,Main.getHEIGHT() - 100));
        for(int i=1;i<next;i++) {
            foeList.add(new Foe((int)(300+foeList.get(i-1).getX()+random.nextInt(500)), Main.getHEIGHT() - 100));
        }


        timer.start();
    }

    public static StaticThing[] getBackground() {
        return new StaticThing[] {backgroundLeft,backgroundRight,livesImage,startImage,youLose};
    }

    public static ArrayList<Foe> getFoes() {return foeList;}

    public static Hero getHero(){return hero;}

    AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            update(time);
        }
    };

    public void update(long time){
        if(!hero.isDead() & start) {
            hero.update(time);
            for (Foe foe : foeList) {
                foe.update(time);
            }
            camera.update(time, hero.getX(), hero.getY());

            backgroundLeft.setY(Main.getHEIGHT() - 150 - camera.getY() - 100);
            backgroundRight.setY(Main.getHEIGHT() - 150 - camera.getY() - 100);

            backgroundLeft.setX(-(camera.getX() % 800));
            backgroundRight.setX((800 - (camera.getX() % 800)));

            this.setOnMouseClicked(event -> {
                System.out.println("Jump");
                hero.Jump();
            });

            this.setOnKeyTyped(space -> {
                System.out.println("Shoot");
                hero.Shoot();
            });

            if (!hero.isInvincible()) {
                for (Foe foe : foeList) {
                    if (foe.getHitBox().intersects(hero.getHitBox())) {
                        System.out.println("hit");
                        hero.collision();
                    }
                }
            }

            livesImage.getImage().setViewport(new Rectangle2D(0, 0, 30 * hero.getLives(), 30));

            //System.out.println(hero.getHitBox().toString()+foeList.get(0).getHitBox().toString());

            backgroundLeft.getImage().setX(backgroundLeft.getX());
            backgroundLeft.getImage().setY(backgroundLeft.getY());
            backgroundRight.getImage().setX(backgroundRight.getX());
            backgroundRight.getImage().setY(backgroundRight.getY());
            for (Foe foe : foeList) {
                foe.getImage().setX(foe.getxInit() - camera.getX());
                foe.getImage().setY(foe.getyInit() - camera.getY() + 150);
            }
        } else if(hero.isDead()){
            System.out.println("t'es mort :(");
            youLose.getImage().setViewport(null);
        } else {
            System.out.println("start");
            livesImage.getImage().setViewport(new Rectangle2D(0,0,1,1));
            backgroundLeft.getImage().setViewport(new Rectangle2D(0,0,1,1));
            backgroundRight.getImage().setViewport(new Rectangle2D(0,0,1,1));
            hero.getImage().setViewport(new Rectangle2D(0,0,1,1));
            youLose.getImage().setViewport(new Rectangle2D(0,0,1,1));
            this.setOnMouseClicked(event ->{
                System.out.println("go");
                start=true;
                startImage.getImage().setViewport(new Rectangle2D(0,0,1,1));
                backgroundLeft.getImage().setViewport(null);
                backgroundRight.getImage().setViewport(null);
            });
        }
    }
}
