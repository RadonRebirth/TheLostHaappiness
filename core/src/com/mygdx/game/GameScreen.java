package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private OrthogonalTiledMapRenderer renderer;
	private TiledMap map;
	private OrthographicCamera camera;
	private Player player;
	Texture background;
	private Vector3 touchPos;
	private Texture currentFrameTime;
	private int frameCount = 0;
	float timer = 0;
	float letterSpawnTime = .1f;
	final Game game;
	private int playerX, playerY;

	private Texture[] framesRight;
	private Texture[] framesLeft;
	private Texture[] framesUp;
	private Texture[] framesDown;
	Texture frame1;
	Texture frame2;
	Texture frame3;
	Texture frame4;
	Texture frame5;
	Texture frame6;

	public GameScreen(final Game game){
	this.game = game;
	}
	@Override
	public void show() {
		Gdx.graphics.setForegroundFPS(144);
		touchPos = new Vector3();

		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		background = new Texture(Gdx.files.internal("map.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho( false, Gdx.graphics.getWidth()/1.5f,  Gdx.graphics.getHeight()/1.5f);

		player = new Player(new Sprite(new Texture("img/player.png")), (TiledMapTileLayer) map.getLayers().get("collisionLayer"));
		player.setPosition(310, 600);
		Gdx.input.setInputProcessor(player);
		camera.update();
	}
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(playerX< 3600 && playerX > 3400 ) {
			if (playerY< 80 && playerY > 60) {
				game.setScreen(new NovellaScreen(game));
			}
		}
		framesUp = new Texture[]{
				frame1 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u1.png")),
				frame2 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u2.png")),
				frame3 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u3.png")),
				frame4 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u4.png"))};

		framesDown = new Texture[]{
				frame1 = new Texture(Gdx.files.internal("data/Animation/WalkDown/d1.png")),
				frame2 = new Texture(Gdx.files.internal("data/Animation/WalkDown/d2.png")),
				frame3 = new Texture(Gdx.files.internal("data/Animation/WalkDown/d3.png")),
				frame4 = new Texture(Gdx.files.internal("data/Animation/WalkDown/d4.png"))};

		framesLeft = new Texture[]{
				frame1 = new Texture(Gdx.files.internal("data/Animation/WalkLeft/l1.png")),
				frame2 = new Texture(Gdx.files.internal("data/Animation/WalkLeft/l2.png")),
				frame3 = new Texture(Gdx.files.internal("data/Animation/WalkLeft/l3.png")),
				frame4 = new Texture(Gdx.files.internal("data/Animation/WalkLeft/l4.png")),
				frame5 = new Texture(Gdx.files.internal("data/Animation/WalkLeft/l5.png")),
				frame6 = new Texture(Gdx.files.internal("data/Animation/WalkLeft/l6.png"))};

		framesRight = new Texture[]{
				frame1 = new Texture(Gdx.files.internal("data/Animation/WalkRight/r1.png")),
				frame2 = new Texture(Gdx.files.internal("data/Animation/WalkRight/r2.png")),
				frame3 = new Texture(Gdx.files.internal("data/Animation/WalkRight/r3.png")),
				frame4 = new Texture(Gdx.files.internal("data/Animation/WalkRight/r4.png")),
				frame5 = new Texture(Gdx.files.internal("data/Animation/WalkRight/r5.png")),
				frame6 = new Texture(Gdx.files.internal("data/Animation/WalkRight/r6.png"))};


		float h = camera.viewportWidth;
		float w = camera.viewportHeight;

		playerX = (int) player.getX();
		playerY = (int) player.getY();
		camera.position.set(playerX, playerY, 0);
		if (camera.position.x < w - 8) {
			camera.position.x = w - 8;
		} else if (camera.position.x > 3840 - w) {
			camera.position.x = 3840 - w;
		}
		if (camera.position.y < h / 3) {
			camera.position.y = h / 3;
		} else if (camera.position.y > h - h / 3 + 2160) {
			camera.position.y = h - h / 3 + 2160;
		}
		camera.update();
		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
//		androidController();
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			timer += delta;
			if (timer >= letterSpawnTime) {
				if (frameCount < framesUp.length) {
					currentFrameTime = framesUp[frameCount];
					frameCount++;
					player.setTexture(currentFrameTime);
				} else {
					frameCount = 0;
				}
				timer -= letterSpawnTime;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			timer += delta;
			if (timer >= letterSpawnTime) {
				if (frameCount < framesLeft.length) {
					currentFrameTime = framesLeft[frameCount];
					frameCount++;
					player.setTexture(currentFrameTime);
				}else{
					frameCount = 0;
				}
				timer -= letterSpawnTime;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			timer += delta;
			if (timer >= letterSpawnTime) {
				if (frameCount < framesDown.length) {
					currentFrameTime = framesDown[frameCount];
					frameCount++;
					player.setTexture(currentFrameTime);
				} else {
					frameCount = 0;
				}
				timer -= letterSpawnTime;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			timer += delta;
			if (timer >= letterSpawnTime) {
				if (frameCount < framesRight.length) {
					currentFrameTime = framesRight[frameCount];
					frameCount++;
					player.setTexture(currentFrameTime);
				} else {
					frameCount = 0;
				}
				timer -= letterSpawnTime;
			}
		}
		player.draw(renderer.getBatch());

		renderer.getBatch().end();
		camera.update();
	}

//	private void androidController() {
//		if (Gdx.input.isTouched()) {
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			if (touchPos.x > player.getX() + 200) {
//				player.velocity.x = player.speed;
//			} else if (touchPos.x < player.getX() - 200 + 32) {
//				player.velocity.x = -player.speed;
//			} else if (touchPos.y > player.getY() + 150) {
//				player.velocity.y = player.speed;
//			} else if (touchPos.y < player.getY() - 150 + 32) {
//				player.velocity.y = -player.speed;
//			}
//		}else {
//			player.velocity.x = 0;
//			player.velocity.y = 0;
//		}
//	}
//


	@Override
	public void dispose() {
		renderer.dispose();
		map.dispose();
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