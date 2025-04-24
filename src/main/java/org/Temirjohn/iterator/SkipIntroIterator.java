package org.Temirjohn.iterator;

import java.util.Iterator;

public class SkipIntroIterator implements Iterator<EpisodeView> {
    private final EpisodeIterator iterator;
    private final int introDurationSec;

    public SkipIntroIterator(EpisodeIterator iterator, int introDurationSec) {
        this.iterator = iterator;
        this.introDurationSec = introDurationSec;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public EpisodeView next() {
        if (!hasNext()) throw new java.util.NoSuchElementException("No more episodes");
        Episode episode = iterator.next();
        return new EpisodeView(episode, introDurationSec);
    }
}