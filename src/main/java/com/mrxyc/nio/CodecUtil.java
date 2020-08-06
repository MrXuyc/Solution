package com.mrxyc.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class CodecUtil {
    public static ByteBuffer read(SocketChannel clientSocketChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int count = 0;
        try {
            count = clientSocketChannel.read(byteBuffer);
            if (count == -1) {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return byteBuffer;
    }

    public static String newString(ByteBuffer readBuffer) {
        //写模式切换为读模式
        readBuffer.flip();
        byte[] bytes = new byte[readBuffer.remaining()];
        System.arraycopy(readBuffer.array(), readBuffer.position(), bytes, 0, readBuffer.remaining());
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }

    public static void write(SocketChannel clientSocketChannel, String content) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            buffer.put(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
        buffer.flip();
        try {
            clientSocketChannel.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
