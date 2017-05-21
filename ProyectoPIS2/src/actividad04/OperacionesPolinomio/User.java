package actividad04.OperacionesPolinomio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		System.out.println("¡Bienvenido a nuestro programa de operaciones con Polinomios!");
		System.out.println("Introduce el coeficiente y el exponente de varios monomios para formar un polinomio");
		System.out.println("Introduzca (0) en el coeficiente y (0) en el exponente para terminar");
		
		//Polinomio 1
		Polinomio p = new Polinomio();
		Monomio m = null;
		System.out.println("Introduzca el coeficiente: ");
		int c = scan.nextInt();
		System.out.println("Introduzca el exponente: ");
		int e = scan.nextInt();
		m = new Monomio(c, e);
		p.add(m);
		
		while (c != 0 || e != 0) {				
			System.out.println("Introduzca el coeficiente: ");
			c = scan.nextInt();
			System.out.println("Introduzca el exponente: ");
			e = scan.nextInt();			
			m = new Monomio(c, e);
			p.add(m);
			if(c == 0 && e == 0) {
				p.remove(m);
			}
		}
		System.out.println("Ha creado el polinomio: " + p.toString());
		System.out.println();
		
		System.out.println("Repite el proceso para crear otro polinomio");	
		//Polinomio 2
		Polinomio p2 = new Polinomio();
		Monomio m2 = null;
		System.out.println("Introduzca el coeficiente: ");
		int c2 = scan.nextInt();
		System.out.println("Introduzca el exponente: ");
		int e2 = scan.nextInt();
		m2 = new Monomio(c2, e2);
		p2.add(m2);
		
		while (c2 != 0 || e2 != 0) {				
			System.out.println("Introduzca el coeficiente: ");
			c2 = scan.nextInt();
			System.out.println("Introduzca el exponente: ");
			e2 = scan.nextInt();			
			m2 = new Monomio(c2, e2);
			p2.add(m2);
			if(c2 == 0 && e2 == 0) {
				p2.remove(m2);
			}
		}
		System.out.println("Ha creado el polinomio: " + p.toString());
		System.out.println();

		
		System.out.println("Usted tiene los siguientes polinomios creados:");
		System.out.println("Polinomio 1: " + p.toString());
		System.out.println("Polinomio 2: " + p2.toString());
		p.simplificar();
		p2.simplificar();
		System.out.println("Polinomio 1 Simplificado: " + p.toString());
		System.out.println("Polinomio 2 Simplificado: " + p2.toString());

		System.out.println("Que operación desea realizar: (1) Sumar\t(2) Multiplicar\t(3) Dividir");
		System.out.println();
		int eleccion = scan.nextInt();
		if(eleccion == 1) {
			System.out.println("El resultado de la suma es: ");
			Polinomio suma = p.sumar(p2);
			System.out.println(suma);
		}
		if(eleccion == 2) {
			System.out.println("El resultado de la multiplicación es: ");
			Polinomio multiplicacion = p.multiplicar(p2);
			System.out.println(multiplicacion);
		}
		if(eleccion == 3) {
			System.out.println("Necesita un polinomio divisor de la forma x + t");
			System.out.println("Introduzca un polinomio raíz (x + t)");
			Polinomio p3 = new Polinomio();
			Monomio m3 = null;
			System.out.println("Introduzca el coeficiente (debe ser igual a 1): ");
			int c3 = scan.nextInt();
			if(c3 != 1) {
				System.out.println("ERROR");
				while(c3 != 1) {
					System.out.println("Introduzca el coeficiente (debe ser igual a 1): ");
					c3 = scan.nextInt();
				}		
			}
			System.out.println("Introduzca el exponente (debe ser igual a 1): ");
			int e3 = scan.nextInt();
			if(e3 != 1) {
				System.out.println("ERROR");
				while(e3 != 1) {
					System.out.println("Introduzca el exponente (debe ser igual a 1): ");
					e3 = scan.nextInt();
				}
			}
			m3 = new Monomio(c3, e3);
			p3.add(m3);
			
			
			System.out.println("Introduzca el coeficiente (t): ");
			c3 = scan.nextInt();
			System.out.println("Introduzca el exponente (debe ser igual a 0): ");
			e3 = scan.nextInt();
			while(e3 != 0) {
				System.out.println("ERROR");
				System.out.println("Introduzca el coeficiente (debe ser igual a 0): ");
				c3 = scan.nextInt();
			}		
			m3 = new Monomio(c3, e3);
			p3.add(m3);
			System.out.println("Divisor creado: " + p3.toString());
			
			System.out.println("Selecciona el polinomio que desea dividir");
			System.out.println("Polinomio 1: " + p.toString());
			System.out.println("Polinomio 2: " + p2.toString());
			ArrayList<Polinomio> lista = new ArrayList<Polinomio>();
			int s = scan.nextInt();
			if(s == 1) {
				System.out.println("Dividendo (en ruffini): " + p.convertirRuffini().toString());
				System.out.println("Divisor: " + p3.toString());
				lista = p.dividir(p3);
				System.out.println("El resultado es: ");
				System.out.println("Cociente: " + lista.get(0));
				System.out.println("Resto: " + lista.get(1));
			}
			if(s == 2) {
				System.out.println("Dividendo (en ruffini): " + p2.convertirRuffini().toString());
				System.out.println("Divisor: " + p3.toString());
				lista = p2.dividir(p3);
				System.out.println("El resultado es: ");
				System.out.println("Cociente: " + lista.get(0));
				System.out.println("Resto: " + lista.get(1));
			}		
		}
	}
}
