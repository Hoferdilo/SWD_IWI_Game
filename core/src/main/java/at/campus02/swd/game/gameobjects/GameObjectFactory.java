package at.campus02.swd.game.gameobjects;

public class GameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        switch (type) {
            case ZOMBIE: return new Zombie();
            case MALE_NINJA: return new Ninja(Ninja.Type.MALE);
            case FEMALE_NINJA: return new Ninja(Ninja.Type.FEMALE);
        }
        return null;
    }
}
