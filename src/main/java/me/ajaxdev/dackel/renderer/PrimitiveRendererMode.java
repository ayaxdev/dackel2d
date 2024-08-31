package me.ajaxdev.dackel.renderer;

import org.lwjgl.opengl.GL11;

public enum PrimitiveRendererMode {
    SQUARE(4, GL11.GL_QUADS),
    TRIANGLE(3, GL11.GL_TRIANGLES);

    public final int bufferSize, flag;

    PrimitiveRendererMode(int bufferSize, int flag) {
        this.bufferSize = bufferSize;
        this.flag = flag;
    }
}
