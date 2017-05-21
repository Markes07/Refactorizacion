package actividad04.OperacionesPolinomio;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User {
	
	private User() {
		throw new IllegalAccessError("Utility class");
	}
	
	static final String coe = "Introduzca el coeficiente: ";
	static final String exp = "Introduzca el exponente: ";
	static final String err = "ERROR";
	 
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
			
		out.println("¡Bienvenido a nuestro programa de operaciones con Polinomios!");
		out.println("Introduce el coeficiente y el exponente de varios monomios para formar un polinomio");
		out.println("Introduzca (0) en el coeficiente y (0) en el exponente para terminar");
		
		//Polinomio 1
		Polinomio p = new Polinomio();
		Monomio m = null;
		out.println(coe);
		int c = scan.nextInt();
		out.println(exp);
		int e = scan.nextInt();
		m = new Monomio(c, e);
		p.add(m);
		
		while (c != 0 || e != 0) {				
			out.println(coe);
			c = scan.nextInt();
			out.println(exp);
			e = scan.nextInt();			
			m = new Monomio(c, e);
			p.add(m);
			if(c == 0 && e == 0) {
				p.remove(m);
			}
		}
		out.println("Ha creado el polinomio: " + p.toString());
		out.println();
		
		out.println("Repite el proceso para crear otro polinomio");	
		//Polinomio 2
		Polinomio p2 = new Polinomio();
		Monomio m2 = null;
		out.println(coe);
		int c2 = scan.nextInt();
		out.println(exp);
		int e2 = scan.nextInt();
		m2 = new Monomio(c2, e2);
		p2.add(m2);
		
		while (c2 != 0 || e2 != 0) {				
			out.println(coe);
			c2 = scan.nextInt();
			out.println(exp);
			e2 = scan.nextInt();			
			m2 = new Monomio(c2, e2);
			p2.add(m2);
			if(c2 == 0 && e2 == 0) {
				p2.remove(m2);
			}
		}
		out.println("Ha creado el polinomio: " + p.toString());
		out.println();

		
		out.println("Usted tiene los siguientes polinomios creados:");
		out.println("Polinomio 1: " + p.toString());
		out.println("Polinomio 2: " + p2.toString());
		p.simplificar();
		p2.simplificar();
		out.println("Polinomio 1 Simplificado: " + p.toString());
		out.println("Polinomio 2 Simplificado: " + p2.toString());

		out.println("Que operación desea realizar: (1) Sumar\t(2) Multiplicar\t(3) Dividir");
		out.println();
		int eleccion = scan.nextInt();
		if(eleccion == 1) {
			out.println("El resultado de la suma es: ");
			Polinomio suma = p.sumar(p2);
			out.println(suma);
		}
		if(eleccion == 2) {
			out.println("El resultado de la multiplicación es: ");
			Polinomio multiplicacion = p.multiplicar(p2);
			out.println(multiplicacion);
		}
		if(eleccion == 3) {
			out.println("Necesita un polinomio divisor de la forma x + t");
			out.println("Introduzca un polinomio raíz (x + t)");
			Polinomio p3 = new Polinomio();
			Monomio m3 = null;
			out.println("Introduzca el coeficiente (debe ser igual a 1): ");
			int c3 = scan.nextInt();
			if(c3 != 1) {
				out.println(err);
				while(c3 != 1) {
					out.println("Introduzca el coeficiente (debe ser igual a 1): ");
					c3 = scan.nextInt();
				}		
			}
			out.println("Introduzca el exponente (debe ser igual a 1): ");
			int e3 = scan.nextInt();
			if(e3 != 1) {
				out.println(err);
				while(e3 != 1) {
					out.println("Introduzca el exponente (debe ser igual a 1): ");
					e3 = scan.nextInt();
				}
			}
			m3 = new Monomio(c3, e3);
			p3.add(m3);
			
			
			out.println("Introduzca el coeficiente (t): ");
			c3 = scan.nextInt();
			out.println("Introduzca el exponente (debe ser igual a 0): ");
			e3 = scan.nextInt();
			while(e3 != 0) {
				out.println(err);
				out.println("Introduzca el coeficiente (debe ser igual a 0): ");
				c3 = scan.nextInt();
			}		
			m3 = new Monomio(c3, e3);
			p3.add(m3);
			out.println("Divisor creado: " + p3.toString());
			
			out.println("Selecciona el polinomio que desea dividir");
			out.println("Polinomio 1: " + p.toString());
			out.println("Polinomio 2: " + p2.toString());
			int s = scan.nextInt();
			if(s == 1) {
				out.println("Dividendo (en ruffini): " + p.convertirRuffini().toString());
				out.println("Divisor: " + p3.toString());
				out.println("El resultado es: ");
				out.println("Cociente: " + p.dividir(p3).get(0));
				out.println("Resto: " + p.dividir(p3).get(1));
			}
			if(s == 2) {
				out.println("Dividendo (en ruffini): " + p2.convertirRuffini().toString());
				out.println("Divisor: " + p3.toString());
				out.println("El resultado es: ");
				out.println("Cociente: " + p2.dividir(p3).get(0));
				out.println("Resto: " + p2.dividir(p3));
			}		
		}
	}
}