package me.ajaxdev.dackel.util;

public class Vec2d {

    public double x, y;

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d() {
        this(0, 0);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }
    
    public Vec2d copy() {
        return new Vec2d(x, y);
    }

    public Vec2d copyAndAdd(double x, double y) {
        final Vec2d copy = copy();
        copy.add(x, y);
        return copy;
    }

    public void sub(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public Vec2d copyAndSub(double x, double y) {
        final Vec2d copy = copy();
        copy.sub(x, y);
        return copy;
    }

    public void mul(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    public Vec2d copyAndMul(double x, double y) {
        final Vec2d copy = copy();
        copy.mul(x, y);
        return copy;
    }

    public void div(double x, double y) {
        this.x /= x;
        this.y /= y;
    }

    public Vec2d copyAndDiv(double x, double y) {
        final Vec2d copy = copy();
        copy.div(x, y);
        return copy;
    }

    public void pow(int pow) {
        this.x = Math.pow(this.x, pow);
        this.y = Math.pow(this.y, pow);
    }

    public Vec2d copyAndPow(int pow) {
        final Vec2d copy = copy();
        copy.pow(pow);
        return copy;
    }

    public void square() {
        pow(2);
    }

    public Vec2d copyAndSquare() {
        final Vec2d copy = copy();
        copy.square();
        return copy;
    }

    public void forward(double angle, double distance) {
        x += distance * Math.cos(Math.toRadians(angle));
        y += distance * Math.sin(Math.toRadians(angle));
    }

    public Vec2d copyAndForward(double angle, double distance) {
        final Vec2d copy = copy();
        copy.forward(angle, distance);
        return copy;
    }

    public double distance(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public double angleBetween(double x, double y) {
        double dot = this.x * x + this.y * y;
        double det = this.x * y - this.y * x;
        double radians = Math.atan2(det, dot);
        return Math.toDegrees(radians);
    }

}
