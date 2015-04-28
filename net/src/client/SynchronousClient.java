package client;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Warrior;
import core.implementations.PlayerProxy;
import core.interfaces.observer.Listener;
import core.interfaces.root.Entity;
import core.interfaces.root.EntityPool;
import core.PlayersPool;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс реализует функционал синхронного
 * многопоточного клиента многопользовательской игры.
 */
public class SynchronousClient implements GameClient, Listener {

    private volatile EntityPool entityPool;
    private volatile ExecutorService service = Executors.newCachedThreadPool();
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Socket clientSocket;

    @Override
    public void update(final Entity entity)  {
        //System.out.print("update " + entity.getName() + "...");
        //Посылаем пакет об изменении состояния

        try {
            objectOutputStream.writeObject(entity);
            objectOutputStream.flush();
         //   System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class IncomingReader extends Thread {
        @Override
        public void run() {
            Object packet;
            try {
               // System.out.println("Incoming reader started!!");
                while ( (packet = objectInputStream.readObject()) != null) {
                   // System.out.println("RECEIVED PACKET");


                    final PlayerProxy player = (PlayerProxy) packet;
                    System.out.println(player.getName() + " packet");
                    //Получаем игрока по имени

                        if (!entityPool.contains(player)) { //Если пул не содержит игрока, добавляем его в пул

                            Gdx.app.postRunnable(new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Warrior warrior = new Warrior();
                                    warrior.setName(player.getName());
                                    entityPool.add(new PlayerProxy(warrior));
                                    MyGdxGame.getInstance().addActor(warrior);
                                    System.out.println(warrior.getName() + " connected!");
                                }
                            }));

                        } else {
                            //Обновляем информацию о координатах
                            PlayerProxy updated = (PlayerProxy) entityPool.getEntityByName(player.getName());
                            System.out.println("");

                            System.out.println( "player " + player.getX() + " " + player.getY());
                            updated.updateXY(player.getX() , player.getY());
                            System.out.println("info about " + player.getName() + " updated!");
                        }
                }
            } catch(Throwable e) {
                e.printStackTrace();
            }
        }
    }


    public SynchronousClient() {
      this.entityPool = new PlayersPool();

    }

    @Override
    public EntityPool getEntityPool() {
        return this.entityPool;
    }

    @Override
    public void connect(String ipAdress, int port) {
        try {
            this.clientSocket = new Socket(ipAdress , port);
            this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
           // service.submit(new IncomingReader());
            service.submit(new IncomingReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
