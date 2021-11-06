package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.observer.CreatureDeathObserver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreatureManager {
    private List<CreatureGameObject> creatures;
    private AbstractGameObjectFactory gameObjectFactory;
    private IEnemyStrategy strategy;
    private List<CreatureDeathObserver> creatureDeathObservers;

    public CreatureManager(AbstractGameObjectFactory gameObjectFactory, IEnemyStrategy strategy) {
        creatures = new ArrayList<>();
        this.strategy = strategy;
        this.strategy.setEnemyManager(this);
        this.gameObjectFactory = gameObjectFactory;
        creatureDeathObservers = new ArrayList<>();
    }

    public void init() {
        strategy.init();
    }

    public CreatureGameObject initPlayer() {
        CreatureGameObject player = gameObjectFactory.createGameObject(GameObjectType.PLAYER);
        creatures.add(player);
        return player;
    }

    public void createEnemy() {
        CreatureGameObject enemy = gameObjectFactory.createGameObject(GameObjectType.ENEMY);
        enemy.setPosition(800, MathUtils.random(0, 600 - 120));
        creatures.add(enemy);
    }

   public CreatureGameObject getCreature(float positionX, float positionY, float range, GameObjectType notHittable) {
       Optional<CreatureGameObject> target = creatures.stream().
               filter(x -> x.getType() != notHittable
                       && positionX > x.getX()-range && positionX < x.getX()+range+x.getWidth()-40 &&
                       positionY > x.getY()-range && positionY < x.getY()+range+x.getHeight()).findFirst();
       if(target.isPresent()){
           return target.get();
       }
       return null;
   }

   public List<CreatureGameObject> list() {
        return creatures;
   }

    public void remove(CreatureGameObject enemy) {
        creatures.remove(enemy);
    }

    public void act(float delta) {
        strategy.act(delta);
    }

    public void drawEnemies(SpriteBatch batch) {
        for (GameObject creature : creatures){
            creature.draw(batch);
        }
    }

    public void attachCreatureDeathObserver(CreatureDeathObserver observer) {
        creatureDeathObservers.add(observer);
    }

    public void detachCreatureDeathObserver(CreatureDeathObserver observer) {
        creatureDeathObservers.remove(observer);
    }

    private void notifyCreatureDeathObserver(CreatureGameObject creature) {
        creatureDeathObservers.forEach(x -> x.update(creature));
    }
}

