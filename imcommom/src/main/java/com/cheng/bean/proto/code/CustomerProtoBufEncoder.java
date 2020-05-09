package com.cheng.bean.proto.code;

import com.cheng.bean.proto.msg.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;


/**
 * @Auther: cheng
 * @Date: 2020/5/9 23:25
 * @Description:
 */
@Slf4j
public class CustomerProtoBufEncoder extends MessageToByteEncoder<ProtoMsg.Message> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtoMsg.Message message, ByteBuf byteBuf) {
        log.info("CustomerProtoBufEncoder encode begin");
        byte[] bytes = message.toByteArray();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(message.toByteArray());
        log.info("CustomerProtoBufEncoder encode end");
    }
}
