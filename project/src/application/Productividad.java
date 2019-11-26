package application;

public class Productividad{
	protected Fecha fecha;
	protected int act_plan;
	protected int act_compl;
	protected double productividad;

	public Productividad() {
	}

	public Productividad(Fecha fecha, int act_plan, int act_compl, double productividad) {
		this.fecha = fecha;
		this.act_plan = act_plan;
		this.act_compl = act_compl;
		this.productividad = productividad;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public int getAct_plan() {
		return act_plan;
	}

	public void setAct_plan(int act_plan) {
		this.act_plan = act_plan;
	}

	public int getAct_compl() {
		return act_compl;
	}

	public void setAct_compl(int act_compl) {
		this.act_compl = act_compl;
	}

	public double getProductividad() {
		return productividad;
	}

	public void setProductividad(double productividad) {
		this.productividad = productividad;
	}

	@Override
	public String toString() {
		return "Productividad{" +
				"fecha=" + fecha.toString() +
				", act_plan=" + act_plan +
				", act_compl=" + act_compl +
				", productividad=" + productividad +
				'}';
	}
}