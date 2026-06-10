import greenfoot.*;  

/**
 * TitleScreen displays the game's title page
 * 
 * @author Marian Li
 * @version Jun 7 2026
 */
public class TitleScreen extends Actor
{
    static final int W = SniperWorld.SW;
    static final int H = SniperWorld.SH;
    
    /**
     * Build a TitleScreen
     * Fill the full screen
     */
    public TitleScreen()
    {
        GreenfootImage img = new GreenfootImage("titlePage.png");
        img.scale(W, H);
        setImage(img);
    }
    
    /**
     * Called every frame
     */
    public void act()
    {
        // Add your action code here.
    }
}
