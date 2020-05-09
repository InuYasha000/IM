package com.cheng.bean.proto.code;

import com.cheng.bean.proto.msg.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Auther: cheng
 * @Date: 2020/5/9 23:41
 * @Description:
 */
@Slf4j
public class CustomerProtoBufDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception{
        log.info("CustomerProtoBufDecoder encode begin");
        if(byteBuf.readableBytes()<8){
            return;
        }
        //标记当前readindex位置
        byteBuf.markReaderIndex();
        int byteLength = byteBuf.readInt();
        if(byteBuf.readableBytes()>byteLength){
            //重新读
            byteBuf.resetReaderIndex();
        }
        byte[] array;
        if(byteBuf.hasArray()){
            //堆缓冲
            array = byteBuf.slice().array();
        }else {
            //直接缓冲
            array = new byte[byteLength];
            byteBuf.readBytes(array);
        }
        ProtoMsg.Message message = ProtoMsg.Message.parseFrom(array);
        list.add(message);
        log.info("CustomerProtoBufDecoder encode end");
    }

}
