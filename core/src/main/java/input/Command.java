package input;

import com.badlogic.gdx.InputProcessor;

public interface Command {
    void execute();
    void endExecution();
}
