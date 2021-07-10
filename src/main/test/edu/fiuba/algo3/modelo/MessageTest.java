package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageTest {
    @Test
    void messageGreeting() {
        Message message = new Message("Hello world!");

        assertEquals("Hello world!", message.greet());
    }
}