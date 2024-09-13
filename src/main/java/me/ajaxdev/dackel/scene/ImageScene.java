package me.ajaxdev.dackel.scene;

import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.texture.ITexture;

import java.util.function.Supplier;

/**
 * A scene which draws an image in the background.
 */
public class ImageScene extends Scene {

    public final Supplier<ITexture> background;

    public ImageScene(final String textureName) {
        this.background = () -> getLastApplication().textureManager.get(textureName);
    }

    public ImageScene(ITexture background) {
        this.background = () -> background;
    }

    public ImageScene(Supplier<ITexture> background) {
        this.background = background;
    }

    public void drawBackground(double windowWidth, double windowHeight, double delta) {
        Gui.Rectangles.textureAbsolute(0, 0, windowWidth, windowHeight, background.get(), -1);

        super.drawBackground(windowWidth, windowHeight, delta);
    }

}
