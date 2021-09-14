package software.design;

import base.GameFactory;
import base.GameObject;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import enemyComponent.ColorType;
import enemyComponent.Enemy;
import gameFactories.LarryGameFactory;
import gameFactories.OtherGameFactory;
import input.PlayerController;
import observer.EnemyEscapeScore;
import player.PlayerCharacter;

import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private List<GameObject> gameObjects;
	private final float updatesPerSecond = 60;
	private final float logicInterval = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		GameFactory gameFactory = new OtherGameFactory();
		gameObjects = new ArrayList<GameObject>();
		EnemyEscapeScore score = new EnemyEscapeScore();
		gameObjects.add(score);
		gameObjects.add(gameFactory.createEnemy(ColorType.BLUE));
		gameObjects.add(gameFactory.createEnemy(ColorType.GREEN));
		gameObjects.add(gameFactory.createEnemy(ColorType.GREEN));
		gameObjects.forEach(x -> {
			if(x instanceof Enemy) {
				Enemy help = (Enemy)x;
				help.register(score);
			}
		});
		PlayerCharacter player = gameFactory.createPlayer();
		gameObjects.add(player);
		Gdx.input.setInputProcessor(new PlayerController(player));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaAccumulator += Gdx.graphics.getDeltaTime();
		batch.begin();
		renderGame();
		batch.end();
		if(deltaAccumulator > logicInterval) {
			deltaAccumulator -= logicInterval;
			updateGame();
		}
	}

	private void updateGame() {
		gameObjects.forEach(x -> x.update());
	}

	private void renderGame() {
		gameObjects.forEach(x -> x.draw(batch));
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}