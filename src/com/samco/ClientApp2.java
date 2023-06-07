package com.samco;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientApp2 {
    public static void startApp() {
        Socket socket;
        try {
            socket = new Socket("127.0.0.1", 8080);
            DataInputStream stream = new DataInputStream(System.in);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                out.writeObject(new Message(stream.readLine()));
//                out.writeUTF(stream.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
//        startApp();
        DataInputStream stream = new DataInputStream(System.in);
        EchoClient echoClient = EchoClient.start();
        while (true) {
            try {
                echoClient.sendMessage(stream.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
