package dao;

import java.util.List;

import entidad.Aula;

public interface AulaDAO {
	//
	public abstract int insertaAula(Aula obj);

	public abstract int actualizaAula(Aula obj);

	public abstract int eliminaAula(int id);

	public abstract List<Aula> listaAula(String filtro);

}
