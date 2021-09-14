package player;

import base.GameObject;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerCharacter extends GameObject {
    private Sprite sprite;
    private final float SPEED;
    private boolean moveRight, moveLeft, moveDown, moveUp;

    public PlayerCharacter(Sprite sprite){
        this.sprite = sprite;
        SPEED = 5;
    }

    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public void update() {
        float x = 0.0f,y = 0.0f;
        if(moveDown){
            y -= SPEED;
        }
        if(moveUp){
            y += SPEED;
        }
        if(moveRight){
            x += SPEED;
        }
        if(moveLeft) {
            x -= SPEED;
        }
        sprite.setPosition(sprite.getX() + x, sprite.getY() + y);
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }
}
