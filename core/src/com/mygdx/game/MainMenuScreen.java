package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenuScreen implements Screen {
    final MainGame game;
    TextureRegion background;
    OrthographicCamera camera;

    public MainMenuScreen(final MainGame gam) {
        game = gam;
        background = new TextureRegion(new Texture("backgroundgame.jpg"), 0, 0, 1440, 720);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1440, 720);
    }
    @Override
    public void show() {}
    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.draw(game.batch, "Welcome to Drop!!! ", 600, 550);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen());
            dispose();
        }
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