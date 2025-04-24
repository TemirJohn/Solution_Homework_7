package org.Temirjohn.iterator;

import java.util.*;

public class WatchHistoryIterator implements EpisodeIterator {
    private final EpisodeIterator iterator;
    private final Set<String> watchedEpisodes; // Tracks episode titles
    private Episode nextUnseenEpisode;

    public WatchHistoryIterator(EpisodeIterator iterator, Set<String> watchedEpisodes) {
        this.iterator = iterator;
        this.watchedEpisodes = new HashSet<>(watchedEpisodes);
        advanceToNextUnseen();
    }

    private void advanceToNextUnseen() {
        nextUnseenEpisode = null;
        while (iterator.hasNext()) {
            Episode candidate = iterator.next();
            if (!watchedEpisodes.contains(candidate.getTitle())) {
                nextUnseenEpisode = candidate;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextUnseenEpisode != null;
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new java.util.NoSuchElementException("No more episodes");
        Episode result = nextUnseenEpisode;
        advanceToNextUnseen();
        return result;
    }
}