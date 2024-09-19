package mathTest;

import me.ajaxdev.dackel.util.Vec2d;

public class AngleBetweenTest {

    public static void main(String[] args) {
        final Vec2d vec2d = new Vec2d(10, 10);

        System.out.printf("%f%n", vec2d.angleBetween(0, 0) * 2);
    }

}
