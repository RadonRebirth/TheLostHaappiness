package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
    public Vector2 velocity = new Vector2();
    public float speed = 150;
    private TiledMapTileLayer collisionLayer;
    private String blockedKey = "blocked";
    boolean collideX, collideY,
    wasCollideX, wasCollideY,
    wasCollideLeft, wasCollideRight,
    wasCollideTop, wasCollideBottom,
    wPressed, sPressed, aPressed, dPressed,
    xBlocked, yBlocked = false;


    public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        setSize(getWidth() , getHeight() );
    }
    @Override
    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    private void update(float delta) {
        float oldX = getX();
        float oldY = getY();

        setX(getX() + velocity.x * delta);

        if (velocity.x < 0) {
            collideX = collidesLeft();
            wasCollideLeft = collideX;
        } else if (velocity.x > 0) {
            collideX = collidesRight();
            wasCollideRight = collideX;
        }

        if (collideX) {
            setX(oldX);
            velocity.x = 0;
            wasCollideX = true;
        }else {
            wasCollideX = false;
        }

        setY(getY() + velocity.y * delta);

        if (velocity.y < 0) {
            collideY = collidesBottom();
            wasCollideBottom = collideY;
        } else if (velocity.y > 0) {
            collideY = collidesTop();
            wasCollideTop = collideY;
        }

        if (collideY) {
            setY(oldY);
            velocity.y = 0;
            wasCollideY = true;
        } else {
            wasCollideY = false;
        }
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                if (!aPressed && !dPressed) {
                    velocity.y += speed;
                    xBlocked = true;
                }
                wPressed = true;
                break;
            case Input.Keys.S:
                if (!aPressed && !dPressed) {
                    velocity.y -= speed;
                    xBlocked = true;
                }
                sPressed = true;
                break;
            case Input.Keys.A:
                if (!sPressed && !wPressed) {
                    velocity.x -= speed;
                    yBlocked = true;
                }
                aPressed = true;
                break;
            case Input.Keys.D:
                if (!sPressed && !wPressed) {
                    velocity.x += speed;
                    yBlocked = true;
                }
                dPressed = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                if (!wasCollideTop & !yBlocked){
                    velocity.y -= speed;
                }
                xBlocked = false;
                wPressed = false;
                wasCollideTop = false;
                if (aPressed & !yBlocked){
                    velocity.x -= speed;
                }
                if (dPressed & !yBlocked){
                    velocity.x += speed;
                }
                break;
            case Input.Keys.S:
                if(!wasCollideBottom & !yBlocked) {
                    velocity.y += speed;
                }
                xBlocked = false;
                sPressed = false;
                wasCollideBottom = false;
                if (aPressed & !yBlocked){
                    velocity.x -= speed;
                }
                if (dPressed & !yBlocked){
                    velocity.x += speed;
                }
                break;
            case Input.Keys.A:
                if(!wasCollideLeft & !xBlocked) {
                    velocity.x += speed;
                }
                yBlocked = false;
                aPressed = false;
                wasCollideLeft = false;
                if (sPressed & !xBlocked){
                    velocity.y -= speed;
                }
                if (wPressed & !xBlocked){
                    velocity.y += speed;
                }
                break;
            case Input.Keys.D:
                if(!wasCollideRight & !xBlocked) {
                    velocity.x -= speed;
                }
                yBlocked = false;
                dPressed = false;
                wasCollideRight = false;
                if (sPressed & !xBlocked){
                    velocity.y -= speed;
                }
                if (wPressed & !xBlocked){
                    velocity.y += speed;
                }
                break;
        }
        return true;
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
    }

    public boolean collidesRight() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX() + getWidth(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY() + getHeight()))
                return true;
        return false;
    }

    public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY()))
                return true;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
