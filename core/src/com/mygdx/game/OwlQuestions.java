package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
    private OrthographicCamera camera;
    private Stage stage;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private SpriteBatch batch;
    private ImageButton button;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button5;
    private ImageButton button6;
    private ImageButton button7;
    private ImageButton button8;
    private ImageButton button9;
    private ImageButton button10;
    private ImageButton button11;
    private ImageButton button12;
    final Game game;
    BitmapFont font;
    Music clickSound;
    boolean end = false;
    String[] StringArray = {
            "Какого цвета был котёнок? ",
            "Что любит ваш котёнок? ",
            "Кто вы? ",
            "Как зовут вашего котёнка? ",
            "Ура!! ура!! вы ответили на все вопросы правильно!! "
            };
    int page = 0;
    int startY = 200;
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
    public OwlQuestions(final Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        myTexture = new Texture(Gdx.files.internal("menu/buttons/button_start.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        button2 = new ImageButton(myTexRegionDrawable);
        button3 = new ImageButton(myTexRegionDrawable);
        button.setPosition(100,220);
        button2.setPosition(450,220);
        button3.setPosition(800,220);
        button4 = new ImageButton(myTexRegionDrawable);
        button5 = new ImageButton(myTexRegionDrawable);
        button6 = new ImageButton(myTexRegionDrawable);
        button4.setPosition(100,220);
        button5.setPosition(450,220);
        button6.setPosition(800,220);
        button7 = new ImageButton(myTexRegionDrawable);
        button8 = new ImageButton(myTexRegionDrawable);
        button9 = new ImageButton(myTexRegionDrawable);
        button7.setPosition(100,220);
        button8.setPosition(450,220);
        button9.setPosition(800,220);
        button10 = new ImageButton(myTexRegionDrawable);
        button11 = new ImageButton(myTexRegionDrawable);
        button12 = new ImageButton(myTexRegionDrawable);
        button10.setPosition(100,220);
        button11.setPosition(450,220);
        button12.setPosition(800,220);
        stage = new Stage(new ScreenViewport());
        stage.addActor(button);
        stage.addActor(button2);
        stage.addActor(button3);
        Gdx.input.setInputProcessor(stage);

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

                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            }else {
                font.draw(batch,strBuffer,startX,startY);
            }
            if (end) {
                stage.draw();
                if (page == StringArray.length){
                    //game.setScreen(new NovellaBegin(game));
                }
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
                });
                break;
            case 4:
                stage.clear();
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
        stage.dispose();
    }
}
