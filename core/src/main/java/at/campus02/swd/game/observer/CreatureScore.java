package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.GameObjectType;

public class CreatureScore implements CreatureDeathObserver {
    private int score = 0;

    @Override
    public void update(CreatureGameObject removed) {
        if(removed.getType() == GameObjectType.ENEMY) {
            score += 1;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
