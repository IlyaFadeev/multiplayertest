package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.awt.event.InputEvent;

/**
 * Created by Ilya on 28.04.2015.
 */
public class Warrior extends Actor {

    private Texture toDraw;
    public Warrior(Texture toDraw)
    {
        this.toDraw = toDraw;
        setSize(50, 50);
        setPosition(10, 10);
    }

    public void draw(Batch batch, float parentAlpha)
    {

        batch.setColor(getColor());
        batch.draw(toDraw, getX(), getY(), getWidth(), getHeight());
    }
}
