package client.initcialier;

import client.handler.ClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Класс инициализирует начальные кодеки канала
 */
public class ClientInitcializer extends ChannelInitializer<Channel>{
    private ClientHandler handler;
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("framer" , new DelimiterBasedFrameDecoder(8192 , Delimiters.lineDelimiter()));
        pipeline.addLast("decoder" , new StringDecoder());
        pipeline.addLast("encoder" , new StringEncoder());
        handler = new ClientHandler();
        pipeline.addLast("handler" , handler );
    }
    public ClientHandler geClientHandler() {
        return this.handler;
    }
}
