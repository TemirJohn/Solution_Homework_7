package org.Temirjohn.iterator;


import org.Temirjohn.iterator.iterators.ReverseSeasonIterator;
import org.Temirjohn.iterator.iterators.SeasonIterator;
import org.Temirjohn.iterator.iterators.ShuffleSeasonIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Season implements Iterable<Episode> {
    private final List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    public EpisodeIterator getNormalIterator() {
        return new SeasonIterator(this);
    }

    public EpisodeIterator getReverseIterator() {
        return new ReverseSeasonIterator(this);
    }

    public EpisodeIterator getShuffleIterator(long seed) {
        return new ShuffleSeasonIterator(this, seed);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public Iterator<Episode> iterator() {
        return new SeasonIterator(this); // Returns java.util.Iterator<Episode>
    }
}