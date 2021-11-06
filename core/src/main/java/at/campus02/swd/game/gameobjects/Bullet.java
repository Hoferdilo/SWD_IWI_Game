package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet implements Projectile {
    private Sprite sprite;
    private float speed;
    private float damage;
    private float size;
    private float health;
    private GameObjectType shootBy;

    public Bullet(Sprite sprite, float speed, float damage, float size, GameObjectType shootBy) {
       this.sprite = sprite;
       this.speed = speed;
       this.damage = damage;
       this.size = size;
       this.health = 1;
       this.shootBy = shootBy;
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
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getWidth() {
        return size;
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public float getHeight() {
        return size;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void damage(float dmg) {
        health -= dmg;
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public GameObjectType getType() {
        return shootBy;
    }

    @Override
    public float getDamage() {
        return damage;
    }
}
