package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import entidad.SistemaEvaluacion;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class MySqlSistemaEvaluacionDAO implements SistemaEvaluacionDAO {

	@Override
	public int insertaSistemaEvaluacion(SistemaEvaluacion obj) {

		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into sistemaevaluacion values(null,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getFormula());
			pstm.setString(3, obj.getEstado());
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
	public int actualizaSistemaEvaluacion(SistemaEvaluacion obj) {

		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update sistemaevaluacion set nombre=?,formula=?, estado=? where idSistemaEvaluacion=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getFormula());
			pstm.setString(3, obj.getEstado());
			pstm.setInt(4, obj.getIdsistemaevaluacion());
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
	public int eliminaSistemaEvaluacion(int idsistemaevaluacion) {

		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "delete from sistemaevaluacion where idSistemaEvaluacion=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idsistemaevaluacion);
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
	public List<SistemaEvaluacion> listaPorNombre(String filtro) {

		ArrayList<SistemaEvaluacion> salida = new ArrayList<SistemaEvaluacion>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from sistemaevaluacion where nombre like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);
			log.info(pstm);
			rs = pstm.executeQuery();
			SistemaEvaluacion obj = null;
			while (rs.next()) {
				obj = new SistemaEvaluacion();
				obj.setIdsistemaevaluacion(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setFormula(rs.getString(3));
				obj.setEstado(rs.getString(4));
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
