package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AulaDAO;
import entidad.Aula;
import fabricas.Fabrica;

/**
 * Servlet implementation class AulaControlador
 */
@WebServlet("/Aula")
public class AulaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String m = request.getParameter("metodo");
		if(m.equals("INSERTA")) {
			inserta(request, response);
		}else if(m.equals("ACTUALIZA")) {
			actualiza(request, response);
		}else if(m.equals("ELIMINA")) {
			elimina(request, response);
		}else if(m.equals("LISTA")) {
			lista(request, response);
		}
	
	}
	
	protected void inserta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1 Recibe los parámetros
			// Son los nombres de las caja de textos en el JSP
			String cod = request.getParameter("codigo");
			String ubi = request.getParameter("ubicacion");
			String est = request.getParameter("estado");

			// 2 Se crea el objeto Alumno
			Aula objAula = new Aula();
			
			objAula.setCodigo(cod);
			objAula.setUbicacion(ubi);
			objAula.setEstado(est);
			
			Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			AulaDAO dao = subFabrica.getAulaDAO();
			
			int s = dao.insertaAula(objAula);
			if (s > 0) {
				List<Aula> lista = dao.listaAula("%");
				request.getSession().setAttribute("CODIGO", lista);	
				request.getSession().setAttribute("MENSAJE", "registro exitoso");
			}else {
				request.getSession().setAttribute("MENSAJE", "registro erróneo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("MENSAJE", "registro erróneo");
		} finally {
			response.sendRedirect("intranetCrudAula.jsp");
		}
	}
	protected void actualiza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1 Recibe los parámetros
					// Son los nombres de las caja de textos en el JSP
			String id  = request.getParameter("idAula");
			String cod = request.getParameter("codigo");
			String ubi = request.getParameter("ubicacion");
			String est = request.getParameter("estado");

			// 2 Se crea el objeto Alumno
			Aula objAula = new Aula();
			objAula.setIdAula(Integer.parseInt(id));
			objAula.setCodigo(cod);
			objAula.setUbicacion(ubi);
			objAula.setEstado(est);
			
			Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			AulaDAO dao = subFabrica.getAulaDAO();
			
			int s = dao.actualizaAula(objAula);
			if (s > 0) {
				List<Aula> lista = dao.listaAula("%");
				request.getSession().setAttribute("CODIGO", lista);	
				request.getSession().setAttribute("MENSAJE", "Actualización exitosa");
			}else {
				request.getSession().setAttribute("MENSAJE", "Actualización errónea");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("MENSAJE", "Actualización errónea");
		} finally {
			response.sendRedirect("intranetCrudAula.jsp");
		}
	}
	// elimina
	protected void elimina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			
			Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			AulaDAO dao = subFabrica.getAulaDAO();
			
			int s = dao.eliminaAula(Integer.parseInt(id));
			if (s > 0) {
				List<Aula> lista = dao.listaAula("%");
				request.getSession().setAttribute("CODIGO", lista);	
				request.getSession().setAttribute("MENSAJE", "Eliminación exitosa");
			}else {
				request.getSession().setAttribute("MENSAJE", "Eliminación errónea");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("MENSAJE", "Eliminación errónea");
		} finally {
			response.sendRedirect("intranetCrudAula.jsp");
		}
	}
	protected void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		
		Fabrica subFabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		AulaDAO dao = subFabrica.getAulaDAO();
		
		 
		List<Aula> lista = dao.listaAula(filtro+"%");
		request.getSession().setAttribute("CODIGO", lista);		
		request.getRequestDispatcher("/intranetCrudAula.jsp").forward(request, response);
	}

}
