package me.ajaxdev.dackel;

import me.ajaxdev.dackel.input.Mouse;
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

    private final String title;
    private final boolean resizable;
    private final IScene defaultScene;

    private long window;
    private int windowWidth, windowHeight;

    public final Mouse mouse = new Mouse();
    public final TextureManager textureManager = new TextureManager();

    /**
     * The scene shown by default when the window is created.
     */
    private IScene currentScene;

    public Application(final ApplicationArgs applicationArgs) {
        this.defaultScene = this.currentScene = applicationArgs.defaultScene();
        this.title = applicationArgs.title();
        this.windowWidth = applicationArgs.width();
        this.windowHeight = applicationArgs.height();
        this.resizable = applicationArgs.resizeable();
    }

    @Deprecated
    public Application(final IScene defaultScene, final int width, final int height, final String title, final boolean resizable) {
        this.defaultScene = this.currentScene = defaultScene;

        this.windowWidth = width;
        this.windowHeight = height;
        this.resizable = resizable;

        this.title = title;
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
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);

        window = GLFW.glfwCreateWindow(windowWidth, windowHeight, title, 0, 0);

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(GLFW.GLFW_TRUE);

        GLFW.glfwShowWindow(window);

        GLFW.glfwSetWindowSizeCallback(window, (context, width, height) -> {
            Application.this.windowWidth = width;
            Application.this.windowHeight = height;

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glViewport(0, 0, windowWidth, windowHeight);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, windowWidth, windowHeight, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        });

        GLFW.glfwSetCursorPosCallback(window, (window, x, y) -> {
            this.mouse.move(x, y);

            if (currentScene != null) {
                currentScene.mouseMoved(mouse.getXDelta(), mouse.getYDelta(), x, y);
            }
        });

        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
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

        GLFW.glfwSetCharCallback(window, (window, chars) -> {
            if (currentScene != null) {
                currentScene.keyTyped((char) chars);
            }
        });

        GLFW.glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
           if (currentScene != null) {
               if (action == GLFW.GLFW_PRESS) {
                   currentScene.mouseClicked(mouse.getX(), mouse.getY(), button);
               } else if (action == GLFW.GLFW_RELEASE) {
                   currentScene.mouseReleased(mouse.getX(), mouse.getY(), button);
               }
           }
        });

        GLFW.glfwSetScrollCallback(window, (window, deltaX, deltaY) -> {
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

        long time = System.currentTimeMillis();

        while (!GLFW.glfwWindowShouldClose(window)) {
            loop(System.currentTimeMillis() - time);

            time = System.currentTimeMillis();
        }

        postLoop();

        return true;
    }

    private void prepareGl() {
        GL.createCapabilities();

        GL11.glMatrixMode(GL11.GL_PROJECTION);

        GL11.glViewport(0, 0, windowWidth, windowHeight);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, windowWidth, windowHeight, 0, 1, -1);

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
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GlManager.resetColor();
        GlManager.resetTexture();

        in();

        if (currentScene != null) {
            currentScene.drawScreen(windowWidth, windowHeight, delta);
        }

        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
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

        scene.show();

        textureManager.reload();

        scene.postShow();

        currentScene = scene;
    }

    /**
     * @return The window handle.
     */
    public long getWindow() {
        return window;
    }

    /**
     * @return The window title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The window width.
     */
    public int getWindowWidth() {
        return windowWidth;
    }

    /**
     * @return The window height.
     */
    public int getWindowHeight() {
        return windowHeight;
    }
}
