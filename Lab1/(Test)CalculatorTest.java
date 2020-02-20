package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    void add() {
        assertEquals(5,calc.add(2,3));
        assertEquals(10,calc.add(1,9));
        assertEquals(-1,calc.add(0,-1));
        assertEquals(0,calc.add(2,-2));
        assertEquals(-4,calc.add(-2,-2));
    }

    @Test
    void divide() {
        assertEquals(5,calc.divide(20,4));
        assertEquals(1,calc.divide(1,1));
        assertEquals(0,calc.divide(0,100));
        assertEquals(0.5,calc.divide(2,4));
        assertThrows(RuntimeException.class,()->calc.divide(2,0));
    }
}
