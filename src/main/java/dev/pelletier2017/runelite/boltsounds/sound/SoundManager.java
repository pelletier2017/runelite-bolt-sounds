package dev.pelletier2017.runelite.boltsounds.sound;

import dev.pelletier2017.runelite.boltsounds.BoltSoundConfig;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Singleton
public class SoundManager {

    @Inject
    private BoltSoundConfig config;

    private static Clip clip;

    private static final int VOLUME = 10;

    // borrowed from https://github.com/Himonn/Imbued-Fart/blob/master/src/main/java/com/imbuedfart/ImbuedFartPlugin.java
    public void play(String soundName) {
        Optional<SoundFile> soundFile = SoundFile.byName(soundName);
        if (soundFile.isEmpty()) {
            throw new IllegalArgumentException(String.format("Sound name [%s] not found", soundName));
        } else {
            play(soundFile.get());
        }
    }

    public void playAll(int delayMs) throws InterruptedException {
        for (SoundFile soundFile : SoundFile.playableSounds()) {
            play(soundFile);
            Thread.sleep(delayMs);
        }
    }

    public void play(SoundFile soundFile) {
        try {
            log.info("Start play");
            if (clip != null) {
                log.info("Start close");
                // this is what freezes
                clip.close();
                log.info("End close");
            }

            if (soundFile.equals(SoundFile.SILENT)) {
                log.info("Playing SILENT sound");
                return;
            }

            InputStream inputStream = getClass().getResourceAsStream(soundFile.getFileName());

            if (inputStream == null) {
                log.error(String.format("Resource not found: %s", soundFile.getFileName()));
                return;
            }

            BufferedInputStream bis = new BufferedInputStream(inputStream);

            AudioInputStream stream = AudioSystem.getAudioInputStream(bis);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volumeValue = volume.getMinimum() + ((50 + (VOLUME*5)) * ((volume.getMaximum() - volume.getMinimum()) / 100));

            volume.setValue(volumeValue);

            log.info("before start");
            clip.start();
            log.info("after start");
            log.info("end play");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        if (clip != null && clip.isOpen()) {
            clip.close();
        }
    }
}
