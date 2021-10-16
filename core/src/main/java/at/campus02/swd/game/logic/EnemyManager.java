package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.AbstractGameObjectFactory;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.GameObjectType;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnemyManager {
    private List<GameObject> enemies;
    private AbstractGameObjectFactory gameObjectFactory;

    public EnemyManager(AbstractGameObjectFactory gameObjectFactory) {
        enemies = new ArrayList<>();
        this.gameObjectFactory = gameObjectFactory;
    }

    public void createEnemy() {
        GameObject enemy = gameObjectFactory.createGameObject(GameObjectType.ENEMY);
        enemy.setPosition(800, MathUtils.random(0, 600 - 120));
        enemies.add(enemy);
    }

    public void removeLeftMostEnemy() {
        Optional<GameObject> enemyToRemove = enemies.stream().min(Comparator.comparingDouble(x -> x.getX()));
        if(enemyToRemove.isPresent()) {
            enemies.remove(enemyToRemove.get());
        }
    }

    public void tryExecuteEnemy(float playerX, float playerY) {
        Optional<GameObject> enemyToKill = enemies.stream().
                filter(x -> playerX > x.getX()-50 && playerX < x.getX()+50
                        && playerY > x.getY()-50 && playerY < x.getY()+50).findFirst();
        if(enemyToKill.isPresent()) {
            enemies.remove(enemyToKill.get());
        }
    }

    /***
     *
     * @return the amount of removed enemies
     */
    public int removeFinishedEnemies() {
        List toRemove = enemies.stream().filter(x -> x.getX() < 0).collect(Collectors.toList());
        enemies.removeAll(toRemove);
        return toRemove.size();
    }

    public void act(float delta) {
        for (GameObject enemy : enemies) {
            enemy.act(delta);
        }
    }

    public void drawEnemies(SpriteBatch batch) {
        for (GameObject enemy : enemies){
            enemy.draw(batch);
        }
    }
}

