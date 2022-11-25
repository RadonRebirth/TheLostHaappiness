package com.mygdx.game;

import static sun.jvm.hotspot.runtime.PerfMemory.end;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
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
	private Texture currentFrameTime;
	private int frameCount = 0;
	private int frame;
	float timer = 0;
	float letterSpawnTime = .07f;
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


	private float playerX, playerY;

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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


		playerX = player.getX();
		playerY = player.getY();
		camera.position.set(player.getX(), player.getY(), 0);
		camera.update();
		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
		//androidController();
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (frameCount < framesUp.length) {
				currentFrameTime = framesUp[frameCount];
				frameCount++;
				player.setTexture(currentFrameTime);
			}else{
				frameCount = 0;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (frameCount < framesLeft.length) {
				currentFrameTime = framesLeft[frameCount];
				frameCount++;
				player.setTexture(currentFrameTime);
			}else{
				frameCount = 0;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (frameCount < framesDown.length) {
				currentFrameTime = framesDown[frameCount];
				frameCount++;
				player.setTexture(currentFrameTime);
			}else{
				frameCount = 0;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				if (frameCount < framesRight.length) {
					currentFrameTime = framesRight[frameCount];
					frameCount++;
					player.setTexture(currentFrameTime);
				}else{
					frameCount = 0;
				}
		}
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
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
