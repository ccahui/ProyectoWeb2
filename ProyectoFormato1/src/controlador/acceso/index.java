package controlador.acceso;

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

import modelo.PMF;
import modelo.Acceso;
import modelo.AccesoAux;
import modelo.Recurso;
import modelo.Role;
import modelo.Usuario;

@SuppressWarnings("serial")
public class index extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || accesoRecurso(user.getEmail(),req.getRequestURI())){

			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query0 = pm.newQuery(Acceso.class);

			List<Acceso> listAcceso = (List<Acceso>)query0.execute("select from Acceso");

			List<AccesoAux> array = new ArrayList<AccesoAux>();
			AccesoAux nuevo;

			for(Acceso acceso:listAcceso){

				Role rol = pm.getObjectById(Role.class,acceso.getIdRole());
				Recurso recurso = pm.getObjectById(Recurso.class,acceso.getIdRecurso());
				nuevo = new AccesoAux(acceso.getId(),rol.getNombre(),recurso.getUrl(),acceso.isEstado());
				array.add(nuevo);
			}

			req.setAttribute("array", array);

			try {
				req.getRequestDispatcher("/WEB-INF/Vistas/Acceso/index.jsp").forward(req, resp);
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
				req.getRequestDispatcher("/WEB-INF/Vistas/AccesoDenegado.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
