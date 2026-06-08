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
        
        int total = world.getTotalAliens();
        int left = world.getAliensLeft();
        int found = total - left;
        
        if (total > 0 && (phase.equals("PLAYING") || phase.equals("LEVEL_DONE"))) {
            drawAlienCounter(img, total, left, found);
        }
        
        img.setColor(new Color(0, 0, 0, 190));
        img.fillRect(0, H - 48, W, 48);
        img.setColor(new Color(60, 200, 60, 200));
        img.drawLine(0, H - 48, W, H - 48);

        img.setFont(new Font("Times New Roman", true, false, 16));

        img.setColor(new Color(180, 255, 140));
        img.drawString("LV " + world.getLevel() + "/10", 12, H - 18);

        img.setColor(new Color(100, 220, 255));
        img.drawString("SCORE: " + world.getScore(), 120, H - 18);

        img.setColor(new Color(255, 80, 80));
        img.drawString("LIVES: " + heartsStr(world.getLives()), 330, H - 18);

        img.setColor(new Color(200, 180, 255));
        img.drawString("SHOTS: " + world.getShots(), 520, H - 18);

        img.setColor(new Color(255, 220, 80));
        img.drawString("ALIENS: " + left + "/" + total, 700, H - 18);
        
        if(world.bannerVisible())
        {
            drawBanner(img, world.getBanner());
        }
        
        if(phase.equals("GAME OVER"))
        {
            drawBigOverlay(img, "GAME OVER", "Score: " + world.getScore(), 
        }
    }
}
