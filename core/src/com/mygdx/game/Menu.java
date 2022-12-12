package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Menu implements Screen {
    OrthographicCamera camera;
    Texture[] menuBackgroundArray;
    Array<Background> animalsDrops;
    Texture menu1;
    Texture menu2;
    Texture menu3;
    Texture menu4;
    Texture menu5;
    Texture menu6;
    Texture menu7;
    Texture menu8;
    Texture menu9;
    Texture menu10;
    Texture menu11;
    Texture menu12;
    Texture menu13;
    Texture menu14;
    Texture menu15;
    Texture currentMenu;
    int i=0;

    Music musicmenu;
    Game game;

    private final Stage stage;
    private final Stage stage2;

    public Menu(Game gam) {
        game = gam;

        com.badlogic.gdx.graphics.Texture myTexture = new Texture(Gdx.files.internal("menu/buttons/button_start.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(400,520);
        stage = new Stage(new ScreenViewport());
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        com.badlogic.gdx.graphics.Texture myTexture2 = new Texture(Gdx.files.internal("menu/buttons/button_exit.png"));
        myTextureRegion = new TextureRegion(myTexture2);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        button.setPosition(400,420);
        stage2 = new Stage(new ScreenViewport());
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        menuBackgroundArray = new Texture[] {
                menu1 = new Texture(Gdx.files.internal("menu/menuanimation/1.png")),
                menu2 = new Texture(Gdx.files.internal("menu/menuanimation/2.png")),
                menu3 = new Texture(Gdx.files.internal("menu/menuanimation/3.png")),
                menu4 = new Texture(Gdx.files.internal("menu/menuanimation/4.png")),
                menu5 = new Texture(Gdx.files.internal("menu/menuanimation/5.png")),
                menu6 = new Texture(Gdx.files.internal("menu/menuanimation/6.png")),
                menu7 = new Texture(Gdx.files.internal("menu/menuanimation/7.png")),
                menu8 = new Texture(Gdx.files.internal("menu/menuanimation/8.png")),
                menu9 = new Texture(Gdx.files.internal("menu/menuanimation/9.png")),
                menu10 = new Texture(Gdx.files.internal("menu/menuanimation/10.png")),
                menu11 = new Texture(Gdx.files.internal("menu/menuanimation/11.png")),
                menu12 = new Texture(Gdx.files.internal("menu/menuanimation/12.png")),
                menu13 = new Texture(Gdx.files.internal("menu/menuanimation/13.png")),
                menu14 = new Texture(Gdx.files.internal("menu/menuanimation/14.png")),
                menu15 = new Texture(Gdx.files.internal("menu/menuanimation/15.png"))};

        animalsDrops = new Array<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);

        musicmenu = Gdx.audio.newMusic(Gdx.files.internal("music/меню.mp3"));
        musicmenu.setLooping(true);
    }

    @Override
    public void show() {
        musicmenu.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setForegroundFPS(12);
        game.batch.begin();

        if(i<menuBackgroundArray.length){
            currentMenu=menuBackgroundArray[i];
            i++;
        }
        else {
            i=0;
        }

        game.batch.draw(currentMenu,0,0);
        game.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        stage2.act(Gdx.graphics.getDeltaTime());
        stage2.draw();
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

    @Override
    public void dispose() {
        musicmenu.dispose();
    }
}
class Background{
    int type;
    public Background(int type) {
        this.type = type;
    }

}
