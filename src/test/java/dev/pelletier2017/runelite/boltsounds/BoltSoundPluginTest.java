package dev.pelletier2017.runelite.boltsounds;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BoltSoundPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(BoltSoundPlugin.class);
		RuneLite.main(args);
	}
}