package repository;


import com.badlogic.gdx.graphics.Texture;

import java.util.Map;
import java.util.TreeMap;

public class TextureRepository {
    private static TextureRepository instance;
    private Map<String, Texture> textureStore;

    private TextureRepository() {
        textureStore = new TreeMap<String, Texture>();
        textureStore.put("Dog", new Texture("dog.png"));
        textureStore.put("Cat", new Texture("cat.png"));
        textureStore.put("Larry", new Texture("larry.png"));
        textureStore.put("Barry", new Texture("barry.png"));
    }

    public static TextureRepository getInstance() {
        if(instance == null) {
            instance = new TextureRepository();
        }
        return instance;
    }

    public Texture getDogTexture() {
        return textureStore.get("Dog");
    }

    public Texture getCatTexture() {
        return textureStore.get("Cat");
    }

    public Texture getLarryTexture() {
        return textureStore.get("Larry");
    }

    public Texture getBarryTexture() {
        return textureStore.get("Barry");
    }
}
