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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

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
	TextureRegion backgroundTexture;

	Sound pickupSound;
	Music gameMusic;

	OrthographicCamera camera;
	SpriteBatch batch;
	Rectangle bucket;
	Array<AnimDrop> animalsDrops;
	long lastDropTime;
	int dropsGathered;
	int speed = 200;


	public GameScreen(final Drop game) {
		this.game = game;

		dropImage = new Texture(Gdx.files.internal("img/droplet.png"));
		boxImage = new Texture(Gdx.files.internal("bucket.png"));
		cuteCatImage = new Texture(Gdx.files.internal("img/droplet.png"));
		AngryCatImage = new Texture(Gdx.files.internal("img/cat.png"));
		OwlImage = new Texture(Gdx.files.internal("img/owl.png"));
		MouseImage = new Texture(Gdx.files.internal("img/mouse.png"));
		backgroundTexture = new TextureRegion(new Texture("background.jpg"), 0, 0, 1920, 1312);

		animalsDropArray = new Texture[] {cuteCatImage, AngryCatImage,OwlImage,MouseImage};

		pickupSound = Gdx.audio.newSound(Gdx.files.internal("drops.wav"));
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

		animalsDrops = new Array<>();
		spawnRaindrop();
	}
	private void spawnRaindrop(){

		Rectangle animaldrop = new Rectangle();
		int type = 0;
		animaldrop.x = random(0,1440-64);
		if(MathUtils.randomBoolean(0.25f)){
			type =0;
		}
		else if(MathUtils.randomBoolean(0.25f)){
			type = 1;
		}
		else if(MathUtils.randomBoolean(0.25f)){
			type = 2;
		}
		else if(MathUtils.randomBoolean(0.25f)){
			type = 3;
		}
		animaldrop.y = 720;
		animaldrop.width = 64;
		animaldrop.height = 64;
		animalsDrops.add(new AnimDrop(animaldrop,type));
		lastDropTime = TimeUtils.nanoTime();
	}
	class AnimDrop{
		Rectangle rectangle;
		int type;

		public AnimDrop(Rectangle rectangle, int type) {
			this.rectangle = rectangle;
			this.type = type;
		}
	}
	public void render(float delta){
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(backgroundTexture, 0, 0);
		game.font.draw(game.batch, "Kittens Collected: " + dropsGathered, 600, 720);
		game.batch.draw(boxImage,bucket.x,bucket.y);
		for(AnimDrop animaldrop: animalsDrops) {
			game.batch.draw(animalsDropArray[animaldrop.type], animaldrop.rectangle.x, animaldrop.rectangle.y);
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

		Iterator<AnimDrop> iter = animalsDrops.iterator();
		while (iter.hasNext()){

			AnimDrop animDrop = iter.next();
			animDrop.rectangle.y -= speed * Gdx.graphics.getDeltaTime();
			if (animDrop.rectangle.y + 64 < 0) iter.remove();
			if(animDrop.rectangle.overlaps(bucket)){
				dropsGathered++;
				pickupSound.play();
				iter.remove();
			}
		}
		switch (dropsGathered){
			case 20:
				dropImage = new Texture(Gdx.files.internal("img/cat.png"));
				boxImage = new Texture(Gdx.files.internal("bucketRed.png"));
				speed = 300;
				break;
			case 30:
				dropImage = new Texture(Gdx.files.internal("img/owl.png"));
				boxImage = new Texture(Gdx.files.internal("img/droplet.png"));
				speed = 400;
				break;
			case 50:
				dropImage = new Texture(Gdx.files.internal("img/mouse.png"));
				boxImage = new Texture(Gdx.files.internal("img/cat.png"));
				speed = 500;
				break;
			case 60:
				speed = 800;
				break;
		}
	}
	@Override
	public void dispose() {
		dropImage.dispose();
		boxImage.dispose();
		batch.dispose();
		pickupSound.dispose();
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
