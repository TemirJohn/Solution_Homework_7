package org.Temirjohn.iterator.iterators;

import org.Temirjohn.iterator.Episode;
import org.Temirjohn.iterator.EpisodeIterator;
import org.Temirjohn.iterator.Season;

import java.util.List;

public class ReverseSeasonIterator implements EpisodeIterator, java.util.Iterator<Episode> {
    private final Season season;
    private int index;

    public ReverseSeasonIterator(Season season) {
        this.season = season;
        this.index = season.getEpisodes().size() - 1;
    }

    public boolean hasNext() {
        return index >= 0;
    }

    public Episode next() {
        if (!hasNext()) throw new IllegalStateException("No more episodes");
        return season.getEpisodes().get(index--);
    }
}
