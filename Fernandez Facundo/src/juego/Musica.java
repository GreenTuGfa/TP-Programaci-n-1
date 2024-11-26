package juego;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {
    private Clip clip;

    public Musica(String path) {
        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducir() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Esto hace que la música se repita
        }
    }

    public void detener() {
        if (clip != null) {
            clip.stop();
        }
    }
}
