package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;


public class NovellaBegin implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    final Game game;
    BitmapFont font;
    Music clickSound;
    boolean end = false;
    TextureRegion back;
    Texture backTex;
    String[] StringArray = {
            "Поздний вечер. ", // 0 кадр 0.1
            "Уже давно пора было готовиться ко сну. ", // 1
            "Но работа иногда наваливается, что приходиться сбивать режим. ", // 2
            "Остаётся только пытаться остаться с гармонией наедине. ", // 3
            //
            "«Да уж. Поздновато.» ", // 4 кадр 0.2
            "*внезапное шуршание* ", // 5
            "– Мяу? Мурк! ", // 6
            //
            "– Уже проснулся ", // 7 кадр 0.3
            //
            "*мурчание* ",// 8 кадр 0.4
            //
            "– Идём, Марси, у нас сегодня на ужин курочка, как ты любишь. ",// 9 кадр 0.5
            //
            " ", // 10 кадр 0.6
            " ", // 11 кадр 0.7

            };
    int page = 0;
    int startY = 100;
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
    public NovellaBegin(final Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        clickSound = Gdx.audio.newMusic(Gdx.files.internal("music/clickSound.mp3"));
        backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.1.png"));
        back = new TextureRegion(backTex, 0 , 0,1280,720);

    }
    @Override
    public void render(float deltaTime) {
        font = game.getFont();
        font.setColor(Color.WHITE);
        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        batch.setProjectionMatrix(normalProjection);
        batch.begin();
        if (page < StringArray.length) {
            batch.draw(back,0,0);
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
                //clickSound.play();
            }else {
                font.draw(batch,strBuffer,startX,startY);
            }

            if (end) {
                clickSound.pause();
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) | Gdx.input.isTouched()) {
                    resume();
                    page++;
                    stringIndex = 0;
                    drawText = "";
                    end = false;
                    if (page == StringArray.length){
                        game.setScreen(new GameScreen());
                    }
                }
            }
            batch.flush();
        }
        switch (page){
            case 4:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.2.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 7:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.3.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 9:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.4.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 11:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.1.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
        }
        batch.end();

    }
    private static void update(float deltaTime) {
    }

    @Override
    public void resize(int width, int height) {
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
