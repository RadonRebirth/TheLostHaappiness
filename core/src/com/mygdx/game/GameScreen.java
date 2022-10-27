package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.awt.Button;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sun.jvm.hotspot.ui.ObjectHistogramPanel;

class GameScreen implements Screen {
	private Player player;
	private OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	private TiledMap map;

	float playerX;
	float playerY;

	Texture upButtonImage;
	Texture downButtonImage;
	Texture leftButtonImage;
	Texture rightButtonImage;

	Rectangle upButton;
	Rectangle downButton;
	Rectangle leftButton;
	Rectangle rightButton;

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
		/*
		renderer.getBatch().draw(upButtonImage, player.getX() + 7 * 32, player.getY() - 3 * 32);
		renderer.getBatch().draw(downButtonImage, player.getX() + 7 * 32, player.getY()- 5 * 32);
		renderer.getBatch().draw(leftButtonImage, player.getX()+ 6 * 32, player.getY() - 4 * 32);
		renderer.getBatch().draw(rightButtonImage, player.getX()+ 8 * 32, player.getY() - 4 * 32);

		 */
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
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
		camera = new OrthographicCamera();
		camera.setToOrtho( false, Gdx.graphics.getWidth()/2,  Gdx.graphics.getHeight()/2);
		/*
		upButtonImage = new Texture(Gdx.files.internal("buttons/up.png"));
		downButtonImage = new Texture(Gdx.files.internal("buttons/down.png"));
		leftButtonImage = new Texture(Gdx.files.internal("buttons/left.png"));
		rightButtonImage = new Texture(Gdx.files.internal("buttons/right.png"));

		upButton = new Rectangle();
		upButton.x = 620 - 32;
		upButton.y = 84;
		upButton.width = 32;
		upButton.height = 32;

		downButton = new Rectangle();
		downButton.x = 620 - 32;
		downButton.y = 20;
		downButton.width = 32;
		downButton.height = 32;

		leftButton = new Rectangle();
		leftButton.x = 620 - 64;
		leftButton.y = 20 + 32;
		leftButton.width = 32;
		leftButton.height = 32;

		rightButton = new Rectangle();
		rightButton.x = 20;
		rightButton.y = 20 + 32;
		rightButton.width = 32;
		rightButton.height = 32;

		 */

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
