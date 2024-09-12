package demo;

import me.ajaxdev.dackel.Application;
import me.ajaxdev.dackel.ApplicationArgs;
import me.ajaxdev.dackel.texture.SimpleTexture;

public class DemoApplication extends Application {

    public DemoApplication() {
        super(ApplicationArgs.builder()
                .setTitle("Demo Game")
                .setHeight(500)
                .setWidth(500)
                .setResizeable(false)
                .setScene(new MenuScene())
                .setAntialiasing(0)
                .setVsync(true)
                .build());
    }

    @Override
    public void preLoop() {
        textureManager.order("gameover", new SimpleTexture("/demo/gameover.png"));
        textureManager.order("back", new SimpleTexture("/demo/back.png"));
        textureManager.order("bug", new SimpleTexture("/demo/bug.png"));
        textureManager.order("finish", new SimpleTexture("/demo/cil.png"));
        textureManager.order("fish", new SimpleTexture("/demo/fish.png"));
        textureManager.order("dragon", new SimpleTexture("/demo/dragon.png"));
        textureManager.order("walking1", new SimpleTexture("/demo/walk1.png"));
        textureManager.order("walking2", new SimpleTexture("/demo/walk2.png"));
        textureManager.order("frog", new SimpleTexture("/demo/frog.png"));
        textureManager.order("menu", new SimpleTexture("/demo/menu.png"));
    }

}
