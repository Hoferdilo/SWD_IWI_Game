package gameFactories;

import base.GameFactory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import enemyComponent.BlueEnemy;
import enemyComponent.ColorType;
import enemyComponent.Enemy;
import enemyComponent.GreenEnemy;
import player.PlayerCharacter;
import repository.TextureRepository;

import java.util.concurrent.ThreadLocalRandom;

public class OtherGameFactory implements GameFactory {
    @Override
    public Enemy createEnemy(ColorType color) {
        Sprite sprite = new Sprite(TextureRepository.getInstance().getDogTexture());
        sprite.flip(true,false);
        sprite.setSize(128,128);
        sprite.setPosition(ThreadLocalRandom.current().nextInt(460 ,640), ThreadLocalRandom.current().nextInt(0, 380));
        switch (color) {
            case BLUE: return new BlueEnemy(sprite);
            case GREEN: return new GreenEnemy(sprite);
            default: return null;
        }
    }

    @Override
    public PlayerCharacter createPlayer() {
        Sprite sprite = new Sprite(TextureRepository.getInstance().getCatTexture());
        sprite.setSize(128,128);
        return new PlayerCharacter(sprite);
    }
}
