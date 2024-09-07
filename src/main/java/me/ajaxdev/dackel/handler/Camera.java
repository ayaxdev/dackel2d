package me.ajaxdev.dackel.handler;

import org.lwjgl.opengl.GL11;

/**
 * Allows you to translate the game's elements accordingly, creating the illusion of a moving camera.
 */
public class Camera {

    private int x = 0, y = 0;

    /**
     * Translates the current matrix.
     * Everything rendered after this and before end() will be affected by the camera movement.
     */
    public void begin() {
        GL11.glTranslated(-x, -y, 0);
    }

    /**
     * Translates the current matrix back after begin() modified it.
     * Everything rendered before this and after begin() will be affected by the camera movement.
     */
    public void end() {
        GL11.glTranslated(x, y, 0);
    }

    /**
     * Moves the camera to a provided location.
     *
     * @param x The provided location's X axis.
     * @param y The provided location's Y axis.
     */
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the camera by a provided delta.
     *
     * @param x The provided delta's X axis.
     * @param y The provided delta's Y axis.
     */
    public void moveBy(int x, int y) {
        moveTo(this.x + x, this.y + y);
    }

    /**
     * @return The camera's current X.
     */
    public int getY() {
        return y;
    }

    /**
     * @return The camera's current Y.
     */
    public int getX() {
        return x;
    }

}
