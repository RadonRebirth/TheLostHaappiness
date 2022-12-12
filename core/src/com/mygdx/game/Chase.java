package com.mygdx.game;

import static com.badlogic.gdx.math.MathUtils.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Chase implements Screen {
    final Game game;
    OrthographicCamera camera;
    private Vector3 touchPos;

    Texture playerT;
    Texture puddleT;
    private Texture[] framesUp;
    Texture frame1;
    Texture frame2;
    Texture frame3;
    Texture frame4;
    private Texture currentFrameTime;

    private int frameCount = 0;
    float timer = 0;
    float letterSpawnTime = .1f;

    Array<Rectangle> puddles;
    Rectangle playerR;
    long lastDropTime;

    int lives = 3;
    int puddlesCounter = 30;

    int position = 1;
    boolean isUpA = true;
    boolean isUpB = true;

    public Chase(final Game game) {
        this.game = game;

        puddleT = new Texture(Gdx.files.internal("puddle.png"));
        playerT = new Texture(Gdx.files.internal("img/player.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        playerR = new Rectangle();
        playerR.x = 400 + 200 - 16;
        playerR.y = 20;
        playerR.width = 32;
        playerR.height = 64;

        puddles = new Array<>();

        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Rectangle puddle = new Rectangle();

        puddle.width = 32;
        puddle.height = 32;
        puddle.x = 400 + (200 - 16) * random(0, 2);
        puddle.y = 720;

        puddles.add(puddle);
        lastDropTime = TimeUtils.nanoTime();
    }

    //TODO сделать плавным
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        framesUp = new Texture[]{
                frame1 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u1.png")),
                frame2 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u2.png")),
                frame3 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u3.png")),
                frame4 = new Texture(Gdx.files.internal("data/Animation/WalkUp/u4.png"))};

        timer += delta;

        if (timer >= letterSpawnTime) {
            if (frameCount < framesUp.length) {
                currentFrameTime = framesUp[frameCount];
                frameCount++;
                playerT = currentFrameTime;
            } else {
                frameCount = 0;
            }
            timer -= letterSpawnTime;
        }

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Жизни: " + lives, 0, 720);
        game.batch.draw(playerT, playerR.x, playerR.y);

        for (Rectangle rectangle : puddles) {
            game.batch.draw(puddleT, rectangle.x, rectangle.y);
        }

        game.batch.end();
        camera.update();

        if (Gdx.input.isKeyPressed(Input.Keys.A) && !(position == 0) && isUpA){
            position--;
            playerR.x -= (200 - 16);
            isUpA = false;
        }else {
            isUpA = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && !(position == 2) && isUpB){
            position++;
            playerR.x += (200 - 16);
            isUpB = false;
        }else {
            isUpB = true;
        }

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x < 200 && !(position == 0) && isUpA) {
                position--;
                playerR.x -= (200 - 16);
                isUpA = false;
            }else {
                isUpA = true;
            }

            if (touchPos.x > 1080 && !(position == 2) && isUpB) {
                position++;
                playerR.x += (200 - 16);
                isUpB = false;
            }else {
                isUpB = true;
            }
        }

        camera.update();

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000 && puddlesCounter != 0) {
            spawnRaindrop();
            puddlesCounter--;
        }

        if(puddlesCounter == 0){
            //TODO Ревлизоать то, что будет при победе
        }

        Iterator<Rectangle> iter = puddles.iterator();
        while (iter.hasNext()) {
            Rectangle puddle = iter.next();
            puddle.y -= 400 * Gdx.graphics.getDeltaTime();
            if (puddle.y + 32 < 0) {
                puddlesCounter--;
                iter.remove();
            }

            if (puddle.overlaps(playerR)) {
                lives--;
                iter.remove();
            }

            if(lives==0){
                //TODO Здесь реализовать, то что должно быть при проигрыше
            }
        }
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        playerT.dispose();
        puddleT.dispose();
    }
}