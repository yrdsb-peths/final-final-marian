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
        
    }
    
    public void refresh()
    {
        GreenfootImage img = new GreenfootImage(W,H);
        img.setColor(new Color(0,0,0,0));
        img.fill();
        
        String phase = world.getPhase();
        
        if (phase.equals("PLAYING")) {
            img.setColor(new Color(255, 255, 255, 160));
            img.setFont(new Font("Times New Roman", false, false, 13));
            img.drawString("Pan view  Z / RClick: Scope  Space / LClick: SHOOT at crosshair center", 10, 20);

            if (world.isZoomed()) {
                img.setColor(new Color(255, 60, 60, 230));
                img.setFont(new Font("Times New Roman", true, false, 15));
                String zs = "WASD to aim  |  Space / Click to FIRE at center dot";
                img.drawString(zs, W / 2 - zs.length() * 4, 42);
            }
        }
    }
}
