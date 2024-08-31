package me.ajaxdev.dackel;

import me.ajaxdev.dackel.input.Mouse;
import me.ajaxdev.dackel.renderer.GlManager;
import me.ajaxdev.dackel.scene.IScene;
import me.ajaxdev.dackel.texture.TextureManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Application {

    private final String title;
    private final boolean resizable;
    private final IScene defaultScene;

    private long window;
    private int windowWidth, windowHeight;

    public final Mouse mouse = new Mouse();
    public final TextureManager textureManager = new TextureManager();

    private IScene currentScene;

    public Application(final IScene defaultScene, final int width, final int height, final String title, final boolean resizable) {
        this.defaultScene = this.currentScene = defaultScene;

        this.windowWidth = width;
        this.windowHeight = height;
        this.resizable = resizable;

        this.title = title;
    }

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

        begin();

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

    private void begin() {
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

    protected void preLoop() { }

    protected void postLoop() { }

    protected void in() { }

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

    public long getWindow() {
        return window;
    }

    public String getTitle() {
        return title;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
