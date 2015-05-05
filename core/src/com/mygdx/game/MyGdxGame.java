package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.appwarp.NotificationListener;
import com.mygdx.game.appwarp.WarpController;
import com.mygdx.game.appwarp.WarpListener;
import com.mygdx.game.appwarp.ZoneListener;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.*;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EventListener;
import java.util.Random;

/**
 * Created by Ilya on 28.04.2015.
 */
public class MyGdxGame extends Game implements WarpListener{


    private WarpController client;
    private static MyGdxGame instance = new MyGdxGame();
    private Warrior warrior;
    private Stage stage;
    Random rnd = new Random();
    String str;
    String[] xy;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String response = "q";
    int id = 0;

    public void create()
    {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        warrior = new Warrior(new Texture("warrior.png"),"war");
        stage.addActor(warrior);
        stage.setKeyboardFocus(warrior);
        id = rnd.nextInt();
        System.out.println(id);
        WarpController.getInstance().startApp(String.valueOf(id));
        WarpController.getInstance().setListener(warrior);
        /*if (WarpController.getInstance().isHost()) {
            Warrior w = new Warrior(new Texture("warrior.png"),"w");
            w.setIsHost(false);
            w.setX(warrior.getX() + 50);
            stage.addActor(w);
        }*/


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


    public void onWaitingStarted(String message){}

    public void onError(String message){}

    public void onGameStarted(String message){}
    public void onGameFinished(int code, boolean isRemote){}

    public void onGameUpdateReceived(String message){}
}
