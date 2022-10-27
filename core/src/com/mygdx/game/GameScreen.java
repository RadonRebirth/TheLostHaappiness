package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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

	private GameStage stage;

	float playerX;
	float playerY;

	public GameScreen(){
		stage = new GameStage(new ScreenViewport(), player);
		Gdx.input.setInputProcessor(stage);
	}

	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		playerX = player.getX();
		playerY = player.getY();
		camera.position.set(player.getX(), player.getY(), 0);
		camera.update();
		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();

		stage.draw();
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
