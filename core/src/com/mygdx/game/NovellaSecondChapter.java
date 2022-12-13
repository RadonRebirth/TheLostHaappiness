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


public class NovellaSecondChapter implements Screen {
    private SpriteBatch batch;
    final Game game;
    BitmapFont font;
    Music filin;
    Music storyforest;
    Music verysad;
    boolean end = false;
    TextureRegion back;
    Texture backTex;
    String[] StringArray = {
             // filin.mp3 – 100%
            //Кадр 2.4: фокус на дерево в сумеречном лесу.
            "«Кто это там?» ",// 0
            "[??] – В глуши, во мраке заточенья, тянулись тихо дни мои. ",
            "– Кто это там разговаривает? ",
            "[??] – Без божества, без вдохновенья, без слез, без жизни, без любви. ",
            "«Стихи читает какие-то.» ",
            "[??] – Душе настало пробужденье: и вот опять явилась ты. ",
            "– Ау? ",
            "[??] – Как мимолетное виденье, как гений чистой... ",
            "– Извините пожалуйста! ",
            "[??] – УХ ТЫ, БАТЮШКИ! ",
            //storyforest.mp3 – 100%
            //Кадр 2.5: сова на дереве.
            "[ФИЛИН] – Это что за вечерний гость? Вы застали меня врасплох! ", // 10
            //Кадр 2.6: чёрный экран, но с полосками, слова в центре." ,
            "– Говорящая сова? ", // 11
            //кадр 2.5: (повтор): сова на дереве
            "[ФИЛИН] – Прошу меня извинить, друг мой удивлённый. ", // 12
            "[ФИЛИН] – Не сова, а Bengalensis Bubo! ",
            "– Буба? ",
            "[ФИЛИН] – Какая я вам Буба?! ",
            "[ФИЛИН] – На вашем языке – Бенгальский Филин. ",
            "– И вы разговариваете? ",
            "[ФИЛИН] – Я не просто разговариваю, я исполняю! И вы, мне, между прочим, помешали. ",
            "– Как вы разговариваете? ",
    "[ФИЛИН] – Что вы заладили: говорящая-говорить?! Никогда с филином не общались? ",
            "– Ну только если во снах. ",
    "[ФИЛИН] – Ох, сон, непостижимая фантазия великих... Но вчера мне приснился кактус. ",
            //Кадр 2.7: чёрный экран и на нем вопрос такой типа:
            "Мне что сова про кактус рассказывает? ", // 23
    //Кадр 2.5: (повтор): сова на дереве
    "[ФИЛИН] – Представляете, он был жёлтым и цвёл орехами. ", // 24
            "– Вам, наверное, уж слишком созерцательный сон приснился. ",
    "[ФИЛИН] – Верно. А зачем, вы, собственно, ко мне пришли в такой поздний вечер? ",
            "– Я не приходила к вам. ",
    "[ФИЛИН] – А что же вы здесь делаете? ",
            "– Я упала в пруд. ",
            "«Блин, я что, с совой разговариваю что ли?» ",
            "[ФИЛИН] – Поэтому вы сильно промокли. У меня есть одеяло. Не желаете согреться? ",
            "«Может, это сон?» ",
            "– Одеяло? ",
            "[ФИЛИН] – Конечно. Без него не уснёшь. Тут зимой очень холодно. ",
            "[ФИЛИН] – А улететь отсюда не по силу практически никому. ",
            "– Подождите. Отсюда невозможно выбраться? ",
            "[ФИЛИН] – Сумеречный лес мудрости и грусти не так прост, как ты думаешь. ",
            "[ФИЛИН] – Так что у тебя случилось? ",
    //storyforest.mp3 – конец
    //verysad.mp3 – 100%
    //Кадр: чёрный экран.
            "– Я. Потеряла котёнка. Сегодня утром. ", // 39
            "– И пошла к пруду, искать его. ",
            "– Случайно поскользнулась. И начала задыхаться, но после меня что-то вытолкнуло. ",
            "– И теперь я тут. ",
    //verysad.mp3 – конец
    //storyforest.mp3 – 100%
    //Кадр 2.5: (повтор): сова на дереве
            "[ФИЛИН] – То есть, ты не местная тут? ", // 43
            "– Тут разве есть такие, как я? ",
            "[ФИЛИН] – Наш лес слишком велик, чтобы запомнить всех его жителей. ",
            "[ФИЛИН] – Поэтому каждый гость – наш житель. ",
            "– Вы. Поможете мне выбраться? ",
            "[ФИЛИН] – Сначала ответь мне на пару вопросов. " // 48

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
    public NovellaSecondChapter(final Game game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        filin = Gdx.audio.newMusic(Gdx.files.internal("music/filin.mp3"));
        storyforest = Gdx.audio.newMusic(Gdx.files.internal("music/storyforest.mp3"));
        verysad = Gdx.audio.newMusic(Gdx.files.internal("music/verysad.mp3"));
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
                        game.setScreen(new OwlQuestions(game));
                    }
                }
            }
            batch.flush();
        }
        switch (page){
            case 0:
                filin.play();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.4.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 10:
                filin.pause();
                storyforest.play();
            case 12:
            case 24:
            case 43:
                verysad.pause();
                storyforest.play();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.5.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 11:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.6.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 23:
            case 39:
                storyforest.pause();
                verysad.play();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 глава/1.0.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
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
