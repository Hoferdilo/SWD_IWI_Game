package at.campus02.swd.game.gameobjects;

public interface AbstractGameObjectFactory {
    CreatureGameObject createGameObject(GameObjectType type);
}