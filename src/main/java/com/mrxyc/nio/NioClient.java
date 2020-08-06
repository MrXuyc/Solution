package com.mrxyc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class NioClient {
    private SocketChannel clientSocketChannel;
    private Selector selector;
    private final List<String> responseQueue = new ArrayList<>();

    private CountDownLatch connected = new CountDownLatch(1);

    public NioClient() throws IOException, InterruptedException {
        clientSocketChannel = SocketChannel.open();
        clientSocketChannel.configureBlocking(false);
        selector = Selector.open();
        //对连接服务器成功感兴趣
        clientSocketChannel.register(selector, SelectionKey.OP_CONNECT);
        clientSocketChannel.connect(new InetSocketAddress("localhost", 8080));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handleKeys();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (connected.getCount() != 0) {
            connected.await();
        }
        System.out.println("client启动完成");
    }

    private void handleKeys() throws IOException {
        while (true) {
            int selectNums = selector.select(30 * 1000L);
            if (selectNums == 0) {
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
                handleKey(selectionKey);
            }
        }

    }

    private void handleKey(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isConnectable()) {
            handleConnectableKey(selectionKey);
        }
        if (selectionKey.isReadable()) {
            handleReadableKey(selectionKey);
        }
        if (selectionKey.isWritable()) {
            handleWriteableKey(selectionKey);
        }
    }

    private void handleConnectableKey(SelectionKey selectionKey) throws IOException {
        if (!clientSocketChannel.isConnectionPending()) {
            return;
        }

        clientSocketChannel.finishConnect();

        System.out.println("接受新channel");
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);

        connected.countDown();
    }


    private void handleReadableKey(SelectionKey selectionKey) {
        SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);
        if (readBuffer.position() > 0) {
            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取数据:" + content);
        }
    }


    private void handleWriteableKey(SelectionKey selectionKey) throws ClosedChannelException {
        SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();
        ArrayList<String> responseQueue = (ArrayList<String>) selectionKey.attachment();
        for (String content : responseQueue) {
            System.out.println("写入数据:" + content);
            CodecUtil.write(clientSocketChannel, content);
        }
        responseQueue.clear();

        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    public synchronized void send(String content) throws ClosedChannelException {
        responseQueue.add(content);
        System.out.println("写入数据" + content);
        clientSocketChannel.register(selector, SelectionKey.OP_WRITE, responseQueue);
        selector.wakeup();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioClient nioClient = new NioClient();
        for (int i = 0; i < 30; i++) {
            nioClient.send("1你好" + i);
            Thread.sleep(1000L);
        }
    }

}
