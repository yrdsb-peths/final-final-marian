import greenfoot.*;

public class SniperWorld extends World {
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
    static final double ZOOM_SCALE = 2.5;
    int zoomviewW = (int)(SW/ZOOM_SCALE);
    int zoomviewH = (int)(SH/ZOOM_SCALE);
    
    displayColumn hud;
    zoomView scope;
    CrosshairActor crosshair;
    
    GreenfootImage fullBgImage;
    public SniperWorld()
    {
        super(SW, SH, 1);
        Greenfoot.setSpeed(50);
        setup();
    }
    
    public void setup()
    {
        fullBgImage = new GreenfootImage("background_final.png");
        
        hud = new displayColumn (this);
        scope = new zoomView(this);
        crosshair = new CrosshairActor();
        
        addObject(hud, SW/2, SH/2);
        addObject(scope, SW/2, SH/2);
        addObject(crosshair, SW/2, SH/2);
        
        scope.setActive(false);
        crosshair.setActive(false);
        
        startLevel(1);
    }
}
