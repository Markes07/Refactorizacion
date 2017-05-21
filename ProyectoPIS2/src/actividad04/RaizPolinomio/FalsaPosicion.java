package actividad04.RaizPolinomio;

import java.util.ArrayList;

public class FalsaPosicion  {
	
	ArrayList iteraciones = new ArrayList();
	
	public double calcularRaiz(Funcion f, double x0, double x1, int n, double error) {	
		double r = Double.NaN;
		double x = x0;
		int k = 0;
		String cadena = "Xizq\t\t\t\tXder\t\t\t\tX\t\t\t\tF(x)";
		iteraciones.add(cadena);
		
		while(Math.abs(f.eval(x)) > error && k < n) {
			x = (x0*f.eval(x1) - x1*f.eval(x0)) / (f.eval(x1) - f.eval(x0));
			cadena = "\n" + x0 + "\t\t" + x1 + "\t\t" + x + "\t\t" + f.eval(x);
			iteraciones.add(cadena);
			if(f.eval(x0)*f.eval(x) < 0) {
				x1 = x;
			}
			else {
				x0 = x;
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
