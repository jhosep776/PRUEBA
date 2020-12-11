package fabricas;

import dao.AulaDAO;
import dao.GradoDAO;
import dao.LibroDAO;
import dao.MySqlAulaDAO;
import dao.MySqlGradoDAO;
import dao.MySqlSistemaEvaluacionDAO;
import dao.MySqlUsuarioDAO;
import dao.MysqlLibroDAO;
import dao.MysqlTipoLibroDAO;
import dao.SistemaEvaluacionDAO;
import dao.TipoLibroDAO;
import dao.UsuarioDAO;

//Es una subfabrica que tiene objetos que acceden
//a la base de datos MYSQL
public class FabricaMysql extends Fabrica {


	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new MySqlUsuarioDAO();
	}

	@Override
	public GradoDAO getGradoDAO() {
		return new MySqlGradoDAO();
	}

	@Override
	public SistemaEvaluacionDAO getSistemaEvaluacionDAO() {
		return new MySqlSistemaEvaluacionDAO();
	}

	@Override
	public AulaDAO getAulaDAO() {
		return new MySqlAulaDAO();
	}
	
	
	@Override
	public LibroDAO getLibroDAO() {

		return new MysqlLibroDAO();
	}

	@Override
	public TipoLibroDAO getTipoLibroDAO() {
		
		return new MysqlTipoLibroDAO();
	}
	


	

}
