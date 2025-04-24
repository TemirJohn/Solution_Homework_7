package org.Temirjohn.iterator;

import java.util.*;

public class Series {
    private final List<Season> seasons = new ArrayList<>();

    public void addSeason(Season s) {
        seasons.add(s);
    }

    public EpisodeIterator getBingeIterator() {
        return new BingeIterator(this);
    }

    public List<Season> getSeasons() {
        return seasons;
    }
}
