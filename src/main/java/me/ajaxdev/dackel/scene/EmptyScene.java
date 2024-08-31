package me.ajaxdev.dackel.scene;

public class EmptyScene implements IScene {

    public void show() { }

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

}
