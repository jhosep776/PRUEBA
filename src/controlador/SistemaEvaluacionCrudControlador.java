package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.SistemaEvaluacionDAO;
import entidad.SistemaEvaluacion;
import fabricas.Fabrica;

/**
 * Servlet implementation class SistemaEvaluacionCrudControlador
 */
@WebServlet("/SistemaEvaluacionCrud")
public class SistemaEvaluacionCrudControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String metodo = request.getParameter("metodo");
		if(metodo.equals("INSERTA")) {
			inserta(request, response);
		}else if(metodo.equals("ACTUALIZA")) {
			actualiza(request, response);
		}else if(metodo.equals("ELIMINA")) {
			elimina(request, response);
		}else if(metodo.equals("LISTA")) {
			lista(request, response);
		}
			
	}

	protected void inserta(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String nom = request.getParameter("nombre");
		String form = request.getParameter("formula");
		String est = request.getParameter("estado");
		
		SistemaEvaluacion obj = new SistemaEvaluacion();
		obj.setNombre(nom);
		obj.setFormula(form);
		obj.setEstado(est);
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		SistemaEvaluacionDAO dao = fabrica.getSistemaEvaluacionDAO();
		try {
		int s = dao.insertaSistemaEvaluacion(obj);
		if( s > 0) {
			List<SistemaEvaluacion> lista = dao.listaPorNombre("%");
			request.getSession().setAttribute("SistemaEvaluacion", lista);
			request.getSession().setAttribute("MENSAJE", "Se insertó correctamente");
		}else {
			request.getSession().setAttribute("MENSAJE", "ERROR al insertar");
		}
	} catch (Exception e) {
		request.getSession().setAttribute("MENSAJE", "ERROR al insertar");
		e.printStackTrace();
	} finally {
		response.sendRedirect("intranetCrudSistemaEvaluacion.jsp");
	}
		
	}
	
	protected void actualiza(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String id = request.getParameter("idSistema");
			String nom = request.getParameter("nombre");
			String form = request.getParameter("formula");
			String est = request.getParameter("estado");
			
			SistemaEvaluacion obj = new SistemaEvaluacion();
			obj.setIdsistemaevaluacion(Integer.parseInt(id));
			obj.setNombre(nom);
			obj.setFormula(form);
			obj.setEstado(est);
			
			Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			SistemaEvaluacionDAO dao = fabrica.getSistemaEvaluacionDAO();
			
			int s = dao.actualizaSistemaEvaluacion(obj);
			if( s > 0) {
				List<SistemaEvaluacion> lista = dao.listaPorNombre("%");
				request.getSession().setAttribute("SistemaEvaluacion", lista);
				request.getSession().setAttribute("MENSAJE", "Se actualizó correctamente");
			}else {
				request.getSession().setAttribute("MENSAJE", "ERROR al actualizar");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("MENSAJE", "ERROR al actualizar");
			e.printStackTrace();
		} finally {
			response.sendRedirect("intranetCrudSistemaEvaluacion.jsp");
		}
		
	}
	
	protected void elimina(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			String id = request.getParameter("id");
			
			Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			SistemaEvaluacionDAO dao = fabrica.getSistemaEvaluacionDAO();
			
			int s = dao.eliminaSistemaEvaluacion(Integer.parseInt(id));
			if( s > 0) {
				List<SistemaEvaluacion> lista = dao.listaPorNombre("%");
				request.getSession().setAttribute("SistemaEvaluacion", lista);
				request.getSession().setAttribute("MENSAJE", "Se elimino correctamente");
			}else {
				request.getSession().setAttribute("MENSAJE", "ERROR al eliminar");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("MENSAJE", "ERROR al eliminar");
			e.printStackTrace();
		} finally {
			response.sendRedirect("intranetCrudSistemaEvaluacion.jsp");
		}
	}
	
	protected void lista(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String filtro = request.getParameter("filtro");
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		SistemaEvaluacionDAO dao = fabrica.getSistemaEvaluacionDAO();
		
		List<SistemaEvaluacion> lista = dao.listaPorNombre(filtro + "%");
		request.getSession().setAttribute("SistemaEvaluacion", lista);
		
		response.sendRedirect("intranetCrudSistemaEvaluacion.jsp");
		
	}
}
	
