import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class zoomView here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class zoomView extends Actor
{
    
    static final int RADIUS = 200;
    static final int IMG_W = SniperWorld.SW;
    static final int IMG_H = SniperWorld.SH;
    
    boolean active = false;
    SniperWorld world;
    
    public zoomView(SniperWorld w)
    {
        this.world = w;
        buildOff();
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
