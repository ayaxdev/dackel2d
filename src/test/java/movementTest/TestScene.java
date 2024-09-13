package movementTest;

import me.ajaxdev.dackel.components.KeyboardObjectMovementComponent;
import me.ajaxdev.dackel.object.GameObject;
import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.scene.Scene;
import me.ajaxdev.dackel.texture.ITexture;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class TestScene extends Scene {

    public void postShow() {
        if (objects.isEmpty() && components.isEmpty()) {
            final ITexture texture = Main.INSTANCE.textureManager.get("bug");

            final GameObject playerObject = new GameObject(texture, 0, 0, 60, 60, -1);

            objects.add(playerObject);

            components.add(new KeyboardObjectMovementComponent(playerObject, 0.1d, GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D));
        }
    }

    @Override
    public void drawBackground(final double windowWidth, final double windowHeight, final double delta) {
        Gui.Rectangles.fillTopToBottomGradientRelative(0, 0, windowWidth, windowHeight, Color.DARK_GRAY.getRGB(), Color.DARK_GRAY.darker().getRGB());
    }

    @Override
    public void drawGame(double windowWidth, double windowHeight, double delta) {
        super.drawGame(windowWidth, windowHeight, delta);

        getLastApplication().display.setTitle(String.format("Game: %d fps", getLastApplication().getFps()));
    }

}
