package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import core.implementations.PlayerProxy;
import core.interfaces.PlayerEntity;
import core.interfaces.observer.Listener;

import java.util.UUID;

/**
 * Created by Ilya on 28.04.2015.
 */
public class Warrior extends Actor  {

    private Texture toDraw;
    private PlayerEntity playerEntity;


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
                    playerEntity.updateXY(x , getY());
                    break;
                case 'd':
                    x = warrior.getX() + offset;
                    playerEntity.updateXY(x, getY());
                    break;
                case 'w':
                    y = warrior.getY() + offset;
                    playerEntity.updateXY(getX() , y);
                    break;
                case 's':
                    y  = warrior.getY() - offset;
                    playerEntity.updateXY(getX(), y);
                    break;
            }
            return false;
        }
    }



    public Warrior() {
        this.setName(UUID.randomUUID().toString());

       // this.setName("second");
        this.playerEntity = new PlayerProxy(this);
        this.playerEntity.addListener((Listener) MyGdxGame.getClient()); //Добавляем слушателя
        this.addListener(new KeysListener());
        this.toDraw = new Texture("warrior.png");
        setSize(50, 50);
        setPosition(50, 50);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
        batch.draw(toDraw, getX(), getY(), getWidth(), getHeight());
    }
}
