package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private Clip backgroundClip;
    private Clip soundEffectClip;

    // Hatterzene inditasa
    public void playBackgroundMusic(String filePath) {
        stopBackgroundMusic();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // vegtelen zenelejatszas
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("Hiba a hatterzene lejatszasakor: " + e.getMessage());
        }
    }

    // Hatterzene leallitasa
    public void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    // Hangeffekt lejatszasa
    public void playSoundEffect(String filePath) {
        try {
            stopSoundEffect(); // Előző hangeffekt leállítása
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            soundEffectClip = AudioSystem.getClip();
            soundEffectClip.open(audioInputStream);
            soundEffectClip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("Hiba a hangeffekt lejátszásakor: " + e.getMessage());
        }
    }

    // Hangeffekt leallitasa
    public void stopSoundEffect() {
        if (soundEffectClip != null && soundEffectClip.isRunning()) {
            soundEffectClip.stop();
        }
    }
}
