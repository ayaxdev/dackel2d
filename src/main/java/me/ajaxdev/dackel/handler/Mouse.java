package me.ajaxdev.dackel.handler;

/**
 * Tracks the mouse pointer state.
 */
public class Mouse {

    /*
        Movement section
     */
    
    private double x, y, xDelta, yDelta;

    /**
     * Updates the mouse pointer position.
     * Is meant to be only called internally by Application.
     * Do not call unless you are fully sure know about what you're doing.
     *
     * @param x New X of the mouse pointer.
     * @param y New Y of the moue pointer;
     */
    public void move(double x, double y) {
        this.xDelta = x - this.x;
        this.yDelta = y - this.y;
        this.x = this.xDelta + this.x;
        this.y = this.yDelta + this.y;
    }

    /**
     * @return The current X of the mouse pointer.
     */
    public double getX() {
        return x;
    }

    /**
     * @return The current Y of the mouse pointer.
     */
    public double getY() {
        return y;
    }

    /**
     * @return The difference between the last X of the mouse pointer and the current X of the mouse pointer.
     */
    public double getXDelta() {
        return xDelta;
    }

    /**
     * @return The difference between the last Y of the mouse pointer and the current Y of the mouse pointer.
     */
    public double getYDelta() {
        return yDelta;
    }

    /*
        Scrolling Section
     */
    
    private double lastScrollX, lastScrollY;
    private double currentScrollX, currentScrollY;

    /**
     * Updates the mouse's scroll wheel position.
     * Is meant to be only called internally by Application.
     * Do not call unless you are fully sure know about what you're doing.
     *
     * @param x New X of the scroll wheel.
     * @param y New Y of the scroll wheel;
     */
    public void scroll(double x, double y) {
        currentScrollX = x;
        currentScrollY = y;
        
        lastScrollX = currentScrollX;
        lastScrollY = currentScrollY;
    }

    /**
     * @return The current Y of the mouse's scroll wheel.
     */
    public double getScrollY() {
        currentScrollY = 0;
        return lastScrollY;
    }

    /**
     * @return The current X of the mouse's scroll wheel.
     */
    public double getScrollX() {
        currentScrollX = 0;
        return lastScrollX;
    }

    /**
     * @return The difference between the last X of the mouse's scroll wheel and the current X of the mouse's scroll wheel.
     */
    public double getLastScrollY() {
        return lastScrollY;
    }

    /**
     * @return The difference between the last Y of the mouse's scroll wheel and the current X of the mouse's scroll wheel.
     */
    public double getLastScrollX() {
        return lastScrollX;
    }
    
    
}
