import javafx.geometry.Rectangle2D;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class Hero extends AnimatedThing{
    private long temp;
    private long timeCounter=0;
    private long lastTime=-1;
    private double xSpeed=550;
    private double ySpeed;
    private long invincibility=0;
    private Integer lives=3;
    private double g;
    private Balle balle;
    private boolean shooting=false;
    private boolean dead=false;

    public Hero() throws MalformedURLException {
        super(new File("images/heros.png").toURI().toURL().toString(),
        0, Main.getHEIGHT()-150, 75, 100,0,new Integer[] {5,0,0,5,1},200000000L);
        //imageView.setX(0);
        ySpeed=0;
        g=9.81;
        balle=new Balle();
    }



    @Override
    public void update(long time) {
        if (lastTime==-1){
            lastTime=time;
        }else {
            if(invincibility<0){
                invincibility=0;
            }else {
                invincibility -= time - lastTime;
            }
            double deltaT=((double)(time-lastTime))/1000000000L;
            temp = (time % (frameDuration * 10) / (frameDuration));
            attitude=0;

            ySpeed+=g*deltaT;//gravity
            x+=xSpeed*deltaT;
            y+=ySpeed;
            if (y>Main.getHEIGHT()-150){
                y=Main.getHEIGHT()-150;
                ySpeed=0;
            }

            //imageView.setY(y);

            attitude=ySpeed>0?2:ySpeed==0?0:1;//changer attitude quand on saute

            switch (attitude) {
                default:
                    switch (index) {
                        default -> imageView.setViewport(new Rectangle2D(0, 0, windowLength, windowHeight));
                        case 1 -> imageView.setViewport(new Rectangle2D(86, 0, windowLength, windowHeight));
                        case 2 -> imageView.setViewport(new Rectangle2D(175, 0, windowLength, windowHeight));
                        case 3 -> imageView.setViewport(new Rectangle2D(254, 0, windowLength, windowHeight));
                        case 4 -> imageView.setViewport(new Rectangle2D(336, 0, windowLength, windowHeight));
                        case 5 -> imageView.setViewport(new Rectangle2D(425, 0, windowLength, windowHeight));
                    }
                    break;
                case 1:
                    imageView.setViewport(new Rectangle2D(0, 161, windowLength, windowHeight));
                    break;
                case 2:
                    imageView.setViewport(new Rectangle2D(83, 161, windowLength, windowHeight));
                    break;
                case 3:
                    switch (index) {
                        default -> imageView.setViewport(new Rectangle2D(2, 326, windowLength, windowHeight));
                        case 1 -> imageView.setViewport(new Rectangle2D(83, 326, windowLength, windowHeight));
                        case 2 -> imageView.setViewport(new Rectangle2D(164, 326, windowLength, windowHeight));
                        case 3 -> imageView.setViewport(new Rectangle2D(247, 326, windowLength, windowHeight));
                        case 4 -> imageView.setViewport(new Rectangle2D(339, 326, windowLength, windowHeight));
                        case 5 -> imageView.setViewport(new Rectangle2D(427, 326, windowLength, windowHeight));
                    }
                    break;
                case 4:
                    switch (index) {
                        default -> imageView.setViewport(new Rectangle2D(7, 491, windowLength, windowHeight));
                        case 1 -> imageView.setViewport(new Rectangle2D(94, 491, windowLength, windowHeight));
                    }
                    break;
            }

            if (timeCounter != temp) {
                timeCounter = temp;
                index = index >= indexMax[attitude] ? 0 : (index = index + 1);
            }
            lastTime=time;
        }
    }

    public void Jump(){
        if (ySpeed==0) {
            ySpeed = -5;
            g=9.81;
        }else{
            g=100;
        }
    }

    public void Shoot(){
        if (!shooting) {
            shooting = true;
        }
    }

    @Override
    public Rectangle2D getHitBox() {
        return new Rectangle2D(x,y,windowLength,windowHeight);
    }

    public void collision(){
        invincibility=2500000000L;
        lives-=1;
        if (lives==0) dead=true;
    }

    public boolean isInvincible(){return invincibility>0;}

    public Integer getLives() {return lives;}

    public boolean isDead(){return dead;};
}