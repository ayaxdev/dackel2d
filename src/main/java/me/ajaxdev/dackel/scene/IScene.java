package me.ajaxdev.dackel.scene;

/**
 * The primary interface for interacting with the framework.
 * Allows you to handle different input and draw events.
 */
public interface IScene {

    void show();

    void postShow();

    void drawScreen(double windowWidth, double windowHeight, long delta);

    void mouseClicked(double x, double y, int button);

    void mouseReleased(double x, double y, int button);

    void mouseMoved(double deltaX, double deltaY, double newX, double newY);

    void mouseScrolled(double deltaX, double deltaY);

    void keyPressed(int keyCode, int scancode);

    void keyRepeat(int keyCode, int scancode);

    void keyReleased(int keyCode, int scancode);

    void keyTyped(char character);
    
}
