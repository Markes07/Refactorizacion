package actividad04.RaizPolinomio;

import java.util.ArrayList;
import java.util.Scanner;

import actividad04.OperacionesPolinomio.Monomio;
import actividad04.OperacionesPolinomio.Polinomio;

public class User {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		System.out.println("¡Bienvenido a nuestro programa para calcular la raíz cero de un polinomio!");
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
		p.simplificar();
		System.out.println("Polinomio simplificado: ==>" + p.toString());
		System.out.println();
				
		System.out.println("Introduce el los valores del intervalo para calcular lar raíz");
		System.out.println("Introduzca Xizq: ");
		int xIzq = scan.nextInt();
		System.out.println("Introduzca Xder: ");
		int xDer = scan.nextInt();
		
		Funcion fIzq = new Funcion() {

			@Override
			public double eval(double x) {
				double valor = 0;
				for(Monomio m: p.getPolinomio()) {
					valor = valor + m.getCoeficiente()*Math.pow(x, m.getExponente());
				}
				// TODO Auto-generated method stub
				return valor;
			}			
		};
		
		Funcion fDer = new Funcion() {

			@Override
			public double eval(double x) {
				double valor = 0;
				for(Monomio m: p.getPolinomio()) {
					valor = valor + m.getCoeficiente()*Math.pow(x, m.getExponente());
				}
				// TODO Auto-generated method stub
				return valor;
			}			
		};
		if((fIzq.eval(xIzq) > 0 && fDer.eval(xDer) > 0) 
				|| ((fIzq.eval(xIzq) < 0 && fDer.eval(xDer) < 0))) {
			while((fIzq.eval(xIzq) > 0 && fDer.eval(xDer) > 0) 
					|| ((fIzq.eval(xIzq) < 0 && fDer.eval(xDer) < 0))) {
				System.out.println("Intervalo incorrecto, introduzca otro intervalo");
				System.out.println("Introduzca Xizq: ");
				xIzq = scan.nextInt();
				System.out.println("Introduzca Xder: ");
				xDer = scan.nextInt();
			}		
		}
		
		Funcion f = new Funcion() {

			@Override
			public double eval(double x) {
				double valor = 0;
				for(Monomio m: p.getPolinomio()) {
					valor = valor + m.getCoeficiente()*Math.pow(x, m.getExponente());
				}
				// TODO Auto-generated method stub
				return valor;
			}			
		};

				
		FalsaPosicion fP = new FalsaPosicion();
		double raiz = fP.calcularRaiz(f, xIzq, xDer, 100, 1e-6);
		System.out.println("Calculando la raíz en el intervalo: [" + xIzq + "," + xDer + "]");
		System.out.println("Raíz: " + raiz);
		System.out.printf("Iteraciones\n" + fP.getIteraciones());

	}	
}
