import greenfoot.*;  

/**
 * LevelSelector provides a clickable panel that lets the player jump to any level
 * Allows the player to jump to different levels
 * 
 * @author Marian Li
 * @version Jun 7 2026
 */
public class LevelSelector extends Actor
{
    static final int W = SniperWorld.SW;
    static final int H = SniperWorld.SH;
    
    boolean panelOpen = false;
    int animTick = 0;
    SniperWorld world;
    
    static final int BTN_W = 38;
    static final int BTN_H = 26;
    
    static final int BTN_X = 88;
    static final int BTN_Y_FROM_BOTTOM = 42;
    
    /**
     * Build a LevelSelector linked to the given world
     */
    public LevelSelector(SniperWorld w)
    {
        this.world = w;
        buildButton(false);
    }
    
    /**
     * Called every frame
     */
    public void act()
    {
        animTick++;
        
        MouseInfo m = Greenfoot.getMouseInfo();
        
        if (m != null && m.getButton() == 1 && m.getClickCount() > 0)
        {
            int mx = m.getX();
            int my = m.getY();
            
            if(panelOpen)
            {
                int clicked = getLevelClicked(mx, my);
                if (clicked > 0)
                {
                    world.jumpToLevel(clicked);
                    panelOpen = false;
                }
                else
                {
                    panelOpen = false;
                }
            }
            else
            {
                int btnScreenY = H - BTN_Y_FROM_BOTTOM;
                if (mx >= BTN_X && mx <= BTN_X + BTN_W
                    && my >= btnScreenY && my <= btnScreenY + BTN_H)
                {
                    panelOpen = true;
                }
            }
        }
        
        if(panelOpen)
        {
            buildPanel(animTick);
        }
        else
        {
            buildButton(isHovered());
        }
    }  
    
    /**
     * Return true if the mouse cursor is currently hovering over the toggle button
     */
    public boolean isHovered()
    {
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m == null)
        {
            return false;
        }
        int mx = m.getX();
        int my = m.getY();
        int btnScreenY = H - BTN_Y_FROM_BOTTOM;
        return mx >= BTN_X && mx <= BTN_X + BTN_W && my >= btnScreenY && my <= btnScreenY + BTN_H;
    }
    
    /**
     * Determine which level button was clicked
     */
    public int getLevelClicked(int mx, int my)
    {
        int panW = 340;
        int panH = 220;
        int panX = BTN_X - 10;
        int panY = H - BTN_Y_FROM_BOTTOM - panH - 8;
        
        if (mx < panX || mx > panX + panW || my < panY || my > panY + panH)
        {
            return 0;
        }
                
        int cellW = panW / 5;
        int cellH = (panH - 36) / 2;
        
        for (int row = 0; row < 2; row++)
        {
            for (int col = 0; col < 5; col++)
            {
                int lv = row * 5 + col + 1;
                int cx = panX + col * cellW + 6;
                int cy = panY + 32 + row * cellH + 4;
                if (mx >= cx && mx <= cx + cellW - 8 && my >= cy && my <= cy + cellH - 8)
                {
                    return lv;
                }
            }
        }
        return 0;
    }
    
    /**
     * Draw small toggle button in the bottom-left area
     */
    public void buildButton(boolean hovered)
    {
        GreenfootImage img = new GreenfootImage(W, H);
        img.setColor(new Color(0, 0, 0, 0));
        img.fill();

        int btnScreenY = H - BTN_Y_FROM_BOTTOM;
        
        if (hovered)
        {
            img.setColor(new Color(60, 200, 60, 200));
        }
        else
        {
            img.setColor(new Color(30, 120, 30, 180));
        }
            
        img.fillRect(BTN_X, btnScreenY, BTN_W, BTN_H);
        
        img.setColor(new Color(100, 255, 100, 180));
        img.drawRect(BTN_X, btnScreenY, BTN_W, BTN_H);
        
        img.setFont(new Font("Times New Roman", true, false, 11));
        img.setColor(Color.WHITE);
        img.drawString("▼LV", BTN_X + 5, btnScreenY + 18);
        
        setImage(img);
    }
    
    /**
     * Draw the open level-selection 
     * Current level highlighted
     */
    public void buildPanel(int tick)
    {
        GreenfootImage img = new GreenfootImage(W, H);
        img.setColor(new Color(0, 0, 0, 0));
        img.fill();
        
        int btnScreenY = H - BTN_Y_FROM_BOTTOM;
        img.setColor(new Color(80, 230, 80, 220));
        img.fillRect(BTN_X, btnScreenY, BTN_W, BTN_H);
        img.setColor(new Color(120, 255, 120, 200));
        img.drawRect(BTN_X, btnScreenY, BTN_W, BTN_H);
        img.setFont(new Font("Times New Roman", true, false, 11));
        img.setColor(Color.WHITE);
        img.drawString("▲LV", BTN_X + 5, btnScreenY + 18);
        
        int panW = 340;
        int panH = 220;
        int panX = BTN_X - 10;
        int panY = H - BTN_Y_FROM_BOTTOM - panH - 8;

        img.setColor(new Color(10, 30, 10, 235));
        img.fillRect(panX, panY, panW, panH);
        img.setColor(new Color(60, 200, 60, 200));
        img.drawRect(panX, panY, panW, panH);
        
        img.setFont(new Font("Times New Roman", true, false, 14));
        img.setColor(new Color(100, 255, 100));
        img.drawString("SELECT LEVEL", panX + panW / 2 - 76, panY + 24);

        img.setColor(new Color(60, 160, 60, 100));
        img.drawLine(panX + 14, panY + 30, panX + panW - 14, panY + 30);

        int cellW = panW / 5;
        int cellH = (panH - 36) / 2;
        int currentLv = world.getLevel();
        
        for (int row = 0; row < 2; row++)
        {
            for (int col = 0; col < 5; col++)
            {
                int lv = row * 5 + col + 1;
                int cx = panX + col * cellW + 6;
                int cy = panY + 32 + row * cellH + 4;
                int cw = cellW - 8;
                int ch = cellH - 8;
                
                if (lv == currentLv)
                {
                    img.setColor(new Color(40, 180, 40, 200));
                    img.fillRect(cx, cy, cw, ch);
                }
                else
                {
                    img.setColor(new Color(20, 60, 20, 180));
                    img.fillRect(cx, cy, cw, ch);
                }

                img.setColor(new Color(60, 180, 60, 150));
                img.drawRect(cx, cy, cw, ch);
                
                img.setFont(new Font("Monospaced", true, false, 18));
                if (lv == currentLv)
                {
                    img.setColor(new Color(255, 255, 80));
                }
                    
                else
                {
                    img.setColor(new Color(180, 255, 180));
                }
                    
                img.drawString(String.valueOf(lv), cx + cw / 2 - (lv < 10 ? 6 : 11), cy + ch / 2 + 7);
            }
        }
        setImage(img);
    }
    
}
