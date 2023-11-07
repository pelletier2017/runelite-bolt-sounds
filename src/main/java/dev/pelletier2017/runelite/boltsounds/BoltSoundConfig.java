package dev.pelletier2017.runelite.boltsounds;

import dev.pelletier2017.runelite.boltsounds.sound.SoundFile;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("boltsound")
public interface BoltSoundConfig extends Config {

	@ConfigItem(
			keyName = "crossbowSound",
			name = "Crossbow Sound",
			description = "The sound to play when crossbow attacks"
	)
	default SoundFile crossbowSound() {
		return SoundFile.DEFAULT;
	}

	@ConfigItem(
			keyName = "rubyProcSound",
			name = "Ruby Proc Sound",
			description = "The sound to play when ruby bolts (e) proc"
	)
	default SoundFile rubyProcSound() {
		return SoundFile.DEFAULT;
	}

	// TODO add volume level
	// TODO for dropdown menu iterate over SoundFile
}
