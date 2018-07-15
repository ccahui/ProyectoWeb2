package carreras;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Carrera;

import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class CarrerasControllerDelete extends HttpServlet {
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

				Carrera carrera = pm.getObjectById(Carrera.class, idLong);
				pm.deletePersistent(carrera);

			}
			catch(Exception e){
				System.out.println("ERROR "+e.getMessage());
			}
			finally{
				pm.close();
			}
			resp.sendRedirect("/carreras");
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

