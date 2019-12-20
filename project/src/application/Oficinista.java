package application;

import java.util.ArrayList;

public class Oficinista {
	protected String usuario;
	protected String password;
	protected String nombre;
	protected String apellidos;
	protected double sueldo;
	protected String departamento;
	protected Fecha f_nac;
	protected ArrayList<Pulso> pulsos = new ArrayList<Pulso>();
	protected ArrayList<Ea> ea = new ArrayList<Ea>();
	protected ArrayList<Productividad> productividad = new ArrayList<Productividad>();

	public Oficinista() {
	}

	public Oficinista(String usuario, String password, String nombre, String apellidos,
			double sueldo, String departamento, Fecha f_nac, ArrayList<Pulso> pulsos, ArrayList<Ea> ea,
			ArrayList<Productividad> productividad) {
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldo = sueldo;
		this.departamento = departamento;
		this.f_nac = f_nac;
		this.pulsos = pulsos;
		this.ea = ea;
		this.productividad = productividad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Fecha getF_nac() {
		return f_nac;
	}

	public void setF_nac(Fecha f_nac) {
		this.f_nac = f_nac;
	}

	public ArrayList<Pulso> getPulsos() {
		return pulsos;
	}

	public void setPulsos(ArrayList<Pulso> pulsos) {
		this.pulsos = pulsos;
	}

	public ArrayList<Ea> getEa() {
		return ea;
	}

	public void setEa(ArrayList<Ea> ea) {
		this.ea = ea;
	}

	public ArrayList<Productividad> getProductividad() {
		return productividad;
	}

	public void setProductividad(ArrayList<Productividad> productividad) {
		this.productividad = productividad;
	}
}
