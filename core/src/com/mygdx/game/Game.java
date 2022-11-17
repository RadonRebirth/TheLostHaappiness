package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;

public class Game extends com.badlogic.gdx.Game {

    SpriteBatch batch;
    BitmapFont font;

    public void create() {
        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());

        batch = new SpriteBatch();
        batch.setProjectionMatrix(normalProjection);

        font = new BitmapFont();
  /*      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ComicMono.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=24;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);
*/
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixelfont_7.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 24;
        param.borderColor = Color.BLACK;
        param.borderWidth = 2;
        String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-[];:.,/?!&";
        param.characters = FONT_CHARACTERS;
        font = generator.generateFont(param);
        font.setColor(Color.WHITE);

        generator.dispose();
        setScreen(new NovellaScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}