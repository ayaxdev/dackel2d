package demo;

import me.ajaxdev.dackel.scene.ImageScene;

public class GameOverScene extends ImageScene {

    public GameOverScene() {
        super(Main.getDemo().textureManager.get("gameover"));
    }

    @Override
    public void mouseClicked(double x, double y, int button) {
        Main.getDemo().openScene(new MenuScene());
    }

}
