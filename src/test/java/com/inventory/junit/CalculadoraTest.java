package com.inventory.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

	Calculadora cal;

	// se ejecuta por única vez cuando se arranque la prueba unitaria
	@BeforeAll
	public static void primer() {
		System.out.println("primero");
	}

	@AfterAll
	public static void ultimo() {
		System.out.println("ultimo");
	}

	@AfterEach
	public void ultimoPorCadaPrueba() {
		System.out.println("ultimo por cada prueba");
	}

	// se ejecuta antes de cada pruba unitaria
	@BeforeEach
	public void primeroPorCadaPrueba() {
		System.out.println("primero por cada prueba");
		cal = new Calculadora();
	}

	@Test
	@DisplayName("prueba de sumar calculadora")
	public void sumarTest() {
		assertEquals(2, cal.sumar(1, 1));
		assertFalse(cal.sumar(2, 2) == 5);
	}

	@Test
	@DisplayName("prueba de restar calculadora")
	@Disabled("se desabilitó por efectos de prueba de anotación")
	public void restarTest() {
		assertEquals(4, cal.restar(5, 1));
	}

	@Test
	@DisplayName("prueba de multiplicar calculadora")
	public void multiplicarTest() {
		assertEquals(25, cal.multiplicar(5, 5));
	}

	@Test
	@DisplayName("prueba de dividir calculadora")
	public void dividrTest() {
		assertTrue(cal.dividir(10, 2) == 5);
	}

}
