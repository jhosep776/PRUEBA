package entidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Libro {

	private int idLibro;
	private String codigo;
	private String titulo;
	private String estado;
	private String tipo;
	private TipoLibro TipoLibro;

}
