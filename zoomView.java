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
        
    }
    
    public void setActive(boolean on)
    {
        active = on;
        if(on){
            buildOn();
        }
        else
        {
            buildOff();
        }
        
    }
    
    public void buildOn()
    {
        GreenfootImage img = new GreenfootImage(IMG_W, IMG_H);
        
        img.setColor(new Color(0, 0, 0, 0));
        img.fill();
        
        int cx = IMG_W / 2;
        int cy = IMG_H / 2;
        int R = RADIUS;
        
        Color dark = new Color(0, 0, 0, 200);
        
        img.setColor(dark);
        img.fillRect(0, 0, IMG_W, cy - R);
        
        img.fillRect(0, cy+R, IMG_W, IMG_H - (cy + R));
        img.fillRect(0, cy-R, cx-R, R*2);
        img.fillRect(cx+R, cy-R, IMG_W-(cx+R), R*2);
        
        for (int i = R; i <= R+40; i++)
        {
            int alphaVal = Math.min(200, (i-R) * 5 + 150);
            img.setColor(new Color(0,0,0,alphaVal));
            img.drawOval(cx-i, cy-i, i*2, i*2);
        }
        
        img.setColor(new Color(10, 35, 10, 25));
        img.fillOval(cx-R, cy-R, R*2,R*2);
        
        for(int i = 0; i < 8; i++)
        {
            int a = 255 - i*22;
            img.setColor(new Color(60,200,60,a));
            img.drawOval(cx-R-i,cy-R-i, (R+i)*2, (R+i)*2);
        }
        
        img.setColor(new Color(180,255,180,80));
        img.drawOval(cx-R+3, cy-R+3, (R-3)*2, (R-3)*2);
        
        int gap = 18;
        Color hairColor = new Color(40,220, 40, 210);
        img.setColor(hairColor);
        
        //horizontal
        img.drawline(cx-R+12, cy, cx-gap, cy);
        img.drawline(cx+gap, cy, cx+R-12, cy);
        
        //vertical
        img.drawline(cx, cy-R+12, cx, cy-gap);
        img.drawline(cx, cy+gap, cx, cy+R-12);
        
        img.setColor(new Color(40, 220, 40, 100));
        img.drawLine(cx - R + 8, cy, cx + R - 8, cy);
        img.drawLine(cx, cy - R + 8, cx, cy + R - 8);
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
