package client.handler;

import interfaces.Listener;
import interfaces.Observer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import java.util.LinkedList;
import java.util.List;


/**
 * Класс-обработчик входящих сообщений
 */
public class ClientHandler extends ChannelInboundMessageHandlerAdapter<String> implements Observer {

    private volatile List<Listener> listeners = new LinkedList<Listener>();
    @Override
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        notifyObservers(message); //Оповещаем слушателей
    }

    @Override
    public synchronized void notifyObservers(String message) {
        for(Listener listener : listeners) {
            listener.update(message);
        }
    }

    @Override
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }
}
