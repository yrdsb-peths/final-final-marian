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
    
    boolean peaking = false;
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
        
    }
    public void act()
    {
        // Add your action code here.
    }
}
