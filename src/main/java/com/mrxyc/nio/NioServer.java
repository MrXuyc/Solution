package com.mrxyc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioServer() throws IOException {
        //打开一个server socket channel
        serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //创建selector
        selector = Selector.open();
        //注册selector到 server socket channel
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server 启动完成");
        handleKeys();
    }

    private void handleKeys() throws IOException {
        while (true) {
            int count = selector.select(30 * 1000L);
            if (count == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (!selectionKey.isValid()) {
                    continue;
                }
                handlekey(selectionKey);
            }
        }

    }

    private void handlekey(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            handleAcceptableKey(selectionKey);
        }
        if (selectionKey.isReadable()) {
            handleReadableKey(selectionKey);
        }
        if (selectionKey.isWritable()) {
            handleWriteableKey(selectionKey);
        }
    }

    private void handleAcceptableKey(SelectionKey selectionKey) throws IOException {
        SocketChannel clientSocketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        clientSocketChannel.configureBlocking(false);
        System.out.println("接受新 channel");
        //一般服务端不会主动发起请求
        clientSocketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<String>());
    }

    private void handleReadableKey(SelectionKey selectionKey) throws ClosedChannelException {
        SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);
        if (readBuffer == null) {
            System.out.println("断开 channel");
            //取消注册
            clientSocketChannel.register(selector, 0);
            return;
        }
        //打印数据
        if (readBuffer.position() > 0) {
            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取数据:" + content);
            ArrayList<String> responseQueue = (ArrayList<String>) selectionKey.attachment();
            responseQueue.add("响应:" + content);
            //
            clientSocketChannel.register(selector, SelectionKey.OP_WRITE, selectionKey.attachment());
        }
    }

    private void handleWriteableKey(SelectionKey selectionKey) throws ClosedChannelException {
        SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();
        ArrayList<String> responseQueue = (ArrayList<String>) selectionKey.attachment();
        for (String content : responseQueue) {
            System.out.println("写入数据：" + content);
            CodecUtil.write(clientSocketChannel, content);
        }
        responseQueue.clear();
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
    }


}
