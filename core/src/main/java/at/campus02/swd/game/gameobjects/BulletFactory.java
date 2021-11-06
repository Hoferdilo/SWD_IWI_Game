package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BulletFactory {
    private float damage;
    private float speed;
    private float size;
    private GameObjectType shootBy;

    public BulletFactory(float damage, float speed, float size, GameObjectType shootBy) {
        this.damage = damage;
        this.speed = speed;
        this.size = size;
        this.shootBy = shootBy;
    }

    public Bullet createBullet() {
        return new Bullet(
                new Sprite(AssetLoaderSingleton.getInstance().getBulletTexture()),
                speed,
                damage,
                size,
                shootBy
        );
    }
}
