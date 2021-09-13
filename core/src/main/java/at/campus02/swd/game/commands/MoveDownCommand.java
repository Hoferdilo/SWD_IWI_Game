package at.campus02.swd.game.commands;

import at.campus02.swd.game.gameobjects.GameObject;

public class MoveDownCommand implements PlayerCommand {
    private final GameObject gameObject;
    private final float distance;

    public MoveDownCommand(GameObject gameObject, float distance) {
        this.gameObject = gameObject;
        this.distance = distance;
    }

    @Override
    public void execute() {
        float newY = Math.max(0, gameObject.getY() - distance);
        this.gameObject.setPosition(gameObject.getX(), newY);
    }
}
