package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import player.PlayerCharacter;

public class MoveCommand implements Command{
    private Direction direction;
    private PlayerCharacter character;

    public MoveCommand(Direction direction, PlayerCharacter character){
        this.direction = direction;
        this.character = character;
    }

    @Override
    public void execute() {
        if(this.direction == Direction.Right) {
            character.setMoveRight(true);
        }
        if(this.direction == Direction.Left) {
            character.setMoveLeft(true);
        }
        if(this.direction == Direction.Up){
            character.setMoveUp(true);
        }
        if(this.direction == Direction.Down){
            character.setMoveDown(true);
        }
    }

    @Override
    public void endExecution() {
        if(this.direction == Direction.Right) {
            character.setMoveRight(false);
        }
        if(this.direction == Direction.Left) {
            character.setMoveLeft(false);
        }
        if(this.direction == Direction.Up){
            character.setMoveUp(false);
        }
        if(this.direction == Direction.Down){
            character.setMoveDown(false);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
