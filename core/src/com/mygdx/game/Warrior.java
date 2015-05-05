package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.appwarp.NotificationListener;
import com.mygdx.game.appwarp.WarpController;
import com.mygdx.game.appwarp.WarpListener;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Ilya on 28.04.2015.
 */
public class Warrior extends Actor implements WarpListener {

    private Texture toDraw;

    Actor warrior = this;
    String str;
    Scanner scanner = new Scanner(System.in);
    String roomId = "";
    public boolean isHost() {
        return isHost;
    }

    public void setIsHost(boolean isHost) {

        this.isHost = isHost;
    }

    private boolean isHost = true;

    //Constructor
    public Warrior(Texture toDraw, String name) {
        this.addListener(new KeysListener());
        this.toDraw = toDraw;
        setSize(50, 50);
        setPosition(50, 50);

        this.setName(name);

    }

    class KeysListener extends InputListener {
        @Override
        public boolean keyTyped(com.badlogic.gdx.scenes.scene2d.InputEvent event, char character) {
            float x = event.getListenerActor().getX();
            float y = event.getListenerActor().getY();
            int offset = 30;

            switch (character) {
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
                    str = String.valueOf(warrior.getX()) + " " + String.valueOf(warrior.getY());
                    JSONObject object = new JSONObject();
                    try {
                        object.put("x",warrior.getX());
                        object.put("y",warrior.getY());
                    }
                    catch(Exception Ex)
                    {
                        Ex.printStackTrace();
                    }

                    WarpController.getInstance().sendGameUpdate(object.toString());


                    break;
                case 's':
                    y = warrior.getY() - offset;
                    warrior.addAction(Actions.moveTo(warrior.getX(), y, 0.1f, Interpolation.linear));

                    break;
            }
            return false;
        }
    }


    private void updateXY(float x, float y) {
        if (!this.isHost) {
            this.setPosition(x, y);
        }
    }


    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
        batch.draw(toDraw, getX(), getY(), getWidth(), getHeight());
    }


    public void onWaitingStarted(String message){

    }

    public void onError(String message){}

    public void onGameStarted(String message){

    }

    public void onGameFinished(int code, boolean isRemote){

    }

    @Override
    public void onGameUpdateReceived (String message) {
        try {
            JSONObject data = new JSONObject(message);
            float x = (float)data.getDouble("x");
            float y = (float)data.getDouble("y");
            warrior.setPosition(x,y);

        } catch (Exception e) {
            // exception in onMoveNotificationReceived
        }
    }


}