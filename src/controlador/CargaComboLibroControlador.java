package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.TipoLibroDAO;
import entidad.TipoLibro;
import fabricas.Fabrica;
import lombok.extern.apachecommons.CommonsLog;


@WebServlet("/CargaComboLibro")
@CommonsLog
public class CargaComboLibroControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String metodo = request.getParameter("metodo");
		if (metodo.equals("TipoLibro")) {
			CargaTipoLibro(request, response);
		}
	}

	protected void CargaTipoLibro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1 Traer los auspiciadores de la base de datos
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		TipoLibroDAO dao = subFabrica.getTipoLibroDAO();
		
		List<TipoLibro> lista = dao.listaTipoLibro();
		
		// 2 Se convierte en formato JSON con la libreria gson
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
