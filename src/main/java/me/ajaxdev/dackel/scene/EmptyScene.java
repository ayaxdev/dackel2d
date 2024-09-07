package me.ajaxdev.dackel.scene;

import me.ajaxdev.dackel.Application;

/**
 * An empty scene which does nothing.
 */
public class EmptyScene implements IScene {

    private Application lastApplication;

    public void show(final Application application) { this.lastApplication = application; }

    public void postShow() { }

    public void drawScreen(double windowWidth, double windowHeight, long delta) { }

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
