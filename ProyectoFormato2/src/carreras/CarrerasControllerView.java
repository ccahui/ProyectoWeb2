package carreras;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Carrera;

import javax.jdo.PersistenceManager;
@SuppressWarnings("serial")
public class CarrerasControllerView extends HttpServlet {
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
				Carrera carrera = pm.getObjectById(Carrera.class,idLong);

				req.setAttribute("carrera",carrera);
				req.getRequestDispatcher("/WEB-INF/Views/Carreras/view.jsp").forward(req, resp);

			} catch (Exception e) {
				System.out.println("Error " +e.getMessage());
			}
			finally{
				pm.close();
			}

		}else {

			try {
				req.getRequestDispatcher("/WEB-INF/Views/AccesoDenegado.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


		String id = req.getParameter("id");
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String semestres = req.getParameter("semestres");
		String mensualidad = req.getParameter("mensualidad");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{

			Long idLong = Long.parseLong(id);
			int semestresInt = Integer.parseInt(semestres);
			int mensualidadInt = Integer.parseInt(mensualidad);

			Carrera carrera = pm.getObjectById(Carrera.class,idLong);

			carrera.setNombre(nombre);
			carrera.setDescripcion(descripcion);
			carrera.setSemestres(semestresInt);
			carrera.setMensualidad(mensualidadInt);

			resp.getWriter().print("1");

		}
		catch(Exception e){
			System.out.println("Se produjo un Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
	}
}

