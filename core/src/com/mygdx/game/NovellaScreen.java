package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class NovellaScreen implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    final Game game;
    int page = 0;
    int startY = 500;
    String[] StringArray = {
            "Вы когда-нибудь задумывались о важных вещах в вашей жизни?",
            "Теряли что-нибудь?",
            "Что вы делали, если не находили какую-то деталь или мелкий предмет?",
            "Думаю, что даже самые маленькие вещи или детальки могут иметь большой смысл в вашей жизни.",
            "Но некоторые люди от плохой судьбы теряют друзей, семью и близких...",
            "Этому и наша поучительная история:",
            "Всегда, и только всегда, берегите абсолютно всё в вашей жизни.",
            "Почему? Потому что для вас это небольшая страница в жизни, а для кого-то – целая жизнь.",
            ""};
    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1440, 720);
        batch = new SpriteBatch();
    }
    public NovellaScreen(final Game game) {
        this.game = game;
    }
    @Override
    public void render(float delta) {

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, StringArray[page], 50, startY);
        camera.update();
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            page+=1;
            startY-=30;
        }
        camera.update();

        if(page == 8){
            game.setScreen(new GameScreen());
            camera.update();
        }
        camera.update();
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
    public void dispose() {
        batch.dispose();
    }
}
