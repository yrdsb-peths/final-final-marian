import greenfoot.*;

public class MyWorld extends World {
    //background image size
    static final int SW = 1672;
    static final int SH = 941;
    
    static final int MAP_W = 1672;
    static final int MAP_H = 941;
    
    //pan
    int panX = 0;
    int panY = 0;
    static final int PAN_SPEED = 6;
    
    //zoom
    
    
    public MyWorld()
    {
        super(SW, SH, 1);
        Greenfoot.setSpeed(50);
    }
}
