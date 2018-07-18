package profesores;

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
import model.entity.Curso;
import model.entity.Profesor;
@SuppressWarnings("serial")
public class misCursos extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		Profesor profesorSession;
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if((profesorSession = ACCESO.accesoProfesor(user.getEmail())) != null && profesorSession.isEstado() == true){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Curso.class);
			//query.setOrdering("nombre descending");
			
			try{
				query.setFilter("idProfesor == idParam");
				query.declareParameters("Long idParam");

				List<Curso> array = (List<Curso>) query.execute(profesorSession.getId()); 

				req.setAttribute("array", array);
				req.setAttribute("profesor", profesorSession);
				req.getRequestDispatcher("/WEB-INF/Views/Profesores/misCursos.jsp").forward(req, resp);
				query.closeAll();

			} catch (Exception e) {
				System.out.println("Error "+e.getMessage());
			}
			finally {
				pm.close();
			}
		}else {

			try {
				req.getRequestDispatcher("/WEB-INF/Views/AccesoDenegado.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
