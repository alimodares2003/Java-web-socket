package com.samco;

import java.io.Serializable;

public class Message implements Serializable {
    String text;

    Message(String text) {
        this.text = text;
    }
}
