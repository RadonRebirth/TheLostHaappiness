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
		boxImage = new Texture(Gdx.files.internal("img/player.png"));
		cuteCatImage = new Texture(Gdx.files.internal("img/droplet.png"));
		AngryCatImage = new Texture(Gdx.files.internal("img/cat.png"));
		OwlImage = new Texture(Gdx.files.internal("img/owl.png"));
		MouseImage = new Texture(Gdx.files.internal("img/mouse.png"));
		TiledMap map = new TmxMapLoader().load("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 1f);

		animalsDropArray = new Texture[] {cuteCatImage, AngryCatImage,OwlImage,MouseImage};

		//pickupSound = Gdx.audio.newSound(Gdx.files.internal("drops.wav"));
		//gameMusic = Gdx.audio.newMusic(Gdx.files.internal("bit.mp3"));
		//gameMusic.setLooping(true);

		camera = new OrthographicCamera();
		camera.setToOrtho(false,1440/3,736/3);

		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = 736/2 - 64/2;
		bucket.y = 20;
		bucket.width = 17;
		bucket.height= 24;

		animalsDrops = new Array<>();
		//spawnRaindrop();
	}/*
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
		animaldrop.y = 1440;
		animaldrop.width = 64;
		animaldrop.height = 64;
		animalsDrops.add(new AnimDrop(animaldrop,type));
		lastDropTime = TimeUtils.nanoTime();
	}
	*/
	class AnimDrop{
		Rectangle rectangle;
		int type;

		public AnimDrop(Rectangle rectangle, int type) {
			this.rectangle = rectangle;
			this.type = type;
		}
	}
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		camera.position.x = bucket.x;
		camera.position.y = bucket.y;
		renderer.setView(camera);
		renderer.render();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		//game.batch.draw(backgroundTexture, 0, 0);
		//game.font.draw(game.batch, "Kittens Collected: " + dropsGathered, camera.position.x-140, camera.position.y+90);
		game.batch.draw(boxImage,bucket.x,bucket.y);
		for(AnimDrop animaldrop: animalsDrops) {
			game.batch.draw(animalsDropArray[animaldrop.type], animaldrop.rectangle.x, animaldrop.rectangle.y);
		}
		game.batch.end();
/* временный
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = (int) (touchPos.x - 64/2);
		}

 */
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			bucket.y += 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			bucket.y -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.y < 0) bucket.y = 0;
		if(bucket.x > 1440 - 24) bucket.x = 1440 - 24;
		if(bucket.y > 720 - 24) bucket.y = 720 - 24;
		//if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		Iterator<AnimDrop> iter = animalsDrops.iterator();
		while (iter.hasNext()){

			AnimDrop animDrop = iter.next();
			animDrop.rectangle.y -= speed * Gdx.graphics.getDeltaTime();
			if (animDrop.rectangle.y + 64 < 0) iter.remove();
			if(animDrop.rectangle.overlaps(bucket)){
				dropsGathered++;
				iter.remove();
			}
	}
		switch (dropsGathered){
			case 20:
				boxImage = new Texture(Gdx.files.internal("bucketRed.png"));
				speed = 300;
				break;
			case 30:
				boxImage = new Texture(Gdx.files.internal("img/droplet.png"));
				speed = 400;
				break;
			case 50:
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
		//pickupSound.dispose();
		//gameMusic.dispose();
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
