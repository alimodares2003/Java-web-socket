package com.samco;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            new WebServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
