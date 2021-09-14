package observer;

import base.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import enemyComponent.Enemy;

public class EnemyEscapeScore extends GameObject implements EnemyEscapeObserver{
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    @Override
    public void update(Enemy enemy) {
        counter++;
    }

    @Override
    public void draw(Batch batch) {
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLACK);
        bitmapFont.draw(batch, "Enemies escaped: "+counter, 50,20);
    }

    @Override
    public void update() {
    }
}
