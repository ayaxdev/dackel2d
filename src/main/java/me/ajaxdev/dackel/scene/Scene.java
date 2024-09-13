package me.ajaxdev.dackel.scene;

import me.ajaxdev.dackel.Application;
import me.ajaxdev.dackel.components.IComponent;
import me.ajaxdev.dackel.object.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * An empty scene which does nothing.
 */
public class Scene implements IScene {

    public final List<GameObject> objects = new ArrayList<>();

    public final List<IComponent> components = new ArrayList<>();

    private Application lastApplication;

    public void show(final Application application) { this.lastApplication = application; }

    public void postShow() { }

    public void drawBackground(double windowWidth, double windowHeight, double delta) {
        components.forEach(component -> component.drawBackground(windowWidth, windowHeight, delta));
    }

    public void drawOverlay(double windowWidth, double windowHeight, double delta) {
        components.forEach(component -> component.drawOverlay(windowWidth, windowHeight, delta));
    }

    public void drawGame(double windowWidth, double windowHeight, double delta) {
        components.forEach(component -> component.drawGame(windowWidth, windowHeight, delta));
        objects.forEach(object -> object.draw(windowWidth, windowHeight, delta));
    }

    public void mouseClicked(double x, double y, int button) {
        components.forEach(component -> component.mouseClicked(x, y, button));
    }

    public void mouseReleased(double x, double y, int button) {
        components.forEach(component -> component.mouseReleased(x, y, button));
    }

    public void mouseMoved(double deltaX, double deltaY, double newX, double newY) {
        components.forEach(component -> component.mouseMoved(deltaX, deltaY, newX, newY));
    }

    public void mouseScrolled(double deltaX, double deltaY) {
        components.forEach(component -> component.mouseScrolled(deltaX, deltaY));
    }

    public void keyPressed(int keyCode, int scancode) {
        components.forEach(component -> component.keyPressed(keyCode, scancode));
    }

    public void keyRepeat(int keyCode, int scancode) {
        components.forEach(component -> component.keyRepeat(keyCode, scancode));
    }

    public void keyReleased(int keyCode, int scancode) {
        components.forEach(component -> component.keyReleased(keyCode, scancode));
    }

    public void keyTyped(char character) {
        components.forEach(component -> component.keyTyped(character));
    }

    protected Application getLastApplication() {
        return lastApplication;
    }

}
