package actividad09.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import actividad04.OperacionesPolinomio.Monomio;
import actividad04.OperacionesPolinomio.Polinomio;

public class PolinomioTest {

	@Test
	public void testOperaciones() {
		Monomio m1 = new Monomio(1, 2);
		Monomio m2 = new Monomio(1, 1);
		Monomio m3 = new Monomio(3, 1);
		Monomio m4 = new Monomio(5, 0);
			
		Polinomio p1 = new Polinomio();
		Polinomio p2 = new Polinomio();
		
		//Creación primer Polinomio x^2 + x
		p1.add(m1);
		p1.add(m2);
		
		//Creación segundo Polinomio 3x + 5
		p2.add(m3);
		p2.add(m4);
		
		//Sumar
		Polinomio suma = new Polinomio();
		suma = p1.sumar(p2);
		
		//Multiplicar
		Polinomio multiplicacion = new Polinomio();
		multiplicacion = p1.multiplicar(p2);
		
		//Dividir (x + t)
		Monomio m5 = new Monomio(1, 1);
		Monomio m6 = new Monomio(5, 0);
		Polinomio p3 = new Polinomio();
		p3.add(m5);
		p3.add(m6);
		ArrayList<Polinomio> resDivision = new ArrayList<Polinomio>();
		Polinomio cociente = new Polinomio();
		Polinomio resto = new Polinomio();
		resDivision = p1.dividir(p3);
		cociente = resDivision.get(0);
		resto = resDivision.get(1);
		
		//Ordenar
		Monomio m7 = new Monomio(1, 2);
		Monomio m8 = new Monomio(1, 1);
		Polinomio p4 = new Polinomio();
		p4.add(m8);
		p4.add(m7);
				
		//Simplificar
		Monomio m9 = new Monomio(1, 2);
		Monomio m10 = new Monomio(1, 2);
		Polinomio p5 = new Polinomio();
		p5.add(m9);
		p5.add(m10);
		
		//Tests
		assertEquals(suma.toString(), "(1)x^2 + (4)x^1 + (5)");
		assertEquals(multiplicacion.toString(), "(3)x^3 + (8)x^2 + (5)x^1");
		assertEquals(cociente.toString(), "(1)x^1 + (-4)");
		assertEquals(resto.toString(), "(20)");
		assertEquals(p4.toString(), "(1)x^1 + (1)x^2"); //Sin ordenar
		p4.ordenar();
		assertEquals(p5.toString(), "(1)x^2 + (1)x^2"); //Sin simplificar
		p5.simplificar();
		assertEquals(p5.toString(), "(2)x^2"); //Simplificado
	}
}