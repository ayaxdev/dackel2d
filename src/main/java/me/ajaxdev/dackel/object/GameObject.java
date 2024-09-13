package me.ajaxdev.dackel.object;

import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.texture.ITexture;

public class GameObject {

    public ITexture texture;

    public double x, y;
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

        this.x = x;
        this.y = y;
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

        this.x = x;
        this.y = y;
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

        this.x = x;
        this.y = y;
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

        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.color = -1;
    }

    /**
     * Is used internally by the engine to draw the object.
     */
    public void draw() {
        if (firstUse) {
            init();

            firstUse = false;
        }

        update();

        Gui.Rectangles.textureRelative(x, y, width, height, texture, color);
    }

    /**
     * Called when the game object is first used.
     */
    protected void init() { }

    /**
     * Called every frame.
     */
    protected void update() { }

    /**
     * Moves the object to a provided location.
     *
     * @param x The provided location's X axis.
     * @param y The provided location's Y axis.
     */
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the object by a provided delta.
     *
     * @param x The provided delta's X axis.
     * @param y The provided delta's Y axis.
     */
    public void moveBy(double x, double y) {
        moveTo(this.x + x, this.y + y);
    }

}
