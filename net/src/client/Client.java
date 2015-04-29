package client;

import client.handler.ClientHandler;
import client.initcialier.ClientInitcializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ChannelFactory;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Главный класс клиента
 */
public class Client {
    private String host;
    private int port;
    Channel clientChannel;
    ClientInitcializer initcializer;
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            initcializer = new ClientInitcializer();
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channelFactory(new ChannelFactory<Channel>() {
                        @Override
                        public Channel newChannel() {
                            return new NioSocketChannel();
                        }
                    })
                    .handler(initcializer);

            Channel channel = bootstrap.connect(host , port).sync().channel();
            this.clientChannel = channel;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
         //   group.shutdownGracefully();
        }
    }
    public void sendMessage(String message) {
        clientChannel.write(message + "\r\n");
    }

    public ClientHandler getClientHandler() {
        return initcializer.geClientHandler();
    }

}
