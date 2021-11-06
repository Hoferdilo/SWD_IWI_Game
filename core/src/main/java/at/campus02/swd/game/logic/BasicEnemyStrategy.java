package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.GameObject;

import java.util.List;
import java.util.stream.Collectors;

public class BasicEnemyStrategy implements IEnemyStrategy {
    private EnemyManager enemyManager;

    public void act(float delta) {
        for (GameObject enemy : enemyManager.list()) {
            enemy.act(delta);
        }
        int amountRemoved = removeFinishedEnemies();
        for (int i = 0; i < amountRemoved; i++) {
            enemyManager.createEnemy();
        }
    }

    @Override
    public void init() {
        for (int i = 0; i < 10; i++) {
            enemyManager.createEnemy();
        }
    }

    private int removeFinishedEnemies() {
        List toRemove = enemyManager.list().stream().filter(x -> x.getX() < 0).collect(Collectors.toList());
        enemyManager.list().removeAll(toRemove);
        return toRemove.size();
    }

    @Override
    public void setEnemyManager(EnemyManager enemyManager) {
        this.enemyManager = enemyManager;
    }
}
