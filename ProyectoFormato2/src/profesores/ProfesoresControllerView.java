package profesores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import model.entity.Profesor;
import model.entity.Curso;
import controller.ACCESO;
import controller.PMF;
@SuppressWarnings("serial")
public class ProfesoresControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Curso.class);
		
		try {
			
			String codigo = req.getParameter("id");
			Long codigoLong = Long.parseLong(codigo); 
			Profesor prof = pm.getObjectById(Profesor.class,codigoLong);
			
			query.setFilter("idProfesor == idParam");
			query.declareParameters("Long idParam");
			
			List<Curso> array = (List<Curso>) query.execute(codigoLong); 
			
			req.setAttribute("array", array);
			req.setAttribute("profesor", prof);
			
			query.closeAll();
			req.getRequestDispatcher("/WEB-INF/Views/Profesores/view.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			pm.close();
		}
	}
		else {

			try {
				req.getRequestDispatcher("/WEB-INF/Views/AccesoDenegado.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		

		
		String id = req.getParameter("id");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String gmail = req.getParameter("gmail");
		String dni = req.getParameter("dni");
		String idRol = req.getParameter("idRol");
		String estado = req.getParameter("estado");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			
			Long idLong = Long.parseLong(id);
			int dniInt = Integer.parseInt(dni);
			//Long idRolLong = Long.parseLong(idRol);
			boolean estadoBoolean = Boolean.parseBoolean(estado);
			
			Profesor prof = pm.getObjectById(Profesor.class,idLong);
			
			prof.setNombre(nombre);
			prof.setApellido(apellido);
			prof.setDni(dniInt);
			prof.setGmail(gmail);
			//prof.setIdRol(idRolLong);
			prof.setEstado(estadoBoolean);
			
			resp.getWriter().print("1");
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
	}
}

