package dao;

import java.util.List;

import entidad.Grado;

public interface GradoDAO {

	public abstract int insertaGrado(Grado obj);

	public abstract int actualizaGrado(Grado obj);

	public abstract int eliminaGrado(int idGrado);

	public abstract List<Grado> listaPorNombre(String filtro);

}
