public class Camera{
    private int x,y;
    private final Integer k=10,m=10,f=12;
    private static double a=0;
    private static double v=0;
    private static double deltaX;
    private static long lastTime;
    private static double deltaT;

    public Camera(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        lastTime = -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return x+","+y;
    }


    public void update(long time,double heroX,double heroY){
        if (lastTime==-1){
            lastTime=time;
        }else {
            /*
            deltaT=((double)(time-lastTime))/1000000000;
            a=(k/m)*(heroX-x)+(f/m)*v;
            v=v+a*deltaT;
            x=(int)(x+v*deltaT);
            lastTime = time;*/
            x=(int)heroX;
            y=(int)heroY;
        }
    }
}
