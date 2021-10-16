package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logging.*;

public class ZombieGameObjectFactory implements AbstractGameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        PositionOutputCSV positionOutputCSV = new PositionOutputCSV("zombie");
        IPositionOutputManager positionOutput = new ZombiePositionOutputManager(positionOutputCSV, "C02");
        switch (type) {
            case ENEMY: return new Zombie(positionOutput);
            case PLAYER: return new Ninja(Ninja.Type.FEMALE);
        }
        return null;
    }
}
