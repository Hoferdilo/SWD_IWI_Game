package strategy;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class FastMovementStrategy implements IMovementStrategy{
    private static float SPEED = 10;

    @Override
    public void executeMovement(Sprite sprite) {
        sprite.setPosition(sprite.getX()+SPEED, sprite.getY());
    }
}
