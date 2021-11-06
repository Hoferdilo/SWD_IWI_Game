package at.campus02.swd.game;

import at.campus02.swd.game.commands.AttackCommand;
import at.campus02.swd.game.commands.MoveDownCommand;
import at.campus02.swd.game.commands.MoveUpCommand;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.logic.BasicEnemyStrategy;
import at.campus02.swd.game.logic.EnemyManager;
import at.campus02.swd.game.logic.IEnemyStrategy;
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
	private AttackCommand attackCommand;
	private EnemyManager enemyManager;

	@Override
	public void create() {
		AssetLoaderSingleton.getInstance().loadAssets();
		gameObjectFactory = new ZombieGameObjectFactory();
		batch = new SpriteBatch();
		player = gameObjectFactory.createGameObject(GameObjectType.PLAYER);
		enemyManager = new EnemyManager(gameObjectFactory, new BasicEnemyStrategy());
		Weapon weapon = new Sword(50, 10,enemyManager);
		((Ninja)player).setWeapon(weapon);
		weapon.setEquippedOn(player);
		moveUpCommand = new MoveUpCommand(player, 10);
		moveDownCommand = new MoveDownCommand(player, 10);
		attackCommand = new AttackCommand((Ninja)player);
		enemyManager.init();
	}

	private void act(float delta) {
		handleInputs(delta);
		enemyManager.act(delta);
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
			attackCommand.execute();
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