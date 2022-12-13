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

public class Ingredients implements Screen {

    final Game gameC;
    Texture AppleImage;
    Texture ConeImage;
    Texture PepperImage;
    Texture BerriesImage;
    Texture CinnamonImage;
    Texture gg;
    Texture[] DropArray;

    TextureRegion backgroundTexture;

    Sound pickupSound;
    Music gameMusic;

    OrthographicCamera camera;
    SpriteBatch batch;
    Rectangle ggk;
    Array<TreeDrop> treesDrops;
    long lastDropTime;
    int dropsGathered;
    int speed = 200;


    public Ingredients(final Game game) {
        this.gameC = game;

        ConeImage = new Texture(Gdx.files.internal("img/cone.png"));
        gg = new Texture(Gdx.files.internal("img/gg anim1.png"));
        AppleImage = new Texture(Gdx.files.internal("img/Apple.png"));
        PepperImage = new Texture(Gdx.files.internal("img/Pepper.png"));
        BerriesImage = new Texture(Gdx.files.internal("img/Berries.png"));
        CinnamonImage = new Texture(Gdx.files.internal("img/Cinnamon.png"));
        backgroundTexture = new TextureRegion(new Texture("background.png"), 0, 0, 1280, 720);


        DropArray = new Texture[] {ConeImage, AppleImage, PepperImage,BerriesImage,CinnamonImage};

        pickupSound = Gdx.audio.newSound(Gdx.files.internal("drops.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("bit.mp3"));
        gameMusic.setLooping(true);


        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);

        batch = new SpriteBatch();

        ggk = new Rectangle();
        ggk.x = 800/2 - 64/2;
        ggk.y = 20;
        ggk.width = 64;
        ggk.height= 64;

        treesDrops = new Array<>();
        spawnRaindrop();
    }
    private void spawnRaindrop(){

        Rectangle treedrop = new Rectangle();
        int type = 0;
        treedrop.x = random(0,1280-64);
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
        gameC.batch.setProjectionMatrix(camera.combined);
        gameC.batch.begin();
        gameC.batch.draw(backgroundTexture, 0, 0);
        gameC.font.draw(gameC.batch, "Cone: " + dropsGathered, 600, 670);
        gameC.batch.draw(gg,ggk.x,ggk.y);

        for(TreeDrop elemdrop: treesDrops) {
            gameC.batch.draw(DropArray[elemdrop.type], elemdrop.rectangle.x, elemdrop.rectangle.y);
        }
        gameC.batch.end();

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            ggk.x = (int) (touchPos.x - 64/2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) ggk.x -= 250 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) ggk.x += 250 * Gdx.graphics.getDeltaTime();
        if(ggk.x < 0) ggk.x = 0;
        if(ggk.x > 1280 - 64) ggk.x = 1280 - 64;

        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        Iterator<TreeDrop> iter = treesDrops.iterator();
        while (iter.hasNext()){

            TreeDrop animDrop = iter.next();
            animDrop.rectangle.y -= speed * Gdx.graphics.getDeltaTime();
            if (animDrop.rectangle.y + 64 < 0) iter.remove();
            if(animDrop.rectangle.overlaps(ggk)){
                if(animDrop.type == 0) {
                    dropsGathered++;
                    pickupSound.play();
                    iter.remove();
                }
                if(animDrop.type !=0){
                    // Проиграл
                    pickupSound.play();
                    iter.remove();
                }
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
                //Выиграл
                break;
        }
    }
    @Override
    public void dispose() {
        AppleImage.dispose();
        gg.dispose();
        batch.dispose();
        pickupSound.dispose();
        gameMusic.dispose();
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
