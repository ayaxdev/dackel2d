package me.ajaxdev.dackel.handler;

import me.ajaxdev.dackel.util.Vec2d;
import org.lwjgl.opengl.GL11;

/**
 * Allows you to translate the game's elements accordingly, creating the illusion of a moving camera.
 */
public class Camera {

    public Vec2d position = new Vec2d();

    /**
     * Translates the current matrix.
     * Everything rendered after this and before end() will be affected by the camera movement.
     */
    public void begin() {
        GL11.glTranslated(-position.x, -position.y, 0);
    }

    /**
     * Translates the current matrix back after begin() modified it.
     * Everything rendered before this and after begin() will be affected by the camera movement.
     */
    public void end() {
        GL11.glTranslated(position.x, position.y, 0);
    }

}
