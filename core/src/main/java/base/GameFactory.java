package base;

import enemyComponent.ColorType;
import enemyComponent.Enemy;
import player.PlayerCharacter;

public interface GameFactory {
    Enemy createEnemy(ColorType color);
    PlayerCharacter createPlayer();
}
