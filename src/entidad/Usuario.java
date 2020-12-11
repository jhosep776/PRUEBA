package entidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

	private int idUsuario;
	private String nombre;
	private String apellido;
	private String dni;
	private String login;
	private String password;
	
	public String getNombreCompleto() {
		return nombre.concat(" ").concat(apellido);
	}
	

}
