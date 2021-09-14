package enemyComponent;

import base.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import observer.EnemyEscapeObserver;
import strategy.IMovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends GameObject  {
    private Sprite sprite;
    private Color color;
    private IMovementStrategy strategy;
    private List<EnemyEscapeObserver> enemyEscapeObservers;

    public Enemy(Sprite sprite, Color color) {
        this.enemyEscapeObservers = new ArrayList<EnemyEscapeObserver>();
        this.sprite = sprite;
        this.color = color;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public void update() {
        if(this.sprite.getX() < 3) {
            this.sprite.setX(640);
            this.sprite.setY(ThreadLocalRandom.current().nextInt(0,360));
            enemyEscapeObservers.forEach(x -> x.update(this));
        }
        this.sprite.setPosition(this.sprite.getX() - 2, this.sprite.getY());
    }

    public void register(EnemyEscapeObserver observer) {
        enemyEscapeObservers.add(observer);
    }

    public void unregister(EnemyEscapeObserver observer) {
        enemyEscapeObservers.remove(observer);
    }

}
