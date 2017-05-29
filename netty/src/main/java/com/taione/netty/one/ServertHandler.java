package com.taione.netty.one;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class ServertHandler extends ChannelInboundHandlerAdapter {

    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws
    // Exception {
    // String body = (String) msg;
    // System.out.println("server" + body);// 前面已经定义了接收为字符串，这里直接接收字符串就可以
    // // 服务端给客户端的响应
    // String response = " hi client!$_";// 发送的数据以定义结束的字符串结尾
    // ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));//
    // 发送必须还是ByteBuf类型
    // }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // Do something with msg
            Send send = (Send) msg;
            System.out.println("client发送：" + send);
            Receive receive = new Receive();
            receive.setId(send.getId());
            receive.setMessage(send.getMessage());
            receive.setName(send.getName());
            ctx.writeAndFlush(receive);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
