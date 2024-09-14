package cameraTest;

import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.scene.Scene;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class TestScene extends Scene {

    private static final int MARK_COLOR = new Color(255, 0, 0, 100).getRGB();
    private static final int INDICATOR_COLOR = new Color(255, 255, 255, 100).getRGB();

    private int state = 0;

    @Override
    public void drawBackground(final double windowWidth, final double windowHeight, final double delta) {
        Gui.Rectangles.fillTopToBottomGradientRelative(0, 0, windowWidth, windowHeight, Color.DARK_GRAY.getRGB(), Color.DARK_GRAY.darker().getRGB());

        Gui.Rectangles.fillRelative(50, 0, 1, windowHeight, MARK_COLOR);

        Gui.Rectangles.fillRelative(100, 0, 1, windowHeight, MARK_COLOR);

        Gui.Rectangles.fillRelative(500, 0, 1, windowHeight, MARK_COLOR);
    }

    @Override
    public void drawGame(final double windowWidth, final double windowHeight, final double delta) {
        Gui.Rectangles.fillRelative(500, 0, 50, 50, INDICATOR_COLOR);
    }

    public void keyPressed(int keyCode, int scancode) {
        if (keyCode == GLFW.GLFW_KEY_V) {
            state++;

            if (state > 3)
                state = 0;
        }

        switch (state) {
            case 0 -> this.getLastApplication().camera.position.set(0, 0);
            case 1 -> this.getLastApplication().camera.position.set(400, 0);
            case 2 -> this.getLastApplication().camera.position.set(450, 0);
            case 3 -> this.getLastApplication().camera.position.set(500, 0);
        }
    }


}
