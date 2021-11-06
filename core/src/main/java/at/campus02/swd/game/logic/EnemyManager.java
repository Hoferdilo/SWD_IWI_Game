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
    private IEnemyStrategy strategy;

    public EnemyManager(AbstractGameObjectFactory gameObjectFactory, IEnemyStrategy strategy) {
        enemies = new ArrayList<>();
        this.strategy = strategy;
        this.strategy.setEnemyManager(this);
        this.gameObjectFactory = gameObjectFactory;
    }

    public void init() {
        strategy.init();
    }

    public void createEnemy() {
        GameObject enemy = gameObjectFactory.createGameObject(GameObjectType.ENEMY);
        enemy.setPosition(800, MathUtils.random(0, 600 - 120));
        enemies.add(enemy);
    }

   public GameObject getEnemy(float positionX, float positionY, float range) {
       Optional<GameObject> enemy = enemies.stream().
               filter(x -> positionX > x.getX()-range && positionX < x.getX()+range &&
                       positionY > x.getY()-range && positionY < x.getY()+range).findFirst();
       if(enemy.isPresent()){
           return enemy.get();
       }
       return null;
   }

   public List<GameObject> list() {
        return enemies;
   }

    public void remove(GameObject enemy) {
        enemies.remove(enemy);
        createEnemy();
    }

    public void act(float delta) {
        strategy.act(delta);
    }

    public void drawEnemies(SpriteBatch batch) {
        for (GameObject enemy : enemies){
            enemy.draw(batch);
        }
    }
}

