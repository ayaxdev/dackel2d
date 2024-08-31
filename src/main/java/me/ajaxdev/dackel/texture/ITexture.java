package me.ajaxdev.dackel.texture;

/**
 * An interface for handling GL textures.
 */
public interface ITexture {

    /**
     * Registers the texture.
     */
    void init();

    /**
     * @return The ID of the texture used for binding.
     */
    int getGlId();

    /**
     * @return The width of the texture.
     */
    int getWidth();

    /**
     * @return The height of the texture.
     */
    int getHeight();

}
