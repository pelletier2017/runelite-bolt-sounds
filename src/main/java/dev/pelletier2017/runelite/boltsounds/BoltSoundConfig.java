package dev.pelletier2017.runelite.boltsounds;

import dev.pelletier2017.runelite.boltsounds.sound.SoundFile;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("boltsound")
public interface BoltSoundConfig extends Config {

	@Range(
			min = 1,
			max = 100
	)
	@ConfigItem(
			keyName = "volumeLevel",
			name = "Volume",
			description = "Adjust volume",
			position = 1
	)
	default int volumeLevel() {
		return 50;
	}

	@ConfigItem(
			keyName = "crossbowSound",
			name = "Crossbow",
			description = "The sound to play when crossbow attacks",
			position = 2
	)
	default SoundFile crossbowSound() {
		return SoundFile.DEFAULT;
	}

	@ConfigItem(
			keyName = "rubyProcSound",
			name = "Ruby Proc",
			description = "The sound to play when ruby bolts (e) proc",
			position = 3
	)
	default SoundFile rubyProcSound() {
		return SoundFile.DEFAULT;
	}

	@ConfigItem(
			keyName = "diamondProcSound",
			name = "Diamond Proc",
			description = "The sound to play when diamond bolts (e) proc",
			position = 4
	)
	default SoundFile diamondProcSound() {
		return SoundFile.DEFAULT;
	}

	@ConfigItem(
			keyName = "dragonstoneProcSound",
			name = "Dstone Proc",
			description = "The sound to play when dragonstone bolts (e) proc",
			position = 5
	)
	default SoundFile dragonstoneProcSound() {
		return SoundFile.DEFAULT;
	}

	@ConfigItem(
			keyName = "onyxProcSound",
			name = "Onyx Proc",
			description = "The sound to play when onyx bolts (e) proc",
			position = 6
	)
	default SoundFile onyxProcSound() {
		return SoundFile.DEFAULT;
	}

	@ConfigItem(
			keyName = "acbSpecSound",
			name = "ACB Spec",
			description = "The sound to play when Armadyl Crossbow specs",
			position = 7
	)
	default SoundFile acbSpecSound() {
		return SoundFile.DEFAULT;
	}

	@ConfigItem(
			keyName = "zcbSpecSound",
			name = "ZCB Spec",
			description = "The sound to play when Zaryte Crossbow specs",
			position = 8
	)
	default SoundFile zcbSpecSound() {
		return SoundFile.DEFAULT;
	}
}
