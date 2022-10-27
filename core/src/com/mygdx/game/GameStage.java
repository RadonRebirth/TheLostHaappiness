package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameStage extends Stage {

    private Skin mySkin;
    private ImageButton up;
    private Player player;
    private Box2DDebugRenderer renderer;
    boolean down = false;

    @Override
    public void draw() {
        super.draw();

    }

    public GameStage(ScreenViewport screenViewport, Player player){
        super(screenViewport);
        this.player = player;
        setupButtons();
    }

    @Override
    public void act() {
        super.act();
        if (down){
            System.out.println("ddd");
        }
    }

    private void setupButtons() {
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        up = new ImageButton(mySkin);
        up.setPosition(640 - 52, 20 + 32 * 2);
        //up.setSize(32, 32);
        up.addListener(new ActorGestureListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (!player.wasCollideBottom){
                    player.velocity.y += player.speed;
                }
                player.wasCollideBottom = false;
                down = false;
            }
            @Override
            public void touchDown (InputEvent event, float x, float y, int pointer, int button) {
                player.velocity.y -= player.speed;
                down = true;
            }
        });
        addActor(up);
    }
}
