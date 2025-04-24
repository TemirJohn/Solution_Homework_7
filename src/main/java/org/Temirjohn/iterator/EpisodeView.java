package org.Temirjohn.iterator;

public class EpisodeView {
    private final Episode episode;
    private final int startSec;
    public EpisodeView(Episode e, int startSec) {
        this.episode = e;
        this.startSec = startSec;
    }
    public void play() {
        System.out.println("Playing "
                + episode.getTitle()
                + " from " + startSec + "s");
    }
}
