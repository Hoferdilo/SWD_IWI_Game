package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Zombie implements GameObject {
    private Sprite sprite;
    private float speed = MathUtils.random(10f, 30f);

    public Zombie() {
        Texture texture = AssetLoaderSingleton.getInstance().getZombieTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
    }

    @Override
    public void act(float delta) {
        sprite.setPosition(sprite.getX() - speed * delta, sprite.getY());
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
