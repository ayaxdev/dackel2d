package me.ajaxdev.dackel.util;

public class Timer {

    private long lastTime;

    public Timer() {
        reset();
    }

    public void reset() {
        lastTime = System.nanoTime();
    }

    public boolean hasTimeElapsedMs(final long time) {
        return System.nanoTime() - lastTime >= (time * 1000000);
    }

    public boolean hasTimeElapsedNs(final long time) {
        return System.nanoTime() - lastTime >= time;
    }

}
