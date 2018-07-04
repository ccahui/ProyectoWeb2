package controlador.acceso;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import modelo.PMF;
import modelo.Acceso;
import modelo.Recurso;
import modelo.Role;
import modelo.Usuario;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();

			Query query = pm.newQuery(Role.class);
			List<Role> arrayRole = (List<Role>)query.execute("select from Role");

			Query query1 = pm.newQuery(Recurso.class);
			//query.setOrdering("nombre descending");
			List<Recurso> arrayRecurso = (List<Recurso>)query1.execute("select from Recurso");


			try {
				req.setAttribute("arrayRole",arrayRole);
				req.setAttribute("arrayRecurso",arrayRecurso);
				req.getRequestDispatcher("/WEB-INF/Vistas/Acceso/insertar.jsp").forward(req, resp);

				query.closeAll();
				query1.closeAll();

			} catch (ServletException e) {
				System.out.println("Error");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		else {

			try {
				req.getRequestDispatcher("/WEB-INF/Vistas/AccesoDenegado.jsp").forward(req, resp);
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

			Query query = pm.newQuery(Acceso.class);
			query.setFilter("idRole == idParam && idRecurso == idParam2");
			query.declareParameters("Long idParam , Long idParam2");

			List<Acceso> array = (List<Acceso>) query.execute(idRoleL,idRecursoL);

			if(array.size()> 0 ){
				resp.getWriter().print("2");
			}

			else {
				Acceso acceso = new Acceso(idRoleL,idRecursoL,true);
				pm.makePersistent(acceso);
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
	private boolean accesoRecurso(String gmail,String url){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Usuario.class);
		query.setFilter("email == idParam");
		query.declareParameters("String idParam");

		List<Usuario> array = (List<Usuario>) query.execute(gmail);

		if(array.size()> 0 ){

			Usuario usuario = array.get(0);
			Query query1 = pm.newQuery(Recurso.class);
			query1.setFilter("url == idParam");
			query1.declareParameters("String idParam");

			List<Recurso> arrayRecurso = (List<Recurso>)query1.execute(url);


			if(arrayRecurso.size() > 0 ){

				Recurso recurso = arrayRecurso.get(0);

				Long idRol = usuario.getIdRol();
				Long idRecurso = recurso.getId();

				Query query2 = pm.newQuery(Acceso.class);
				query2.setFilter("idRole == idParam && idRecurso == idParam2");
				query2.declareParameters("Long idParam , Long idParam2");
				System.out.println("das");

				List<Acceso> arrayAcceso = (List<Acceso>)query2.execute(idRol,idRecurso);

				if(arrayAcceso.size()>0){
					Acceso acceso = arrayAcceso.get(0);
					if(acceso.isEstado()){
						pm.close();
						return true;
					}
				}
			}

		}
		pm.close();
		return false;
	}
}

