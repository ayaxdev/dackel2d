package me.ajaxdev.dackel.texture;

import me.ajaxdev.dackel.renderer.GlManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * A simple 2D texture dependent on an image in the program's resources.
 */
public class SimpleTexture implements ITexture {

    public final String path;
    private final TextureType textureType;

    private final int textureFilter, textureWrap;

    private int glId, width, height;

    public SimpleTexture(final String path) {
        this(path, TextureType.RGBA);
    }

    public SimpleTexture(final String path, final TextureType textureType) {
        this(path, textureType, GL11.GL_CLAMP, GL11.GL_LINEAR);
    }

    public SimpleTexture(final String path, final TextureType textureType, final int textureWrap, final int textureFilter) {
        this.textureWrap = textureWrap;
        this.textureFilter = textureFilter;
        this.textureType = textureType;
        this.path = path;
    }

    public void init() {
        if (glId != 0)
            return;

        try (final InputStream stream = SimpleTexture.class.getResourceAsStream(path)) {
            if (stream == null)
                throw new NullPointerException("Texture stream is null");

            final BufferedImage image = ImageIO.read(stream);

            if (image == null)
                throw new IOException("Image format not supported or image is null");

            width = image.getWidth();
            height = image.getHeight();

            final int[] pixels = new int[width * height];

            image.getRGB(0, 0, width, height, pixels, 0, width);

            final ByteBuffer buf = BufferUtils.createByteBuffer(textureType.channels * width * height);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels[y * width + x];
                    buf.put((byte) ((pixel >> 16) & 0xFF)); // Red component
                    buf.put((byte) ((pixel >> 8) & 0xFF));  // Green component
                    buf.put((byte) (pixel & 0xFF));         // Blue component

                    if (textureType == TextureType.RGBA)
                        buf.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component
                }
            }

            buf.flip();

            GlManager.enableTexture(glId = GL11.glGenTextures());

            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, textureFilter);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, textureFilter);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, textureWrap);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, textureWrap);

            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, textureType.glFormat, width, height, 0, textureType.glFormat, GL11.GL_UNSIGNED_BYTE, buf);

            GlManager.disableTexture();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load texture", e);
        }
    }

    public int getGlId() {
        return glId;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public enum TextureType {
        RGB(GL11.GL_RGB, 3),
        RGBA(GL11.GL_RGBA, 4);

        public final int glFormat, channels;

        TextureType(int glFormat, int channels) {
            this.glFormat = glFormat;
            this.channels = channels;
        }
    }
}
