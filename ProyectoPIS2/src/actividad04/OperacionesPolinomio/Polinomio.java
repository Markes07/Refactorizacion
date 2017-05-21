package actividad04.OperacionesPolinomio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polinomio {
	
	private ArrayList<Monomio> polinomio;
	
	public Polinomio() {
		polinomio = new ArrayList<Monomio>();
	}
	public Polinomio(ArrayList<Monomio> p) {
		polinomio = p;
	}
	public ArrayList<Monomio> getPolinomio() {
		return polinomio;
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
		polinomio.add(monomio);
		this.ordenar();
		return true;
	}
	
	public boolean remove(Monomio monomio) {
		polinomio.remove(monomio);
		this.ordenar();
		return true;
	}
	
	public boolean isOrdenado() {
		boolean ordenado = false;
		for(int i = 0; i < polinomio.size() - 1; i++) {
			if(polinomio.get(i).getExponente() > polinomio.get(i + 1).getExponente()) {
				ordenado = true;
			}
			else {
				ordenado = false;
			}
		}
		return ordenado;
	}
	
	public void ordenar() {
		Collections.sort(polinomio, new Comparator<Monomio>() {
			@Override
			public int compare(Monomio m1, Monomio m2) {
				return new Integer(m2.getExponente()).compareTo(new Integer(m1.getExponente()));
			}	
		});
	}
	
	public void simplificar() {
		ArrayList<Integer> exponentes = new ArrayList<Integer>();
		ArrayList<Monomio> monomiosMismoExponente = new ArrayList<Monomio>();
		ArrayList<Monomio> simplificado = new ArrayList<Monomio>();
		for(Monomio m: polinomio) {
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
			for(Monomio m: polinomio) {
				if(m.getExponente() == i) {
					monomiosMismoExponente.add(m);
					encontrado = true;
				}			
			}
			for(Monomio m2: monomiosMismoExponente) {		
				suma = suma + m2.getCoeficiente();
			}
			monomiosMismoExponente.clear();
			if(encontrado == true) {
				Monomio mSimplificado = new Monomio(suma, i);
				simplificado.add(mSimplificado);
				encontrado = false;
			}
			i++;
		}
		polinomio = simplificado;
		ordenar();
	}
	
	public Polinomio sumar(Polinomio p) {
		ArrayList<Monomio> lista = new ArrayList<Monomio>();
		ArrayList<Integer> exponentes = new ArrayList<Integer>();
		for(Monomio m: polinomio) {
			for(Monomio m2: p.polinomio) {
				if(m.getExponente() == m2.getExponente()) {
					int c = m.getCoeficiente() + m2.getCoeficiente();
					Monomio m3 = new Monomio(c, m.getExponente());
					lista.add(m3);
					exponentes.add(m.getExponente());
				}
			}
		}
		for(Monomio m: polinomio) {
			if(!exponentes.contains(m.getExponente())) {
				lista.add(m);
			}
		}
		for(Monomio m: p.polinomio) {
			if(!exponentes.contains(m.getExponente())) {
				lista.add(m);
			}
		}
		Polinomio suma = new Polinomio(lista);
		suma.ordenar();
		return suma;
	}
	
	public Polinomio multiplicar(Polinomio p) {
		ArrayList<Monomio> lista = new ArrayList<Monomio>();
		for(Monomio m: polinomio) {
			for(Monomio m2: p.polinomio) {
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
		ArrayList<Polinomio> resultado = new ArrayList<Polinomio>();
		ArrayList<Monomio> dividendo = new ArrayList<Monomio>();
		ArrayList<Monomio> cociente = new ArrayList<Monomio>();
		ArrayList<Integer> exponentes = new ArrayList<Integer>();
		if(p.size() > 2 || p.get(0).getCoeficiente() != 1 
				|| p.get(0).getExponente() != 1 || p.get(1).getExponente() != 0) {
			throw new RuntimeException();
		}
		int grado = polinomio.get(0).getExponente();
		for(int i = grado; i >= 0; i--) {
			exponentes.add(i);
		}
		boolean encontrado = false;
		for(Integer i: exponentes) {
			for(Monomio mon: polinomio) {
				if(i == mon.getExponente()) {
					Monomio nuevo = new Monomio(mon.getCoeficiente(), mon.getExponente());
					dividendo.add(nuevo);
					encontrado = true;
				}				
			}
			if(encontrado == false) {
				Monomio nuevo2 = new Monomio(0, i);
				dividendo.add(nuevo2);
			}
			encontrado = false;
		}
		
		Polinomio r = new Polinomio();
		int divisor = -p.get(1).getCoeficiente();
		Monomio m = new Monomio(polinomio.get(0).getCoeficiente(), polinomio.get(0).getExponente() - 1);
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
		return polinomio.size();
	}
	
	public Monomio get(int index) {
		return polinomio.get(index);
	}
	public void remove(int index) {
		polinomio.remove(index);
	}
	
	public String toString() {
		String cadena = "";
		for(int i = 0; i < polinomio.size(); i++) {		
			if(i == polinomio.size() - 1 && polinomio.get(i).getExponente() == 0) {
				cadena = cadena + "(" + polinomio.get(i).getCoeficiente() + ")";
			}
			else if(i == polinomio.size() - 1 && polinomio.get(i).getCoeficiente() > 0) {
				cadena = cadena + "(" + polinomio.get(i).getCoeficiente() + ")x^" + polinomio.get(i).getExponente();
			}
			else {
				cadena = cadena + "(" + polinomio.get(i).getCoeficiente()
						+ ")x^" + polinomio.get(i).getExponente() + " + ";
			}		
		}
		return cadena;
	}
	public Polinomio convertirRuffini() {
		ArrayList<Monomio> dividendo = new ArrayList<Monomio>();
		ArrayList<Integer> exponentes = new ArrayList<Integer>();

		int grado = polinomio.get(0).getExponente();
		for(int i = grado; i >= 0; i--) {
			exponentes.add(i);
		}
		boolean encontrado = false;
		for(Integer i: exponentes) {
			for(Monomio mon: polinomio) {
				if(i == mon.getExponente()) {
					Monomio nuevo = new Monomio(mon.getCoeficiente(), mon.getExponente());
					dividendo.add(nuevo);
					encontrado = true;
				}				
			}
			if(encontrado == false) {
				Monomio nuevo2 = new Monomio(0, i);
				dividendo.add(nuevo2);
			}
			encontrado = false;
		}
		Polinomio pol = new Polinomio(dividendo);
		return pol;
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
