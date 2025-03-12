package main;

import javax.swing.*;

import utils.SoundManager;

public class Main {
    public static void main(String[] args) {

        SoundManager soundManager = new SoundManager();
        soundManager.playBackgroundMusic("res/sound/bg_music.wav");

        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        //GamePanel
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.launchGame();

        new Thread(() -> {
            while (true) {
                if (gp.gameover) { // ha vege a jateknak
                    soundManager.stopBackgroundMusic();
                    soundManager.playSoundEffect("res/sound/checkmate_effect.wav");
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                soundManager.stopBackgroundMusic();
                System.exit(0);
            }
        });
    }
}
