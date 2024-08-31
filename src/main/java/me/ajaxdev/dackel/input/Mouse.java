package me.ajaxdev.dackel.input;

public class Mouse {

    /*
        Movement section
     */
    
    private double x, y, xDelta, yDelta;

    public void move(double x, double y) {
        this.xDelta = x - this.x;
        this.yDelta = y - this.y;
        this.x = this.xDelta + this.x;
        this.y = this.yDelta + this.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXDelta() {
        return xDelta;
    }

    public double getYDelta() {
        return yDelta;
    }

    /*
        Scrolling Section
     */
    
    private double lastScrollX, lastScrollY;
    private double currentScrollX, currentScrollY;
    
    public void scroll(double x, double y) {
        currentScrollX = x;
        currentScrollY = y;
        
        lastScrollX = currentScrollX;
        lastScrollY = currentScrollY;
    }
    
    public double getScrollY() {
        currentScrollY = 0;
        return lastScrollY;
    }

    public double getScrollX() {
        currentScrollX = 0;
        return lastScrollX;
    }

    public double getLastScrollY() {
        return lastScrollY;
    }

    public double getLastScrollX() {
        return lastScrollX;
    }
    
    
}
