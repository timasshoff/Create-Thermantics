package com.skytendo.thermantics.season;

import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class SeasonManager {

    private static final int CYCLE_LENGTH = 100;

    private static final ArrayList<Season> seasons = new ArrayList<>();

    static {
//        seasons.add(new Season("Spring", 0x00FF00, 0xFFFF00));
//        seasons.add(new Season("Summer", 0xFF0000, 0xFFFF00));
//        seasons.add(new Season("Autumn", 0xFFA500, 0xFFFF00));
//        seasons.add(new Season("Winter", 0xFFFFFF, 0xFFFF00));
    }

    public static Season getCurrentSeason(Level level) {
        if (level.dimension() != Level.OVERWORLD) {
            return null;
        }

        int dayInCycle = (int) (level.getDayTime() / 24000L) % CYCLE_LENGTH;
        final int seasonLength = 100 / seasons.size();

        for (int i = 0; i < seasons.size(); i++) {
            if (dayInCycle < seasonLength * (i + 1)) {
                return seasons.get(i);
            }
        }
        return null;
    }

}
