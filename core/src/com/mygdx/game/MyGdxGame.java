package com.mygdx.game;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

class GameScreen implements Screen {
	final Drop game;

	Texture dropImage;
	Texture bucketImage;
	Sound meowSound;
	Music gameMusic;
	TextureRegion backgroundTexture;
	OrthographicCamera camera;
	SpriteBatch batch;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastDropTime;
	int dropsGathered;


	public GameScreen(final Drop gam) {
		this.game = gam;

		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		dropImage = new Texture(Gdx.files.internal("img/droplet.png"));
		backgroundTexture = new TextureRegion(new Texture("background.jpg"), 0, 0, 1920, 1312);
		meowSound = Gdx.audio.newSound(Gdx.files.internal("drops.wav"));
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("bit.mp3"));
		gameMusic.setLooping(true);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1440,720);

		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = 800/2 - 64/2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height= 64;

		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}
	private void spawnRaindrop(){

		Rectangle raindrop = new Rectangle();
		raindrop.x = random(0,1440-64);
		raindrop.y = 720;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
	public void render(float delta){
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(backgroundTexture, 0, 0);
		game.font.draw(game.batch, "Kittens Collected: " + dropsGathered, 1440/2 - 64, 720);
		game.batch.draw(bucketImage,bucket.x,bucket.y);
		for(Rectangle raindrop: raindrops) {
			game.batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		game.batch.end();

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = (int) (touchPos.x - 64/2);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 1440 - 64) bucket.x = 1440 - 64;

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		Iterator<Rectangle> iter = raindrops.iterator();
		while (iter.hasNext()){
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0) iter.remove();
			if(raindrop.overlaps(bucket)){
				dropsGathered++;
				meowSound.play();
				iter.remove();
			}
		}
		if(dropsGathered==20){
			dropImage = new Texture(Gdx.files.internal("img/cat.png"));
			bucketImage = new Texture(Gdx.files.internal("bucketRed.png"));
		}
		if(dropsGathered==30){
			dropImage = new Texture(Gdx.files.internal("img/owl.png"));
			bucketImage = new Texture(Gdx.files.internal("img/droplet.png"));
		}
		if(dropsGathered==50){
			dropImage = new Texture(Gdx.files.internal("img/mouse.png"));
			bucketImage = new Texture(Gdx.files.internal("img/cat.png"));
		}
	}
	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		batch.dispose();
		meowSound.dispose();
		gameMusic.dispose();
	}

	@Override
	public void show() {
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {

	}
}
