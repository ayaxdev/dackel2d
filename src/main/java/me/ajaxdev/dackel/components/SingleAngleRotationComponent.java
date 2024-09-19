package me.ajaxdev.dackel.components;

import me.ajaxdev.dackel.object.GameObject;
import me.ajaxdev.dackel.util.MathHelper;

public class SingleAngleRotationComponent implements IObjectComponent {

    public final GameObject parent;

    public SingleAngleRotationComponent(GameObject parent) {
        this.parent = parent;
    }

    /**
     * The object's angle.
     */
    public double angle;

    /**
     * Moves the game object forward by a specified amount.
     *
     * @param distance The distance the object shall travel.
     */
    public void forward(final double distance) {
        parent.position.forward(this.angle, distance);
    }

    /**
     * Rotates the object by a specified angle.
     *
     * @param angle The angle by which the object shall rotate.
     */
    public void rotate(final double angle) {
        this.angle = MathHelper.wrapDegrees(this.angle + angle);
    }

    @Override
    public GameObject getGameObject() {
        return parent;
    }
}
