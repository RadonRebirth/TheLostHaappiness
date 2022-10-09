package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

class GameScreen implements Screen {
	static class Player {
		static float WIDTH;
		static float HEIGHT;
		static float MAX_VELOCITY = 10f;
		static float JUMP_VELOCITY = 40f;
		static float DAMPING = 0.87f;

		enum State {
			Standing, Walking
		}

		final Vector2 position = new Vector2();
		final Vector2 velocity = new Vector2();
		State state = State.Walking;
		float stateTime = 0;
		boolean facesRight = true;
		boolean grounded = false;
	}


	final Drop game;
	Texture PlayerImg;
	private OrthogonalTiledMapRenderer renderer;

	OrthographicCamera camera;
	SpriteBatch batch;
	Rectangle Player;

	TiledMapTileLayer.Cell cell;

	TiledMap map = new TmxMapLoader().load("map.tmx");
	TiledMapTileLayer collisionLayer = (TiledMapTileLayer)map.getLayers().get("1");
	int tileWidth = 32;
	int tileHeight = 32;

	int speed = 150;
	boolean blocked;

	public GameScreen(final Drop game) {
		this.game = game;

		PlayerImg = new Texture(Gdx.files.internal("img/player.png"));
		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();
		camera.setToOrtho(false,1440/3,720/3);

		batch = new SpriteBatch();

		Player = new Rectangle();
		Player.x = 736/2 - 64/2;
		Player.y = 20;
		Player.width = 32;
		Player.height= 32;
	}

	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.x = Player.x;
		camera.position.y = Player.y;
		camera.update(); // Здесь точно нужно
		renderer.setView(camera);
		renderer.render();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(PlayerImg,Player.x,Player.y);
		game.batch.end();

		if(Player.x < 0) Player.x = 0;
		if(Player.y < 0) Player.y = 0;
/*
		for (int i = 0; i < collisionObjects.getCount(); i++) {
			RectangleMapObject obj = (RectangleMapObject) collisionObjects.get(i);
			Rectangle rect = obj.getRectangle();

			rect.set(Player.x, Player.y, Player.width,Player.height);

			Rectangle rectobject = obj.getRectangle();
			rectobject.x /= tileWidth;
			rectobject.y /= tileHeight;
			rectobject.width /= tileWidth;
			rectobject.height /= tileHeight;
			if(Player.overlaps(rectobject)) {
				Gdx.app.exit();
			}
		}

 */
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			Player.x -= speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			Player.x += speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			Player.y += speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			Player.y -= speed * Gdx.graphics.getDeltaTime();
		}

		if(Player.x > 1440 - 17) Player.x = 1440 - 17;
		if(Player.y > 720 - 24) Player.y = 720 - 24;
	}
	@Override
	public void dispose() {
		PlayerImg.dispose();
		batch.dispose();
		renderer.dispose();
	}
	@Override
	public void show() {
		//gameMusic.play();
		}
	@Override
	public void resize(int width, int height) {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
}
