package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Libro;
import entidad.TipoLibro;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class MysqlLibroDAO implements LibroDAO {

	@Override
	public int insertaLibro(Libro obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into libro values (null,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getCodigo());
			pstm.setString(2, obj.getTitulo());
			pstm.setString(3, obj.getEstado());
			pstm.setString(4, obj.getTipo());
			pstm.setInt(5, obj.getTipoLibro().getIdTipoLibro());
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
	public List<Libro> listaLibro() {
		
		ArrayList<Libro> lista = new ArrayList<Libro>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT l.*, t.nombre FROM libro l inner join tipolibro t on l.idTipoLibro = t.idTipoLibro";

			pstm = conn.prepareStatement(sql);
			log.info(sql);
			rs = pstm.executeQuery();

			Libro objLib = null;
			TipoLibro objTip = null;
			while (rs.next()) {
				objLib = new Libro();
				objLib.setIdLibro(rs.getInt(1));
				objLib.setCodigo(rs.getString(2));
				objLib.setTitulo(rs.getString(3));
				objLib.setEstado(rs.getString(4));
				objLib.setTipo(rs.getString(5));

				objTip = new TipoLibro();
				objTip.setIdTipoLibro(rs.getInt(6));
				objTip.setNombre(rs.getString(7));

				objLib.setTipoLibro(objTip);
				lista.add(objLib);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return lista;
	}

	@Override
	public List<Libro> listaLibroPorTipoLibro(int idTipoLibro) {
		
		ArrayList<Libro> lista = new ArrayList<Libro>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT l.*, t.nombre FROM libro l inner join tipolibro t on l.idTipoLibro = t.idTipoLibro where t.idTipoLibro =?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idTipoLibro);
			log.info(sql);

			rs = pstm.executeQuery();

			Libro objLib = null;
			TipoLibro objTip = null;
			while (rs.next()) {
				objLib = new Libro();
				objLib.setIdLibro(rs.getInt(1));
				objLib.setCodigo(rs.getString(2));
				objLib.setTitulo(rs.getString(3));
				objLib.setEstado(rs.getString(4));
				objLib.setTipo(rs.getString(5));

				objTip = new TipoLibro();
				objTip.setIdTipoLibro(rs.getInt(6));
				objTip.setNombre(rs.getString(7));

				objLib.setTipoLibro(objTip);
				lista.add(objLib);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return lista;
	}


}
