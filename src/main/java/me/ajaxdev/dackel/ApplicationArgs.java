package me.ajaxdev.dackel;

import me.ajaxdev.dackel.scene.EmptyScene;
import me.ajaxdev.dackel.scene.IScene;

public record ApplicationArgs(IScene defaultScene, String title, boolean resizeable, int width, int height, int antialiasing) {

    public static ApplicationBuilder builder() {
        return new ApplicationBuilder();
    }

    public static class ApplicationBuilder {

        private IScene defaultScene;
        private String title;
        private boolean resizeable = true;
        private int width = -1, height = -1;
        private int antialiasing = -1;

        public ApplicationBuilder setTitle(final String title) {
            this.title = title;
            return this;
        }

        public ApplicationBuilder setResizeable(final boolean resizeable) {
            this.resizeable = resizeable;
            return this;
        }

        public ApplicationBuilder setWidth(final int width) {
            this.width = width;
            return this;
        }

        public ApplicationBuilder setHeight(final int height) {
            this.height = height;
            return this;
        }

        public ApplicationBuilder setScene(final IScene defaultScene) {
            this.defaultScene = defaultScene;
            return this;
        }

        public ApplicationBuilder setAntialiasing(final int antialiasing) {
            this.antialiasing = antialiasing;
            return this;
        }

        public ApplicationArgs build() {
            return new ApplicationArgs(
                    defaultScene == null ? new EmptyScene() : defaultScene,
                    this.title == null ? "Dackel Application" : title,
                    this.resizeable,
                    this.width == -1 ? 100 : Math.abs(this.width),
                    this.height == -1 ? 100 : Math.abs(this.height),
                    this.antialiasing
            );
        }
    }

}
