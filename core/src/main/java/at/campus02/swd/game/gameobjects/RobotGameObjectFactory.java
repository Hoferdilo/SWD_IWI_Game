package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logging.IPositionOutputManager;
import at.campus02.swd.game.logging.PositionOutputCSV;
import at.campus02.swd.game.logging.PositionOutputLine;
import at.campus02.swd.game.logging.RobotPositionOutputManager;

public class RobotGameObjectFactory implements AbstractGameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        PositionOutputLine positionOutputLine = new PositionOutputLine();
        PositionOutputCSV positionOutputCSV = new PositionOutputCSV("robot");
        IPositionOutputManager positionOutput = new RobotPositionOutputManager(positionOutputLine, positionOutputCSV);
        switch (type) {
            case ENEMY: return new Robot(positionOutput);
            case PLAYER: return new Ninja(Ninja.Type.MALE);
        }
        return null;
    }
}
