package at.campus02.swd.game;

import at.campus02.swd.game.commands.MoveDownCommand;
import at.campus02.swd.game.commands.MoveUpCommand;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.logic.EnemyManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private AbstractGameObjectFactory gameObjectFactory;

	private GameObject player;
	private MoveDownCommand moveDownCommand;
	private MoveUpCommand moveUpCommand;
	private EnemyManager enemyManager;

	@Override
	public void create() {
		AssetLoaderSingleton.getInstance().loadAssets();
		gameObjectFactory = new ZombieGameObjectFactory();
		batch = new SpriteBatch();
		player = gameObjectFactory.createGameObject(GameObjectType.PLAYER);
		enemyManager = new EnemyManager(gameObjectFactory);
		moveUpCommand = new MoveUpCommand(player, 10);
		moveDownCommand = new MoveDownCommand(player, 10);
		for(int i = 0; i < 10; i++) {
			enemyManager.createEnemy();
		}
	}

	private void act(float delta) {
		handleInputs(delta);
		enemyManager.act(delta);
		int amountRemoved = enemyManager.removeFinishedEnemies();
		for (int i = 0; i < amountRemoved; i++) {
			enemyManager.createEnemy();
		}
	}

	private void draw() {
		batch.begin();
		enemyManager.drawEnemies(batch);
		player.draw(batch);
		batch.end();
	}

	private void handleInputs(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			moveDownCommand.setBooster();
			moveUpCommand.setBooster();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			moveDownCommand.execute();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			moveUpCommand.execute();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			enemyManager.tryExecuteEnemy(player.getX(), player.getY());
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float delta = Gdx.graphics.getDeltaTime();
		deltaAccumulator += delta;
		while(deltaAccumulator > logicFrameTime) {
			deltaAccumulator -= logicFrameTime;
			act(logicFrameTime);
		}
		draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
		AssetLoaderSingleton.getInstance().dispose();
	}
}