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
        
        img.setColor(new Color(0, 0, 0, alpha / 3));
        img.drawLine(cx - len, cy + 1, cx - gap, cy + 1);
        img.drawLine(cx + gap, cy + 1, cx + len, cy + 1);
        img.drawLine(cx + 1, cy - len, cx + 1, cy - gap);
        img.drawLine(cx + 1, cy + gap, cx + 1, cy + len);
        
        img.setColor(new Color(40, 220, 40, alpha / 2));
        img.drawLine(cx - len - 2, cy, cx - gap + 2, cy);
        img.drawLine(cx + gap - 2, cy, cx + len + 2, cy);
        img.drawLine(cx, cy - len - 2, cx, cy - gap + 2);
        img.drawLine(cx, cy + gap - 2, cx, cy + len + 2);
        
        img.setColor(new Color(255, 55, 55, alpha));
        img.drawLine(cx - len, cy, cx - gap, cy);
        img.drawLine(cx + gap, cy, cx + len, cy);
        img.drawLine(cx, cy - len, cx, cy - gap);
        img.drawLine(cx, cy + gap, cx, cy + len);
        
        img.setColor(new Color(255, 80, 80, alpha));
        img.fillOval(cx - 3, cy - 3, 6, 6);
        img.setColor(new Color(255, 220, 220, alpha));
        img.fillOval(cx - 1, cy - 1, 2, 2);
    }
    
    
    
    
    
    
}
