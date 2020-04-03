package com.company;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class TStacks {
    Stacks stack=new Stacks();

    @Test
    void pop() {
        assertThrows(EmptyStackException.class,()->stack.pop());
        stack.push(5);
        assertEquals(5,stack.pop());
        stack.push(3);
        stack.push(8);
        assertEquals(8,stack.pop());
    }

    @Test
    void peek() {
        stack.push(5);
        assertEquals(5,stack.peek());
        stack.push(6);
        stack.push(78);
        assertEquals(78,stack.peek());
        stack.push("bbb");
        assertEquals("bbb",stack.peek());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertThrows(EmptyStackException.class,()->stack.peek());
    }

    @Test
    void push() {
        stack.push(5);
        assertEquals(5,stack.peek());
        stack.push(3);
        assertEquals(3,stack.peek());
        stack.push("a");
        assertEquals("a",stack.peek());
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(5);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0,stack.size());
        stack.push(2);
        stack.push("b");
        assertEquals(2,stack.size());
        stack.pop();
        assertEquals(1,stack.size());
    }
}