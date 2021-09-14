package strategy;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SlowMovementStrategy implements IMovementStrategy{
    private static float SPEED = 5;

    @Override
    public void executeMovement(Sprite sprite) {
        sprite.setPosition(sprite.getX()+SPEED, sprite.getY());
    }
}
