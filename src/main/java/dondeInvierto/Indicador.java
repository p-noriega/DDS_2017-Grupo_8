package dondeInvierto;

public class Indicador {
	private String nombre;
	private String formula;
	
	public Indicador(String nombre, String formula) {
		this.nombre = nombre;
		this.formula = formula;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getFormula() {
		return this.formula;
	}
}