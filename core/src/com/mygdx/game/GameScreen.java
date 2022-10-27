package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

class GameScreen implements Screen {
	private Player player;
	private OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	private TiledMap map;
	Vector3 touchPos;

	float playerX;
	float playerY;

	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		playerX = player.getX();
		playerY = player.getY();
		camera.position.set(player.getX(), player.getY(), 0);
		camera.update();
		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (touchPos.x > player.getX()) {
				player.velocity.x = 150;
			} else {
				player.velocity.x = -150;
			}
		}
		else {
			player.velocity.x = 0;
		}

		player.draw(renderer.getBatch());
		renderer.getBatch().end();
	}
	@Override
	public void dispose() {
		renderer.dispose();
	}
	@Override
	public void show() {
		touchPos = new Vector3();
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho( false, Gdx.graphics.getWidth()/2,  Gdx.graphics.getHeight()/2);
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
