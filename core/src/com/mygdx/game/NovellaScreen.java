package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import java.util.Arrays;

import javax.swing.border.StrokeBorder;


public class NovellaScreen implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    final Game game;
    BitmapFont font;
    Music clickSound;
    boolean end = false;
    String[] StringArray = {
            "Вы когда-нибудь задумывались о важных вещах в вашей жизни? ",
            "Теряли что-нибудь? ",
            "Что вы делали, если не находили какую-то деталь или предмет? ",
            "Думаю, даже самые маленькие вещи имеют большой смысл в жизни. ",
            "Но некоторые люди от плохой судьбы теряют друзей, семью и близких... ",
            "Этому и наша поучительная история. ",
            "Всегда, и только всегда, берегите абсолютно всё в вашей жизни. ",
            "Для вас это небольшая страница в жизни, ",
            "А для кого-то – целая жизнь. "
            };
    int page = 0;
    int startY = Gdx.graphics.getWidth()-880;
    int startX = 25;
    boolean paused = false;
    StringBuffer strBuffer;

    float letterSpawnTime = .1f;
    float timer = 0;
    int stringIndex = 0;
    String drawText = "";

    @Override
    public void show() {
    }
    public NovellaScreen(final Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        clickSound = Gdx.audio.newMusic(Gdx.files.internal("music/clickSound.mp3"));

    }
    @Override
    public void render(float deltaTime) {

        font = game.getFont();
        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        batch.setProjectionMatrix(normalProjection);
        batch.begin();
        if (page < StringArray.length) {
            timer += deltaTime;
            strBuffer = new StringBuffer(StringArray[page]);
            if (timer >= letterSpawnTime) {
                drawText = drawText + strBuffer.charAt(stringIndex);
                if(stringIndex < strBuffer.length()-1) {
                    stringIndex++;
                }else {
                    end = true;
                }
                timer -= letterSpawnTime;
            }
            if (!end){
                font.draw(batch, drawText, startX, startY);
                clickSound.play();
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            }
            if (end) {
                clickSound.pause();
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) | Gdx.input.isTouched()) {
                    resume();
                    page++;
                    stringIndex = 0;
                    drawText = "";
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                    end = false;
                    if (page == StringArray.length){
                        game.setScreen(new GameScreen());
                    }
                }
            }
            batch.flush();
        }
        switch (page){
            case 6:
                font.setColor(Color.PURPLE);
                break;
            case 7:
                font.setColor(Color.WHITE);
                break;
            case 8:
                font.setColor(Color.RED);
                break;
        }
        batch.end();

    }
    private static void update(float deltaTime) {
    }

    @Override
    public void resize(int width, int height) {
        startY = Gdx.graphics.getWidth()/3;
        startX = 150;
    }
    @Override
    public void pause() {
        paused = true;
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {}

    @Override
    public void dispose() {
        clickSound.dispose();
        batch.dispose();
        font.dispose();
    }
}
