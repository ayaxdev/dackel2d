package me.ajaxdev.dackel.renderer;

import org.lwjgl.opengl.GL11;

/**
 * Class for tracking and updating GL state.
 * Use this instead of direct GL__.** calls.
 * Only contains a limited amount of methods and does not cover all of GL's functions, for those cases, it is safe to directly call for example GL11.
 */
public class GlManager {

    private static boolean blend = false;

    private static float red = 0;
    private static float green = 0;
    private static float blue = 0;
    private static float alpha = 0;

    private static int boundTexture = 0;

    private static boolean texture2d = false;

    /**
     * Checks whether the color state is different from the provided color and updates it if so.
     *
     * @param red   The red value of the new color state.
     * @param green The green value of the new color state.
     * @param blue  The blue value of the new color state.
     * @param alpha The alpha value of the new color state.
     */
    public static void color(float red, float green, float blue, float alpha) {
        if (GlManager.red != red || GlManager.green != green || GlManager.blue != blue || GlManager.alpha != alpha) {
            GlManager.red = red;
            GlManager.green = green;
            GlManager.blue = blue;
            GlManager.alpha = alpha;

            GL11.glColor4f(red, green, blue, alpha);
        }
    }

    /**
     * Resets the current color state.
     */
    public static void resetColor() {
        color(-1, -1, -1, -1);
    }

    /**
     * Checks whether gl blending is enabled, and starts blending if not.
     */
    public static void enableBlend() {
        if (!blend) {
            GL11.glEnable(GL11.GL_BLEND);

            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            blend = true;
        }
    }

    /**
     * Checks whether gl blending is enabled, and stops blending if so.
     */
    public static void disableBlend() {
        if (blend) {
            GL11.glDisable(GL11.GL_BLEND);

            blend = false;
        }
    }

    /**
     * Checks whether current bound texture is identical to the provided one, and changes the bound texture if so.
     *
     * @param texture New bound texture.
     */
    public static void bindTexture(int texture) {
        if (texture != boundTexture) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
            boundTexture = texture;
        }
    }

    /**
     * Checks whether there is currently a bound texture and resets it if so.
     */
    public static void resetTexture() {
        if (boundTexture != 0)
            bindTexture(0);
    }

    /**
     * Checks whether binding 2D textures is enabled and enables it if not.
     *
     * @param texture New bound textured.
     */
    public static void enableTexture(int texture) {
        if (texture2d && texture != boundTexture) {
            disableTexture();
        }

        if (!texture2d) {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            bindTexture(texture);
            texture2d = true;
        }
    }

    /**
     * Checks whether binding 2D textures is enabled and disabled it if so.
     */
    public static void disableTexture() {
        if (texture2d) {
            resetTexture();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            texture2d = false;
        }
    }

}
