package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import entidad.Aula;
import util.MySqlDBConexion;
 
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class MySqlAulaDAO implements AulaDAO {

	
	@Override
	public int insertaAula(Aula obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm= null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into aula values(null,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,obj.getCodigo());
			pstm.setString(2, obj.getUbicacion());
			pstm.setString(3,obj.getEstado());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	// actualiza

	@Override
	public int actualizaAula(Aula obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm= null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update aula set codigo=?, ubicacion=?, estado=? where idAula=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,obj.getCodigo());
			pstm.setString(2,obj.getUbicacion());
			pstm.setString(3,obj.getEstado());
			pstm.setInt(4,obj.getIdAula());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}

	@Override
	public int eliminaAula(int id) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm= null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "delete from aula where idAula=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,id);
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}

	
	public List<Aula> listaAula(String filtro) {
		
		ArrayList<Aula>salida = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm= null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from aula where codigo like ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,filtro);
			log.info(pstm);
			rs = pstm.executeQuery();
			Aula obj = null;
			while(rs.next()) {
				
				obj = new Aula();
				obj.setIdAula(rs.getInt(1));
				obj.setCodigo(rs.getString(2));
				obj.setUbicacion(rs.getString(3));
				obj.setEstado(rs.getString(4));
				salida.add(obj);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	}

