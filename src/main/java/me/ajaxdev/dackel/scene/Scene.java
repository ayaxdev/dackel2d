package me.ajaxdev.dackel.scene;

import me.ajaxdev.dackel.Application;
import me.ajaxdev.dackel.object.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * An empty scene which does nothing.
 */
public class Scene implements IScene {

    public final List<GameObject> objects = new ArrayList<>();

    private Application lastApplication;

    public void show(final Application application) { this.lastApplication = application; }

    public void postShow() { }

    public void drawBackground(double windowWidth, double windowHeight, long delta) { }

    public void drawOverlay(double windowWidth, double windowHeight, long delta) { }

    public void drawGame(double windowWidth, double windowHeight, long delta) {
        objects.forEach(GameObject::draw);
    }

    public void mouseClicked(double x, double y, int button) { }

    public void mouseReleased(double x, double y, int button) { }

    public void mouseMoved(double deltaX, double deltaY, double newX, double newY) { }

    public void mouseScrolled(double deltaX, double deltaY) { }

    public void keyPressed(int keyCode, int scancode) { }

    public void keyRepeat(int keyCode, int scancode) { }

    public void keyReleased(int keyCode, int scancode) { }

    public void keyTyped(char character) { }

    protected Application getLastApplication() {
        return lastApplication;
    }

}
