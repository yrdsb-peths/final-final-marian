import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Alien here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien extends Actor
{
    int mapX;
    int mapY;
    int type;
    int level;
    boolean alive = true;
    boolean hidden = false;
    boolean useCamo = true;
    
    int bgR;
    int bgG;
    int bgB;
    
    int swayPhase;
    int tickCount = 0;
    int bobOffset = 0;
    
    boolean peeking = false;
    int peekTimer = 0;
    int peekCooldown;
    
    int deathTick = 0;
    static final int DEATH_DUR = 30;
    
    GreenfootImage imgNormal;
    GreenfootImage imgPeek;
    GreenfootImage imgDead;
    
    public Alien (int mapX, int mapY, int type, int level, Color bgColor)
    {
        this.mapX = mapX;
        this.mapY = mapY;
        this.type = type;
        this.level = level;
        
        if (bgColor != null)
        {
            this.bgR = bgColor.getRed();
            this.bgG = bgColor.getGreen();
            this.bgB = bgColor.getBlue();
            this.useCamo = true;
        }
        else
        {
            this.bgR = 60;
            this.bgG = 200;
            this.bgB = 60;
            this.useCamo = false;
        }
        
        swayPhase = Greenfoot.getRandomNumber(300);
        peekCooldown = 100 + Greenfoot.getRandomNumber(160) + level * 20;
        
        buildImages();
        setImage(imgNormal);
        
    }
    
    public void buildImages()
    {
        imgNormal = makeImage(false, false);
        imgPeek = makeImage(true, false);
        imgDead = makeImage(false, true);
    }
    
    public int baseSize()
    {
        if (type == 0)
        {
            return 36;
        }
        else if (type == 1)
        {
            return 30;
        }
        else if (type == 2)
        {
            return 26;
        }
        else if (type == 3)
        {
            return 22;
        }
        return 30;
    }
    
    public GreenfootImage makeImage(boolean peek, boolean dead)
    {
        int s = baseSize();
        int h = s + s*3 /4;
        int iw = s+20;
        int ih = h+16;
        
        GreenfootImage img = new GreenfootImage(iw, ih);
        
        img.setColor(new Color(0,0,0,0));
        img.fill();
        
        int cx = iw / 2;
        
        int shift;
        int alpha;
        
        if (type == 0)
        {
            shift = 85;
            alpha = 245;
        }
        else if (type == 1)
        {
            shift = 45;
            alpha = 220;
        }
        else if (type == 2)
        {
            shift = 28;
            alpha = 195;
        }
        else if (type == 3)
        {
            shift = 15;
            alpha = 140;
        }
        else
        {
            shift = 60;
            alpha = 230;
        }
        
        int br;
        int bg;
        int bb;
        
        if(useCamo == false)
        {
            br = 50 + type * 10;
            bg = 200 - type * 20;
            bb = 50;
        }
        else
        {
            int brightness = (bgR + bgG + bgB) / 3;
            if (brightness > 128) {
                br = clamp(bgR - shift, 0, 255);
                bg = clamp(bgG - shift + 20, 0, 255);
                bb = clamp(bgB - shift + 10, 0, 255);
            } 
            else {
                br = clamp(bgR + shift, 0, 255);
                bg = clamp(bgG + shift, 0, 255);
                bb = clamp(bgB + shift / 2, 0, 255);
            }
        }
        
        int er = clamp(255 - br + 80, 0, 255);
        int eg = clamp(60, 0, 255);
        int eb = clamp(255 - bb, 0, 255);
        
        if (peek)
        {
            br = clamp(br + 70, 0, 255);
            bg = clamp(bg + 70, 0, 255);
            bb = clamp(bb + 70, 0, 255);
            alpha = Math.min(255, alpha + 60);
        }
        
        if (dead)
        {
            br = 180; bg = 50; bb = 50;
            alpha = 190;
        }
        
        Color bodyColor = new Color(br, bg, bb, alpha);
        Color outlineColor = new Color(clamp(br-30,0,255), clamp(bg-30,0,255), clamp(bb-30,0,255), alpha);
        Color eyeColor = new Color(er, eg, eb, Math.min(255, alpha + 20));
        Color shineColor = new Color(255, 255, 255, Math.min(255, alpha));
        Color antColor = new Color(clamp(br+20,0,255), clamp(bg+20,0,255), clamp(bb+20,0,255), alpha);
        
        
        
        if (!dead) {
            Color glowColor = new Color(br, bg, bb, alpha / 5);
            img.setColor(glowColor);
            img.fillOval(cx - s/2 - 4, h/3 - 4, s + 8, h * 2/3 + 8);
        }
        
        img.setColor(bodyColor);
        img.fillOval(cx - s/2, h/3, s, h*2/3);
        img.fillOval(cx - s*2/5, 6, s*4/5, s*4/5);
        
        img.setColor(outlineColor);
        img.drawOval(cx - s/2, h/3, s, h*2/3);
        img.drawOval(cx - s*2/5, 6, s*4/5, s*4/5);
        
        if (!dead) {
            Color stripeColor = new Color(clamp(br+15,0,255), clamp(bg+15,0,255), clamp(bb+15,0,255), alpha/2);
            img.setColor(stripeColor);
            int bx = cx - s/3;
            int by = h/2;
            img.fillOval(bx, by, s*2/3, s/3);
        }
        
        img.setColor(antColor);
        img.drawLine(cx - 6, 10, cx - 13, 1);
        img.drawLine(cx + 6, 10, cx + 13, 1);
        
        Color orbColor;
        if(peek)
        {
            orbColor = new Color(255, 255, 100, 240);
        }
        else
        {
            orbColor = eyeColor;
        }
        img.setColor(orbColor);
        img.fillOval(cx - 17, -3, 8, 8);
        img.fillOval(cx + 9,  -3, 8, 8);
        
        img.setColor(shineColor);
        img.fillOval(cx - 16, -2, 3, 3);
        img.fillOval(cx + 10, -2, 3, 3);
        
        //Eyes
        img.setColor(new Color(15, 15, 15, alpha));  
        img.fillOval(cx - 11, s/3 - 1, 10, 10);
        img.fillOval(cx + 1,  s/3 - 1, 10, 10);
        
        img.setColor(eyeColor);
        img.fillOval(cx - 10, s/3, 8, 8);
        img.fillOval(cx + 2,  s/3, 8, 8);
        
        img.setColor(shineColor);
        img.fillOval(cx - 9,  s/3 + 1, 3, 3);
        img.fillOval(cx + 3,  s/3 + 1, 3, 3);
        
        if (dead)
        {
            img.setColor(new Color(220, 30, 30, 200));
            img.drawLine(cx-11, s/3-1, cx-1, s/3+9);
            img.drawLine(cx-11, s/3+9, cx-1, s/3-1);
            img.drawLine(cx+1, s/3-1, cx+11, s/3+9);
            img.drawLine(cx+1, s/3+9, cx+11, s/3-1);
        }
        
        //arms
        img.setColor(outlineColor);
        img.drawLine(cx - s/2, h/2 + 4, cx - s/2 - 8, h/2 + 14);
        img.drawLine(cx + s/2 - 1, h/2 + 4, cx + s/2 + 7, h/2 + 14);
        
        //hand
        img.setColor(bodyColor);
        img.fillOval(cx - s/2 - 10, h/2 + 12, 6, 6);
        img.fillOval(cx + s/2 + 5,  h/2 + 12, 6, 6);
        
        //feet
        img.setColor(bodyColor);
        img.fillOval(cx - s/3 - 2, h - 4, s/3, 8);
        img.fillOval(cx + 2, h - 4, s/3, 8);
        img.setColor(outlineColor);
        img.drawOval(cx - s/3 - 2, h - 4, s/3, 8);
        img.drawOval(cx + 2, h - 4, s/3, 8);
        
        return img;
    }
    
    public void tick(int level)
    {
        tickCount++;
        
        if(!alive)
        {
            deathTick++;
            if(deathTick <= DEATH_DUR)
            {
                double scale = 1.0 - (double) deathTick / DEATH_DUR;
                int iw = baseSize() + 20;
                int ih = baseSize() + baseSize() * 3/4 + 16;
                int nw = Math.max(1, (int)(iw * scale));
                int nh = Math.max(1, (int)(ih * scale));
                GreenfootImage d = new GreenfootImage(imgDead);
                d.scale(nw, nh);
                setImage(d);
            }
            return;
        }
        
        int phase = tickCount + swayPhase;

        while(phase >= 60)
        {
            phase = phase - 60;
        }
        
        if(phase < 30)
        {
            bobOffset = 1;
        }
        else
        {
            bobOffset = 0;
        }
        
        SniperWorld w = (SniperWorld) getWorld();
        if(w != null)
        {
            if(w.isZoomed() == false)
            {
                int sx = mapX - w.panX;
                int sy = mapY - w.panY + bobOffset;
                setLocation(sx, sy);
            }
        }
        
        
        int remainder = tickCount;

        while(remainder >= peekCooldown)
        {
            remainder = remainder - peekCooldown;
        }
        
        if(peeking == false && remainder == 0)
        {
            peeking = true;
        
            int t = 30 - level * 2;
        
            if(t < 8)
            {
                peekTimer = 8;
            }
            else
            {
                peekTimer = t;
            }
        
            setImage(imgPeek);
        }
        
        if(peeking)
        {
            peekTimer--;
            if(peekTimer <= 0)
            {
                peeking = false;
                setImage(imgNormal);
            }
        }
    }
    
    
    public boolean readyToRemove()
    {
        return !alive && deathTick > DEATH_DUR + 5;
    }
    
    public int clamp(int a, int b, int c)
    {
        return Math.max(b, Math.min(c,a));
    }
    
    public void setHidden(boolean hide)
    {
        hidden = hide;
        if(hide)
        {
            setImage(new GreenfootImage(1,1));
        }
        else
        {
            if(!alive)
            {
                setImage(imgDead);
            }
            else if (peeking)
            {
                setImage(imgPeek);
            }
            else
            {
                setImage(imgNormal);
            }
        }
    }
}
