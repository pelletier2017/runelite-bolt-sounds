package dev.pelletier2017.runelite.boltsounds.sound;

import lombok.Getter;

@Getter
public enum RunescapeSound {
    // TODO figure out real sound IDs
    ZCB_SPEC(5306), // unverified (CuteLittleBunny grabbed from visual sounds gave 2 sounds at once? [blood_sacrifice(2911) 5306]
    ACB_SPEC(3892), // unverified (pulled from reddit)
    RUBY_PROC(2911), // verified
    DIAMOND_PROC(2913), // verified
    DRAGONSTONE_PROC(2915), // verified

    ONYX_PROC(2917), // verified
    CROSSBOW(2695), // verified
    // TODO test with 2 tick dart sound to make sure it wont lag when overlapping sound, what about chance for double proc?

    // dont include some low priority bolts
    //    SAPPHIRE_PROC(2912), // verified
    ;

    private final int soundId;

    RunescapeSound(int soundId) {
        this.soundId = soundId;
    }
}
