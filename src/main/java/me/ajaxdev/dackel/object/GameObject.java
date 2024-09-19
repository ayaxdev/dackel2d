package me.ajaxdev.dackel.object;

import me.ajaxdev.dackel.components.IObjectComponent;
import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.texture.ITexture;
import me.ajaxdev.dackel.util.MathHelper;
import me.ajaxdev.dackel.util.Vec2d;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    /**
     * The texture of the object.
     * Is used to draw the object.
     */
    public ITexture texture;

    /**
     * The position of the object.
     */
    public final Vec2d position;

    /**
     * The size of the object.
     */
    public double width, height;

    /**
     * List of the object components.
     * These get ran along the object.
     */
    public final List<IObjectComponent> components = new ArrayList<>();

    /**
     * The color of the object.
     */
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
    protected void init() {
        components.forEach(IObjectComponent::init);
    }

    /**
     * Called every frame.
     */
    protected void update(double windowWidth, double windowHeight, double delta) {
        components.forEach(component -> component.update(windowWidth, windowHeight, delta));
    }

    /**
     * @param other     The other game object.
     * @return          Whether this object intercepts with the other object.
     */
    public boolean intercepts(final GameObject other) {
        return intercepts(other.position.x, other.position.y, other.width, other.height);
    }

    /**
     * @param x         X position.
     * @param y         Y position.
     * @param width     The width.
     * @param height    The height.
     * @return          Whether this object intercepts with the provided bounds.
     */
    public boolean intercepts(final double x, final double y, final double width, final double height) {
        // Check for conditions where rectangles DON'T intersect
        // If any of these are true, then the rectangles don't intersect
        if (this.position.x + this.width <= x // this is to the left of other
                || this.position.x >= x + width // this is to the right of other
                || this.position.y + this.height <= y // this is above other
                || this.position.y >= y + height) { // this is below other
            return false;
        }

        // If none of the above conditions are met, the rectangles must intersect
        return true;
    }

}
