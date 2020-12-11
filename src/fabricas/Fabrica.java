package fabricas;

import dao.AulaDAO;
import dao.GradoDAO;
import dao.LibroDAO;
import dao.SistemaEvaluacionDAO;
import dao.TipoLibroDAO;
import dao.UsuarioDAO;

//Es una fábrica de objetos
//Se usa el patrón DAO (Data Access Object)
public abstract class Fabrica {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;

	public abstract UsuarioDAO getUsuarioDAO();
	public abstract GradoDAO getGradoDAO();
	public abstract SistemaEvaluacionDAO getSistemaEvaluacionDAO();
	public abstract AulaDAO getAulaDAO();
	public abstract LibroDAO getLibroDAO();
	public abstract TipoLibroDAO getTipoLibroDAO();
	 
	
	//Va fabricar subfabricas (Mysql y sqlserver)
	public static Fabrica getFabrica(int tipo){
		Fabrica salida = null;
		switch(tipo){
			case MYSQL: 
				salida = new FabricaMysql();
				break;
			
		}
		return salida;
	}
}
