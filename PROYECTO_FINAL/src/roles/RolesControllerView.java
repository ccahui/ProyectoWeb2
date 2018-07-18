package roles;

import java.io.IOException;
import java.util.Date;
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
public class RolesControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {

				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id); 
				Role rol = pm.getObjectById(Role.class,idLong);
				req.setAttribute("role",rol);
				req.getRequestDispatcher("/WEB-INF/Views/Roles/view.jsp").forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				pm.close();
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
		String id = req.getParameter("id");
		String nombre = req.getParameter("nombre");
		String estado = req.getParameter("estado");
		Date fecha = new Date();

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Long idLong = Long.parseLong(id);
			boolean estadoBool = Boolean.parseBoolean(estado);

			Role prof = pm.getObjectById(Role.class,idLong);
			prof.setNombre(nombre.toUpperCase());
			prof.setFecha(fecha);
			prof.setEstado(estadoBool);

		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/roles");
	}

}

