package me.ajaxdev.dackel.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import me.ajaxdev.dackel.renderer.GlManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SimpleTexture implements ITexture {

    public final String path;
    private final TextureType textureType;

    private int glId, width, height;

    public SimpleTexture(final String path) {
        this(path, TextureType.PNG_RGBA);
    }

    public SimpleTexture(final String path, final TextureType textureType) {
        this.path = path;
        this.textureType = textureType;
    }

    public void init() {
        if (glId != 0)
            return;

        try (final InputStream stream = SimpleTexture.class.getResourceAsStream(path)) {
            final PNGDecoder decoder = new PNGDecoder(stream);

            width = decoder.getWidth();
            height = decoder.getHeight();

            final ByteBuffer buf = BufferUtils.createByteBuffer(textureType.channels * width * height);

            decoder.decode(buf, width * textureType.channels, textureType.decoderFormat);

            buf.flip();

            GlManager.enableTexture(glId = GL11.glGenTextures());

            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, textureType.glFormat, width, height, 0, textureType.glFormat, GL11.GL_UNSIGNED_BYTE, buf);

            GlManager.disableTexture();
        } catch (IOException e) {
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
        PNG_RGB(PNGDecoder.Format.RGB, GL11.GL_RGB, 3),
        PNG_RGBA(PNGDecoder.Format.RGBA, GL11.GL_RGBA, 4);

        public final PNGDecoder.Format decoderFormat;
        public final int glFormat, channels;

        TextureType(PNGDecoder.Format decoderFormat, int glFormat, int channels) {
            this.decoderFormat = decoderFormat;
            this.glFormat = glFormat;
            this.channels = channels;
        }
    }
}
