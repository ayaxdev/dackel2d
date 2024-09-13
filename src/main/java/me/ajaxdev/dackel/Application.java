package me.ajaxdev.dackel;

import me.ajaxdev.dackel.handler.Camera;
import me.ajaxdev.dackel.handler.Display;
import me.ajaxdev.dackel.handler.Mouse;
import me.ajaxdev.dackel.renderer.GlManager;
import me.ajaxdev.dackel.scene.IScene;
import me.ajaxdev.dackel.texture.TextureManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 * The main class of a Dackel program.
 * Extend this class to create a window and draw to it.
 */
public class Application {

    private final String defaultTitle;
    private final boolean resizable;
    private final IScene defaultScene;
    private final int defaultWindowWidth, defaultWindowHeight;
    private final int antialiasing;
    private final boolean vsyncByDefault;

    public final Display display = new Display(this);
    public final Mouse mouse = new Mouse();
    public final Camera camera = new Camera();
    public final TextureManager textureManager = new TextureManager();

    private int lastFramesPerSecond;
    private int framesPerSecond;
    private long lastUpdate;

    /**
     * The scene shown by default when the window is created.
     */
    private IScene currentScene;

    public Application(final ApplicationArgs applicationArgs) {
        this.defaultScene = this.currentScene = applicationArgs.defaultScene();
        this.defaultTitle = applicationArgs.title();
        this.defaultWindowWidth = applicationArgs.width();
        this.defaultWindowHeight = applicationArgs.height();
        this.resizable = applicationArgs.resizeable();
        this.antialiasing = applicationArgs.antialiasing();
        this.vsyncByDefault = applicationArgs.vsync();
    }

    @Deprecated
    public Application(final IScene defaultScene, final int width, final int height, final String defaultTitle, final boolean resizable) {
        this.defaultScene = this.currentScene = defaultScene;

        this.defaultWindowWidth = width;
        this.defaultWindowHeight = height;
        this.resizable = resizable;

        this.defaultTitle = defaultTitle;

        this.antialiasing = 0;
        this.vsyncByDefault = true;
    }

    /**
     * Creates the application's window and begins the application loop.
     *
     * @return Whether the application successfully finished.
     */
    public boolean run() {
        if(!GLFW.glfwInit()) {
            System.err.println("Unable to Init GLFW");
            return false;
        }

        GLFW.glfwDefaultWindowHints();

        if (antialiasing != -1)
            GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, antialiasing);
        else
            GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);

        display.prepare(defaultWindowWidth, defaultWindowHeight, defaultTitle);

        display.show(vsyncByDefault);

        GLFW.glfwSetCursorPosCallback(display.getHandle(), (window, x, y) -> {
            this.mouse.move(x, y);

            if (currentScene != null) {
                currentScene.mouseMoved(mouse.getXDelta(), mouse.getYDelta(), x, y);
            }
        });

        GLFW.glfwSetKeyCallback(display.getHandle(), (window, key, scancode, action, mods) -> {
            if (currentScene != null) {
                if (action == GLFW.GLFW_PRESS) {
                    currentScene.keyPressed(key, scancode);
                } else if (action == GLFW.GLFW_RELEASE) {
                    currentScene.keyReleased(key, scancode);
                } else if (action == GLFW.GLFW_REPEAT) {
                    currentScene.keyRepeat(key, scancode);
                }
            }
        });

        GLFW.glfwSetCharCallback(display.getHandle(), (window, chars) -> {
            if (currentScene != null) {
                currentScene.keyTyped((char) chars);
            }
        });

        GLFW.glfwSetMouseButtonCallback(display.getHandle(), (window, button, action, mods) -> {
           if (currentScene != null) {
               if (action == GLFW.GLFW_PRESS) {
                   currentScene.mouseClicked(mouse.getX(), mouse.getY(), button);
               } else if (action == GLFW.GLFW_RELEASE) {
                   currentScene.mouseReleased(mouse.getX(), mouse.getY(), button);
               }
           }
        });

        GLFW.glfwSetScrollCallback(display.getHandle(), (window, deltaX, deltaY) -> {
            mouse.scroll(deltaX, deltaY);

            if (currentScene != null) {
                currentScene.mouseScrolled(deltaX, deltaY);
            }
        });

        prepareGl();

        preLoop();

        if (currentScene != null) {
            openScene(currentScene);
        }

        long time = System.nanoTime();

        while (!display.isCloseRequested()) {
            final long currentTime = System.nanoTime();

            if ((currentTime - lastUpdate) / 1000000 >= 1000) {
                lastUpdate = currentTime;
                lastFramesPerSecond = framesPerSecond;
                framesPerSecond = 0;
            }

            final long delta = currentTime - time;

            loop(delta);

            framesPerSecond++;

            time = System.nanoTime();
        }

        display.destroy();

        postLoop();

        return true;
    }

    private void prepareGl() {
        GL.createCapabilities();

        GL11.glMatrixMode(GL11.GL_PROJECTION);

        GL11.glViewport(0, 0, defaultWindowWidth, defaultWindowHeight);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, defaultWindowWidth, defaultWindowHeight, 0, 1, -1);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);

        GlManager.enableBlend();
        GlManager.resetColor();
    }

    private void loop(long delta) {
        GlManager.clear();

        in();

        if (currentScene != null) {
            currentScene.drawBackground(display.getWindowWidth(), display.getWindowHeight(), delta);

            camera.begin();

            currentScene.drawGame(display.getWindowWidth(), display.getWindowHeight(), delta);

            camera.end();

            currentScene.drawOverlay(display.getWindowWidth(), display.getWindowHeight(), delta);
        }

        display.continueLoop();
    }

    /**
     * Ran before the application loop started.
     * Put initialization (loading textures, etc.) code here.
     */
    protected void preLoop() { }

    /**
     * Ran after the application loop finished and the application is closing.
     */
    protected void postLoop() { }

    /**
     * Ran inside the application loop before the current scene draws.
     */
    protected void in() { }

    /**
     * Sets the provided scene to the current scene.
     *
     * @param scene New scene.
     */
    public void openScene(final IScene scene) {
        if (scene == null) {
            openScene(defaultScene);

            return;
        }

        scene.show(this);

        textureManager.reload();

        scene.postShow();

        currentScene = scene;
    }

    public void updateWidth(final int windowWidth, final int windowHeight) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glViewport(0, 0, windowWidth, windowHeight);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, windowWidth, windowHeight, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    /**
     * @return The amount of frames drawn in the last second.
     */
    public int getFps() {
        return lastFramesPerSecond;
    }
}
