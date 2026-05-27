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
    boolean zoomed = false;
    int zoomCX = SW / 2;
    int zoomCY = SH / 2;
    static final double ZOOM_SCALE = 2.5;
    int zoomviewW = (int)(SW/ZOOM_SCALE);
    int zoomviewH = (int)(SH/ZOOM_SCALE);
    
    GreenfootImage fullBgImage;
    public MyWorld()
    {
        super(SW, SH, 1);
        Greenfoot.setSpeed(50);
        setup();
    }
    
    public void setup()
    {
        fullBgImage = new GreenfootImage("background_final.png");
        
 
        
    }
}
