package test;

import me.ajaxdev.dackel.Application;

public class Main extends Application {

    public Main() {
        super(new TestScene(), 500, 500, "Test", false);
    }

    public static void main(String[] args) {
        if (new Main().run()) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }

}
