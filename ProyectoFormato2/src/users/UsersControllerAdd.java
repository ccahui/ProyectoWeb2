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
import model.entity.Role;
import model.entity.Users;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Role.class);
		//query.setOrdering("nombre descending");
		
		List<Role> array = (List<Role>)query.execute("select from Role");
		
		req.setAttribute("array", array);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Users/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
			//Realizar la persistencia
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String email = req.getParameter("email");
		Long idRol = Long.parseLong(req.getParameter("idRol"));
	
		boolean estado = true;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Users usuario = new Users (nombre,apellido,email,idRol,estado);
		
		try{
			
			Query query = pm.newQuery(Users.class);
			query.setFilter("email == idParam");
			query.declareParameters("String idParam");

			List<Role> arrayRecurso = (List<Role>)query.execute(email);

			if(arrayRecurso.size() > 0 ){
				resp.getWriter().print("2");
			}
			else {
				Users user = new Users(nombre, apellido, email, idRol, estado);
				pm.makePersistent(user);
				query.closeAll();
				resp.getWriter().print("1");
			}
			
			}
		finally{
			pm.close();
		}
	}

	
}

