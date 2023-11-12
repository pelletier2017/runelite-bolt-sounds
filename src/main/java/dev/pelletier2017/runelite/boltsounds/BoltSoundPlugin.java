package dev.pelletier2017.runelite.boltsounds;

import com.google.inject.Provides;
import dev.pelletier2017.runelite.boltsounds.sound.RunescapeSound;
import dev.pelletier2017.runelite.boltsounds.sound.SoundFile;
import dev.pelletier2017.runelite.boltsounds.sound.SoundManager;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.SoundEffectPlayed;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.Random;

@Slf4j
@PluginDescriptor(
        name = "Bolt Sounds"
)
public class BoltSoundPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private BoltSoundConfig config;

    @Inject
    private SoundManager soundManager;

    @Subscribe
    public void onCommandExecuted(CommandExecuted event) throws InterruptedException {
        String command = event.getCommand();
        log.debug("onCommandExecuted=" + command);
        if (command.equals("boltsound")) {
            soundManager.play(randomSound());
        }

        if (command.equals("boltsounds")) {
            soundManager.playAll(1000);
        }
    }

    private SoundFile randomSound() {
        while (true) {
            SoundFile[] sounds = SoundFile.playableSounds();
            int randomNum = new Random().nextInt(sounds.length);
            SoundFile randomSound = sounds[randomNum];
            if (!randomSound.equals(SoundFile.DEFAULT) && !randomSound.equals(SoundFile.SILENT)) {
                return randomSound;
            }
        }
    }

    @Subscribe
    public void onSoundEffectPlayed(SoundEffectPlayed event) {
        log.debug("Sound Played = " + event.getSoundId());
        int id = event.getSoundId();

        if (id == RunescapeSound.CROSSBOW.getSoundId()) {
            playSound(config.crossbowSound(), event);

        } else if (id == RunescapeSound.RUBY_PROC.getSoundId()) {
            playSound(config.rubyProcSound(), event);

        } else if (id == RunescapeSound.DIAMOND_PROC.getSoundId()) {
            playSound(config.diamondProcSound(), event);

        } else if (id == RunescapeSound.DRAGONSTONE_PROC.getSoundId()) {
            playSound(config.dragonstoneProcSound(), event);

        } else if (id == RunescapeSound.ONYX_PROC.getSoundId()) {
            playSound(config.onyxProcSound(), event);

        } else if (id == RunescapeSound.ACB_SPEC.getSoundId()) {
            playSound(config.acbSpecSound(), event);

        } else if (id == RunescapeSound.ZCB_SPEC.getSoundId()) {
            playSound(config.zcbSpecSound(), event);
        }
    }

    private void playSound(SoundFile soundFile, SoundEffectPlayed event) {
        if (soundFile.equals(SoundFile.DEFAULT)) {
            log.debug("Default sound, doing nothing");
            return;
        }
        event.consume();
        soundManager.play(soundFile);
    }

    @Provides
    BoltSoundConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(BoltSoundConfig.class);
    }
}
