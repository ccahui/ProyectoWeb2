package access;

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
public class AccessControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			try {

				Query query = pm.newQuery(Role.class);
				List<Role> arrayRole = (List<Role>)query.execute("select from Role");

				Query query1 = pm.newQuery(Resource.class);
				List<Resource> arrayRecurso = (List<Resource>)query1.execute("select from Recurso");

				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id); 

				Access access = pm.getObjectById(Access.class,idLong);

				req.setAttribute("acceso",access);
				req.setAttribute("arrayRole",arrayRole);
				req.setAttribute("arrayRecurso",arrayRecurso);



				req.getRequestDispatcher("/WEB-INF/Views/Access/view.jsp").forward(req, resp);

				query.closeAll();
				query1.closeAll();
			} catch (Exception e) {
				System.out.println("Error "+e.getMessage());
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
		String idRole = req.getParameter("role");
		String idRecurso = req.getParameter("recurso");
		String estado = req.getParameter("estado");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Long idLong = Long.parseLong(id);
			Long idRoleL = Long.parseLong(idRole);
			Long idRecursoL = Long.parseLong(idRecurso);

			boolean estadoBool = Boolean.parseBoolean(estado);

			Access access = pm.getObjectById(Access.class,idLong);
			access.setIdRecurso(idRecursoL);
			access.setIdRole(idRoleL);
			access.setEstado(estadoBool);

		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/access");
	}
}

