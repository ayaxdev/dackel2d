package me.ajaxdev.dackel.texture;

import java.util.function.Supplier;

/**
 * A texture which switches between 2 different textures depending on the provided switch.
 */
public class BivalentTexture implements ITexture {

    private final ITexture defaultTexture;
    private final ITexture secondaryTexture;
    private final Supplier<Boolean> textureSelector;

    public BivalentTexture(final Supplier<Boolean> textureSelector, final ITexture defaultTexture, final ITexture secondaryTexture) {
        this.defaultTexture = defaultTexture;
        this.secondaryTexture = secondaryTexture;
        this.textureSelector = textureSelector;
    }

    @Override
    public void init() {
        defaultTexture.init();
        secondaryTexture.init();
    }

    @Override
    public int getGlId() {
        return this.textureSelector.get() ? secondaryTexture.getGlId() : defaultTexture.getGlId();
    }

    @Override
    public int getWidth() {
        return this.textureSelector.get() ? secondaryTexture.getWidth() : defaultTexture.getWidth();
    }

    @Override
    public int getHeight() {
        return this.textureSelector.get() ? secondaryTexture.getHeight() : defaultTexture.getHeight();
    }
}
