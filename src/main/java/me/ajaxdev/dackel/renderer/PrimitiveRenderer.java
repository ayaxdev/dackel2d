package me.ajaxdev.dackel.renderer;

import me.ajaxdev.dackel.texture.ITexture;
import org.lwjgl.opengl.GL11;

/**
 * A class for drawing GUI with GL.
 */
public class PrimitiveRenderer {

    private double[][] vertexPositionBuffer;
    private float[][] vertexColorBuffer;
    private double[][] vertexTexCoordBuffer;

    private int color = -1;

    private final boolean colorVertices, textureVertices;

    private ITexture texture;

    private int vertexCount, flag;

    public PrimitiveRenderer(final boolean colorVertices, final boolean textureVertices) {
        this.colorVertices = colorVertices;
        this.textureVertices = textureVertices;
    }

    public void begin(final PrimitiveRendererMode primitiveRendererMode) {
        vertexPositionBuffer = new double[primitiveRendererMode.bufferSize][3];

        if (colorVertices)
            vertexColorBuffer = new float[primitiveRendererMode.bufferSize][4];

        if (textureVertices)
            vertexTexCoordBuffer = new double[primitiveRendererMode.bufferSize][3];

        flag = primitiveRendererMode.flag;
    }

    public PrimitiveRenderer texture(final ITexture texture) {
        this.texture = texture;

        return this;
    }

    public PrimitiveRenderer position(final double x, final double y, final double z) {
        vertexPositionBuffer[vertexCount][0] = x;
        vertexPositionBuffer[vertexCount][1] = y;
        vertexPositionBuffer[vertexCount][2] = z;

        return this;
    }

    public PrimitiveRenderer position(final double x, final double y) {
        return position(x, y, 0);
    }

    public PrimitiveRenderer texCoord(final double x, final double y, final double z) {
        vertexTexCoordBuffer[vertexCount][0] = x;
        vertexTexCoordBuffer[vertexCount][1] = y;
        vertexTexCoordBuffer[vertexCount][2] = z;

        return this;
    }

    public PrimitiveRenderer texCoord(final double x, final double y) {
        return texCoord(x, y, 0);
    }

    public PrimitiveRenderer color(final int color) {
        if (colorVertices) {
            vertexColorBuffer[vertexCount][0] = ((color >> 16) & 0xFF) / 255f;
            vertexColorBuffer[vertexCount][1] = ((color >> 8) & 0xFF) / 255f;
            vertexColorBuffer[vertexCount][2] = ((color/* >> 0*/) & 0xFF) / 255f;
            vertexColorBuffer[vertexCount][3] = ((color >> 24) & 0xFF) / 255f;
        } else {
            this.color = color;
        }

        return this;
    }

    public PrimitiveRenderer color(final float r, final float g, final float b, final float a) {
        if (colorVertices) {
            vertexColorBuffer[vertexCount][0] = r;
            vertexColorBuffer[vertexCount][1] = g;
            vertexColorBuffer[vertexCount][2] = b;
            vertexColorBuffer[vertexCount][3] = a;
        } else {
            color = (((int) (a * 255) & 0xFF) << 24) |
                    (((int) (r * 255) & 0xFF) << 16) |
                    (((int) (g * 255) & 0xFF) << 8)  |
                    (((int) (b * 255) & 0xFF)/* << 0*/);
        }

        return this;
    }

    public PrimitiveRenderer next() {
        this.vertexCount++;

        return this;
    }

    public void draw() {
        if (vertexCount != vertexPositionBuffer.length) {
            throw new IllegalStateException(String.format("Vertex buffer is not of the proper size: %d / %d", vertexCount, vertexPositionBuffer.length));
        }

        if (colorVertices && vertexCount != vertexColorBuffer.length) {
            throw new IllegalStateException(String.format("Color buffer is not of the proper size: %d / %d", vertexCount, vertexColorBuffer.length));
        }

        if (textureVertices && vertexCount != vertexTexCoordBuffer.length) {
            throw new IllegalStateException(String.format("Texture buffer is not of the proper size: %d / %d", vertexCount, vertexColorBuffer.length));
        }

        final boolean textured = textureVertices;

        if (textured) {
            GlManager.enableTexture(texture.getGlId());
        }

        GL11.glBegin(flag);

        for (int i = 0; i < vertexCount; i++) {
            if (vertexColorBuffer != null)
                GlManager.color(vertexColorBuffer[i][0], vertexColorBuffer[i][1], vertexColorBuffer[i][2], vertexColorBuffer[i][3]);
            else
                GlManager.color(((color >> 16) & 0xFF) / 255f, ((color >> 8) & 0xFF) / 255f, ((color/* >> 0*/) & 0xFF) / 255f, ((color >> 24) & 0xFF) / 255f);

            if (textureVertices)
                GL11.glTexCoord3d(vertexTexCoordBuffer[i][0], vertexTexCoordBuffer[i][1], vertexTexCoordBuffer[i][2]);

            GL11.glVertex3d(vertexPositionBuffer[i][0], vertexPositionBuffer[i][1], vertexPositionBuffer[i][2]);
        }

        GlManager.resetColor();

        GL11.glEnd();

        if (textured) {
            GlManager.disableTexture();
        }
    }

}
