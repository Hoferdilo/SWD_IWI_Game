package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logging.IPositionOutput;
import at.campus02.swd.game.logging.PositionOutputCSV;

public class ZombieGameObjectFactory implements AbstractGameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        IPositionOutput positionOutput = new PositionOutputCSV();
        switch (type) {
            case ENEMY: return new Zombie(positionOutput);
            case PLAYER: return new Ninja(Ninja.Type.FEMALE);
        }
        return null;
    }
}
