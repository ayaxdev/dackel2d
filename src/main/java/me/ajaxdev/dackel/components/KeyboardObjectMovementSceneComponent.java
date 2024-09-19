package me.ajaxdev.dackel.components;

import me.ajaxdev.dackel.object.GameObject;

public class KeyboardObjectMovementSceneComponent implements ISceneComponent {

    private final GameObject object;
    private final double speed;

    private final int topKey, bottomKey, leftKey, rightKey;

    private boolean top = false, bottom = false, left = false, right = false;

    public KeyboardObjectMovementSceneComponent(final GameObject object, final double speed, final int topKey, final int bottomKey, final int leftKey, final int rightKey) {
        this.object = object;
        this.speed = speed;

        this.topKey = topKey;
        this.bottomKey = bottomKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    @Override
    public void drawGame(double windowWidth, double windowHeight, double delta) {
        final double curSpeed = (speed * delta);

        if (left ^ right) {
            if (left) {
                object.position.sub(curSpeed, 0);
            } else {
                object.position.add(curSpeed, 0);
            }
        }

        if (top ^ bottom) {
            if (top) {
                object.position.sub(0, curSpeed);
            } else {
                object.position.add(0, curSpeed);
            }
        }
    }

    public void keyPressed(int keyCode, int scancode) {
        if (topKey == keyCode)
            top = true;

        if (bottomKey == keyCode)
            bottom = true;

        if (leftKey == keyCode)
            left = true;

        if (rightKey == keyCode)
            right = true;
    }

    public void keyReleased(int keyCode, int scancode) {
        if (topKey == keyCode)
            top = false;

        if (bottomKey == keyCode)
            bottom = false;

        if (leftKey == keyCode)
            left = false;

        if (rightKey == keyCode)
            right = false;
    }


}
