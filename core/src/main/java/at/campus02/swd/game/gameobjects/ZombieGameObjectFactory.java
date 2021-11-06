package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logging.*;

public class ZombieGameObjectFactory implements AbstractGameObjectFactory {
    private ProjectileManager projectileManager;

    public ZombieGameObjectFactory(ProjectileManager projectileManager) {
        this.projectileManager = projectileManager;
    }

    public CreatureGameObject createGameObject(GameObjectType type) {
        PositionOutputCSV positionOutputCSV = new PositionOutputCSV("zombie");
        IPositionOutputManager positionOutput = new ZombiePositionOutputManager(positionOutputCSV, "C02");
        switch (type) {
            case ENEMY: Zombie zombie = new Zombie(positionOutput);
            zombie.setWeapon(new Gun(projectileManager, 80, 5));
            return zombie;
            case PLAYER: Ninja ninja = new Ninja(Ninja.Type.FEMALE, new NullPositionOutputManager());
            ninja.setWeapon(new Gun(projectileManager, -100, 5));
            return ninja;
        }
        return null;
    }
}
