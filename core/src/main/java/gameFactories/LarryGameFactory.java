package gameFactories;

import base.GameFactory;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import enemyComponent.BlueEnemy;
import enemyComponent.ColorType;
import enemyComponent.Enemy;
import enemyComponent.GreenEnemy;
import player.PlayerCharacter;
import repository.TextureRepository;

import java.util.concurrent.ThreadLocalRandom;

public class LarryGameFactory implements GameFactory {
    @Override
    public Enemy createEnemy(ColorType color) {
        Sprite sprite = new Sprite(TextureRepository.getInstance().getBarryTexture());
        sprite.setPosition(ThreadLocalRandom.current().nextInt(460 ,640), ThreadLocalRandom.current().nextInt(0, 380));
        switch(color) {
            case BLUE: return new BlueEnemy(sprite);
            case GREEN: return new GreenEnemy(sprite);
            default: return new Enemy(sprite, Color.CLEAR);
        }
    }

    @Override
    public PlayerCharacter createPlayer() {
        Sprite sprite = new Sprite(TextureRepository.getInstance().getLarryTexture());
        return new PlayerCharacter(sprite);
    }
}
