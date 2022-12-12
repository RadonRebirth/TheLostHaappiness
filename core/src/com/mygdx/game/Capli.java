package com.mygdx.game;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Capli implements Screen {

    final Drop game;
    Texture AppleImage;
    Texture ConeImage;
    Texture PepperImage;
    Texture BerriesImage;
    Texture CinnamonImage;
    Texture gg;
    private Player player;

    Texture[] DropArray;
    private Texture[] framesAnim;
    Texture frame1;
    Texture frame2;
    Texture frame3;
    private Texture currentFrameTime;
    private int frameCount = 0;
    float letterSpawnTime = .1f;
    float timer = 0;

    TextureRegion backgroundTexture;



    OrthographicCamera camera;
    SpriteBatch batch;
    Rectangle bucket;
    Array<TreeDrop> treesDrops;
    long lastDropTime;
    int dropsGathered;
    int speed = 200;


    public Capli(final Drop game) {
        this.game = game;

        ConeImage = new Texture(Gdx.files.internal("img/cone.png"));
        gg = new Texture(Gdx.files.internal("img/anim/gg anim1.png"));
        AppleImage = new Texture(Gdx.files.internal("img/Apple.png"));
        PepperImage = new Texture(Gdx.files.internal("img/Pepper.png"));
        BerriesImage = new Texture(Gdx.files.internal("img/Berries.png"));
        CinnamonImage = new Texture(Gdx.files.internal("img/Cinnamon.png"));
        backgroundTexture = new TextureRegion(new Texture("background.png"), 0, 0, 1920, 1312);


        DropArray = new Texture[] {AppleImage, PepperImage,BerriesImage,CinnamonImage};

        camera = new OrthographicCamera();
        camera.setToOrtho(false,1440,720);

        batch = new SpriteBatch();

        bucket = new Rectangle();
        bucket.x = 800/2 - 64/2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height= 64;

        treesDrops = new Array<>();
        spawnRaindrop();
    }
    private void spawnRaindrop(){

        Rectangle treedrop = new Rectangle();
        int type = 0;
        treedrop.x = random(0,1440-64);
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
        treedrop.y = 720;
        treedrop.width = 64;
        treedrop.height = 64;
        treesDrops.add(new TreeDrop(treedrop,type));
        lastDropTime = TimeUtils.nanoTime();
    }
    class TreeDrop{
        Rectangle rectangle;
        int type;

        public TreeDrop(Rectangle rectangle, int type) {
            this.rectangle = rectangle;
            this.type = type;
        }
    }
    public void render(float delta){
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0);
        game.font.draw(game.batch, "Cone: " + dropsGathered, 600, 720);
        game.batch.draw(gg,bucket.x,bucket.y);

        /*framesAnim = new Texture[]{
                frame1 = new Texture(Gdx.files.internal("img/anim/gg anim1.png")),
                frame2 = new Texture(Gdx.files.internal("img/anim/gg anim2.png")),
                frame3 = new Texture(Gdx.files.internal("img/anim/gg anim3.png"))};

        timer += delta;
        if (timer >= letterSpawnTime) {
            if (frameCount < framesAnim.length) {
                currentFrameTime = framesAnim[frameCount];
                frameCount++;
                game.batch.draw(currentFrameTime,bucket.x,bucket.y);
            } else {
                frameCount = 0;
            }
            timer -= letterSpawnTime;
        }*/

        for(TreeDrop elemdrop: treesDrops) {
            game.batch.draw(DropArray[elemdrop.type], elemdrop.rectangle.x, elemdrop.rectangle.y);
        }
        game.batch.end();

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = (int) (touchPos.x - 64/2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
        if(bucket.x < 0) bucket.x = 0;
        if(bucket.x > 1440 - 64) bucket.x = 1440 - 64;

        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        Iterator<TreeDrop> iter = treesDrops.iterator();
        while (iter.hasNext()){

            TreeDrop animDrop = iter.next();
            animDrop.rectangle.y -= speed * Gdx.graphics.getDeltaTime();
            if (animDrop.rectangle.y + 64 < 0) iter.remove();
            if(animDrop.rectangle.overlaps(bucket)){
                dropsGathered++;
                iter.remove();
            }
        }
        switch (dropsGathered){
            case 5:
                speed = 300;
                break;
            case 10:
                speed = 400;
                break;
            case 15:
                speed = 500;
                break;
            case 20:

                break;
        }
    }
    @Override
    public void dispose() {
        AppleImage.dispose();
        gg.dispose();
        batch.dispose();
    }

    @Override
    public void show() {
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
