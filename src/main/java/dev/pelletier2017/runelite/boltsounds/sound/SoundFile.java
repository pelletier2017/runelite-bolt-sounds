package dev.pelletier2017.runelite.boltsounds.sound;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum SoundFile {
    DEFAULT("default", null),
    SILENT("silent", null),
    AMONGUS("among us", "amongus.wav"),
    DC17_BLASTER("dc17 blaster", "dc17Blaster.wav"),
    GUNSHOT3("gunshot3", "gunshot3.wav"),
    LAZER_CANNON("lazer cannon", "lazercannon.wav"),
    PEW("pew", "pew.wav"),
    PISTOL_RICCOCHET("pistol riccochet", "pistol_riccochet.wav"),
    STARWARS_BLASTER("starwars blaster", "starwarsBlaster.wav"),
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
