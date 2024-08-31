package me.ajaxdev.dackel.texture;

import java.util.function.Supplier;

/**
 * A texture which switches between a static texture and an animated one depending on the provided switch.
 */
public class SemiAnimatedTexture extends BivalentTexture {

    public SemiAnimatedTexture(final Supplier<Boolean> textureSelector, final ITexture[] textures, final int defaultIndex, final int animationDelay) {
        super(textureSelector, textures[defaultIndex], new AnimatedTexture(textures, animationDelay));
    }

    public SemiAnimatedTexture(final Supplier<Boolean> textureSelector, final ITexture[] animatedTextures, final ITexture defaultTexture, final int animationDelay) {
        super(textureSelector, defaultTexture, new AnimatedTexture(animatedTextures, animationDelay));
    }

}
