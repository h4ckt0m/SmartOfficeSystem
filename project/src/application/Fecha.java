package application;

public class Fecha {
    protected int year;
    protected int mes;
    protected int dia;

    public Fecha() {
    }

    public Fecha(int year, int mes, int dia) {
        this.year = year;
        this.mes = mes;
        this.dia = dia;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Fecha{" +
                "year=" + year +
                ", mes=" + mes +
                ", dia=" + dia +
                '}';
    }
}