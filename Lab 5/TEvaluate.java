package com.company;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class TEvaluate {
    Evaluate ev=new Evaluate();

    @Test
    void infixToPostfix() {
        assertEquals("10 20 + ",ev.infixToPostfix("10  +  20"));
        assertEquals("2 3 4 * + ",ev.infixToPostfix("2 + 3 * 4"));
        assertEquals("a b c - d + / e a - * c * ",ev.infixToPostfix("(a / (b - c + d)) * (e - a) * c"));
        assertEquals("a b / c - d e * + a c * - ",ev.infixToPostfix("a / b - c + d * e - a * c"));
        assertEquals("1 2 5 2 + * 3 * + 0 1 - - ",ev.infixToPostfix("1+2*(5+2)*3--1"));
        assertEquals("1 5 2 + 1 * + 1 - ",ev.infixToPostfix("1+((5+2)*1)-1"));
        assertEquals("10 20 200 0 + * + ",ev.infixToPostfix("10+20*(200+0)"));
        assertNotEquals("1 2 + ",ev.infixToPostfix("1+2+3"));
        assertNotEquals("5 6 + - ",ev.infixToPostfix("7 *8"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("(1+2"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("1+2+()"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("1++2"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("1+2-"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("/1+2"));
        assertThrows(EmptyStackException.class,()->ev.infixToPostfix("8*2)"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("(1+2)(5*8)"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix(""));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("8*(1+2)3"));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("1+2(5/8)"));
    }

    @Test
    void evaluate() {
        assertThrows(IllegalArgumentException.class,()->ev.evaluate("6 0 / "));
        assertThrows(IllegalArgumentException.class,()->ev.infixToPostfix("a b + "));
        assertEquals(14,ev.evaluate("2 3 4 * + "));
        assertEquals(7,ev.evaluate("1 5 2 + 1 * + 1 - "));
        assertEquals(44,ev.evaluate("1 2 5 2 + * 3 * + 0 1 - - "));
        assertNotEquals(4,ev.evaluate("1 8 2 * + "));
        assertEquals(4010,ev.evaluate("10 20 200 0 + * + "));
    }
}