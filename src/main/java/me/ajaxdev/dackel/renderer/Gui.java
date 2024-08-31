package me.ajaxdev.dackel.renderer;

import me.ajaxdev.dackel.texture.ITexture;

public class Gui {

    public static class Rectangles {
        public static void fillRelative(double x, double y, double width, double height, int color) {
            final PrimitiveRenderer primitiveRenderer = new PrimitiveRenderer(false, false);

            primitiveRenderer.begin(PrimitiveRendererMode.SQUARE);

            primitiveRenderer.color(color);

            primitiveRenderer.position(x, y).next();
            primitiveRenderer.position(x + width, y).next();
            primitiveRenderer.position(x + width, y + height).next();
            primitiveRenderer.position(x, y + height).next();

            primitiveRenderer.draw();
        }

        public static void fillAbsolute(double x, double y, double x2, double y2, int color) {
            if (x > x2)
                x = x2;

            if (y > y2)
                y = y2;

            final PrimitiveRenderer primitiveRenderer = new PrimitiveRenderer(false, false);

            primitiveRenderer.begin(PrimitiveRendererMode.SQUARE);

            primitiveRenderer.color(color);

            primitiveRenderer.position(x, y).next();
            primitiveRenderer.position(x2, y).next();
            primitiveRenderer.position(x2, y2).next();
            primitiveRenderer.position(x, y2).next();

            primitiveRenderer.draw();
        }

        public static void fillGradientRelative(double x, double y, double width, double height, int color1, int color2, int color3, int color4) {
            final PrimitiveRenderer primitiveRenderer = new PrimitiveRenderer(true, false);

            primitiveRenderer.begin(PrimitiveRendererMode.SQUARE);

            primitiveRenderer.position(x, y).color(color1).next();
            primitiveRenderer.position(x + width, y).color(color2).next();
            primitiveRenderer.position(x + width, y + height).color(color3).next();
            primitiveRenderer.position(x, y + height).color(color4).next();

            primitiveRenderer.draw();
        }

        public static void fillGradientAbsolute(double x, double y, double x2, double y2, int color1, int color2, int color3, int color4) {
            if (x > x2)
                x = x2;

            if (y > y2)
                y = y2;

            final PrimitiveRenderer primitiveRenderer = new PrimitiveRenderer(false, false);

            primitiveRenderer.begin(PrimitiveRendererMode.SQUARE);

            primitiveRenderer.position(x, y).color(color1).next();
            primitiveRenderer.position(x2, y).color(color2).next();
            primitiveRenderer.position(x2, y2).color(color3).next();
            primitiveRenderer.position(x, y2).color(color4).next();

            primitiveRenderer.draw();
        }

        public static void fillLeftToRightGradientAbsolute(double x, double y, double x2, double y2, int color1, int color2) {
            fillGradientAbsolute(x, y, x2, y2, color1, color2, color2, color1);
        }

        public static void fillLeftToRightGradientRelative(double x, double y, double x2, double y2, int color1, int color2) {
            fillGradientRelative(x, y, x2, y2, color1, color2, color2, color1);
        }

        public static void fillTopToBottomGradientAbsolute(double x, double y, double x2, double y2, int color1, int color2) {
            fillGradientAbsolute(x, y, x2, y2, color1, color1, color2, color2);
        }

        public static void fillTopToBottomGradientRelative(double x, double y, double x2, double y2, int color1, int color2) {
            fillGradientRelative(x, y, x2, y2, color1, color1, color2, color2);
        }

        public static void textureAbsolute(double x, double y, double x2, double y2, ITexture texture, int color1) {
            if (x > x2)
                x = x2;

            if (y > y2)
                y = y2;

            final PrimitiveRenderer backgroundRenderer = new PrimitiveRenderer(false, true);

            backgroundRenderer.begin(PrimitiveRendererMode.SQUARE);

            backgroundRenderer.texture(texture);

            backgroundRenderer.color(color1);

            backgroundRenderer.position(x, y, 0).texCoord(0, 0, 0).next().
                    position(x2, y, 0).texCoord(1, 0, 0).next().
                    position(x2, y2, 0).texCoord(1, 1, 0).next().
                    position(x, y2, 0).texCoord(0, 1, 0).next();

            backgroundRenderer.draw();
        }

        public static void textureRelative(double x, double y, double width, double height, ITexture texture, int color1) {
            final PrimitiveRenderer backgroundRenderer = new PrimitiveRenderer(false, true);

            backgroundRenderer.begin(PrimitiveRendererMode.SQUARE);

            backgroundRenderer.texture(texture);

            backgroundRenderer.color(color1);

            backgroundRenderer.position(x, y, 0).texCoord(0, 0, 0).next().
                    position(x + width, y, 0).texCoord(1, 0, 0).next().
                    position(x + width, y + height, 0).texCoord(1, 1, 0).next().
                    position(x, y + height, 0).texCoord(0, 1, 0).next();

            backgroundRenderer.draw();
        }
    }

    public static class Triangles {

        public static void fill(double x, double y, double size, int color) {
            final PrimitiveRenderer primitiveRenderer = new PrimitiveRenderer(false, false);

            primitiveRenderer.begin(PrimitiveRendererMode.TRIANGLE);

            primitiveRenderer.color(color);

            primitiveRenderer.position(x + size * 0.5d, y).next();
            primitiveRenderer.position(x + size, y + size).next();
            primitiveRenderer.position(x, y + size).next();

            primitiveRenderer.draw();
        }

        public static void fillGradient(double x, double y, double size, int color1, int color2, int color3) {
            final PrimitiveRenderer primitiveRenderer = new PrimitiveRenderer(true, false);

            primitiveRenderer.begin(PrimitiveRendererMode.TRIANGLE);

            primitiveRenderer.position(x + size * 0.5d, y).color(color1).next();
            primitiveRenderer.position(x + size, y + size).color(color2).next();
            primitiveRenderer.position(x, y + size).color(color3).next();

            primitiveRenderer.draw();
        }

    }

}
