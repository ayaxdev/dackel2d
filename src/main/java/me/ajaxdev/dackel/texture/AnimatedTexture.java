package me.ajaxdev.dackel.texture;

import me.ajaxdev.dackel.util.Timer;

/**
 * A texture which switches between provided textures depending on the provided delay.
 */
public class AnimatedTexture implements ITexture {

    public final ITexture[] textures;
    public final long delay;
    private final Timer timer = new Timer();

    private int index = 0;

    public AnimatedTexture(final ITexture[] textures, final long animationDelay) {
        this.textures = textures;
        this.delay = animationDelay;
    }

    @Override
    public void init() {
        for (final ITexture texture : textures) {
            texture.init();
        }
    }

    @Override
    public int getGlId() {
        updateAnimation();

        return textures[index].getGlId();
    }

    @Override
    public int getWidth() {
        updateAnimation();

        return textures[index].getWidth();
    }

    @Override
    public int getHeight() {
        updateAnimation();

        return textures[index].getHeight();
    }

    public void reset() {
        this.index = 0;
        this.timer.reset();
    }

    private void updateAnimation() {
        if (this.timer.hasTimeElapsedMs(delay)) {
            index = index == textures.length - 1 ? 0 : index + 1;

            this.timer.reset();
        }
    }
}
