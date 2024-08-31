package me.ajaxdev.dackel.util;

/**
 * A utility designed to track time elapsed from its creation.
 */
public class Timer {

    private long lastTime;

    public Timer() {
        reset();
    }

    /**
     * Resets the elapsed time to 0.
     */
    public void reset() {
        lastTime = System.nanoTime();
    }

    /**
     * Compares the provided time to the elapsed time in milliseconds.
     *
     * @param time  The time to be tested.
     * @return      Has the provided time already elapsed.
     */
    public boolean hasTimeElapsedMs(final long time) {
        return System.nanoTime() - lastTime >= (time * 1000000);
    }

    /**
     * Compares the provided time to the elapsed time in nanoseconds.
     *
     * @param time  The time to be tested.
     * @return      Has the provided time already elapsed.
     */
    public boolean hasTimeElapsedNs(final long time) {
        return System.nanoTime() - lastTime >= time;
    }

}
