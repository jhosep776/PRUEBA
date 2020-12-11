package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import entidad.TipoLibro;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class MysqlTipoLibroDAO implements TipoLibroDAO {

	@Override
	public List<TipoLibro> listaTipoLibro() {
		
       ArrayList<TipoLibro> lista = new ArrayList<TipoLibro>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from tipolibro";
			
			pstm = conn.prepareStatement(sql);
			log.info(sql);
			rs = pstm.executeQuery();
			
			TipoLibro obj = null;
			while(rs.next()) {
				obj = new TipoLibro();
				obj.setIdTipoLibro(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				lista.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null ) rs.close();
				if(pstm != null ) pstm.close();
				if(conn != null ) conn.close();
			} catch (Exception e2) {}
		}
		return lista;
	}

}
