package dev.pelletier2017.runelite.boltsounds.sound;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum SoundFile {
    DEFAULT("default", null),
    SILENT("silent", null),
    GUNSHOT("cockAndShoot", "cockAndShoot.wav"),
    GUNSHOT2("cockAndShoot2", "cockAndShoot2.wav"),
    LONG_3_SHOTS("long3shots", "long3shots.wav"),
    LONG_COCK_SHOOT("longCockAndShoot", "longCockAndShoot.wav"),
    PEW("pew", "pew.wav"),
    ;

    private final String soundName;
    private final String fileName;

    SoundFile(String soundName, String fileName) {
        this.soundName = soundName;
        this.fileName = fileName;
    }

    public static Optional<SoundFile> byName(String soundName) {
        for (SoundFile soundFile : SoundFile.values()) {
            if (soundFile.soundName.equals(soundName)) {
                return Optional.of(soundFile);
            }
        }
        return Optional.empty();
    }

    public static SoundFile[] playableSounds() {
        return Arrays.stream(SoundFile.values())
                .filter(soundFile -> !soundFile.equals(SoundFile.DEFAULT) && !soundFile.equals(SoundFile.SILENT))
                .toArray(SoundFile[]::new);
    }

}
