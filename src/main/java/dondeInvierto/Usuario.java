package dondeInvierto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import seguridad.Encriptacion;

@Entity
@NamedQueries(value = { @NamedQuery(name = "Usuario.getAll", query = "SELECT b FROM Usuario b") })
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "ID_USUARIO")
	private Long id;
	@Column(name = "USUARIO")
	private String usuario;
	@Column(name = "PASS")
	private byte[] pass;
	@Column(name = "CANT_INTENTOS")
	private int cant_intentos;
	
	public Usuario(String usuario, String pass, int cant_intentos) {
		super();
		this.usuario = usuario;
		Encriptacion encriptacion = new Encriptacion();
		try {
			this.pass = encriptacion.cifra(pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cant_intentos = cant_intentos;
	}
	
	public Usuario() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario()  {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		Encriptacion encriptacion = new Encriptacion();
		try {
			return encriptacion.descifra(this.pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public void setPass(byte[] pass) {
		this.pass = pass;
	}
	public int getCant_intentos() {
		return cant_intentos;
	}
	public void setCant_intentos(int cant_intentos) {
		this.cant_intentos = cant_intentos;
	}

}
