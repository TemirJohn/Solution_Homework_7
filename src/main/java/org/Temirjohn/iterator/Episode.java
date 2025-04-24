package org.Temirjohn.iterator;

public class Episode {
    String title;
    int runtimeSec;

    public Episode(String title, int runtimeSec) {
        this.title = title;
        this.runtimeSec = runtimeSec;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntimeSec() {
        return runtimeSec;
    }

    public void setRuntimeSec(int runtimeSec) {
        this.runtimeSec = runtimeSec;
    }

    @Override
    public String toString() {
        return title + ": " + '\'' +
                ", runtimeSec=" + runtimeSec +
                '}';
    }
}
