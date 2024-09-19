package me.ajaxdev.dackel.util;

public class MathHelper {

    public static float wrapDegrees(float degrees) {
        degrees %= 360;

        if (degrees < 0)
            degrees += 360;

        return degrees;
    }

    public static double wrapDegrees(double degrees) {
        degrees %= 360;

        if (degrees < 0)
            degrees += 360;

        return degrees;
    }

    public static int wrapDegrees(int degrees) {
        degrees %= 360;

        if (degrees < 0)
            degrees += 360;

        return degrees;
    }

}
