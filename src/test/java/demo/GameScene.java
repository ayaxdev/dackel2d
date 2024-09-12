package demo;

import me.ajaxdev.dackel.Application;
import me.ajaxdev.dackel.renderer.Gui;
import me.ajaxdev.dackel.scene.ImageScene;
import me.ajaxdev.dackel.texture.ITexture;
import me.ajaxdev.dackel.texture.SemiAnimatedTexture;
import me.ajaxdev.dackel.util.Timer;

import java.awt.*;

public class GameScene extends ImageScene {

    private boolean walking = false;
    private double playerX, playerY;

    private ITexture frog;

    private long showTime;

    private Event event;

    private boolean carryingBug = false;

    private double rescuedX, rescuedY;
    private double finishX, finishY;

    private final Timer directionResetTimer = new Timer();
    private boolean moveDragon = false;
    private float dragonRot = 90;
    private boolean invertY = false;
    private double dragonX = 50, dragonY = 50;

    private final double[][] bugFinish = new double[5][2];

    private final float elementSize = 50;

    public GameScene() {
        super(() -> Main.getDemo().textureManager.get("back"));
    }

    @Override
    public void show(final Application application) {
        super.show(application);

        final double width = Main.getDemo().display.getWindowWidth();
        final double height = Main.getDemo().display.getWindowHeight();

        bugFinish[0][0] = width / 4 / 2;
        bugFinish[0][1] = height / 3 / 2;

        bugFinish[1][0] = width / 2 / 2;
        bugFinish[1][1] = height / 3;

        bugFinish[2][0] = width / 3 * 2;
        bugFinish[2][1] = height / 5;

        bugFinish[3][0] = width - width / 5;
        bugFinish[3][1] = height / 3;

        bugFinish[4][0] = width / 3 * 2;
        bugFinish[4][1] = height - height / 3;

        dragonX = 50;
        dragonY = 50;

        playerX = width - 50;
        playerY = height - height / 3;

        dragonRot = 90;

        showTime = System.currentTimeMillis();

        walking = false;
    }

    @Override
    public void postShow() {
        frog = new SemiAnimatedTexture(() -> walking, new ITexture[]{
                Main.getDemo().textureManager.get("walking1"),
                Main.getDemo().textureManager.get("walking2"),
        }, Main.getDemo().textureManager.get("frog"), 100);
    }

    @Override
    public void drawGame(double windowWidth, double windowHeight, long delta) {
        super.drawGame(windowWidth, windowHeight, delta);

        getLastApplication().display.setTitle(String.format("Game: %d fps", getLastApplication().getFps()));

        if (System.currentTimeMillis() - showTime > 2000)
            moveDragon = true;

        final double goalX = Main.getDemo().mouse.getX();
        final double goalY = Main.getDemo().mouse.getY();

        walking = playerX != goalX || playerY != goalY;

        if (walking) {
            final double diffX = goalX - playerX;
            final double diffY = goalY - playerY;

            final double walkDistanceX = diffX / 5;
            final double walkDistanceY = diffY / 5;

            final double adjustedWalkDistanceX = Math.abs(walkDistanceX) < 0.5f ? diffX : walkDistanceX;
            final double adjustedWalkDistanceY = Math.abs(walkDistanceY) < 0.5f ? diffY : walkDistanceY;

            playerX += adjustedWalkDistanceX;
            playerY += adjustedWalkDistanceY;
        }

        Gui.Rectangles.textureRelative(playerX, playerY, elementSize, elementSize, frog, -1);

        if (moveDragon) {
            boolean changeDirection = directionResetTimer.hasTimeElapsedMs(500);

            double a = playerX - dragonX;
            double b = playerY - dragonY;
            double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

            if (dragonX <= 10 || dragonY <= 10 || dragonX >= windowWidth - 10 || dragonY >= windowHeight - 10) {
                changeDirection = true;
            }

            if (c > 35) {
                changeDirection = true;
            }

            if (changeDirection) {
                double sin = a / c;

                double rot = Math.asin(sin);

                invertY = b > 0;

                dragonRot = (float) rot;

                directionResetTimer.reset();
            }

            dragonX += Math.sin(dragonRot) * 3;

            if (invertY)
                dragonY += Math.cos(dragonRot) * 3;
            else
                dragonY -= Math.cos(dragonRot) * 3;
        }

        Gui.Rectangles.textureRelative(dragonX - elementSize / 2, dragonY - elementSize / 2, elementSize, elementSize, Main.getDemo().textureManager.get("dragon"), -1);

        if (event == null) {
            event = Math.random() > 0.5 ? Event.FISH : Event.BUG;

            if (event == Event.BUG) {
                final int finish = (int) Math.round(Math.random() * 4);
                finishX = bugFinish[finish][0];
                finishY = bugFinish[finish][1];
            } else {
                finishX = Math.random() * windowWidth;
                finishY = windowHeight - windowHeight / 5;
            }

            rescuedX = Math.random() * windowWidth;
            rescuedY = Math.random() * (windowHeight - windowHeight / 3);
        }

        if (carryingBug) {
            Gui.Rectangles.textureRelative(finishX - elementSize / 2, finishY - elementSize / 2, elementSize, elementSize, Main.getDemo().textureManager.get("finish"), new Color(255, 255, 255, 100).getRGB());

            rescuedX = playerX + elementSize;
            rescuedY = playerY + elementSize;
        }

        Gui.Rectangles.textureRelative(rescuedX - elementSize / 2, rescuedY - elementSize / 2, elementSize, elementSize, event == Event.BUG ? Main.getDemo().textureManager.get("bug") : Main.getDemo().textureManager.get("fish"), -1);

        if (Math.abs((playerX) - dragonX) < elementSize / 2 && Math.abs((playerY) - dragonY) < elementSize / 2) {
            Main.getDemo().openScene(new GameOverScene());
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (carryingBug) {
            final double x = finishX - elementSize / 2;
            final double y = finishY - elementSize / 2;
            final double x2 = x + elementSize;
            final double y2 = y + elementSize;

            if (mouseX > x && mouseX < x2 && mouseY > y && mouseY < y2) {
                event = null;
                carryingBug = false;
            }
        } else {
            final double x = rescuedX - elementSize / 2;
            final double y = rescuedY - elementSize / 2;
            final double x2 = x + elementSize;
            final double y2 = y + elementSize;

            if (mouseX > x && mouseX < x2 && mouseY > y && mouseY < y2) {
                carryingBug = true;
            }
        }
    }

    public enum Event {
        FISH, BUG
    }
}
