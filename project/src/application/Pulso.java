package application;

public class Pulso {
	protected Fecha fecha;
	protected int dato;

	public Pulso() {
	}

	public Pulso(Fecha fecha, int dato) {
		this.fecha = fecha;
		this.dato = dato;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	@Override
	public String toString() {
		return "Pulso{" +
				"fecha=" + fecha.toString() +
				", dato=" + dato +
				'}';
	}
}