package org.Temirjohn.iterator.iterators;


import org.Temirjohn.iterator.Episode;
import org.Temirjohn.iterator.EpisodeIterator;
import org.Temirjohn.iterator.Season;
import org.Temirjohn.iterator.Series;

import java.util.*;

public class BingeIterator implements EpisodeIterator {
    private final Series series;
    private int seasonIndex;
    private EpisodeIterator currentSeasonIterator;

    public BingeIterator(Series series) {
        this.series = series;
        this.seasonIndex = 0;
        this.currentSeasonIterator = series.getSeasons().isEmpty() ? null : series.getSeasons().get(0).getNormalIterator();
    }

    public boolean hasNext() {
        if (currentSeasonIterator == null) return false;
        if (currentSeasonIterator.hasNext()) return true;

        // Move to the next season
        seasonIndex++;
        while (seasonIndex < series.getSeasons().size()) {
            currentSeasonIterator = series.getSeasons().get(seasonIndex).getNormalIterator();
            if (currentSeasonIterator.hasNext()) return true;
            seasonIndex++;
        }
        return false;
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new IllegalStateException("No more episodes");
        return currentSeasonIterator.next();
    }
}