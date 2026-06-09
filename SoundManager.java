import greenfoot.*;

public class SoundManager  
{
    // instance variables - replace the example below with your own
    private GreenfootSound bgMusic;
    private GreenfootSound killSound;
    private GreenfootSound levelClearSound;
    private GreenfootSound gameOverSound;

    private boolean musicPlaying = false;
    
    public SoundManager()
    {
        bgMusic = new GreenfootSound("backgroundMusic.mp3");
        killSound = new GreenfootSound("kill.mp3");
        levelClearSound = new GreenfootSound("winnerSound.mp3");
        gameOverSound = new GreenfootSound("gameOver.mp3");
        
        bgMusic.setVolume(60);
        killSound.setVolume(90);
        levelClearSound.setVolume(85);
        gameOverSound.setVolume(85);
    }

    public void startMusic()
    {
        if (!musicPlaying)
        {
            bgMusic.playLoop();
            musicPlaying = true;
        }
    }
    
    public void stopMusic()
    {
        bgMusic.stop();
        musicPlaying = false;
    }
    
    public void playKill()
    {
        killSound.stop();
        killSound.play();
    }
    
    public void playLevelClear()
    {
        stopMusic();
        levelClearSound.stop();
        levelClearSound.play();
    }
    
    public void playGameOver()
    {
        stopMusic();
        gameOverSound.stop();
        gameOverSound.play();
    }
    
    public void resumeMusic()
    {
        if (!musicPlaying)
        {
            bgMusic.playLoop();
            musicPlaying = true;
        }
    }
}
