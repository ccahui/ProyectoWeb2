package users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.*;
import controller.ACCESO;
import controller.PMF;
@SuppressWarnings("serial")
public class UsersControllerIndex extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");



		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){

			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Users.class);

			//query.setOrdering("nombre descending");
			List<Users> array = (List<Users>)query.execute("select from Usuario");
			List<Role> arrayRol = new ArrayList<Role>();

			for(Users usuario:array){
				Role rol = pm.getObjectById(Role.class,usuario.getIdRol());
				arrayRol.add(rol);
			}

			req.setAttribute("array", array);
			req.setAttribute("arrayRol", arrayRol);
			try {
				req.getRequestDispatcher("/WEB-INF/Views/Users/index.jsp").forward(req, resp);
				query.closeAll();

			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
