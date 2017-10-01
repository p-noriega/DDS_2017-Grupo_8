package dondeInvierto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import dondeInvierto.Condicion;
import dondeInvierto.CondicionFiltro;
import dondeInvierto.CondicionOrdenamiento;
import dondeInvierto.Empresa;
import dondeInvierto.Indicador;

import dondeInvierto.Metodologia;

/**
 * Contiene todas las empresas, cuentas, identificadores y metodologías del dominio.
 */
public enum MercadoBursatil {
	INSTANCE;
	
	private List<Empresa> empresas = new ArrayList<Empresa>();
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	private List<Metodologia> metodologias = new ArrayList<Metodologia>();
	
	/**
	 * Agrega datos de prueba (empresas, cuentas e indicadores) al mercado bursátil.
	 */
	public void init() {
		
		addEmpresa("Facebook Inc.");
		addEmpresa("Tesla Inc.");
		addEmpresa("Twitter Inc.");
		
		try {
			addCuenta("Facebook Inc.", "EBITDA", "20151231", "8162");
			addCuenta("Facebook Inc.", "EBITDA", "20161231", "14870");
			addCuenta("Facebook Inc.", "FCF", "20151231", "3.99");
			addCuenta("Tesla Inc.", "EBITDA", "20151231", "213");
			addCuenta("Tesla Inc.", "EBITDA", "20161231", "630");
			addCuenta("Twitter Inc.", "EBITDA", "20161231", "751");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		addIndicador("Ingreso Neto", 
				"Ingreso Neto = Ingreso Neto En Operaciones Continuas + "
				+ "Ingreso Neto En Operaciones Discontinuadas");
		addIndicador("Retorno sobre capital total",
				"Retorno sobre capital total = (Ingreso Neto - Dividendos) "
				+ "/ Capital Total");
		addIndicador("Proporcion Deuda",
				"Proporcion Deuda = Pasivo"
				+ "/ Acciones");
		
		/*addMetodologia("MaximizarROE",("Retorno sobre capital total",
				"Retorno sobre capital total = (Ingreso Neto - Dividendos)"
				+ "/ Capital Total"),(">=",0));
		
		addMetodologia("MaximizarROE",("Retorno sobre capital total",
				"Retorno sobre capital total = (Ingreso Neto - Dividendos)"
				+ "/ Capital Total"),(">=",0));
		*/
		

	}
	
	/**
	 * Devuelve todas las empresas que existen en el mercado bursátil.
	 */
	public List<Empresa> getEmpresas() {
		return this.empresas;
	}
	
	/**
	 * Devuelve la empresa con el nombre buscado.
	 */
	public Empresa getEmpresa(String empresa) {
		return getEmpresas().stream().
				filter(e -> empresa.equals(e.getNombre())).
				findFirst().orElse(null);
	}
	
	/**
	 * Agrega la empresa a la lista de empresas del mercado bursátil.
	 */
	public void addEmpresa(String nombre) {
		getEmpresas().add(new Empresa(nombre));
	}
	
	/**
	 * Devuelve true si el mercado bursátil tiene alguna empresa con el nombre buscado.
	 */
	public boolean containsEmpresa(String empresa) {		
		return getEmpresas().stream().
				anyMatch(e -> empresa.equals(e.getNombre()));
	}
	
	/**
	 * Agrega la cuenta en la lista de cuentas de la empresa y la lista de cuentas del mercado bursátil.
	 * Si la empresa de la cuenta no existe, la crea.
	 * @throws ParseException 
	 */
	public void addCuenta(String empresa, String tipo, String periodo, String valor) throws ParseException {
		
		if(!containsEmpresa(empresa)) {
			addEmpresa(empresa);
		}
		
		Empresa emp = getEmpresa(empresa);
		
		if (!emp.containsCuenta(tipo, periodo)) {
			try {
				emp.addCuenta(new Cuenta(tipo, periodo, valor));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Devuelve todos los indicadores que existen en el mercado bursátil.
	 */
	public List<Indicador> getIndicadores() {
		return this.indicadores;
	}
	
	/**
	 * Devuelve el indicador buscado.
	 */
	public Indicador getIndicador(String nombre) {
		return getIndicadores().stream().
				filter(i -> (nombre.equals(i.getNombre()))).
				findFirst().orElse(null);
	}
	
	/**
	 * Devuelve true si el mercado bursátil tiene algún indicador con el nombre buscado.
	 */
	public boolean containsIndicador(String indicador) {		
		return getIndicadores().stream().
				anyMatch(i -> indicador.equals(i.getNombre()));
	}
	
	/**
	 * Agrega el indicador en la lista de indicadores del mercado bursátil.
	 */
	public void addIndicador(String nombre, String formula) {
		if (!containsIndicador(nombre)) {
			try {
				getIndicadores().add(new Indicador(nombre, formula));
			} catch (IllegalStateException e) {
				System.err.println("[ERROR] (ANTLR) " + e.getMessage() + ". " +
						"Se produjo un error al intentar parsear la expresión ingresada (" + nombre + " = " +
						formula + "). El indicador no ha sido creado. Por favor, revísela e intente nuevamente.");
			}
		}
	}
	
	/**
	 * Calcula el valor de un indicador para una determinada empresa, en un periodo dado.
	 */
	public Double calcularIndicador(Indicador indicador, Empresa empresa, String periodo) {
		return indicador.getValorFor(empresa, periodo);
	}
	
	/**
	 * Devuelve listado de metodologias.
	 */
	
	public List<Metodologia> getMetodologias() {
		return this.metodologias;
	}	
	
	/**
	 * Devuelve la metodologia con el nombre buscado.
	 */
	public Metodologia getMetodologia(String metodologia) {
		return getMetodologias().stream().
				filter(m -> metodologia.equals(m.getNombre())).
				findFirst().orElse(null);
	}
	
	
	/**
	 * Agrega la metodologia a la lista de metodologias del mercado bursátil.
	 */
	public void addMetodologia(String nombre, Set<CondicionFiltro> condicionesFiltro, Set<CondicionOrdenamiento> orden1){
		if (this.containsMetodologia(nombre)){
			System.out.println("Ya existe una metodologia con ese nombre.");
		} else {
			getMetodologias().add(new Metodologia(nombre,condicionesFiltro,orden1));
		}
		
	}
	
	
	
	/**
	 * Devuelve true si el mercado bursátil tiene alguna empresa con el nombre buscado.
	 */
	public boolean containsMetodologia(String nombre) {		
		return getMetodologias().stream().
				anyMatch(m -> nombre.equals(m.getNombre()));
	}
	
	
}