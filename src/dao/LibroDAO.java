package dao;

import java.util.List;

import entidad.Libro;

public interface LibroDAO {
	
public int insertaLibro(Libro obj);
	
	public List<Libro> listaLibro();
	public List<Libro> listaLibroPorTipoLibro(int idTipoLibro);

}
