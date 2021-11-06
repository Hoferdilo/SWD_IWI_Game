package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.logging.IPositionOutputManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ninja implements CreatureGameObject {
    private Sprite sprite;
    private IPositionOutputManager positionOutputManager;
    private Weapon weapon;
    private float health;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        weapon.setEquippedOn(this);
    }

    public enum Type {
        MALE,
        FEMALE
    }

    public Ninja(Type type, IPositionOutputManager positionOutputManager) {
        Texture texture = type == Type.MALE
                ? AssetLoaderSingleton.getInstance().getMaleNinjaTexture()
                : AssetLoaderSingleton.getInstance().getFemaleNinjaTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        this.positionOutputManager = positionOutputManager;
        this.health = 100;
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        positionOutputManager.printPosition(x,y);
        sprite.setPosition(x,y);
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
        return sprite.getHeight();
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void damage(float dmg) {
        health = health - dmg;
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public GameObjectType getType() {
        return null;
    }
}
