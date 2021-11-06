package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.GameObjectType;

import java.util.List;
import java.util.stream.Collectors;

public class BasicEnemyStrategy implements IEnemyStrategy {
    private CreatureManager creatureManager;

    public void act(float delta) {
        for (GameObject enemy : creatureManager.list()) {
            enemy.act(delta);
        }
        int amountRemoved = removeFinishedEnemies();
        for (int i = 0; i < amountRemoved; i++) {
            creatureManager.createEnemy();
        }
    }

    @Override
    public void init() {
        for (int i = 0; i < 10; i++) {
            creatureManager.createEnemy();
        }
    }

    private int removeFinishedEnemies() {
        List<CreatureGameObject> toRemove = creatureManager.list().stream().filter(x -> x.getX() < 0 ||
                x.getHealth() <= 0).collect(Collectors.toList());
        toRemove.forEach(x -> creatureManager.remove(x));
        return toRemove.size();
    }

    @Override
    public void setEnemyManager(CreatureManager creatureManager) {
        this.creatureManager = creatureManager;
    }
}
