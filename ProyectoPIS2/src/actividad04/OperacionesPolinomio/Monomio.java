package actividad04.OperacionesPolinomio;

public class Monomio  {

	private int coeficiente;
	private int exponente;
	
	public Monomio(int coeficiente, int exponente) {
		this.coeficiente = coeficiente;
		this.exponente = exponente;
	}
	
	public int getCoeficiente() {
		return coeficiente;
	}
	
	public int getExponente() {
		return exponente;
	}
	
	public void cambiarSigno() {
		coeficiente = -coeficiente;
	}
	public void setCoeficiente(int value) {
		coeficiente = value;
	}
	
	public void setExponente(int value) {
		exponente = value;
	}
}
