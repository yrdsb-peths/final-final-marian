import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class SniperWorld extends World {
    //background image size
    static final int SW = 1370;
    static final int SH = 770;

    static final int MAP_W = 1672;
    static final int MAP_H = 941;

    //pan
    int panX = 0;
    int panY = 0;
    static final int PAN_SPEED = 6;

    //zoom
    boolean zoomed = false;
    static final double ZOOM_SCALE = 2.5;
    int zoomViewW = (int)(SW/ZOOM_SCALE);
    int zoomViewH = (int)(SH/ZOOM_SCALE);

    int zoomPanX = 0;
    int zoomPanY = 0;
    int prevMouseX = SW/2;
    int prevMouseY = SH/2;
    static final int ZOOM_PAN_SPEED = 4;
    
    int currentLevel = 1;
    static final int MAX_LEVEL = 10;
    int aliensRemaining = 0;
    int totalAliensThisLevel = 0;
    int score = 0;
    int shotsFired = 0;
    int lives = 3;
    
    String gamePhase = "PLAYING";
    
    String bannerText = "";
    int bannerTimer = 0;
    int shootCooldown = 0;
    
    List<Alien> aliens = new ArrayList<>();

    displayColumn hud;
    zoomView scope;
    CrosshairActor crosshair;

    boolean prevZ = false;
    boolean prevSpace = false;
    boolean prevLeftClick = false;
    
    
    GreenfootImage fullBgImage;
    public SniperWorld()
    {
        super(SW, SH, 1);
        Greenfoot.setSpeed(50);
        setup();
    }

    public void setup()
    {
        fullBgImage = new GreenfootImage("background_final.png");

        hud = new displayColumn (this);
        scope = new zoomView(this);
        crosshair = new CrosshairActor();

        addObject(hud, SW/2, SH/2);
        addObject(scope, SW/2, SH/2);
        addObject(crosshair, SW/2, SH/2);

        scope.setActive(false);
        crosshair.setActive(false);

        startLevel(1);
    }

    public void act()
    {
        if(gamePhase.equals("PLAYING"))
        {
            doPanning();
            doZoom();

        }
    }

    public void doPanning()
    {
        if(zoomed) return;

        int speed = PAN_SPEED;

        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
            panX -= speed;

        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
            panX += speed;

        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
            panX -= speed;

        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
            panX += speed;

        panX = clamp(panX, 0, MAP_W - SW);
        panY = clamp(panY, 0, MAP_H - SH);

        for(Alien a : aliens)
        {
            a.setLocation(a.mapX - panX, a.mapY - panY);
        }
    }

    public void doZoom()
    {
        boolean zNow = Greenfoot.isKeyDown("z");
        if (zNow && !prevZ)
        {
            toggleZoom();
        }
        prevZ = zNow;

        MouseInfo m = Greenfoot.getMouseInfo();
        if (m != null && m.getButton() == 3 && m.getClickCount() > 0)
        {
            toggleZoom();
        }

        if (zoomed)
        {
            if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) zoomPanX = clamp(zoomPanX - ZOOM_PAN_SPEED, 0, MAP_W - zoomViewW);
            if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) zoomPanX = clamp(zoomPanX - ZOOM_PAN_SPEED, 0, MAP_W - zoomViewW);
            if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) zoomPanX = clamp(zoomPanX - ZOOM_PAN_SPEED, 0, MAP_W - zoomViewH);
            if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) zoomPanX = clamp(zoomPanX - ZOOM_PAN_SPEED, 0, MAP_W - zoomViewH);

            crosshair.setLocation(SW / 2, SH / 2);
            scope.setLocation(SW / 2, SH / 2);

            drawBackground();
        }
    }/**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }

    public void toggleZoom()
    {
        zoomed = !zoomed;
        scope.setActive(zoomed);
        crosshair.setActive(zoomed);
        if(zoomed)
        {
            zoomPanX = clamp(panX + SW/2 - zoomViewW/2, 0, MAP_W - zoomViewW);
            zoomPanY = clamp(panY + SH/2 - zoomViewH/2, 0, MAP_H - zoomViewH);
            MouseInfo m = Greenfoot.getMouseInfo();
            if (m != null) {
                prevMouseX = m.getX();
                prevMouseY = m.getY();
            }
            crosshair.setLocation(SW / 2, SH / 2);
            scope.setLocation(SW / 2, SH / 2);
        }
        drawBackground();
    }

    public void doShooting()
    {
        if (shootCooldown > 0)
        {
            return;
        }
        
        boolean spaceNow = Greenfoot.isKeyDown("space");
        MouseInfo m = Greenfoot.getMouseInfo();
        boolean leftClickNow = (m != null & m.getButton() == 1 && m.getClickCount() > 0);
        
        boolean firePressed = (spaceNow && !prevSpace) || (leftClickNow && zoomed && !prevLeftClick);
        prevSpace = spaceNow;
        prevLeftClick = leftClickNow;
        
        if (leftClickNow && !zoomed && m != null)
        {
            toggleZoom();
            return;
        }
        
        if (firePressed && zoomed)
        {
            fireShot();
        }
    }

    public void fireShot()
    {
        shootCooldown = 15;
        shotsFired++;
        
        int targetMapX = zoomPanX + zoomViewW / 2;
        int targetMapY = zoomPanY + zoomViewH / 2;
        
        int hitR = 35;
        boolean hit = false;
        
        for(Alien a : aliens)
        {
            if (!a.alive) continue;
            int dx = a.mapX - targetMapX;
            int dy = a.mapY - targetMapY;
            
            if (dx * dx + dy * dy <= hitR * hitR)
            {
                killAlien(a);
                hit = true;
                break;
            }
        }
        
        if (!hit)
        {
            lives--;
            showBanner("MISSED ! Lives: " + heartsStr(lives), 90);
            if(lives <= 0)
            {
                gamePhase = "GAME OVER";
                showBanner("GAME OVER - Press R to Restart", 9999);
            }
        }
    }

    public void killAllien(Alien a)
    {
        a.alive = false;
        aliensRemaining--;
        int pts = 100 + currentLevel * 20;
        score += pts;
        showBanner ("HIT! +" + pts + " pts - " + aliensRemaining + " left", 80);
        
        if (aliensRemaining <= 0)
        {
            int bonus = Math.max(0, 300 * lives);
            sscore += bonus;
            gamePhase = "LEVEL FINISHED";
            showBanner("LEVEL " + currentLevel + " CLEAR! BONUS +" + bonus + " SPACE to continue", 9999);
            zoomed = false;
            scope.setActive(false);
            crosshair.setActive(false);
        }
    }
    
    public void doAlienTick()
    {
        List<Alien> toRemove = new ArrayList<>();
        for (Alien a : aliens)
        {
            a.tick(currentLevel);
            if (a.readyToRemove())
            {
                toRemove.add(a);
            }
        }
        for (Alien a : toRemove)
        {
            removeObject(a);
            aliens.remove(a);
        }
    }
    
    public void drawBackground()
    {
        if (fullBgImage == null)
        {
            return;
        }
        
        if (zoomed)
        {
            int srcX = zoomPanX;
            int srcY = zoomPanY;
            int srcW = zoomViewW;
            int srcH = zoomViewH;
            
            GreenfootImage view = new GreenfootImage(SW, SH);
            GreenfootImage cropped = new GreenfootImage(srcW, srcH);
            cropped.drawImage(fullBgImage, -srcX, -srcY);
            cropped.scale(SW, SH);
            view.drawImage(cropped, 0, 0);
            setBackground(view);
            
            for (Alien a : aliens)
            {
                int screenX = (int) ((a.mapX - srcX) * ZOOM_SCALE);
                int screenY = (int) ((a.mapY - srcY) * ZOOM_SCALE);
                a.setLocation(screenX, screenY);
                
                boolean inCircle = isInScopeCircle(screenX, screenY);
                a.setHidden(! (inCircle && a.alive));
            }
        } else
            {
               GreenfootImage view = new GreenfootImage(SW, SH);
               view.drawImage(fullBgImage, -panX, -panY);
               setBackground(view);
               
               for (Alien a : aliens)
               {
                   a.setLocation(a.mapX - panX, a.mapY - panY);
                   a.setHidden(false);
               }
            }
        
    }
    
    public boolean isInScopeCircle(int sx, int sy)
    {
        int dx = sx - SW / 2;
        int dy = sy - SY / 2;
        int r = zoomView.RADIUS;
        return dx * dx + dy * dy <= r * r;
    }
    
    public void showBanner(String text, int ticks)
    {
        bannerText = text;
        bannerTimer = ticks;
    }
    
    public static int clamp(int a, int b, int c)
    {
        return Math.max(b, Math.min(c, a));
    }
    
    public String heartsStr(int lives)
    {
        return (lives >= 3 ? "♥♥♥" : lives == 2 ? "♥♥" : lives == 1 ? "♥" : "");
    }
    
    public int getLevel()
    {
        return currentLevel;
    }
    
    public int getAliensLeft()
    {
        return aliensRemaining;
    }
    
    public int getTotalAliens()
    {
        return totalAliensThisLevel;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getShots()
    {
        return shotsFired;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public String getBanner()
    {
        return bannerText;
    }
    
    public boolean bannerVisible()
    {
        return bannerTimer > 0;
    }
    
    public String getPhase()
    {
        return gamePhase;
    }
    
    public boolean isZoomed()
    {
        return zoomed;
    }

    


    
}
