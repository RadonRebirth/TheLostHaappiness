package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

class GameScreen implements Screen {
	private Player player;
	private OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	private TiledMap map;

	Matrix4 normalProjection;
 	Batch batch;

	float playerX;
	float playerY;

	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playerX = player.getX();
		playerY = player.getY();

		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		renderer.setView(camera);
		renderer.render();

		batch.begin();
		player.draw(batch);
		batch.end();
	}
	@Override
	public void dispose() {
		renderer.dispose();
	}
	@Override
	public void show() {
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("map.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);
		normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
		batch = renderer.getBatch();
		batch.setProjectionMatrix(normalProjection);

		camera = new OrthographicCamera();
		camera.setToOrtho(false,1280/2,720/2);

		player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get("collisionLayer"));
		player.setPosition(320, 440);

		Gdx.input.setInputProcessor(player);
		}
	@Override
	public void resize(int width, int height) {
		camera.update();
	}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
}
