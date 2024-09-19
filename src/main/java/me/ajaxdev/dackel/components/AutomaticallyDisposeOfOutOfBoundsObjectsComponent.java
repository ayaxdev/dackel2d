package me.ajaxdev.dackel.components;

import me.ajaxdev.dackel.scene.Scene;

public class AutomaticallyDisposeOfOutOfBoundsObjectsComponent implements ISceneComponent {

    public final Scene parent;

    public AutomaticallyDisposeOfOutOfBoundsObjectsComponent(Scene parent) {
        this.parent = parent;
    }

    public void drawBackground(double windowWidth, double windowHeight, double delta) {
        this.parent.objects.removeIf(gameObject ->
                (gameObject.position.x < -gameObject.width || gameObject.position.x > windowWidth) ||
                (gameObject.position.y < -gameObject.height || gameObject.position.y > windowHeight));
    }

}
