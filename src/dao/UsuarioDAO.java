package dao;

import java.util.List;

import entidad.Opcion;
import entidad.Usuario;

public interface UsuarioDAO {
	
	public Usuario  login(Usuario bean) throws Exception;
	public abstract List<Opcion> traerEnlacesDeUsuario(int idUsuario) throws Exception;
	//crud
	public abstract int eliminaUsuario(int idUsuario);

	public abstract int insertaUsuario(Usuario obj);

	public abstract int actualizaUsuario(Usuario obj);

	public abstract List<Usuario> listaUsuario(String filtro);
	
	public abstract List<Usuario> listaUsuario();
}

