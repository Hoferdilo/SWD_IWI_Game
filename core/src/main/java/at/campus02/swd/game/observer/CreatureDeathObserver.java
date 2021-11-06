package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.CreatureGameObject;

public interface CreatureDeathObserver {
    void update(CreatureGameObject removed);
}
