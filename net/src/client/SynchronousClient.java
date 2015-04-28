package client;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Warrior;
import core.implementations.PlayerProxy;
import core.interfaces.PlayerEntity;
import core.interfaces.root.EntityPool;
import core.PlayersPool;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс реализует функционал синхронного
 * многопоточного клиента многопользовательской игры.
 */
public class SynchronousClient implements GameClient {

    //private Logger logger = new Logger();
    private volatile EntityPool entityPool;
    private volatile ExecutorService service = Executors.newCachedThreadPool();
    ObjectInputStream objectInputStream;
    Socket clientSocket;

    private class IncomingReader extends Thread {

        @Override
        public void run() {
            Object packet;
            try {
                System.out.println("Incoming reader started!!");
                while ( (packet = objectInputStream.readObject()) != null) {
                    System.out.println("RECEIVED PACKET");
                    if (packet instanceof PlayerEntity) {

                        PlayerProxy player = (PlayerProxy)packet;
                            //Получаем игрока по имени
                            PlayerProxy updated = (PlayerProxy)entityPool.getEntityByName(player.getName());
                            //Обновляем информацию о координатах
                            updated.setX(player.getX());
                            updated.setY(player.getY());
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
            service.submit(new IncomingReader());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
