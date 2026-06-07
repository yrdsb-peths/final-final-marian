import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CrosshairActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CrosshairActor extends Actor
{
    boolean active = false;
    int pulse = 0;
    
    public CrosshairActor()
    {
        buildOff();
    }

    
    public void act()
    {
        if (!active)
        {
            return;
        }
        pulse = (pulse + 5) % 360;
        int alpha = 150 + (int)(80 * Math.sin(Math.sin(Math.toRadians(pulse))));
        buildOn(alpha);
    }
    
    public void setActive(boolean on)
    {
        active = on;
        if(on){
            buildOff();
        }
        else
        {
            buildOn(210);
        }
        
    }
    
    public void buildOn(int alpha)
    {
        int S = 28;
        GreenfootImage img = new GreenfootImage(S*2, S*2);
        img.setColor(new Color(0,0,0,0));
        img.fill();
        
        int cx = S;
        int cy = S;
        int len = 16;
        int gap = 6;
        
        
    }
    
    
    
    
    
    
}
