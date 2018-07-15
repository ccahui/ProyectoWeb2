package alumnos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Alumno;
import model.entity.Factura;
import model.entity.Nota;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class AlumnosControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);

			Alumno alumno = pm.getObjectById(Alumno.class, idLong);
			Long idCarrera = alumno.getIdCarrera();
			
			Query query = pm.newQuery(Nota.class);
			Query query2 = pm.newQuery(Factura.class);
			
			query.setFilter("idAlumno == idParam");
			query.declareParameters("Long idParam");
			query.deletePersistentAll(idLong); 
			
			query2.setFilter("idAlumno == idParam");
			query2.declareParameters("Long idParam");
			query2.deletePersistentAll(idLong); 
			
			
			pm.deletePersistent(alumno);

			System.out.println("1");
			
			resp.sendRedirect("/alumnos/carrera?id="+idCarrera);
		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
			resp.sendRedirect("/alumnos");
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

}

