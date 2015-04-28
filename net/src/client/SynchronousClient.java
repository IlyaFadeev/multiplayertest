package client;

import core.interfaces.root.EntityPool;
import core.PlayersPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс реализует функционал синхронного
 * многопоточного клиента многопользовательской игры.
 */
public class SynchronousClient implements GameClient {

    private volatile EntityPool playersPool;
    private volatile ExecutorService service = Executors.newCachedThreadPool();
    public SynchronousClient() {
      playersPool  = new PlayersPool();
    }
//f
    @Override
    public void connect(String ipAdress, int port) {

    }
}
