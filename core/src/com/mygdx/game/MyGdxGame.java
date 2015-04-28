package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by Ilya on 28.04.2015.
 */
public class MyGdxGame extends Game {

    private static MyGdxGame instance = new MyGdxGame();
    Warrior warrior;
    Stage stage;
    long speed = 1000000;

    class KeysListener extends InputListener {
        @Override
        public boolean keyTyped (InputEvent event, char character) {
            float x = event.getListenerActor().getX();
            float y = event.getListenerActor().getY();

            switch(character) {
                case 'a':
                    x = warrior.getX() - 15;
                    warrior.addAction(Actions.moveTo(x, warrior.getY(), 0.1f, Interpolation.linear));
                    break;
                case 'd':
                    x = warrior.getX() + 15;
                    warrior.addAction(Actions.moveTo(x, warrior.getY(), 0.1f, Interpolation.linear));
                    break;
                case 'w':
                    y = warrior.getY() + 15;
                    warrior.addAction(Actions.moveTo(warrior.getX(), y, 0.1f, Interpolation.linear));
                    break;
                case 's':
                    y  = warrior.getY() - 15;
                    warrior.addAction(Actions.moveTo(warrior.getX(), y, 0.1f, Interpolation.linear));
                    break;
            }
            return false;
        }
    }
    public void create()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        warrior = new Warrior(new Texture("warrior.png"));
        warrior.addListener(new KeysListener());
        stage.addActor(warrior);
        stage.setKeyboardFocus(warrior);
    }



    public static MyGdxGame getInstance() {
        return instance;
    }

    private MyGdxGame(){}
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());

    }
}
