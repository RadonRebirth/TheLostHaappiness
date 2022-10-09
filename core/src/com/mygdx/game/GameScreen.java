package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

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

	TiledMap map = new TmxMapLoader().load("map.tmx");
	TiledMapTileLayer collisionLayer = (TiledMapTileLayer)map.getLayers().get("CollisionLayer");
	MapObjects collisionObjects = collisionLayer.getObjects();
	int tileWidth = 32;
	int tileHeight = 32;

	int speed = 150;

	public GameScreen(final Drop game) {
		this.game = game;

		dropImage = new Texture(Gdx.files.internal("img/droplet.png"));
		boxImage = new Texture(Gdx.files.internal("img/player.png"));
		cuteCatImage = new Texture(Gdx.files.internal("img/droplet.png"));
		AngryCatImage = new Texture(Gdx.files.internal("img/cat.png"));
		OwlImage = new Texture(Gdx.files.internal("img/owl.png"));
		MouseImage = new Texture(Gdx.files.internal("img/mouse.png"));

		renderer = new OrthogonalTiledMapRenderer(map, 1 / 1f);



		animalsDropArray = new Texture[] {cuteCatImage, AngryCatImage,OwlImage,MouseImage};

		int tileWidth = 16;
		int tileHeight = 16;

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

		camera.position.x = bucket.x;
		camera.position.y = bucket.y;
		camera.update(); // Здесь точно нужно
		renderer.setView(camera);
		renderer.render();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(boxImage,bucket.x,bucket.y);
		game.batch.end();

		if(bucket.x < 0) bucket.x = 0;
		if(bucket.y < 0) bucket.y = 0;

		for (int i = 0; i < collisionObjects.getCount(); i++) {
			RectangleMapObject obj = (RectangleMapObject) collisionObjects.get(i);
			Rectangle rect = obj.getRectangle();

			rect.set(bucket.x, bucket.y, 17,24);

			Rectangle rectobject = obj.getRectangle();
			rectobject.x /= tileWidth;
			rectobject.y /= tileHeight;
			rectobject.width /= tileWidth;
			rectobject.height /= tileHeight;
			if(bucket.overlaps(rectobject)) {
				speed = 0;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			bucket.x -= speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			bucket.x += speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			bucket.y += speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			bucket.y -= speed * Gdx.graphics.getDeltaTime();
		}

		if(bucket.x > 1440 - 17) bucket.x = 1440 - 17;
		if(bucket.y > 720 - 24) bucket.y = 720 - 24;
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
