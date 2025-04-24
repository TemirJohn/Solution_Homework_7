package org.Temirjohn.iterator;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        // Create a series with two seasons
        Series series = new Series();
        Season season1 = new Season();
        season1.addEpisode(new Episode("S1E1: Pilot", 1800));
        season1.addEpisode(new Episode("S1E2: Conflict", 1700));
        Season season2 = new Season();
        season2.addEpisode(new Episode("S2E1: Return", 1900));
        season2.addEpisode(new Episode("S2E2: Finale", 2000));
        series.addSeason(season1);
        series.addSeason(season2);

        // Demo normal iterator
        System.out.println("Normal order:");
        EpisodeIterator normalIter = season1.getNormalIterator();
        while (normalIter.hasNext()) {
            System.out.println(normalIter.next().getTitle());
        }

        // Demo reverse iterator
        System.out.println("\nReverse order:");
        EpisodeIterator reverseIter = season1.getReverseIterator();
        while (reverseIter.hasNext()) {
            System.out.println(reverseIter.next().getTitle());
        }

        // Demo shuffle iterator
        System.out.println("\nShuffled order (seed=42):");
        EpisodeIterator shuffleIter = season1.getShuffleIterator(42);
        while (shuffleIter.hasNext()) {
            System.out.println(shuffleIter.next().getTitle());
        }

        // Demo for-each loop (Iterable)
        System.out.println("\nFor-each loop (Season 1):");
        for (Episode e : season1) {
            System.out.println(e.getTitle());
        }

        // Demo binge iterator
        System.out.println("\nBinge mode (entire series):");
        EpisodeIterator bingeIter = series.getBingeIterator();
        while (bingeIter.hasNext()) {
            System.out.println(bingeIter.next().getTitle());
        }

        // Demo Skip Intro iterator
        System.out.println("\nSkip Intro (30 seconds):");
        SkipIntroIterator skipIntroIter = new SkipIntroIterator(season1.getNormalIterator(), 30);
        while (skipIntroIter.hasNext()) {
            EpisodeView view = skipIntroIter.next();
            view.play();
        }

        // Demo Watch-History filter
        System.out.println("\nUnseen episodes (watched S1E1):");
        Set<String> watched = new HashSet<>();
        watched.add("S1E1: Pilot");
        WatchHistoryIterator historyIter = new WatchHistoryIterator(season1.getNormalIterator(), watched);
        while (historyIter.hasNext()) {
            System.out.println(historyIter.next().getTitle());
        }
    }
}
