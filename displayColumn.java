import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class displayColumn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayColumn extends Actor
{
    /**
     * Act - do whatever the displayColumn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SniperWorld world;
    
    static final int W = SniperWorld.SW;
    static final int H = SniperWorld.SH;
    
    public DisplayColumn(SniperWorld w)
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
        img.drawString("           SCORE: " + world.getScore(), 120, H - 18);

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
            drawBigOverlay(img, "GAME OVER", "Score: " + world.getScore(), "Press R to Restart", new Color(140,20,20,220));
        } else if (phase.equals("YOU WIN"))
        {
            drawBigOverlay(img, "YOU WIN!", "Final Score: " + world.getScore(), "Press R to Play Again", new Color(20,60,180,220));
        } else if (phase.equals("LEVEL DOWN"))
        {
            img.setColor(new Color(0, 0, 0, 145));
            img.fillRect(W / 2 - 290, H / 2 - 58, 580, 82);
            img.setColor(new Color(80, 230, 80, 210));
            img.drawRect(W / 2 - 290, H / 2 - 58, 580, 82);

            img.setColor(new Color(155, 255, 110));
            img.setFont(new Font("Times New Roman", true, false, 30));
            String s = "LEVEL " + world.getLevel() + " CLEAR!";
            img.drawString(s, W / 2 - s.length() * 17 / 2, H / 2 - 15);

            img.setColor(new Color(220, 255, 190));
            img.setFont(new Font("Times New Roman", false, false, 16));
            String hint = "Press SPACE or ENTER to continue";
            img.drawString(hint, W / 2 - hint.length() * 9 / 2, H / 2 + 16);
        }
        setImage(img);
    }
    
    public void drawAlienCounter(GreenfootImage img, int total, int left, int found)
    {
        int iconSize = 24;
        int spacing = 30;
        int totalWidth = total * spacing;
        int startX = W / 2 - totalWidth/2;
        int y = 58;
        
        img.setColor(new Color(0,0,0,150));
        img.fillRect(startX - 12, y-8, totalWidth+12, iconSize+16);
        img.setColor(new Color(80,200,80,120));
        img.drawRect(startX - 12, y-8, totalWidth+12, iconSize+16);
        
        img.setFont(new Font("Times New Roman", true, false, 12));
        img.setColor(new Color(255,230,100,200));
        img.drawString("TARGETS:", startX-80, y+16);
        
        for (int i = 0; i < total; i++)
        {
            int ix = startX + i*spacing;
            boolean killed = i < found;
            drawMiniAlien(img, ix+iconSize/2, y+iconSize/2, iconSize, killed);
        }
    }
    
    public void drawMiniAlien(GreenfootImage img, int cx, int cy, int size, boolean killed)
    {
        int s = size;
        int r = s/2;
        
        if(killed)
        {
            img.setColor(new Color(120, 120, 120, 150));
            img.fillOval(cx - r, cy - r, s, s);
            img.setColor(new Color(200, 60, 60, 200));
            img.drawLine(cx - r + 2, cy - r + 2, cx + r - 2, cy + r - 2);
            img.drawLine(cx + r - 2, cy - r + 2, cx - r + 2, cy + r - 2);
        }
        else
        {
            img.setColor(new Color(40, 220, 60, 230));
            img.fillOval(cx - r, cy - r + 2, s, s - 2);
            img.setColor(new Color(255, 40, 40, 240));
            img.fillOval(cx - 4, cy - 2, 3, 3);
            img.fillOval(cx + 1, cy - 2, 3, 3);
            img.setColor(new Color(40, 200, 40, 200));
            img.drawLine(cx - 2, cy - r + 2, cx - 5, cy - r - 2);
            img.drawLine(cx + 2, cy - r + 2, cx + 5, cy - r - 2);
        }
    }
    
    public void drawBanner(GreenfootImage img, String text)
    {
        int tw = text.length() * 11;
        int bx = W/2 - tw/2-24;
        int bw = tw + 48;
        int by = H/2-32;
        
        img.setColor(new Color(0, 0, 0, 165));
        img.fillRect(Math.max(0, bx), by, Math.min(W, bw), 54);
        img.setColor(new Color(255, 230, 60, 220));
        img.drawRect(Math.max(0, bx), by, Math.min(W, bw), 54);
        
        img.setColor(new Color(255, 255, 130));
        img.setFont(new Font("Times New Roman", true, false, 18));
        img.drawString(text, Math.max(8, W/2 - tw / 2), H / 2 + 4);
    }
    
    public void drawBigOverlay(GreenfootImage img, String title, String sub, String hint, Color panelColor)
    {
        img.setColor(new Color(0, 0, 0, 175));
        img.fillRect(0, 0, W, H);

        img.setColor(panelColor);
        img.fillRect(W / 2 - 280, H / 2 - 115, 560, 230);
        img.setColor(new Color(255, 255, 255, 150));
        img.drawRect(W / 2 - 280, H / 2 - 115, 560, 230);
        
        img.setColor(Color.WHITE);
        img.setFont(new Font("Times New Roman", true, false, 42));
        int tw = title.length() * 25;
        img.drawString(title, W / 2 - tw / 2, H / 2 - 40);
        
        img.setFont(new Font("Times New Roman", true, false, 22));
        int sw = sub.length() * 13;
        img.drawString(sub, W / 2 - sw / 2, H / 2 + 15);
        
        img.setColor(new Color(255, 255, 100));
        img.setFont(new Font("Times New Roman", false, false, 17));
        int hw = hint.length() * 10;
        img.drawString(hint, W / 2 - hw / 2, H / 2 + 68);        
    }
    
    public String heartsStr(int lives)
    {
        if(lives >= 3)
        {
            return "♥♥♥";
        }
        
        if(lives == 2)
        {
            return "♥♥";
        }
        
        if(lives == 1)
        {
            return "♥";
        }
        return "---";
    }
    
    

}
