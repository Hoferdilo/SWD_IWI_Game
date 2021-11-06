package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.logging.IPositionOutputManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sun.tools.jdeprscan.CSV;

import java.util.Random;

public class Zombie implements CreatureGameObject {
    private Sprite sprite;
    private float speed = MathUtils.random(30f, 60f);
    private IPositionOutputManager positionOutput;
    private float health;
    private Weapon weapon;

    public Zombie(IPositionOutputManager positionOutput) {
        this.positionOutput = positionOutput;
        Texture texture = AssetLoaderSingleton.getInstance().getZombieTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
        health = 10;
    }

    @Override
    public void act(float delta) {
        if(weapon != null) {
            weapon.execute(0.0001f + (float)Math.random() * (0.001f - 0.0001f));
        }
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
    public GameObjectType getType() {
        return GameObjectType.ENEMY;
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getWidth() {
        return sprite.getWidth();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public float getHeight() {
        return sprite.getHeight()-10;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void damage(float dmg) {
        health =- dmg;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        weapon.setEquippedOn(this);
    }
}
