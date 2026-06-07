import greenfoot.*;

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

    displayColumn hud;
    zoomView scope;
    CrosshairActor crosshair;

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

    }

    public void fireShot()
    {

    }

    public void killAllien(Alien a)
    {

    }
    
    static int clamp(int a, int b, int c)
    {
        return Math.max(b, Math.min(c, a));
    }
}
