package dev.pelletier2017.runelite.boltsounds.sound;

import lombok.Getter;

@Getter
public enum RunescapeSound {

    ZCB_SPEC(5306),
    ACB_SPEC(3892),
    RUBY_PROC(2911),
    DIAMOND_PROC(2913),
    DRAGONSTONE_PROC(2915),
    ONYX_PROC(2917),
    CROSSBOW(2695),
    DART(2696),

    // dont include some low priority bolts
    //    SAPPHIRE_PROC(2912),
    ;

    private final int soundId;

    RunescapeSound(int soundId) {
        this.soundId = soundId;
    }
}
