package actividad04.RaizPolinomio;

import java.util.ArrayList;

public class FalsaPosicion {
	
	ArrayList iteraciones = new ArrayList();
	
	public double calcularRaiz(Pol f, double x0, double x1, int n, double error) {	
		double r = Double.NaN;
		double x = x0;
		int k = 0;
		double r0 = x0;
		double r1 = x1;
		String cadena = "Xizq\t\t\t\tXder\t\t\t\tX\t\t\t\tF(x)";
		iteraciones.add(cadena);
		
		while(Math.abs(f.eval(x)) > error && k < n) {
			x = (r0*f.eval(r1) - r1*f.eval(r0)) / (f.eval(r1) - f.eval(r0));
			cadena = "\n" + r0 + "\t\t" + r1 + "\t\t" + x + "\t\t" + f.eval(x);
			iteraciones.add(cadena);
			if(f.eval(r0)*f.eval(x) < 0) {
				r1 = x;
			}
			else {
				r0 = x;
			}
			k++;
		}
		if(k < n) {
			r = x;			
		}
		return r;		
	}
	
	public ArrayList getIteraciones() {
		return iteraciones;
	}
}