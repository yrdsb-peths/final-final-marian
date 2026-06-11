import greenfoot.*;

/**
 * SoundManager centralises all audio playback for the Alien Lurk game
 * Include background music, kill, level-clear, and game-over sounds.
 * 
 * @author Marian Li
 * @version Jun 7 2026
 */
public class SoundManager  
{
    
    private GreenfootSound bgMusic;
    private GreenfootSound killSound;
    private GreenfootSound levelClearSound;
    private GreenfootSound gameOverSound;

    private boolean musicPlaying = false;
    
    /**
     * Build a SoundManager and loads all audio files
     * Volume levels are set to keep audio balanced relative to each other
     */
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
    
    /**
     * Starts the background music loop if it is not already playing
     */
    public void startMusic()
    {
        if (!musicPlaying)
        {
            bgMusic.playLoop();
            musicPlaying = true;
        }
    }
    
    /**
     * Stops the background music
     */
    public void stopMusic()
    {
        bgMusic.stop();
        musicPlaying = false;
    }
    
    /**
     * Play the kill sound effect
     */
    public void playKill()
    {
        killSound.stop();
        killSound.play();
    }
    
    /**
     * Stops the background music and play the level clear sound
     */
    public void playLevelClear()
    {
        stopMusic();
        levelClearSound.stop();
        levelClearSound.play();
    }
    
    /**
     * Stop the background music and plays the game-over sound
     */
    public void playGameOver()
    {
        stopMusic();
        gameOverSound.stop();
        gameOverSound.play();
    }
    
    /**
     * Resume the background music loop
     */
    public void resumeMusic()
    {
        if (!musicPlaying)
        {
            bgMusic.playLoop();
            musicPlaying = true;
        }
    }
}
