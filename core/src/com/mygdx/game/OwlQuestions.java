package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OwlQuestions implements Screen {
    private final Stage stage;
    private SpriteBatch batch;
    private final ImageButton button;
    private final ImageButton button2;
    private final ImageButton button3;
    private final ImageButton button4;
    private final ImageButton button5;
    private final ImageButton button6;
    private final ImageButton button7;
    private final ImageButton button8;
    private final ImageButton button9;
    private final ImageButton button10;
    private final ImageButton button11;
    private final ImageButton button12;
    final Game game;
    BitmapFont font;
    Music clickSound;
    boolean end = false;
    String[] StringArray = {
            "Какого цвета был котёнок? ",
            "Что любит ваш котёнок? ",
            "Кто вы? ",
            "Как зовут вашего котёнка? ",
            "",
            "Пойдём, я покажу тебе надёжную дорогу. "
            };
    int page = 0;
    int startY = 440;
    int startX = 100;
    boolean paused = false;
    StringBuffer strBuffer;

    float letterSpawnTime = .1f;
    float timer = 0;
    int stringIndex = 0;
    String drawText = "";
    TextureRegion back;
    Texture backTex;

    @Override
    public void show() {
    }
    public OwlQuestions(final Game game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        Texture myTexture = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/gray.png"));
        Texture myTexture2 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/white.png"));
        Texture myTexture3 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/3color.png"));

        Texture myTexture4 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/fish.png"));
        Texture myTexture5 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/chicken.png"));
        Texture myTexture6 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/milk.png"));

        Texture myTexture7 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/school.png"));
        Texture myTexture8 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/student.png"));
        Texture myTexture9 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/teacher.png"));

        Texture myTexture10 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/first.png"));
        Texture myTexture11 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/second.png"));
        Texture myTexture12 = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/buttons/third.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegion myTextureRegion2 = new TextureRegion(myTexture2);
        TextureRegion myTextureRegion3 = new TextureRegion(myTexture3);

        TextureRegion myTextureRegion4 = new TextureRegion(myTexture4);
        TextureRegion myTextureRegion5 = new TextureRegion(myTexture5);
        TextureRegion myTextureRegion6 = new TextureRegion(myTexture6);

        TextureRegion myTextureRegion7 = new TextureRegion(myTexture7);
        TextureRegion myTextureRegion8 = new TextureRegion(myTexture8);
        TextureRegion myTextureRegion9 = new TextureRegion(myTexture9);

        TextureRegion myTextureRegion10 = new TextureRegion(myTexture10);
        TextureRegion myTextureRegion11 = new TextureRegion(myTexture11);
        TextureRegion myTextureRegion12 = new TextureRegion(myTexture12);

        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        TextureRegionDrawable myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        TextureRegionDrawable myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);

        TextureRegionDrawable myTexRegionDrawable4 = new TextureRegionDrawable(myTextureRegion4);
        TextureRegionDrawable myTexRegionDrawable5 = new TextureRegionDrawable(myTextureRegion5);
        TextureRegionDrawable myTexRegionDrawable6 = new TextureRegionDrawable(myTextureRegion6);

        TextureRegionDrawable myTexRegionDrawable7 = new TextureRegionDrawable(myTextureRegion7);
        TextureRegionDrawable myTexRegionDrawable8 = new TextureRegionDrawable(myTextureRegion8);
        TextureRegionDrawable myTexRegionDrawable9 = new TextureRegionDrawable(myTextureRegion9);

        TextureRegionDrawable myTexRegionDrawable10 = new TextureRegionDrawable(myTextureRegion10);
        TextureRegionDrawable myTexRegionDrawable11 = new TextureRegionDrawable(myTextureRegion11);
        TextureRegionDrawable myTexRegionDrawable12 = new TextureRegionDrawable(myTextureRegion12);
        button = new ImageButton(myTexRegionDrawable);
        button2 = new ImageButton(myTexRegionDrawable2);
        button3 = new ImageButton(myTexRegionDrawable3);
        button.setPosition(42,263);
        button2.setPosition(42,176);
        button3.setPosition(42,93);

        button4 = new ImageButton(myTexRegionDrawable4);
        button5 = new ImageButton(myTexRegionDrawable5);
        button6 = new ImageButton(myTexRegionDrawable6);
        button4.setPosition(42,263);
        button5.setPosition(42,176);
        button6.setPosition(42,93);

        button7 = new ImageButton(myTexRegionDrawable7);
        button8 = new ImageButton(myTexRegionDrawable8);
        button9 = new ImageButton(myTexRegionDrawable9);
        button7.setPosition(42,263);
        button8.setPosition(42,176);
        button9.setPosition(42,93);

        button10 = new ImageButton(myTexRegionDrawable10);
        button11 = new ImageButton(myTexRegionDrawable11);
        button12 = new ImageButton(myTexRegionDrawable12);
        button10.setPosition(42,263);
        button11.setPosition(42,176);
        button12.setPosition(42,93);

        stage = new Stage(new ScreenViewport());
        stage.addActor(button);
        stage.addActor(button2);
        stage.addActor(button3);
        Gdx.input.setInputProcessor(stage);
        backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.8.png"));
        back = new TextureRegion(backTex, 0 , 0,1280,720);

    }
    public void nextQuest(){
        if(end) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            resume();
            page++;
            stringIndex = 0;
            drawText = "";
            end = false;
            stage.clear();
        }
    }
    @Override
    public void render(float deltaTime) {

        font = game.getFont();
        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        batch.setProjectionMatrix(normalProjection);
        batch.begin();
        batch.draw(back,0,0);
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

            }else {
                font.draw(batch,strBuffer,startX,startY);
            }
            if (end) {
                stage.draw();
            }
            batch.flush();
        }
        switch (page){
            case 0:
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                    });
                button2.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                });
                button3.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        nextQuest();
                    }
                });
                break;
            case 1:
                stage.addActor(button4);
                stage.addActor(button5);
                stage.addActor(button6);
                button4.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                });
                button5.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        nextQuest();
                    }
                });
                button6.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                });
                break;
            case 2:
                stage.addActor(button7);
                stage.addActor(button8);
                stage.addActor(button9);
                button7.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                });
                button8.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                });
                button9.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        nextQuest();
                    }
                });
                break;
            case 3:
                stage.addActor(button10);
                stage.addActor(button11);
                stage.addActor(button12);
                button10.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                });
                button11.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new OwlIncorrectAnswer(game));
                    }
                });
                button12.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new NovellaThirdChapter(game));
                        stage.clear();
                    }
                });
                break;
        }
        batch.end();

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
        stage.dispose();
    }
}
