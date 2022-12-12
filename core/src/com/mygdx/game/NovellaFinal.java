package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;


public class NovellaFinal implements Screen {
    private SpriteBatch batch;
    final Game game;
    BitmapFont font;
    Music filin;
    boolean end = false;
    TextureRegion back;
    Texture backTex;
    String[] StringArray = {
            //Кадр 3.13: корзина с шишками:
            "— Ух ты! Как у тебя хорошо получилось! ", // 0
            "— Молодец. ",
            //Кадр 3.6: (повтор)
            "— Спасибо тебе большое. ",// 2
"— Теперь я успею приготовить подарок для семьи. ",
"— Спасибо ещё раз, пока! Удачи вам! ",
   // Кадр 3.4: (повтор)
            "— Пока! Хорошего вечера. ", // 5
   // Кадр 3.16: лес.
"«Похоже, мне никогда не вернуться домой.» ",// 6
            "«Оставила Марси без присмотра.» ",
            "«А в итоге лишилась и друга, и дома.» ",
    //Кадр 3.17: лес. (темнее)
    //jungle – конец
    //wind.mp3 – 100%
            "«Холоднов-в-вато...» ", // 9
    //Кадр 3.18: лес. (ещё темнее)
    //wind.mp3 – конец
    //rain.mp3 – 100%
            "«Ещё и промокну...» ", // 10
            "«Что за день сегодня такой?» ",
            "– Нужно найти укрытие от дождя. ",
            // Кадр 3.19: дерево. (всё также в затемнённом лесу)
    //rain.mp3 – 60%
    //meowrain.mp3 – 80%
            "«Что это?» ",// 13
            "«Кто это?» ",
            "«Скорее всего, кто-то потерялся.» ",
    //Кадр 3.20: кусты.
"– Звук оттуда. ",//16
            "– Кис-кис. ",
            //Кадр 3.21: Марси в кустах.
            //meowrain.mp3 – конец
"– Марси? ", // 18
            "– Марси! ",
            "– Вот ты где! ",
            "– Я искала тебя везде! ",
            "– Ты бы знала, как я за тебя переживала! ",
    //Кадр 0.6: (повтор)
    //rain.mp3 – 30%
            "– Я уснула ночью. ", // 23
            //Кадр 1.5: (повтор)
            "– Проснулась, искала тебя у соседей. ", // 24
   // Кадр 1.9: (повтор)
            "– Искала в нашем лесу. ", // 25
    //Кадр 1.10: (повтор)
            "– Но упала в болото. ", /// 26
    //Кадр 2.4: (повтор)
            "– Попала в другое измерение, словно сон. ", // 27
       //     Кадр 2.5: (повтор)
            "– Встретила говорящую сову, которая помогла мне. ", // 28
         //   Кадр 3.22: лес диких зверей.
"– Но попала в другую вселенную. ", // 29
        //    rain.mp3 – 100%
    //Кадр 3.17: (повтор)
            "– Я уже потеряла тебя, моё счастье. ", // 30
      //      Кадр: чёрный экран.
           "– Больше так никогда не убегай. ", // 31
        //    end.mp3 – 100%
            "Вы когда-нибудь задумывались о важных вещах в вашей жизни? ", // 32
            "Теряли что-нибудь? ",
            "Что вы делали, если не находили что-то? ",
            "Думаю, что любая вещь имеет смысл в жизни. ",
"Разработчики поздравляют вас с прохождением игры. ",
            "Над игрой работали: ",
            "Главный кодер – Исаев Ислям. ",
            "Главный художник, сценарист, кодер – Лобкова Елизавета. <3 ",
            "Художник, кодер – Михотин Иван. ",
            "Кодер – Никита Байкин. ",
            "«Всегда, и только всегда, берегите абсолютно все вещи в вашей жизни.» ",
            "– The lost Happiness. ",
"Конец. " // 4
};
    int page = 0;
    int startY = 100;
    int startX = 25;
    boolean paused = false;
    StringBuffer strBuffer;

    float letterSpawnTime = .05f;
    float timer = 0;
    int stringIndex = 0;
    String drawText = "";

    @Override
    public void show() {
    }
    public NovellaFinal(final Game game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        filin = Gdx.audio.newMusic(Gdx.files.internal("music/clickSound.mp3"));
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
                filin.pause();
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) | Gdx.input.isTouched()) {
                    resume();
                    page++;
                    stringIndex = 0;
                    drawText = "";
                    end = false;
                    if (page == StringArray.length){
                        Gdx.app.exit();
                    }
                }
            }
            batch.flush();
        }
        switch (page){
            case 0:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.13.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 2:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.6.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 9:
            case 30:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.17.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 5:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.4.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 6:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.16.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 10:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.18.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 13:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.19.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 16:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.20.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 18:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.21.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 23:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.6.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 24:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 глава/1.5.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 25:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 глава/1.9.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 26:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 глава/1.10.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 27:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.4.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 28:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.5.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 29:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.22.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 31:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 глава/1.0.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 32:
                startY = Gdx.graphics.getWidth()/3;
                startX = 150;
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
        batch.dispose();
        font.dispose();
    }
}
