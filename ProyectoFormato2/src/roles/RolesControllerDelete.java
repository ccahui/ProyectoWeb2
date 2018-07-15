package roles;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;
import model.entity.Users;
import controller.ACCESO;
import controller.PMF;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class RolesControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try{
				Query query = pm.newQuery(Role.class);
				query.setFilter("id == idParam");
				query.declareParameters("Long idParam");
				query.deletePersistentAll(idLong);
				query.closeAll();
			}
			finally{
				pm.close();
			}
			resp.sendRedirect("/roles");}
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

