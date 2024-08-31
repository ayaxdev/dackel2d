package me.ajaxdev.dackel.scene;

import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.texture.ITexture;

import java.util.function.Supplier;

/**
 * A scene which draws an image in the background.
 */
public class ImageScene extends EmptyScene {

    public final Supplier<ITexture> background;

    public ImageScene(ITexture background) {
        this.background = () -> background;
    }

    public ImageScene(Supplier<ITexture> background) {
        this.background = background;
    }

    public void drawScreen(double windowWidth, double windowHeight, long delta) {
        Gui.Rectangles.textureAbsolute(0, 0, windowWidth, windowHeight, background.get(), -1);

        super.drawScreen(windowWidth, windowHeight, delta);
    }

}
