package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by Ilya on 28.04.2015.
 */
public class Warrior extends Actor  {

    private Texture toDraw;

    Actor warrior = this;
    class KeysListener extends InputListener {
        @Override
        public boolean keyTyped (com.badlogic.gdx.scenes.scene2d.InputEvent event, char character) {
            float x = event.getListenerActor().getX();
            float y = event.getListenerActor().getY();
            int offset = 30;

            switch(character) {
                case 'a':
                    x = warrior.getX() - offset;
                    warrior.addAction(Actions.moveTo(x, warrior.getY(), 0.1f, Interpolation.linear));
                    break;
                case 'd':
                    x = warrior.getX() + offset;
                    warrior.addAction(Actions.moveTo(x, warrior.getY(), 0.1f, Interpolation.linear));
                    break;
                case 'w':
                    y = warrior.getY() + offset;
                    warrior.addAction(Actions.moveTo(warrior.getX(), y, 0.1f, Interpolation.linear));
                    break;
                case 's':
                    y  = warrior.getY() - offset;
                    warrior.addAction(Actions.moveTo(warrior.getX(), y, 0.1f, Interpolation.linear));
                    break;
            }
            return false;
        }
    }



    public Warrior(Texture toDraw) {
        this.addListener(new KeysListener());
        this.toDraw = toDraw;
        setSize(50, 50);
        setPosition(50, 50);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
        batch.draw(toDraw, getX(), getY(), getWidth(), getHeight());
    }
}
