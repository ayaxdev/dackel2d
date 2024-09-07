package test;

import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.scene.EmptyScene;

import java.awt.*;

public class TestScene extends EmptyScene {

    @Override
    public void drawScreen(final double windowWidth, final double windowHeight, final long delta) {
        Gui.Rectangles.fillTopToBottomGradientRelative(0, 0, windowWidth, windowHeight, Color.DARK_GRAY.getRGB(), Color.DARK_GRAY.darker().getRGB());

        final double size = 300;

        Gui.Triangles.fillGradient(windowWidth / 2 - size / 2, windowHeight / 2 - size / 2, size, Color.RED.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB());

        this.getLastApplication().display.setWindowX(50);
    }

}
