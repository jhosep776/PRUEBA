package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Opcion;
import entidad.Usuario;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;
@CommonsLog
public class MySqlUsuarioDAO implements UsuarioDAO {

	@Override
	public Usuario login(Usuario bean) throws Exception {
		Connection conn= null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Usuario obj = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql ="select * from usuario where login = ? and password =? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bean.getLogin());
			pstm.setString(2, bean.getPassword());
			
			log.info(pstm);
			
			rs = pstm.executeQuery();
			while(rs.next()){
				obj = new Usuario();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setLogin(rs.getString(5));
				obj.setPassword(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!= null) rs.close();
				if(pstm!= null) pstm.close();
				if(conn!= null) conn.close();
			} catch (Exception e2) {
			}
		}
		return obj;
	}

	@Override
	public List<Opcion> traerEnlacesDeUsuario(int idUsuario)	throws Exception {
		Connection conn= null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Opcion> data = new ArrayList<Opcion>();
		Opcion obj = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql ="select p.idopcion, p.nombre, p.ruta, p.estado, p.tipo  from opcion p inner join rol_has_opcion r on p.idopcion = r.idopcion inner join rol c on r.idrol = c.idrol inner join usuario_has_rol h on c.idrol = h.idrol where idusuario = ? order by 2; ";     
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);

			log.info(pstm);
			
			rs = pstm.executeQuery();
			while(rs.next()){
				obj = new Opcion();
				obj.setIdOpcion(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setRuta(rs.getString(3));
				obj.setEstado(rs.getString(4));
				obj.setTipo(rs.getInt(5));
				data.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!= null) rs.close();
				if(pstm!= null) pstm.close();
				if(conn!= null) conn.close();
			} catch (Exception e2) {
			}
		}
		
		return data;
	}
//crud
	@Override
	public int insertaUsuario(Usuario obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "insert into usuario values(null,?,?,?,?,?)";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getApellido());
			pstm.setString(3, obj.getDni());
			pstm.setString(4, obj.getLogin());
			pstm.setString(5, obj.getPassword());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}

	@Override
	public int actualizaUsuario(Usuario obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "update usuario set nombre =?, apellido =?, dni =?, login =?, password =? where idUsuario =?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getApellido());
			pstm.setString(3, obj.getDni());
			pstm.setString(4, obj.getLogin());
			pstm.setString(5, obj.getPassword());
			pstm.setInt(6, obj.getIdUsuario());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}

	@Override
	public List<Usuario> listaUsuario(String filtro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			String sql = "select * from usuario where nombre like ?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro + "%");
			log.info(pstm);
			rs = pstm.executeQuery();
			Usuario bean = null;
			while(rs.next()){
				bean = new Usuario();
				bean.setIdUsuario(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setApellido(rs.getString(3));
				bean.setDni(rs.getString(4));
				bean.setLogin(rs.getString(5));
				bean.setPassword(rs.getString(6));
				lista.add(bean);
			}
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return lista;
	}

	@Override
	public int eliminaUsuario(int idUsuario) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "delete from usuario where idusuario =?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}

	@Override
	public List<Usuario> listaUsuario() {
		List<Usuario> data = new ArrayList<Usuario>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from usuario";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			Usuario obj = null;
			while (rs.next()) {
				obj = new Usuario();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setLogin(rs.getString(5));
				obj.setPassword(rs.getString(6));
				data.add(obj);
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
		return data;
	}

	
}
