package me.ajaxdev.dackel.components;

public interface IComponent {

    default void drawBackground(double windowWidth, double windowHeight, double delta) { }

    default void drawOverlay(double windowWidth, double windowHeight, double delta) { }

    default void drawGame(double windowWidth, double windowHeight, double delta) { }

    default void mouseClicked(double x, double y, int button) { }

    default void mouseReleased(double x, double y, int button) { }

    default void mouseMoved(double deltaX, double deltaY, double newX, double newY) { }

    default void mouseScrolled(double deltaX, double deltaY) { }

    default void keyPressed(int keyCode, int scancode) { }

    default void keyRepeat(int keyCode, int scancode) { }

    default void keyReleased(int keyCode, int scancode) { }

    default void keyTyped(char character) { }
    
}
