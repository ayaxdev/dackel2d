package me.ajaxdev.dackel.components;

import me.ajaxdev.dackel.object.GameObject;
import me.ajaxdev.dackel.util.Vec2d;

public interface IObjectComponent {

    /**
     * Called when the game object is first used.
     */
    default void init() { }

    /**
     * Called every frame.
     */
    default void update(double windowWidth, double windowHeight, double delta) { }

    GameObject getGameObject();

}
