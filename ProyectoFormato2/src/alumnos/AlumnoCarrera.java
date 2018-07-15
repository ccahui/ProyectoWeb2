package alumnos;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Alumno;
import model.entity.Carrera;

@SuppressWarnings("serial")
public class AlumnoCarrera extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Alumno.class);
		//query.setOrdering("nombre descending");
		
		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);
			
			Carrera carrera = pm.getObjectById(Carrera.class,idLong);
			
			
			query.setFilter("idCarrera == idParam");
			query.declareParameters("Long idParam");
			List<Alumno> array = (List<Alumno>) query.execute(idLong); 

			
			req.setAttribute("carrera",carrera);
			req.setAttribute("array", array);
			req.getRequestDispatcher("/WEB-INF/Views/Alumnos/alumnoCarrera.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
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
