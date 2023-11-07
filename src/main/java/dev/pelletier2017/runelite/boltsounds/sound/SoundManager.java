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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Singleton
public class SoundManager {

    @Inject
    private BoltSoundConfig config;

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static Clip clip;

    public void play(String soundName) {
        Optional<SoundFile> soundFile = SoundFile.byName(soundName);
        if (soundFile.isEmpty()) {
            throw new IllegalArgumentException(String.format("Sound name [%s] not found", soundName));
        } else {
            play(soundFile.get());
        }
    }

    public void playAll(int delayMs) {
        executorService.submit(() -> {
            for (SoundFile soundFile : SoundFile.playableSounds()) {
                play(soundFile);
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // borrowed from https://github.com/Himonn/Imbued-Fart/blob/master/src/main/java/com/imbuedfart/ImbuedFartPlugin.java
    public void play(SoundFile soundFile) {

        try {
            log.info("Playing=" + soundFile.getFileName());
            if (clip != null) {
                executorService.submit(() -> {
                    clip.close();
                });
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

            executorService.submit(() -> {
                try {
                    clip.open(stream);
                } catch (LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }

                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volumeValue = (float) (volume.getMinimum() + ((50 + (config.volumeLevel() / 2.0)) * ((volume.getMaximum() - volume.getMinimum()) / 100)));
                volume.setValue(volumeValue);

                clip.start();
            });

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
