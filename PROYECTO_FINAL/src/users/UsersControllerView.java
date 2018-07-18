package users;

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

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import controller.ACCESO;
import controller.PMF;

@SuppressWarnings("serial")
public class UsersControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {

				String codigo = req.getParameter("id");
				Long codigoLong = Long.parseLong(codigo); 
				Users usuario = pm.getObjectById(Users.class,codigoLong);

				Query query = pm.newQuery(Role.class);
				//query.setOrdering("nombre descending");

				List<Role> array = (List<Role>)query.execute("select from Role");

				req.setAttribute("array", array);


				req.setAttribute("usuario",usuario);
				req.getRequestDispatcher("/WEB-INF/Views/Users/view.jsp").forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				pm.close();
			}}else {

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
		String codigo = req.getParameter("id");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String email = req.getParameter("email");
		String estado = req.getParameter("estado");
		Long idRol = Long.parseLong(req.getParameter("idRol"));

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Long codigoLong = Long.parseLong(codigo);
			boolean estadoBoolean = Boolean.parseBoolean(estado);

			Users usuario = pm.getObjectById(Users.class,codigoLong);

			System.out.println(usuario.getApellido());
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setEmail(email);
			usuario.setEstado(estadoBoolean);
			usuario.setIdRol(idRol);
		}
		catch(Exception e){
			System.out.println("Se produjo un Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/users");
	}
	

}

