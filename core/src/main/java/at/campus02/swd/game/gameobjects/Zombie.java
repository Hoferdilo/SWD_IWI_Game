package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.logging.IPositionOutput;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sun.tools.jdeprscan.CSV;
import org.graalvm.compiler.debug.CSVUtil;

public class Zombie implements GameObject {
    private Sprite sprite;
    private float speed = MathUtils.random(30f, 60f);
    private IPositionOutput positionOutput;

    public Zombie(IPositionOutput positionOutput) {
        this.positionOutput = positionOutput;
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
        positionOutput.printPosition(x,y);
        sprite.setPosition(x,y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }
}
