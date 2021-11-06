package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logging.*;

public class RobotGameObjectFactory implements AbstractGameObjectFactory {
    public CreatureGameObject createGameObject(GameObjectType type) {
        PositionOutputLine positionOutputLine = new PositionOutputLine();
        PositionOutputCSV positionOutputCSV = new PositionOutputCSV("robot");
        IPositionOutputManager positionOutput = new RobotPositionOutputManager(positionOutputLine, positionOutputCSV);
        switch (type) {
            case ENEMY: return new Robot(positionOutput);
            case PLAYER: return new Ninja(Ninja.Type.MALE, new NullPositionOutputManager());
        }
        return null;
    }
}
