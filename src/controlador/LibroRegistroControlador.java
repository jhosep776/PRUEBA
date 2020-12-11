package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.LibroDAO;
import entidad.Libro;
import entidad.TipoLibro;
import fabricas.Fabrica;


@WebServlet("/RegistroLibro")
public class LibroRegistroControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		try {
			// 1 Recibe los parámetros
			// Son los nombres de las caja de textos en el JSP
			
			String cod = request.getParameter("codigo");
			String tit = request.getParameter("titulo");
			String est = request.getParameter("estado");
			String tip = request.getParameter("tipo");
			
			String tipolibro = request.getParameter("TipoLibro");

			// 2 Se crea el objeto Alumno
			TipoLibro objtipolibro = new TipoLibro();
			objtipolibro.setIdTipoLibro(Integer.parseInt(tipolibro));
			
			Libro objlib = new Libro();
			objlib.setCodigo(cod);
			objlib.setTitulo(tit);
			objlib.setEstado(est);
			objlib.setTipo(tip);
			objlib.setTipoLibro(objtipolibro);
			
			Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			LibroDAO dao = subFabrica.getLibroDAO();
			
			int s = dao.insertaLibro(objlib);
			if (s > 0)
				request.getSession().setAttribute("MENSAJE", "registro exitoso");
			else
				request.getSession().setAttribute("MENSAJE", "registro erróneo");

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("MENSAJE", "registro erróneo");
		} finally {
			response.sendRedirect("intranetRegistraLibro.jsp");
		}

	}


}
