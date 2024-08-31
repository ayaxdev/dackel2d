package me.ajaxdev.dackel.texture;

import java.util.HashMap;
import java.util.Map;

/**
 * The manager which bounds textures and allows us to access them.
 */
public class TextureManager {

    private final Map<String, ITexture> loadedTextureMap = new HashMap<>();
    private final Map<String, ITexture> unloadedTextureMap = new HashMap<>();

    public void reload() {
        while (!unloadedTextureMap.isEmpty()) {
            final Map.Entry<String, ITexture> entry = unloadedTextureMap.entrySet().iterator().next();
            entry.getValue().init();
            loadedTextureMap.put(entry.getKey(), entry.getValue());
            unloadedTextureMap.remove(entry.getKey());
        }
    }

    public void order(final String name, final ITexture texture) {
        if (loadedTextureMap.containsKey(name) || unloadedTextureMap.containsKey(name)) {
            return;
        }

        unloadedTextureMap.put(name, texture);
    }

    public ITexture get(final String name) {
        return loadedTextureMap.get(name);
    }

}
