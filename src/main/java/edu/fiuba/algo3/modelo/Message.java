package edu.fiuba.algo3.modelo;

public class Message {
    private final String content;

    public Message(String content) {
        this.content = content;
    }

    public String greet() {
        return content;
    }
}
