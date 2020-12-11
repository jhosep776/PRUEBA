package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Grado;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class MySqlGradoDAO implements GradoDAO {

	@Override
	public int insertaGrado(Grado obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into grado values(null,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getEstado());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

	@Override
	public int actualizaGrado(Grado obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update grado set nombre=?, estado=? where idGrado=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getEstado());
			pstm.setInt(3, obj.getIdGrado());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

	@Override
	public int eliminaGrado(int idGrado) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "delete from grado where idGrado=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idGrado);
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

	@Override
	public List<Grado> listaPorNombre(String filtro) {
		ArrayList<Grado> salida = new ArrayList<Grado>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from grado where nombre like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);
			log.info(pstm);
			rs = pstm.executeQuery();
			Grado obj = null;
			while (rs.next()) {
				obj = new Grado();
				obj.setIdGrado(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setEstado(rs.getString(3));
				salida.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

}
