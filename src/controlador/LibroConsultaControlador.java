package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.LibroDAO;
import entidad.Libro;
import fabricas.Fabrica;
import lombok.extern.apachecommons.CommonsLog;

@WebServlet("/ConsultaLibro")
@CommonsLog
public class LibroConsultaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		LibroDAO dao = subFabrica.getLibroDAO();
		
		String tipo= request.getParameter("TipoLibro");
		
		List<Libro> lista = null;
		if(tipo == null || tipo.trim().equals("")) {
			lista = new ArrayList<Libro>();
		}else if(tipo.equals("-1")){
			lista = dao.listaLibro();
		}else{
			lista = dao.listaLibroPorTipoLibro(Integer.parseInt(tipo));
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		log.info("json-->" + json);

		// 3 Notificamos el tipo de archivo que se envía al browser
		response.setContentType("application/json;charset=UTF-8");

		// 4 Se genera el archivo JSON y se envía al browser
		PrintWriter out = response.getWriter();
		out.println(json);
		
	}

}
