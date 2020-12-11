package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GradoDAO;
import entidad.Grado;
import fabricas.Fabrica;

@WebServlet("/crudGrado")
public class GradoCrudControlador extends HttpServlet {
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
		try {
			String nom = request.getParameter("nombre");
			String est = request.getParameter("estado");
			
			Grado obj = new Grado();
			obj.setNombre(nom);
			obj.setEstado(est);
			
			Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			GradoDAO dao = fabrica.getGradoDAO();
			
			int s = dao.insertaGrado(obj);
			if( s > 0) {
				List<Grado> lista = dao.listaPorNombre("%");
				request.getSession().setAttribute("GRADOS", lista);
				request.getSession().setAttribute("MENSAJE", "Se insertó correctamente");
			}else {
				request.getSession().setAttribute("MENSAJE", "ERROR al insertar");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("MENSAJE", "ERROR al insertar");
			e.printStackTrace();
		} finally {
			response.sendRedirect("intranetCrudGrado.jsp");
		}
	}
	protected void actualiza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("idGrado");
			String nom = request.getParameter("nombre");
			String est = request.getParameter("estado");
			
			Grado obj = new Grado();
			obj.setIdGrado(Integer.parseInt(id));
			obj.setNombre(nom);
			obj.setEstado(est);
			
			Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			GradoDAO dao = fabrica.getGradoDAO();
			
			int s = dao.actualizaGrado(obj);
			if( s > 0) {
				List<Grado> lista = dao.listaPorNombre("%");
				request.getSession().setAttribute("GRADOS", lista);
				request.getSession().setAttribute("MENSAJE", "Se actualizó correctamente");
			}else {
				request.getSession().setAttribute("MENSAJE", "ERROR al actualizar");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("MENSAJE", "ERROR al actualizar");
			e.printStackTrace();
		} finally {
			response.sendRedirect("intranetCrudGrado.jsp");
		}
	}
	protected void elimina(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			
			Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			GradoDAO dao = fabrica.getGradoDAO();
			
			int s = dao.eliminaGrado(Integer.parseInt(id));
			if( s > 0) {
				List<Grado> lista = dao.listaPorNombre("%");
				request.getSession().setAttribute("GRADOS", lista);
				request.getSession().setAttribute("MENSAJE", "Se eliminó correctamente");
			}else {
				request.getSession().setAttribute("MENSAJE", "ERROR al eliminar");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("MENSAJE", "ERROR al eliminar");
			e.printStackTrace();
		} finally {
			response.sendRedirect("intranetCrudGrado.jsp");
		}
	}
	
	protected void lista(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		GradoDAO dao = fabrica.getGradoDAO();
		
		List<Grado> lista = dao.listaPorNombre(filtro + "%");
		request.getSession().setAttribute("GRADOS", lista);
		
		response.sendRedirect("intranetCrudGrado.jsp");
	}
		
}
