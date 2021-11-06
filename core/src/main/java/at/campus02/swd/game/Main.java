package at.campus02.swd.game;

import at.campus02.swd.game.commands.AttackCommand;
import at.campus02.swd.game.commands.MoveDownCommand;
import at.campus02.swd.game.commands.MoveUpCommand;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.logic.BasicEnemyStrategy;
import at.campus02.swd.game.logic.CreatureManager;
import at.campus02.swd.game.observer.CreatureScore;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private AbstractGameObjectFactory gameObjectFactory;
	private CreatureScore creatureScore;

	private MoveDownCommand moveDownCommand;
	private MoveUpCommand moveUpCommand;
	private AttackCommand attackCommand;
	private CreatureManager creatureManager;
	private ProjectileManager projectileManager;

	@Override
	public void create() {
		AssetLoaderSingleton.getInstance().loadAssets();
		projectileManager = new ProjectileManager();
		gameObjectFactory = new ZombieGameObjectFactory(projectileManager);
		batch = new SpriteBatch();
		creatureManager = new CreatureManager(gameObjectFactory, new BasicEnemyStrategy());
		CreatureGameObject player = creatureManager.initPlayer();
		projectileManager.setCreatureManager(creatureManager);
		moveUpCommand = new MoveUpCommand(player, 10);
		moveDownCommand = new MoveDownCommand(player, 10);
		attackCommand = new AttackCommand((Ninja)player);
		creatureScore = new CreatureScore();
		creatureManager.attachCreatureDeathObserver(creatureScore);
		creatureManager.init();
	}

	private void act(float delta) {
		handleInputs(delta);
		creatureManager.act(delta);
		projectileManager.act(delta);
	}

	private void draw() {
		batch.begin();
		creatureManager.drawEnemies(batch);
		projectileManager.drawProjectiles(batch);
		batch.end();
	}

	private void handleInputs(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			moveDownCommand.setBooster();
			moveUpCommand.setBooster();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			moveDownCommand.execute(delta);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			moveUpCommand.execute(delta);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			attackCommand.execute(delta);
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