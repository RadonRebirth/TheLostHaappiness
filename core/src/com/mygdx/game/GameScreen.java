package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

class GameScreen implements Screen {
	final Drop game;
	Texture dropImage;
	Texture cuteCatImage;
	Texture AngryCatImage;
	Texture OwlImage;
	Texture MouseImage;
	Texture boxImage;
	Texture[] animalsDropArray;
	private OrthogonalTiledMapRenderer renderer;

	OrthographicCamera camera;
	SpriteBatch batch;
	Rectangle bucket;

	public GameScreen(final Drop game) {
		this.game = game;

		dropImage = new Texture(Gdx.files.internal("img/droplet.png"));
		boxImage = new Texture(Gdx.files.internal("img/player.png"));
		cuteCatImage = new Texture(Gdx.files.internal("img/droplet.png"));
		AngryCatImage = new Texture(Gdx.files.internal("img/cat.png"));
		OwlImage = new Texture(Gdx.files.internal("img/owl.png"));
		MouseImage = new Texture(Gdx.files.internal("img/mouse.png"));
		TiledMap map = new TmxMapLoader().load("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 1f);

		animalsDropArray = new Texture[] {cuteCatImage, AngryCatImage,OwlImage,MouseImage};

		camera = new OrthographicCamera();
		camera.setToOrtho(false,1440/3,736/3);

		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = 736/2 - 64/2;
		bucket.y = 20;
		bucket.width = 17;
		bucket.height= 24;
	}

	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		camera.position.x = bucket.x;
		camera.update();
		camera.position.y = bucket.y;
		camera.update();
		renderer.setView(camera);
		camera.update();
		renderer.render();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		camera.update();
		game.batch.begin();
		camera.update();
		game.batch.draw(boxImage,bucket.x,bucket.y);
		camera.update();
		game.batch.end();
		camera.update();
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			bucket.x -= 150 * Gdx.graphics.getDeltaTime();
		}
		camera.update();
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			bucket.x += 150 * Gdx.graphics.getDeltaTime();
		}
		camera.update();
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			bucket.y += 150 * Gdx.graphics.getDeltaTime();
		}
		camera.update();
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			bucket.y -= 150 * Gdx.graphics.getDeltaTime();
		}
		camera.update();
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.y < 0) bucket.y = 0;
		if(bucket.x > 1440 - 24) bucket.x = 1440 - 24;
		if(bucket.y > 720 - 24) bucket.y = 720 - 24;
		camera.update();
	}
	@Override
	public void dispose() {
		dropImage.dispose();
		boxImage.dispose();
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
