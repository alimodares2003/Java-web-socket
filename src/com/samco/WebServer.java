package com.samco;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class WebServer {
    private Socket socket = null;
    private ObjectInputStream input = null;

    public void startServer() throws IOException {
        try {
//            ServerSocket serverSocket = new ServerSocket(8080);
//            socket = serverSocket.accept();
            Selector selector = Selector.open();
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress("localhost", 8080));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(2);

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        SocketChannel client = socketChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        client.read(buffer);
                        buffer.flip();
                        System.out.println("receive=" + new String(buffer.array()).trim());
//                        client.write(buffer);
                        buffer.clear();
                    }
                    iter.remove();
                }
            }

//            input = new ObjectInputStream(socket.getInputStream());

//            while (true) {
//                System.out.println(input.readUTF());
//                try {
//                    lastMessage = obj;
//                Object obj = null;
//                try {
//                    obj = input.readObject();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                if (obj instanceof Message) {
//                    System.out.println(((Message) obj).text);
//                }
//                    else {
//                        System.out.println("Can't parse object");
//                    }
//                } catch (Exception e) {
//                    socket.close();
//                    stream.close();
//                }
//            }
//            socket.close();
//            out.close();
        } catch (IOException e) {
//            socket.close();
//            input.close();
            e.printStackTrace();
        }
    }
}
