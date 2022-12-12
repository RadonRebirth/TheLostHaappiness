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


public class NovellaThirdChapter implements Screen {
    private SpriteBatch batch;
    final Game game;
    BitmapFont font;
    Music filin;
    boolean end = false;
    TextureRegion back;
    Texture backTex;
    String[] StringArray = {
            //кадр 2.5: (повтор): сова на дереве
            "[ФИЛИН] – Мне очень жаль, что тебе пришлось потерять своего друга. ",// 0
            "[ФИЛИН] – Пойдём, я покажу тебе надёжную дорогу. ",
            //storyforest.mp3 – конец
            // чёрный кадр jungle – 100%
            //Кадр 3.1: чёрный экран.
            "— Надеюсь, я проснулась. ", // 2
            "— Потому что говорящая сова — это уже все причины недосыпа и усталости. ",
            "— Наверное, я упала в пруд и просто у меня были глюки. ",
            "— Странно звучит. ",
            "— Но сейчас всё нормально. ",
            //Кадр 3.2:  дом.
            "— А где мой дом? ", // 7
            "— Это не похоже совсем на мою деревню. ",
            //Кадр 3.3: чёрный экран с вопросами.
            //jungle – 60%
            //upal.mp3 – один раз воспроизвести, как звук.
            "[ЗНАК ВОПРОСА] — Эй! ", // 9
            "[ЗНАК ВОПРОСА] — Смотри куда идёшь! ",
            "[ЗНАК ВОПРОСА] — Не видишь ничего. ",
            //jungle – 100%
            //Кадр 3.4: стоит ёжик, смотрит в камеру.
            "[ЁЖ] — Я целый день таскал, таскал, носил, носил. ", // 12
            "[ЁЖ] — Но под ноги смотреть надо! ",
            "— Говорящий ёж? ",
            //Кадр: чёрный экран.
            "— О... Боже... Я до сих пор сплю? ", // 15
            "[ЁЖ] — В точности верно, раз вы умудрились меня сбить. ",
            //Кадр 3.4: (повтор)
            "— Ну... Извините, пожалуйста? ", // 17
            "[ЁЖ] — Я добрый, конечно, извиню. ",
            //Кадр 3.5: на траве разбросаны яблоки.
            "— Я помогу, вы тут уронили. ", // 19
            "[ЁЖ] — Не стоит их поднимать, они уже несъедобны. ",
            // Кадр 3.4: стоит ёжик, смотрит в камеру.
            "— Почему? ", // 21
            "[ЁЖ] — Плоды святого древа упав однажды, уже никогда не будут съедобны. ",
            "— Что за древо? ",
            "[ЁЖ] — Это долгая история. ",
            "[ЁЖ] — В центре нашего поселения всегда росло одно дерево. ",
            "[ЁЖ] — На нём растут все плоды. ",
            "[ЁЖ] — Которые способны прокормить каждого и всех. ",
            "[ЁЖ] — Я для своей семьи, например, собираю яблоки и грибы. ",
            "[ЁЖ] — Наши соседи любят шишки. ",
            "[ЁЖ] — Много кто собирает бананы и малину. ",
            "[ЁЖ] — Варенье из них очень вкусное. ",
            "— На этом дереве растёт столько всего... ",
            "[ЁЖ] — Именно, я очень рад, что всегда нахожусь под защитой природы. ",
            "[ЁЖ] — О, кстати наши соседи. ",
            "[ЁЖ] — Доброе утро! ",
            //Кадр: 3.6: стоит белка.
            "[БЕЛКА] — Здравствуйте. ", // 36
            // Кадр 3.4: (повтор)
            "[ЁЖ] — Как у вас дела? ", // 37
            //Кадр 3.6: (повтор)
            "[БЕЛКА] — Д на самом деле не очень. ", // 38
            "[БЕЛКА] — Сегодня с утра просыпаюсь. ",
            "[БЕЛКА] — Чтобы слазить в нижнюю нору за вареньем. ",
            "[БЕЛКА] — А там дыра в стене. ",
            //Кадр 3.4: (повтор)
            "[ЁЖ] — Негодяи какие, устраивать такой беспредел. ", // 42
            //Кадр: 3.6: (повтор)
            "[БЕЛКА] — Я о том же говорю. ", // 43
            "[БЕЛКА] — Вся семья в шоке была. ",
            "[БЕЛКА] — Потом стук в дверь. ",
            "[БЕЛКА] — Я открываю, а там крот наш. ",
            "[БЕЛКА] — Который на другом конце дерева живёт. ",
            "[БЕЛКА] — Говорит, мол, извините. ",
            "[БЕЛКА] — Что ночью начал прокапывать свои пути и переборщил. ",
            "[БЕЛКА] — Раскопал нам весь подвал! ",
            //Кадр 3.4: (повтор)
            "[ЁЖ] — Теперь заделывать придётся. ", // 51
            "[ЁЖ] — Обещали дождик послезавтра, вас затопить может. ",
            "[ЁЖ] — Если надо помочь с подвалом, то зовите. ",
            //Кадр: 3.6: (повтор)
            "[БЕЛКА] — Да... Насчёт дождя я помню предсказание. ", // 54
            "[БЕЛКА] — Крот сам обещал сделать, так что всё обойдётся. ",
            "[БЕЛКА] — Но есть один минус. ",
            "[БЕЛКА] — Банка с вареньем разбилась, была последняя. ",
            "[БЕЛКА] — Мы сегодня хотели попить чай, а не получится. ",
            "[БЕЛКА] — Сейчас только полдня потрачу на то, чтобы собрать шишки. ",
            //Кадр 3.4: (повтор)
            "[ЁЖ] — Может вам помочь?",// 61
            //Кадр 3.7: белка и ёж рядом стоят.
            "— Я вам помогу. ", // 62
            // Кадр 3.6: (повтор)
            "[БЕЛКА] — Правда? ",// 63
            "[БЕЛКА] — Спасибо вам большое! Я мигом за корзинкой. ",
            //Кадр 3.8: большое дерево.
            "— Ух ты, большое дерево. ", // 65
            "[ЁЖ] — Сегодня все собирают ягоды, поэтому их мало. ",
            //Кадр 3.9: часть дерева.
            "[БЕЛКА] — Эта ветка фруктов, мы ей редко пользуемся. ", //67
            "[БЕЛКА] — Обычно для сладостей. ",
            "[ЁЖ] — Кстати лисица обещала сегодня тоже прийти. ",
            "[ЁЖ] — Они решили праздник устроить. ",
            "[БЕЛКА] — Какой? ",
            "[ЁЖ] — День рождения у старшего сына. ",
            "[БЕЛКА] — Точно, как я могла забыть. ",
            //Кадр 3.10: часть дерева.
            "[БЕЛКА] — Это ветка специй. ", // 74
            "[БЕЛКА] — Тут обычно растёт всё острое, сладкое, горькое. ",
            "[БЕЛКА] — Но не просто фрукты, овощи или ягоды. ",
            "[БЕЛКА] — То, что тут растёт идёт для улучшения вкуса или запаха еды. ",
            "[ЁЖ] — Мы корицу в мороженное добавляем. ",
            "[БЕЛКА] — Тут ещё есть острый перец чили, его не собирай. ",
            "[БЕЛКА] — Он очень острый. ",
            //Кадр 3.11: часть дерева.
            "[БЕЛКА] — А вот и наша ветка. ", // 81
            "[БЕЛКА] — Мне нужно всего двадцать штук шишек. ",
            "[БЕЛКА] — Чтобы заготовить их на несколько дней. ",
            "[БЕЛКА] — А дальше я сама как-нибудь. ",
            "[БЕЛКА] — Только аккуратнее, с соседних веток могут свалиться другие ингредиенты. ",
            "[БЕЛКА] — Спасибо тебе заранее! ",
//    Капелька 3.1:
//    jungle – 100%
//    звук падения предмета – op.wav

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
    public NovellaThirdChapter(final Game game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        filin = Gdx.audio.newMusic(Gdx.files.internal("music/claviature.mp3"));
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
                        game.setScreen(new Ingredients(game));
                    }
                }
            }
            batch.flush();
        }
        switch (page){
            case 0:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.5.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 7:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.2.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 9:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 глава/2.6.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 12:
            case 17:
            case 21:
            case 37:
            case 42:
            case 51:
            case 61:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.4.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 2:
            case 15:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 глава/1.0.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 19:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.5.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 36:
            case 38:
            case 43:
            case 54:
            case 63:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.6.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 62:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.7.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 65:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.8.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 67:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.9.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 74:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.10.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 81:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/3 глава/3.11.png"));
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
