package actividad04.OperacionesPolinomio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polinomio {
	
	private final class Comparador implements Comparator<Monomio> {
		@Override
		public int compare(Monomio m1, Monomio m2) {
			return Integer.compare(m2.getExponente(), m1.getExponente());
		}
	}
	
	private ArrayList<Monomio> pol;
	
	public Polinomio() {
		pol = new ArrayList<>();
	}
	
	public Polinomio(ArrayList<Monomio> p) {
		pol = p;
	}
	
	public ArrayList<Monomio> getPolinomio() {
		return pol;
	}
		
	public boolean add(Monomio monomio){
		/*boolean encontrado = false;
		for(Monomio m: polinomio) {
			if(polinomio.contains(m)) {
				encontrado = true;
			}
			else {
				polinomio.add(monomio);
				encontrado = false;
			}
		}
		return encontrado;*/
		pol.add(monomio);
		return true;
	}
	
	public boolean remove(Monomio monomio) {
		pol.remove(monomio);
		this.ordenar();
		return true;
	}
	
	public boolean isOrdenado() {
		boolean ordenado = false;
		for(int i = 0; i < pol.size() - 1; i++) {
			if(pol.get(i).getExponente() > pol.get(i + 1).getExponente()) {
				ordenado = true;
			}
			else {
				ordenado = false;
			}
		}
		return ordenado;
	}
	
	public void ordenar() {
		Collections.sort(pol, new Comparador());
	}	
	
	public void simplificar() {
		ArrayList<Integer> exponentes = new ArrayList<>();
		ArrayList<Monomio> monomiosMismoExponente = new ArrayList<>();
		ArrayList<Monomio> simplificado = new ArrayList<>();
		for(Monomio m: pol) {
			exponentes.add(m.getExponente());
		}
		int max = 0;
		for(Integer i: exponentes) {
			if(i > max) {
				max = i;
			}
		}
		int i = 0;
		boolean encontrado = false;
		while(i <= max) {
			int suma = 0;
			for(Monomio m: pol) {
				if(m.getExponente() == i) {
					monomiosMismoExponente.add(m);
					encontrado = true;
				}			
			}
			for(Monomio m2: monomiosMismoExponente) {		
				suma = suma + m2.getCoeficiente();
			}
			monomiosMismoExponente.clear();
			if(encontrado) {
				Monomio mSimplificado = new Monomio(suma, i);
				simplificado.add(mSimplificado);
				encontrado = false;
			}
			i++;
		}
		pol = simplificado;
		ordenar();
	}
	
	public Polinomio sumar(Polinomio p) {
		ArrayList<Monomio> lista = new ArrayList<>();
		ArrayList<Integer> exponentes = new ArrayList<>();
		for(Monomio m: pol) {
			for(Monomio m2: p.pol) {
				if(m.getExponente() == m2.getExponente()) {
					int c = m.getCoeficiente() + m2.getCoeficiente();
					Monomio m3 = new Monomio(c, m.getExponente());
					lista.add(m3);
					exponentes.add(m.getExponente());
				}
			}
		}
		for(Monomio m: pol) {
			if(!exponentes.contains(m.getExponente())) {
				lista.add(m);
			}
		}
		for(Monomio m: p.pol) {
			if(!exponentes.contains(m.getExponente())) {
				lista.add(m);
			}
		}
		Polinomio suma = new Polinomio(lista);
		suma.ordenar();
		return suma;
	}
	
	public Polinomio multiplicar(Polinomio p) {
		ArrayList<Monomio> lista = new ArrayList<>();
		for(Monomio m: pol) {
			for(Monomio m2: p.pol) {
				int c = m.getCoeficiente()*m2.getCoeficiente();
				int e = m.getExponente() + m2.getExponente();
				Monomio m3 = new Monomio(c, e);
				lista.add(m3);
			}
		}	
		Polinomio multiplicacion = new Polinomio(lista);
		multiplicacion.simplificar();
		return multiplicacion;
	}
	
	public ArrayList<Polinomio> dividir(Polinomio p) {
		ArrayList<Polinomio> resultado = new ArrayList<>();
		ArrayList<Monomio> dividendo = new ArrayList<>();
		ArrayList<Monomio> cociente = new ArrayList<>();
		ArrayList<Integer> exponentes = new ArrayList<>();
		if(p.size() > 2 || p.get(0).getCoeficiente() != 1 
				|| p.get(0).getExponente() != 1 || p.get(1).getExponente() != 0) {
			throw new RuntimeException();
		}
		int grado = pol.get(0).getExponente();
		for(int i = grado; i >= 0; i--) {
			exponentes.add(i);
		}
		boolean encontrado = false;
		for(Integer i: exponentes) {
			for(Monomio mon: pol) {
				if(i == mon.getExponente()) {
					Monomio nuevo = new Monomio(mon.getCoeficiente(), mon.getExponente());
					dividendo.add(nuevo);
					encontrado = true;
				}				
			}
			if(!encontrado) {
				Monomio nuevo2 = new Monomio(0, i);
				dividendo.add(nuevo2);
			}
			encontrado = false;
		}
		
		Polinomio r = new Polinomio();
		int divisor = -p.get(1).getCoeficiente();
		Monomio m = new Monomio(pol.get(0).getCoeficiente(), pol.get(0).getExponente() - 1);
		cociente.add(m);
		int n = dividendo.get(0).getCoeficiente();
		int x = n*divisor;;
		int x2 = dividendo.get(0 + 1).getCoeficiente();
		int suma = x + x2;
		Monomio monomio = new Monomio(suma, dividendo.get(0 + 1).getExponente() - 1);
		cociente.add(monomio);
		for(int i = 1; i < dividendo.size() - 1; i++) {
			suma = suma*divisor + dividendo.get(i + 1).getCoeficiente();		
			monomio = new Monomio(suma, dividendo.get(i + 1).getExponente() - 1);
			cociente.add(monomio);
		}
		Polinomio division = new Polinomio(cociente);
		Monomio resto = new Monomio(division.get(division.size() - 1).getCoeficiente(),
				division.get(division.size() - 1).getExponente() + 1);
		r.add(resto);
		division.remove(division.size() - 1);
		resultado.add(division);
		resultado.add(r);
		return resultado;
	}
	
	public int size() {
		return pol.size();
	}
	
	public Monomio get(int index) {
		return pol.get(index);
	}
	public void remove(int index) {
		pol.remove(index);
	}
	
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for(int i = 0; i < pol.size(); i++) {		
			if(i == pol.size() - 1 && pol.get(i).getExponente() == 0) {
				cadena.append("(" + pol.get(i).getCoeficiente() + ")");
			}
			else if(i == pol.size() - 1 && pol.get(i).getCoeficiente() > 0) {
				cadena.append("(" + pol.get(i).getCoeficiente() + ")x^" + pol.get(i).getExponente());
			}
			else {
				cadena.append("(" + pol.get(i).getCoeficiente()
						+ ")x^" + pol.get(i).getExponente() + " + ");
			}		
		}
		return cadena.toString();
	}
	public Polinomio convertirRuffini() {
		ArrayList<Monomio> dividendo = new ArrayList<>();
		ArrayList<Integer> exponentes = new ArrayList<>();

		int grado = pol.get(0).getExponente();
		for(int i = grado; i >= 0; i--) {
			exponentes.add(i);
		}
		boolean encontrado = false;
		for(Integer i: exponentes) {
			for(Monomio mon: pol) {
				if(i == mon.getExponente()) {
					Monomio nuevo = new Monomio(mon.getCoeficiente(), mon.getExponente());
					dividendo.add(nuevo);
					encontrado = true;
				}				
			}
			if(!encontrado) {
				Monomio nuevo2 = new Monomio(0, i);
				dividendo.add(nuevo2);
			}
			encontrado = false;
		}
		return new Polinomio(dividendo);
	}
	
	/*
	 public boolean comprobarEstable() {
		boolean estable = true;
		boolean encontrado = false;
		int grado = polinomio.get(0).getExponente();
		if(polinomio.size() != grado + 1) {
			estable = false;
		}
		else {
			for(Monomio m: polinomio) {
				if(m.getCoeficiente() > 0) {
					encontrado = true;
				}
			}
			if(encontrado == true) {
				for(Monomio m: polinomio) {
					if(m.getCoeficiente() < 0) {
						estable = false;
					}
				}
			}
		}	
		return estable;	
	}
	
	public boolean coeficientesNegativos() {
		boolean negativos = false;
		for(Monomio m: polinomio) {
			if(m.getCoeficiente() < 0) {
				negativos = true;
			}
			else {
				negativos = false;
			}
		}
		return negativos;
	}
	
	public void cambiarSigno() {
		for(Monomio m: polinomio) {
			m.setCoeficiente(m.getCoeficiente()*-1);
		}
	}
	
	public int[][] crearMatriz(Polinomio p) {
		int tam = p.size();
		int n = Math.round(tam/2);
		int q = 1;
		int k = 0;
		int [] par = null;
		int [] impar = null;
		int [][] matriz = null;
		int x;
		int orden;
		int c;
		int d;
		int[]a = null;
		int[]b = null;
		
		for (int i = 1; i < tam; i++) { 
			if (i%2 == 0) {
			  	par[k] = polinomio.get(i).getCoeficiente(); 
			}
			else {
				impar[q] = polinomio.get(i).getCoeficiente();      
			}
		k = k+1;
		q = q+1;
		}
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < n; j++) {
				matriz[i][j] = 0;
			}
		}
		if (tam/2 != Math.round(tam/2)) {
			par[n] = 0;
		}
		for(int i = 0; i < impar.length; i++) {
			matriz[i][0] = impar[i];
		}
		for(int i = 0; i < impar.length; i++) {
			matriz[i][1] = par[i];
		}
		for (int i = 3; i < tam; i++) {
			for (int j = 1; j < n-1; j++) {
				x = matriz[i-1][1];
				matriz[i][j] = ((matriz[i-1][1]*matriz[i-2][j+1])-(matriz[i-2][1]*matriz[i-1][j+1]))/x;
			}
			if(matriz[i][0] == 0) {
				orden = tam - 1 + 1;
				c = 0;
				d = 1;
				for(int j = 1; j < n - 1; j++) {
					matriz[i][j] = (orden-c)*matriz[i-1][d];
					d = d + 1;
					c = c + 2;
				}
			}
		}
		int PolosDerechos = 0;
		for (int i = 1; i < tam-1; i++) {
			if(matriz[i][1] > 0) {
				a[i] = 1;
			}			
			if(matriz[i][1] == 0) {
				a[i] = 0;
			}
			if(matriz[i][1] < 0) {
				a[i] = -1;
			}
		}
		for (int i = 1; i < tam-1; i++) {			
			if(matriz[i + 1][1] > 0) {
				b[i] = 1;
			}			
			if(matriz[i + 1][1] == 0) {
				b[i] = 0;
			}
			if(matriz[i + 1][1] < 0) {
				b[i] = -1;
			}
		}
		for (int i = 1; i < a.length; i++) {
			if(a[i]*b[i] == -1) {
				 PolosDerechos = PolosDerechos+1;
			}	
		}
		return matriz;	
	}*/		
}