package dev.pelletier2017.runelite.boltsounds.sound;

import dev.pelletier2017.runelite.boltsounds.BoltSoundConfig;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Singleton
public class SoundManager {

    @Inject
    private BoltSoundConfig config;

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

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

    public void play(SoundFile soundFile) {
        try {
            log.debug("Playing=" + soundFile.getFileName());
            if (soundFile.equals(SoundFile.SILENT)) {
                log.debug("Playing SILENT sound");
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

            Clip clip = (Clip) AudioSystem.getLine(info);

            executorService.submit(() -> {
                log.debug("start on thread playing=" + soundFile.getFileName());
                try {
                    clip.open(stream);
                } catch (LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }

                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volumeValue = (float) (volume.getMinimum() + ((50 + (config.volumeLevel() / 2.0)) * ((volume.getMaximum() - volume.getMinimum()) / 100)));
                volume.setValue(volumeValue);

                // clip.start() fails when multiple sounds are playing
                while (!clip.isRunning()) {
                    clip.start();
                }

                executorService.submit(() -> {
                    log.debug("start closing=" + soundFile.getFileName());
                    while (clip.isRunning()) {
                        try {
                            log.debug("Sleeping waiting to close");
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.debug("No longer running");

                    clip.close();
                    log.debug("done closing=" + soundFile.getFileName());
                });
                log.debug("done on thread playing=" + soundFile.getFileName());
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
