package users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Users;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class UsersControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){

			String codigo = req.getParameter("id");
			Long codigoLong = Long.parseLong(codigo);
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try{
				Query query = pm.newQuery(Users.class);
				query.setFilter("id == codigoParam");
				query.declareParameters("Long codigoParam");
				query.deletePersistentAll(codigoLong);
				query.closeAll();
			}
			finally{
				pm.close();
			}
			resp.sendRedirect("/users");
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

