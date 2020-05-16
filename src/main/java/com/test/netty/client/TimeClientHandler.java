package com.test.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private final byte[] req = ("QUERY Time ORDER" + System.getProperty("line.separator")).getBytes();

    public TimeClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        IntStream.range(0,100).forEach((i)->{
            ByteBuf firstMessage = Unpooled.buffer(req.length);
            firstMessage.writeBytes(req);
            ctx.writeAndFlush(firstMessage);
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req =  new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,UTF_8);
        System.out.println("Now is : "+body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
