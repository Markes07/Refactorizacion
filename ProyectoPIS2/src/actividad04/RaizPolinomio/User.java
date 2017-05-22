package actividad04.RaizPolinomio;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

	private User() {
		throw new IllegalAccessError("Utility class");
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		PrintStream out = System.out;
		
		out.println("¡Bienvenido a nuestro programa para calcular la raíz cero de un polinomio!");
		out.println("Introduce el coeficiente y el exponente de varios monomios para formar un polinomio");
		out.println("Introduzca (0) en el coeficiente y (0) en el exponente para terminar");
		
		//Polinomio 1
		Pol p = new Pol();
		Mon m;
		out.println("Introduzca el coeficiente: ");
		int c = scan.nextInt();
		out.println("Introduzca el exponente: ");
		int e = scan.nextInt();
		m = new Mon(c, e);
		p.add(m);
		
		while (c != 0 || e != 0) {				
			out.println("Introduzca el coeficiente: ");
			c = scan.nextInt();
			out.println("Introduzca el exponente: ");
			e = scan.nextInt();			
			m = new Mon(c, e);
			p.add(m);
			if(c == 0 && e == 0) {
				p.remove(m);
			}
		}
		
		out.println("Ha creado el polinomio: " + p.toString());
		p.simplificar();
		out.println("Polinomio simplificado: ==>" + p.toString());
		out.println();
				
		out.println("Introduce el los valores del intervalo para calcular lar raíz");
		out.println("Introduzca Xizq: ");
		int i = scan.nextInt();
		out.println("Introduzca Xder: ");
		int d = scan.nextInt();
		double xIzq = i*1.0;
		double xDer = d*1.0;

		if((p.eval(xIzq) > 0 && p.eval(xDer) > 0) 
				|| ((p.eval(xIzq) < 0 && p.eval(xDer) < 0))) {
			while((p.eval(xIzq) > 0 && p.eval(xDer) > 0) 
					|| ((p.eval(xIzq) < 0 && p.eval(xDer) < 0))) {
				out.println("Intervalo incorrecto, introduzca otro intervalo");
				out.println("Introduzca Xizq: ");
				xIzq = scan.nextInt();
				out.println("Introduzca Xder: ");
				xDer = scan.nextInt();
			}		
		}
				
		FalsaPosicion fP = new FalsaPosicion();
		double raiz = fP.calcularRaiz(p, xIzq, xDer, 100, 1e-6);
		out.println("Calculando la raíz en el intervalo: [" + xIzq + "," + xDer + "]");
		out.println("Raíz: " + raiz);
		StringBuilder s = new StringBuilder();
		s.append(fP.getIteraciones());
		String cadena = String.format("%s", s.toString());
		out.println(cadena);	
	}
}