package test;

import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.scene.Scene;

import java.awt.*;

public class TestScene extends Scene {

    @Override
    public void drawGame(final double windowWidth, final double windowHeight, final double delta) {
        Gui.Rectangles.fillTopToBottomGradientRelative(0, 0, windowWidth, windowHeight, Color.DARK_GRAY.getRGB(), Color.DARK_GRAY.darker().getRGB());

        final double size = 300;

        Gui.Triangles.fillGradient(windowWidth / 2 - size / 2, windowHeight / 2 - size / 2, size, Color.RED.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB());
    }

}
