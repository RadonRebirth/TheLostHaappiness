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


public class NovellaBegin implements Screen {
    private SpriteBatch batch;
    final Game game;
    BitmapFont font;
    Music clickSound;
    Music family;
    Music meow;
    Music lovelymeow;
    Music morning;
    Music sadness;
    Music forest;
    Music waterjump;
    boolean end = false;
    TextureRegion back;
    Texture backTex;
    String[] StringArray = {
            /////////// Вступление
            "Поздний вечер. ", // 0 кадр 0.1
            "Уже давно пора было готовиться ко сну. ", // 1
            "Но работа иногда наваливается, что приходиться сбивать режим. ", // 2
            "Остаётся только пытаться остаться с гармонией наедине. ", // 3
            "«Да уж. Поздновато.» ", // 4 кадр 0.2
            "*внезапное шуршание* ", // 5
            "– Мяу? Мурк! ", // 6
            "– Уже проснулся ", // 7 кадр 0.3
            "*мурчание* ",// 8 кадр
            "– Идём, Марси, у нас сегодня на ужин курочка, как ты любишь. ",// 9 кадр 0.4
            "«Я далеко не самый успешный человек в своей жизни.»  ", // 10 кадр 0.1
            "«Живя одной в этом доме, каждый день ходила на работу.» ", // 11
            "«И хоть людей вокруг много, всё равно чувствую себя одинокой.» ",
            "«Но совсем недавно мне почудилось найти котёнка у себя под дверью.» ",
            "«Он потирался о мою ногу, просил еды.» ",
            "«И я пустила его домой.» ",
            "«С тех пор мы живём вместе.» ",
            "«За время я привязалась, очень сильно.» ", // 17 Кадр 0.5
            "«Это был единственный друг, который выручал меня в одинокие вечера.» ",// 18
            "... ", // 19 кадр 0.6
            "«Завтра нужно будет заняться делами.» ", // 20 кадр 0.1
            "– Спокойной ночи, Марси. ",
            "– Мурк! ",//22
            "*спокойное мурчание*  ", // 23
            ////////// Глава 1
           // Кадр 1.1: окно, утро.
	"Порой есть в мире обыденные вещи, которые очень необходимы в такие моменты. ", // 24
	"Просыпаться в уютном доме не одному, что даёт тебе силы и мотивацию. ",
	"Это желание вполне себе привычное, но всё же достаётся не всем. ",
            	"– Почему так тихо? ", // - ГГ
                " – Марси? ",
            	"«Непонятно.» ",
                "Марси, кис-кис, ты куда убежала? ",
            // Кадр 1.2: кухня персонажа.
            	"– Никого. ", // 31
            //Кадр 1.3: гостиная персонажа.
                "– И тут тоже. ", // 32
                "– Куда она делась? ",
            //звук утра – 40%
            //Кадр 1.4: изображения дома гг, но уже днём.
    "Иногда спонтанные вещи, которые случаются в вашей жизни. ", // 34
	"Могут кардинально изменить её. ",
            "«Нужно спросить у соседей. Может, убежала.» ",
            //Кадр 1.5: изображение домов других со стороны.
    "– Здравствуйте, а вы не видели моего котёнка? ", // 37
            "– С жёлтым ошейником. ",
	"– . . . ",
        "– Может вы его во дворе видели? ",
            "– . . . ",
            "– Понятно, спасибо. ",
            //Кадр 1.6: изображение домов других со стороны.
        "– Извините, не видели котёнка? ", // 43
        "– С жёлтым ошейником. ",
    "– . . . ",
        "– Понятно, спасибо. ",
            //звук утра – конец
            //грустная музыка – 100%
            //Кадр 1.7: персонаж на тёмном фоне, с закрытыми глазами.
            "Весь выходной прошёл в волнении. ", // 47
            "Каждая дверь – новый отказ. ",
            "И каждые слова: «Нет, мы не видели вашего котёнка.» ",
    //Кадр 1.8: персонаж на тёмном фоне, с открытыми глазами.
            "Весь выходной. ", //50
            "Целый день без него. ",
    //Кадр 1.9:  лес, уже темнеет, можно лучи от солнца.
	"«Может к пруду убежал.» ", // 52
            "Иногда даже давно знакомые места могут представлять опасность. ",
   // Кадр 1.10: пруд.
    "– Марси! ", // 54
    "– Кис-кис-кис! ",
    "– Марси. ",
    "– Марси... ",
    "«Похоже, всё потерянно.» ",
    "«Я никогда не смогу её найти.» ",
    //Кадр 1.11: черный экран.
            "«Что-то голова кружится.» ", // 60
    //грустная музыка - конец
            "– Ой! ", // 61  звук упал в воду
           // Кадр 2.1: сумеречный лес.
            "– Какой ужас. ", //62
            "«Одежда промокла.» ",
            "– Что сегодня за день такой?! ",
    //Кадр 2.2:
            "– Что это за место? ", // 65
    //Кадр 2.3:
            "– Где я вообще?! ", // 66
            "«Я ничего не понимаю.» ",
            "– Помогите! " // 68

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
    public NovellaBegin(final Game game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        clickSound = Gdx.audio.newMusic(Gdx.files.internal("music/clickSound.mp3"));
        family = Gdx.audio.newMusic(Gdx.files.internal("music/family.mp3"));
        meow = Gdx.audio.newMusic(Gdx.files.internal("music/meow.mp3"));
        lovelymeow = Gdx.audio.newMusic(Gdx.files.internal("music/lovelymeow.mp3"));
        morning = Gdx.audio.newMusic(Gdx.files.internal("music/morning.mp3"));
        sadness = Gdx.audio.newMusic(Gdx.files.internal("music/sadness.mp3"));
        forest = Gdx.audio.newMusic(Gdx.files.internal("music/forest.mp3"));
        waterjump = Gdx.audio.newMusic(Gdx.files.internal("music/waterjump.mp3"));
        family.play();
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
                clickSound.pause();
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) | Gdx.input.isTouched()) {
                    resume();
                    page++;
                    stringIndex = 0;
                    drawText = "";
                    end = false;
                    if (page == StringArray.length){
                        game.setScreen(new GameScreen(game));
                    }
                }
            }
            batch.flush();
        }
        switch (page){
            case 4:
                family.setVolume(0.6f);
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.2.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 6:
                meow.play();
                meow.setLooping(false);
                break;
            case 7:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.3.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 8:
                lovelymeow.play();
                break;
            case 9:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.4.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 10:
                family.setVolume(1);
            case 20:
                family.setVolume(0.6f);
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.1.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 17:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.5.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 19:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/0.6.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 22:
                meow.play();
                meow.setLooping(false);
                break;
            case 23:
                lovelymeow.play();
                family.pause();
                break;
            case 24:
                morning.play();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.1.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 31:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.2.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 32:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.3.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 34:
                morning.setVolume(0.4f);
            case 60:
                forest.pause();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.0.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 61:
                waterjump.play();
                break;
            case 37:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.5.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 43:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.6.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 47:
                morning.pause();
                sadness.play();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.7.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 50:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.8.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 52:
                sadness.pause();
                forest.play();
                forest.isLooping();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.9.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 54:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/1 Глава/1.10.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 62:
                forest.play();
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 Глава/2.1.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 65:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 Глава/2.2.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 66:
                backTex = new Texture(Gdx.files.internal("data/Backgrounds/2 Глава/2.3.png"));
                back = new TextureRegion(backTex, 0 , 0,1280,720);
                break;
            case 68:
                forest.pause();
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
