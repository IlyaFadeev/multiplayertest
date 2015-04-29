package com.mygdx.game;

import client.Client;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Ilya on 28.04.2015.
 */
public class MyGdxGame extends Game {



    private static MyGdxGame instance = new MyGdxGame();
    private Warrior warrior;
    private Stage stage;
    private long speed = 1000000;

    public void create()
    {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        warrior = new Warrior(new Texture("warrior.png"));
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
