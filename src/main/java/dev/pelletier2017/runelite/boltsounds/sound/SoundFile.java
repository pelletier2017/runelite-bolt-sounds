package dev.pelletier2017.runelite.boltsounds.sound;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SoundFile {
    DEFAULT(null),
    SILENT(null),
    AMONGUS("amongus.wav"),
    DC17_BLASTER("dc17Blaster.wav"),
    GUNSHOT("gunshot3.wav"),
    LAZER_CANNON("lazercannon.wav"),
    PEW("pew.wav"),
    PISTOL_RICOCHET("pistol_riccochet.wav"),
    STARWARS_BLASTER("starwarsBlaster.wav"),
    ;

    private final String fileName;

    SoundFile(String fileName) {
        this.fileName = fileName;
    }

    public static SoundFile[] playableSounds() {
        return Arrays.stream(SoundFile.values())
                .filter(soundFile -> !soundFile.equals(SoundFile.DEFAULT) && !soundFile.equals(SoundFile.SILENT))
                .toArray(SoundFile[]::new);
    }

}
