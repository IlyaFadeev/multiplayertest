package com.mygdx.game;

import client.GameClient;
import client.SynchronousClient;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Ilya on 28.04.2015.
 */
public class MyGdxGame extends Game {

    private static GameClient client;

    private static MyGdxGame instance = new MyGdxGame();
    private Warrior first;
    private Stage stage;
    private long speed = 1000000;



    public void create()
    {
        //Создаем клиент
        client = new SynchronousClient();

        //Подключаемся к серверу
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.connect("localhost", 5555);
            }
        }).start();


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        first = new Warrior();

        first.setName("first");
       /* Warrior second = new Warrior();
        second.setName("second");
        Warrior third = new Warrior();
        third.setName("third"); */


        stage.addActor(first);
        stage.setKeyboardFocus(first);
    }

    public static MyGdxGame getInstance() {
        return instance;
    }
    public static GameClient getClient() {
        return client;
    }

    private MyGdxGame(){}
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());

    }

    public void addActor(Actor actor) {
        this.stage.addActor(actor);
    }
}
