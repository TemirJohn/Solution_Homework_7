package org.Temirjohn.iterator.iterators;

import org.Temirjohn.iterator.Episode;
import org.Temirjohn.iterator.EpisodeIterator;
import org.Temirjohn.iterator.Season;

import java.util.*;


public class ShuffleSeasonIterator implements EpisodeIterator, java.util.Iterator<Episode> {
    private final List<Episode> shuffledEpisodes;
    private int index;

    public ShuffleSeasonIterator(Season season, long seed) {
        this.shuffledEpisodes = new ArrayList<>(season.getEpisodes());
        Collections.shuffle(shuffledEpisodes, new Random(seed));
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < shuffledEpisodes.size();
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new IllegalStateException("No more episodes");
        return shuffledEpisodes.get(index++);
    }
}