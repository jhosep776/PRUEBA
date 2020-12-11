package dao;

import entidad.SistemaEvaluacion;
import java.util.List;

public interface SistemaEvaluacionDAO {

	public abstract int insertaSistemaEvaluacion(SistemaEvaluacion obj);
	
	public abstract int actualizaSistemaEvaluacion(SistemaEvaluacion obj);
	
	public abstract int eliminaSistemaEvaluacion(int idsistemaevaluacion);
	
	public abstract List<SistemaEvaluacion> listaPorNombre(String filtro);
}
