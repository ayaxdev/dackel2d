package me.ajaxdev.dackel.input;

import me.ajaxdev.dackel.Application;
import org.lwjgl.glfw.GLFW;

public class Display {

    private final Application owner;

    private long handle = Integer.MIN_VALUE;

    private int windowWidth, windowHeight;
    private int windowX, windowY;

    public Display(Application owner) {
        this.owner = owner;
    }

    /**
     * Creates the window and prepares it for being shown.
     *
     * @param defaultWidth  The default width of the window.
     * @param defaultHeight The default height of the window.
     * @param defaultTitle  The default title of the window.
     */
    public void prepare(final int defaultWidth, final int defaultHeight, final String defaultTitle) {
        handle = GLFW.glfwCreateWindow(defaultWidth, defaultHeight, defaultTitle, 0, 0);

        GLFW.glfwSetWindowSizeCallback(handle, (context, width, height) -> {
            windowWidth = width;
            windowHeight = height;

            owner.updateWidth(windowWidth, windowHeight);
        });

        GLFW.glfwSetWindowPosCallback(handle, (context, x, y) -> {
            windowX = x;
            windowY = y;
        });
    }

    /**
     * Shows the window.
     */
    public void show() {
        check("show");

        GLFW.glfwMakeContextCurrent(handle);
        GLFW.glfwSwapInterval(GLFW.GLFW_TRUE);
        GLFW.glfwShowWindow(handle);
    }

    /**
     * Finishes a frame and allows to continue in the game loop.
     */
    public void continueLoop() {
        check("continue");

        GLFW.glfwSwapBuffers(handle);
        GLFW.glfwPollEvents();
    }

    /**
     * @return The window X position from the last window's movement.
     */
    public int getWindowX() {
        check("get X of");

        return windowX;
    }

    /**
     * @return The window X position gathered by calling a native GLFW method.
     */
    public int glfwGetWindowX() {
        check("get X of");

        int[] xBuffer = new int[1];
        int[] yBuffer = new int[1];
        GLFW.glfwGetWindowPos(handle, xBuffer, yBuffer);
        return xBuffer[0];
    }

    /**
     * Sets the X position of the current window.
     *
     * @param x The window's new X position.
     */
    public void setWindowX(final int x) {
        check("set X of");

        GLFW.glfwSetWindowPos(handle, x, glfwGetWindowY());
    }

    /**
     * @return The window Y position from the last window's movement.
     */
    public int getWindowY() {
        check("get Y of");

        return windowY;
    }

    /**
     * @return The window Y position gathered by calling a native GLFW method.
     */
    public int glfwGetWindowY() {
        check("get Y of");

        int[] xBuffer = new int[1];
        int[] yBuffer = new int[1];
        GLFW.glfwGetWindowPos(handle, xBuffer, yBuffer);
        return yBuffer[0];
    }

    /**
     * Sets the Y position of the current window.
     *
     * @param y The window's new X position.
     */
    public void setWindowY(final int y) {
        check("set Y of");

        GLFW.glfwSetWindowPos(handle, glfwGetWindowX(), y);
    }

    /**
     * @return The window position from the last window's movement.
     */
    public int[] getWindowPos() {
        check("get position of");

        return new int[] {windowX, windowY};
    }

    /**
     * @return The window position gathered by calling a native GLFW method.
     */
    public int[] glfwGetWindowPos() {
        check("get position of");

        int[] xBuffer = new int[1];
        int[] yBuffer = new int[1];
        GLFW.glfwGetWindowPos(handle, xBuffer, yBuffer);
        return new int[] {xBuffer[0], yBuffer[0]};
    }

    /**
     * Sets the position of the window.
     *
     * @param x The X axis.
     * @param y The Y axis.
     */
    public void setWindowPos(final int x, final int y) {
        check("set position of");

        GLFW.glfwSetWindowPos(handle, x, y);
    }

    /**
     * @return The window width from the last window's resize.
     */
    public int getWindowWidth() {
        check("get width of");

        return windowWidth;
    }

    /**
     * @return The window width gathered by calling a native GLFW method.
     */
    public int glfwGetWindowWidth() {
        check("get width of");

        int[] xBuffer = new int[1];
        int[] yBuffer = new int[1];
        GLFW.glfwGetWindowSize(handle, xBuffer, yBuffer);
        return xBuffer[0];
    }

    /**
     * Sets the width of the window.
     *
     * @param width The window's new width.
     */
    public void setWindowWidth(int width) {
        check("set width of");

        GLFW.glfwSetWindowSize(handle, width, glfwGetWindowHeight());
    }

    /**
     * @return The window height from the last window's resize.
     */
    public int getWindowHeight() {
        check("get height of");

        return windowHeight;
    }

    /**
     * @return The window height gathered by calling a native GLFW method.
     */
    public int glfwGetWindowHeight() {
        check("get height of");

        int[] xBuffer = new int[1];
        int[] yBuffer = new int[1];
        GLFW.glfwGetWindowSize(handle, xBuffer, yBuffer);
        return yBuffer[0];
    }

    /**
     * Sets the height of the window.
     *
     * @param height The window's new height.
     */
    public void setWindowHeight(int height) {
        check("set height of");

        GLFW.glfwSetWindowSize(handle, glfwGetWindowWidth(), height);
    }

    /**
     * @return The window's size from the last window's resize.
     */
    public int[] getWindowSize() {
        check("get size of");

        return new int[] {windowWidth, windowHeight};
    }

    /**
     * @return The window size gathered by calling a native GLFW method.
     */
    public int[] glfwGetWindowSize() {
        check("get size of");

        int[] xBuffer = new int[1];
        int[] yBuffer = new int[1];
        GLFW.glfwGetWindowSize(handle, xBuffer, yBuffer);
        return new int[] {xBuffer[0], yBuffer[0]};
    }

    /**
     * Sets the size of the window.
     *
     * @param width  Thw window's new width.
     * @param height The window's new height.
     */
    public void setWindowSize(final int width, final int height) {
        check("set size of");

        GLFW.glfwSetWindowSize(handle, width, height);
    }

    /**
     * @return The window title gathered by calling a native GLFW method.
     */
    public String getTitle() {
        check("get title of");

        return GLFW.glfwGetWindowTitle(handle);
    }

    /**
     * Sets the title of the window.
     *
     * @param title The window's net title.
     */
    public void setTitle(final String title) {
        check("set title of");

        GLFW.glfwSetWindowTitle(handle, title);
    }

    /**
     * @return Whether the window should be closed.
     */
    public boolean isCloseRequested() {
        check("determine state");

        return GLFW.glfwWindowShouldClose(handle);
    }

    /**
     * Destroys the window.
     */
    public void destroy() {
        check("destroy");

        GLFW.glfwDestroyWindow(handle);
    }

    /**
     * @return The window handle.
     */
    public long getHandle() {
        return handle;
    }

    /**
     * @return Whether the window has already been created.
     */
    public boolean isCreated() {
        return handle != Integer.MIN_VALUE;
    }

    private void check(final String action) {
        if (!isCreated())
            error(action);
    }

    private void error(final String action) {
        throw new IllegalStateException(String.format("Cannot %s an uncreated window", action));
    }
}
