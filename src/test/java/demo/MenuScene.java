package demo;

import me.ajaxdev.dackel.scene.ImageScene;

public class MenuScene extends ImageScene {

    public MenuScene() {
        super(() -> Main.getDemo().textureManager.get("menu"));
    }

    @Override
    public void mouseClicked(double x, double y, int button) {
        Main.getDemo().openScene(new GameScene());
    }

}
