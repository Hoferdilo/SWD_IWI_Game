package at.campus02.swd.game.logic;

public interface IEnemyStrategy {
    void act(float delta);
    void init();
    void setEnemyManager(CreatureManager creatureManager);
}
