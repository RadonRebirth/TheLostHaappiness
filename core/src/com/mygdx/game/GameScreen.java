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

class GameScreen implements Screen {
	private Player player;
	private OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	private TiledMap map;

	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();

	}
	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();
	}
	@Override
	public void show() {
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1440/3,720/3);
		player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get(1));
		player.setPosition(40, 40);
		Gdx.input.setInputProcessor(player);
		}
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width/3;
		camera.viewportHeight = height/3;
		camera.update();
	}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
}
