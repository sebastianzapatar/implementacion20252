package com.sindocker.sindocker;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PruebaTest {
    @Test
    void testSuma() {
        Pruebas calc = new Pruebas();
        int resultado = calc.sumar(2, 3);
        assertEquals(5,resultado);
    }

    @Test
    void testMultiplicacion() {
        Pruebas calc = new Pruebas();
        int resultado = calc.multiplicar(4, 2);
        assertEquals(8, resultado);
    }
}
