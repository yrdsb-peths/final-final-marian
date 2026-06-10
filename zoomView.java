import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ZoomView renders the sniper scope overlay when the player zooms in
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZoomView extends Actor
{
    
    static final int RADIUS = 200;
    static final int IMG_W = SniperWorld.SW;
    static final int IMG_H = SniperWorld.SH;
    
    boolean active = false;
    SniperWorld world;
    
    public ZoomView(SniperWorld w)
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
        img.drawLine(cx-R+12, cy, cx-gap, cy);
        img.drawLine(cx+gap, cy, cx+R-12, cy);
        
        //vertical
        img.drawLine(cx, cy-R+12, cx, cy-gap);
        img.drawLine(cx, cy+gap, cx, cy+R-12);
        
        img.setColor(new Color(40, 220, 40, 100));
        img.drawLine(cx - R + 8, cy, cx + R - 8, cy);
        img.drawLine(cx, cy - R + 8, cx, cy + R - 8);
        
        img.setColor(new Color(50, 210, 50, 180));
        for (int i = -3; i <= 3; i++) {
            if (i == 0) continue;
            img.fillOval(cx + i * 42 - 3, cy - 3, 7, 7);
            img.fillOval(cx - 3, cy + i * 42 - 3, 7, 7);
        }
        
        img.setColor(new Color(50, 180, 50, 120));
        for (int i = -3; i <= 3; i++) {
            int tx = cx + i * 42;
            int ty = cy + i * 42;
            img.drawLine(tx, cy - 5, tx, cy + 5);
            img.drawLine(cx - 5, ty, cx + 5, ty);
        }
        
        img.setColor(new Color(255, 80, 80, 230));
        img.fillOval(cx-4, cy-4, 8, 8);
        img.setColor(new Color(255, 200, 200, 180));
        img.fillOval(cx-1, cy-1, 3, 3);
        
        img.setFont(new Font("Monospaced", false, false, 9));
        img.setColor(new Color(100, 240, 100, 140));
        int[] ranges = {100, 200, 300, 400, 500, 600};
        for (int i = 1; i <= 3; i++) {
            if (i < ranges.length) {
                img.drawString(String.valueOf(ranges[i-1]), cx + i * 42 + 4, cy - 5);
                img.drawString(String.valueOf(ranges[i-1]), cx + 4, cy - i * 42 - 2);
            }
        }
        
        setImage(img);
    }
    
    public void buildOff()
    {
        GreenfootImage img = new GreenfootImage(1, 1);
        img.setColor(new Color(0,0,0,0));
        img.fill();
        setImage(img);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
