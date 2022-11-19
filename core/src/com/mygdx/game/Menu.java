package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.awt.Button;

import sun.print.BackgroundLookupListener;

public class Menu implements Screen {

    OrthographicCamera camera;
    Texture[] menuBackgroundArray;
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
    Texture menu16;
    Texture menu17;
    Texture menu18;
    Texture menu19;
    Texture menu20;
    Texture menu21;
    Texture menu22;
    Texture menu23;
    Texture menu24;
    Texture currentMenu;
    int i=0;
    Music musicmenu;
    Game game;

    public Menu(Game gam) {
        game = gam;

        menuBackgroundArray = new Texture[] {
                menu1 = new Texture(Gdx.files.internal("menuanimation/menu1.png")),
                menu2 = new Texture(Gdx.files.internal("menuanimation/menu2.png")),
                menu3 = new Texture(Gdx.files.internal("menuanimation/menu3.png")),
                menu4 = new Texture(Gdx.files.internal("menuanimation/menu4.png")),
                menu5 = new Texture(Gdx.files.internal("menuanimation/menu5.png")),
                menu6 = new Texture(Gdx.files.internal("menuanimation/menu6.png")),
                menu7 = new Texture(Gdx.files.internal("menuanimation/menu7.png")),
                menu8 = new Texture(Gdx.files.internal("menuanimation/menu8.png")),
                menu9 = new Texture(Gdx.files.internal("menuanimation/menu9.png")),
                menu10 = new Texture(Gdx.files.internal("menuanimation/menu10.png")),
                menu11 = new Texture(Gdx.files.internal("menuanimation/menu11.png")),
                menu12 = new Texture(Gdx.files.internal("menuanimation/menu12.png")),
                menu13 = new Texture(Gdx.files.internal("menuanimation/menu13.png")),
                menu14 = new Texture(Gdx.files.internal("menuanimation/menu14.png")),
                menu15 = new Texture(Gdx.files.internal("menuanimation/menu15.png")),
                menu16 = new Texture(Gdx.files.internal("menuanimation/menu16.png")),
                menu17 = new Texture(Gdx.files.internal("menuanimation/menu17.png")),
                menu18 = new Texture(Gdx.files.internal("menuanimation/menu18.png")),
                menu19 = new Texture(Gdx.files.internal("menuanimation/menu19.png")),
                menu20 = new Texture(Gdx.files.internal("menuanimation/menu20.png")),
                menu21 = new Texture(Gdx.files.internal("menuanimation/menu21.png")),
                menu22 = new Texture(Gdx.files.internal("menuanimation/menu22.png")),
                menu23 = new Texture(Gdx.files.internal("menuanimation/menu23.png")),
                menu24 = new Texture(Gdx.files.internal("menuanimation/menu24.png")),};

        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);

        //musicmenu = Gdx.audio.newMusic(Gdx.files.internal(""));
        //musicmenu.setLooping(true);

        Button startgame = new Button();
        Button settings = new Button();
    }

    @Override
    public void show() {}

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
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}