import greenfoot.*;  

/**
 * EndScreen displays the final results after a game ends
 * 
 * @author Marian Li 
 * @version Jun 7 2026
 */
public class EndScreen extends Actor
{
    static final int W = SniperWorld.SW;
    static final int H = SniperWorld.SH;
    
    
    String type;
    int score;
    int level;
    int animTick = 0;
    
    GreenfootImage bgImage;
    
    /**
     * Build an EndScreen with the given type, score, and level
     */
    public EndScreen(String type, int score, int level)
    {
        this.type = type;
        this.score = score;
        this.level = level;
        bgImage = new GreenfootImage("EndingBackground.png");
        bgImage.scale(W, H);
        draw(0);
    }
    
    /**
     * Called every frame
     */    
    public void act()
    {
        animTick++;
        if(animTick % 2 == 0)
        {
            draw(animTick);
        }
    }
    
    /**
     * Redraws the end screen image 
     */
    private void draw(int tick)
    {
        GreenfootImage img = new GreenfootImage(W, H);
        
        img.drawImage(bgImage, 0, 0);
        img.setColor(new Color(0,0,0,100));
        img.fillRect(0,0,W,H);
        
        boolean win = type.equals("WIN");
        int pulse = (int)(Math.sin(tick * 0.08) * 6);
        
        img.setFont(new Font("Times New Roman", true, false, 100 + pulse));
        img.setColor(new Color(0, 0, 0, 160));
        img.drawString(win ? "YOU WIN!" : "GAME OVER", W / 2 - 290 + 4, H / 2 - 130 + 4);
        
        if (win)
        {
            img.setColor(new Color(255, 230, 60));
        }
        else
        {
            img.setColor(new Color(255, 60, 60));
        }
        img.drawString(win ? "YOU WIN!" : "GAME OVER", W / 2 - 290, H / 2 - 130);
        
        int cardW = 520;
        int cardH = 160;
        int cardX = W/2 - cardW/2;
        int cardY = H/2 - 40;
        
        img.setColor(new Color(0, 0, 0, 170));
        img.fillRect(cardX, cardY, cardW, cardH);
        
        if (win)
        {
            img.setColor(new Color(255, 220, 60, 180));
        }
        else
        {
            img.setColor(new Color(220, 60, 60, 180));
        }
        img.drawRect(cardX, cardY, cardW, cardH);
        img.drawRect(cardX + 2, cardY + 2, cardW - 4, cardH - 4);
        
        /**
         * Final score line
         */
        img.setFont(new Font("Times New Roman", true, false, 28));
        img.setColor(new Color(255, 240, 100));
        String scoreStr = "Final Score:  " + score;
        img.drawString(scoreStr, W / 2 - scoreStr.length() * 8, cardY + 50);
        
        /**
         * Level info line
         */
        img.setFont(new Font("Times New Roman", false, true, 20));
        img.setColor(new Color(220, 220, 220));
        String levelStr = win ? "All 10 levels conquered!" : "Reached Level " + level;
        img.drawString(levelStr, W / 2 - levelStr.length() * 6, cardY + 88);
        
        /**
         * Congratulations message
         */
        if (win)
        {
            img.setFont(new Font("Times New Roman", true, false, 24));
            img.setColor(new Color(255, 220, 50));
            String stars = "* * *  Congratulations!  * * *";
            img.drawString(stars, W / 2 - stars.length() * 7, cardY + 130);
        }
        else
        {
            img.setFont(new Font("Times New Roman", false, true, 18));
            img.setColor(new Color(200, 200, 200));
            String msg = "Better luck next time!";
            img.drawString(msg, W / 2 - msg.length() * 5, cardY + 130);
        }
        
        /**
         * Restart button
         */
        int btnPulse = (int)(Math.sin(tick * 0.09) * 8);
        int btnW = 260 + btnPulse;
        int btnH = 52;
        int btnX = W / 2 - btnW / 2;
        int btnY = cardY + cardH + 30;

        img.setColor(new Color(30, 100, 30, 210));
        img.fillRect(btnX, btnY, btnW, btnH);
        img.setColor(new Color(100, 220, 100, 220));
        img.drawRect(btnX, btnY, btnW, btnH);
        img.drawRect(btnX + 2, btnY + 2, btnW - 4, btnH - 4);

        img.setFont(new Font("Times New Roman", true, false, 22));
        img.setColor(Color.WHITE);
        img.drawString("Press  R  to  Restart", btnX + 22, btnY + 35);

        setImage(img);
    }
}
