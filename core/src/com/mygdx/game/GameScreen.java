package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

class GameScreen implements Screen {
	private OrthogonalTiledMapRenderer renderer;
	private TiledMap map;
	private OrthographicCamera camera;
	private Player player;
	private Vector3 touchPos;
	/*
	private Robot robot;
	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	 */


	private float playerX, playerY;

	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		playerX = player.getX();
		playerY = player.getY();
		camera.position.set(player.getX(), player.getY(), 0);
		camera.update();
		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
		//androidController();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
		camera.update();
	}
/*
	private void androidController() {
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (touchPos.x > player.getX() + 200) {
				robot.keyPress(KeyEvent.VK_D);
			} else if (touchPos.x < player.getX() - 200 + 32) {
				robot.keyPress(KeyEvent.VK_A);
			} else if (touchPos.y > player.getY() + 150) {
				robot.keyPress(KeyEvent.VK_W);
			} else if (touchPos.y < player.getY() - 150 + 32) {
				robot.keyPress(KeyEvent.VK_S);
			}
		}else {
			robot.keyRelease(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_W);
		}
	}

 */

	@Override
	public void dispose() {
		renderer.dispose();
	}
	@Override
	public void show() {
		Gdx.graphics.setForegroundFPS(144);
		touchPos = new Vector3();

		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();
		camera.setToOrtho( false, Gdx.graphics.getWidth()/2,  Gdx.graphics.getHeight()/2);

		player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get("collisionLayer"));
		player.setPosition(320, 440);
		Gdx.input.setInputProcessor(player);
		camera.update();
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
