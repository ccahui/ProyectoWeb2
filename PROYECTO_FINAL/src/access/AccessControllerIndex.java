package access;

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

import controller.ACCESO;
import controller.PMF;
import model.entity.Access;
import model.entity.AccesoAux;
import model.entity.Resource;
import model.entity.Role;
import model.entity.Users;

@SuppressWarnings("serial")
public class AccessControllerIndex extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){

			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query0 = pm.newQuery(Access.class);

			List<Access> listAcceso = (List<Access>)query0.execute("select from Acceso");

			List<AccesoAux> array = new ArrayList<AccesoAux>();
			AccesoAux nuevo;

			for(Access access:listAcceso){

				Role rol = pm.getObjectById(Role.class,access.getIdRole());
				Resource resource = pm.getObjectById(Resource.class,access.getIdRecurso());
				nuevo = new AccesoAux(access.getId(),rol.getNombre(),resource.getUrl(),access.isEstado());
				array.add(nuevo);
			}

			req.setAttribute("array", array);

			try {
				req.getRequestDispatcher("/WEB-INF/Views/Access/index.jsp").forward(req, resp);
				query0.closeAll();

			} catch (ServletException e) {
				// TODO Auto-generated catch block
				System.out.println("Error" + e.toString());
			}
			finally {
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
	
}
