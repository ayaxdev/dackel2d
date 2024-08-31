package me.ajaxdev.dackel.renderer;

import org.lwjgl.opengl.GL11;

public class GlManager {

    private static boolean blend = false;

    private static float red = 0;
    private static float green = 0;
    private static float blue = 0;
    private static float alpha = 0;

    private static int boundTexture = 0;

    private static boolean texture2d = false;

    public static void color(float red, float green, float blue, float alpha) {
        if (GlManager.red != red || GlManager.green != green || GlManager.blue != blue || GlManager.alpha != alpha) {
            GlManager.red = red;
            GlManager.green = green;
            GlManager.blue = blue;
            GlManager.alpha = alpha;

            GL11.glColor4f(red, green, blue, alpha);
        }
    }

    public static void resetColor() {
        color(-1, -1, -1, -1);
    }

    public static void enableBlend() {
        if (!blend) {
            GL11.glEnable(GL11.GL_BLEND);

            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            blend = true;
        }
    }

    public static void disableBlend() {
        if (blend) {
            GL11.glDisable(GL11.GL_BLEND);

            blend = false;
        }
    }

    public static void bindTexture(int texture) {
        if (texture != boundTexture) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
            boundTexture = texture;
        }
    }

    public static void resetTexture() {
        bindTexture(0);
    }

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

    public static void disableTexture() {
        if (texture2d) {
            resetTexture();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            texture2d = false;
        }
    }

}
