package test;

import me.ajaxdev.dackel.Application;
import me.ajaxdev.dackel.ApplicationArgs;

public class Main extends Application {

    public Main() {
        super(ApplicationArgs.builder()
                .setTitle("Test")
                .setHeight(500)
                .setWidth(600)
                .setResizeable(true)
                .setScene(new TestScene())
                .build());
    }

    public static void main(String[] args) {
        if (new Main().run()) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }

}
