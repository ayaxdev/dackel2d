package movementTest;

import me.ajaxdev.dackel.Application;
import me.ajaxdev.dackel.ApplicationArgs;
import me.ajaxdev.dackel.texture.SimpleTexture;

public class Main extends Application {

    public static final Main INSTANCE = new Main();

    public Main() {
        super(ApplicationArgs.builder()
                .setTitle("Test")
                .setHeight(500)
                .setWidth(600)
                .setResizeable(true)
                .setScene(new TestScene())
                .setVsync(false)
                .build());
    }

    public static void main(String[] args) {
        if (INSTANCE.run()) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }

    @Override
    public void preLoop() {
        textureManager.order("bug", new SimpleTexture("/demo/bug.png"));
    }

}
