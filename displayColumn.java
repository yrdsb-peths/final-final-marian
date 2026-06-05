import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class displayColumn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class displayColumn extends Actor
{
    /**
     * Act - do whatever the displayColumn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SniperWorld world;
    
    static final int W = SniperWorld.SW;
    static final int H = SniperWorld.SH;
    
    public displayColumn(SniperWorld w)
    {
        this.world = w;
        setImage(new GreenfootImage(W, H));
    }
    public void act()
    {
        // Add your action code here.
    }
}
