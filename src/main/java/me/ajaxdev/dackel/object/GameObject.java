package me.ajaxdev.dackel.object;

import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.texture.ITexture;
import me.ajaxdev.dackel.util.Vec2d;

public class GameObject {

    public ITexture texture;

    public Vec2d position;
    public double width, height;
    public int color;

    private boolean firstUse = true;

    /**
     * Creates a new game object.
     *
     * @param texture   The game object's texture.
     * @param x         The game object's position on the X axis.
     * @param y         The game object's position on the Y axis.
     * @param width     The game object's width.
     * @param height    The game object's height.
     * @param color     The game object's color. (Recolors the texture)
     */
    public GameObject(final ITexture texture, final double x, final double y, final double width, final double height, final int color) {
        this.texture = texture;

        this.position = new Vec2d(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Creates a new game object without any color changes.
     *
     * @param texture   The game object's texture.
     * @param x         The game object's position on the X axis.
     * @param y         The game object's position on the Y axis.
     * @param width     The game object's width.
     * @param height    The game object's height.
     */
    public GameObject(final ITexture texture, final double x, final double y, final double width, final double height) {
        this.texture = texture;

        this.position = new Vec2d(x, y);
        this.width = width;
        this.height = height;
        this.color = -1;
    }

    /**
     * Creates a new game object with the width and height being based on the texture's parameters.
     *
     * @param texture   The game object's texture.
     * @param x         The game object's position on the X axis.
     * @param y         The game object's position on the Y axis.
     * @param color     The game object's color. (Recolors the texture)
     */
    public GameObject(final ITexture texture, final double x, final double y, final int color) {
        this.texture = texture;

        this.position = new Vec2d(x, y);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.color = color;
    }

    /**
     * Creates a new game object with the width and height being based on the texture's parameters and without any color changes.
     *
     * @param texture   The game object's texture.
     * @param x         The game object's position on the X axis.
     * @param y         The game object's position on the Y axis.
     */
    public GameObject(final ITexture texture, final double x, final double y) {
        this.texture = texture;

        this.position = new Vec2d(x, y);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.color = -1;
    }

    /**
     * Is used internally by the engine to draw the object.
     */
    public void draw(double windowWidth, double windowHeight, double delta) {
        if (firstUse) {
            init();

            firstUse = false;
        }

        update(windowWidth, windowHeight, delta);

        Gui.Rectangles.textureRelative(position.x, position.y, width, height, texture, color);
    }

    /**
     * Called when the game object is first used.
     */
    protected void init() { }

    /**
     * Called every frame.
     */
    protected void update(double windowWidth, double windowHeight, double delta) { }

}
