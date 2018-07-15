package access;

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
public class AccessControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();

			Query query = pm.newQuery(Role.class);
			List<Role> arrayRole = (List<Role>)query.execute("select from Role");

			Query query1 = pm.newQuery(Resource.class);
			//query.setOrdering("nombre descending");
			List<Resource> arrayRecurso = (List<Resource>)query1.execute("select from Recurso");


			try {
				req.setAttribute("arrayRole",arrayRole);
				req.setAttribute("arrayRecurso",arrayRecurso);
				req.getRequestDispatcher("/WEB-INF/Views/Access/add.jsp").forward(req, resp);

				query.closeAll();
				query1.closeAll();

			} catch (ServletException e) {
				System.out.println("Error "+e.getMessage());
				// TODO Auto-generated catch block
				//e.printStackTrace();
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
		String idRecurso = req.getParameter("recurso");
		String idRole = req.getParameter("role");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Long idRecursoL = Long.parseLong(idRecurso);
			Long idRoleL = Long.parseLong(idRole);

			Query query = pm.newQuery(Access.class);
			query.setFilter("idRole == idParam && idRecurso == idParam2");
			query.declareParameters("Long idParam , Long idParam2");

			List<Access> array = (List<Access>) query.execute(idRoleL,idRecursoL);

			if(array.size()> 0 ){
				resp.getWriter().print("2");
			}

			else {
				Access access = new Access(idRoleL,idRecursoL,true);
				pm.makePersistent(access);
				query.closeAll();
				resp.getWriter().print("1");
			}

		}
		catch(Exception e){
			System.out.println("Se produjo un Error"+e.getMessage());
		}
		finally{
			pm.close();
		}

	}
	
}

