package client;

import core.interfaces.root.EntityPool;

public interface GameClient {
    public EntityPool getEntityPool();
    public void connect(String ipAdress , int port);
}
