package input;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import player.PlayerCharacter;

import java.util.Map;
import java.util.TreeMap;

public class PlayerController extends ApplicationAdapter implements InputProcessor  {
    private Map<Integer, Command> input;

    public PlayerController(PlayerCharacter player) {
        input = new TreeMap<Integer, Command>();
        input.put(Input.Keys.W, new MoveCommand(Direction.Up, player));
        input.put(Input.Keys.A, new MoveCommand(Direction.Left, player));
        input.put(Input.Keys.D, new MoveCommand(Direction.Right, player));
        input.put(Input.Keys.S, new MoveCommand(Direction.Down, player));
    }

    @Override
    public boolean keyDown(int keycode) {
        Command action = input.get(keycode);
        if(action != null) {
            action.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Command action = input.get(keycode);
        if(action != null) {
            action.endExecution();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
