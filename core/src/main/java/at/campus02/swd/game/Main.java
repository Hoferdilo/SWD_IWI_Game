package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.GameObjectFactory;
import at.campus02.swd.game.gameobjects.GameObjectType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private final Array<GameObject> gameObjects = new Array<>();
	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;

	private GameObject player;

	@Override
	public void create() {
		AssetLoaderSingleton.getInstance().loadAssets();
		GameObjectFactory gameObjectFactory = new GameObjectFactory();
		batch = new SpriteBatch();
		player = gameObjectFactory.createGameObject(GameObjectType.FEMALE_NINJA);
		gameObjects.add(player);
		for(int i = 0; i < 10; i++) {
			GameObject gameObject = gameObjectFactory.createGameObject(GameObjectType.ZOMBIE);
			gameObject.setPosition(MathUtils.random(800), MathUtils.random(600));
			gameObjects.add(gameObject);
		}
	}

	private void act(float delta) {
		for(GameObject gameObject : gameObjects) {
			gameObject.act(delta);
		}
	}

	private void draw() {
		batch.begin();
		for(GameObject gameObject : gameObjects) {
			gameObject.draw(batch);
		}
		batch.end();
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