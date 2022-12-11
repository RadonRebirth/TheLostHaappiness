package com.mygdx.game;

import static com.badlogic.gdx.math.MathUtils.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Chase implements Screen {
    final Game game;

    Texture playerT;
    Texture puddleT;

    OrthographicCamera camera;
    Rectangle playerR;
    Array<Rectangle> puddles;
    long lastDropTime;
    int lives = 5;

    private Texture[] framesUp;
    Texture frame1;
    Texture frame2;
    Texture frame3;
    Texture frame4;

    private Texture currentFrameTime;
    private int frameCount = 0;
    float timer = 0;
    float letterSpawnTime = .1f;

    public Chase(final Game game) {
        this.game = game;

        puddleT = new Texture(Gdx.files.internal("puddle.png"));
        playerT = new Texture(Gdx.files.internal("img/player.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        playerR = new Rectangle();
        playerR.x = 1280 / 2 - 64 / 2;
        playerR.y = 20;
        playerR.width = 64;
        playerR.height = 64;

        puddles = new Array<>();

        spawnRaindrop();

    }

    private void spawnRaindrop() {
        Rectangle puddle = new Rectangle();

        puddle.x = random(0, 1280 - 32);
        puddle.y = 720;
        puddle.width = 32;
        puddle.height = 32;

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
        //1
        //TODO Здесь реализовать управление на телефоне
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            playerR.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            playerR.x += 200 * Gdx.graphics.getDeltaTime();

        if (playerR.x < 0)
            playerR.x = 0;
        if (playerR.x > 1280 - 64)
            playerR.x = 1280 - 64;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        Iterator<Rectangle> iter = puddles.iterator();
        while (iter.hasNext()) {
            Rectangle puddle = iter.next();
            puddle.y -= 400 * Gdx.graphics.getDeltaTime();
            if (puddle.y + 32 < 0) {
                lives--;
                iter.remove();
            }
            if(lives==0){
                //TODO Здесь реализовать, то что должно быть при проигрыше
            }
            if (puddle.overlaps(playerR)) {
                iter.remove();
            }
        }
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        Timer myTimer;
        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            public void run() {
                timerTick();
            }
        }, 0, 5000); //TODO Установить нужно время(сейчас 5 сек)
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

    private void timerTick() {
        this.runOnUiThread();
    }

    private void runOnUiThread() {
        //TODO Тут должно быть написано то, что долно произойти по истечению времени
    }
}