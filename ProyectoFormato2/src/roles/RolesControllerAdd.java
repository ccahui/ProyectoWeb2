package roles;

import java.io.IOException;
import java.util.Date;
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
public class RolesControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			try {
				req.getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
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

		//Realizar la persistencia
		String nombre = req.getParameter("nombre");
		
		Date fecha = new Date(); 

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			
			Query query = pm.newQuery(Role.class);
			query.setFilter("nombre == idParam");
			query.declareParameters("String idParam");

			List<Role> arrayRecurso = (List<Role>)query.execute(nombre.toUpperCase());


			if(arrayRecurso.size() > 0 ){
				resp.getWriter().print("2");
			}
			else {
				Role rol = new Role(nombre.toUpperCase(),fecha,true);
				pm.makePersistent(rol);
				query.closeAll();
				resp.getWriter().print("1");
			}
			
		}
		finally{
			pm.close();
		}
	}


}

