package dev.pelletier2017.runelite.boltsounds.sound;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum SoundFile {
    // requirements (needs to be 2 ticks or less or else they overlap a lot)

    // find more sound effects here https://pixabay.com/sound-effects

    // website to make wave files
    // youtube downloader https://ytmp3.nu/8zCl/
    // clip audio https://clideo.com/cut-audio
    // convert mp3 to wav https://cloudconvert.com/mp3-to-wav

    // mario coin sound effects https://www.youtube.com/watch?v=gdPFOF91xb0
    // cartoon arrow https://www.youtube.com/watch?v=c2uglXLrWgo
    // arrow hitting target https://www.youtube.com/watch?v=LhILCoFuy3Y
    // arrow going through target https://www.youtube.com/watch?v=bI0i2jRYTeA
    // airhorn (2-3 seconds) https://www.youtube.com/watch?v=OFr74zI1LBM
    // trumpet (2 seconds) https://www.youtube.com/watch?v=gTFoHCyXTIY
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
