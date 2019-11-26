package application;

public class Ea{
	protected Fecha entrada;
	protected Fecha salida;
	protected double tiempo;
	protected String h_entrada;
	protected String h_salida;

	public Ea() {
	}

	public Ea(Fecha entrada, Fecha salida, double tiempo, String h_entrada, String h_salida) {
		this.entrada = entrada;
		this.salida = salida;
		this.tiempo = tiempo;
		this.h_entrada = h_entrada;
		this.h_salida = h_salida;
	}

	public Fecha getEntrada() {
		return entrada;
	}

	public void setEntrada(Fecha entrada) {
		this.entrada = entrada;
	}

	public Fecha getSalida() {
		return salida;
	}

	public void setSalida(Fecha salida) {
		this.salida = salida;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public String getH_entrada() {
		return h_entrada;
	}

	public void setH_entrada(String h_entrada) {
		this.h_entrada = h_entrada;
	}

	public String getH_salida() {
		return h_salida;
	}

	public void setH_salida(String h_salida) {
		this.h_salida = h_salida;
	}

	@Override
	public String toString() {
		return "Ea{" +
				"entrada=" + entrada +
				", salida=" + salida +
				", tiempo=" + tiempo +
				", h_entrada='" + h_entrada + '\'' +
				", h_salida='" + h_salida + '\'' +
				'}';
	}
}