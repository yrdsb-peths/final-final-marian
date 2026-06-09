import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends Actor
{
    static final int W = SniperWorld.SW;
    static final int H = SniperWorld.SH;
    
    public TitleScreen()
    {
        GreenfootImage img = new GreenfootImage("titlePage.png");
        img.scale(W, H);
        setImage(img);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
