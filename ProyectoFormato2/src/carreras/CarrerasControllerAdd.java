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
public class CarrerasControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			try {
				req.getRequestDispatcher("/WEB-INF/Views/Carreras/add.jsp").forward(req, resp);
			} catch (ServletException e) {
				System.out.println("Error "+e.getMessage());
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



		PersistenceManager pm = PMF.get().getPersistenceManager();

		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String semestres = req.getParameter("semestres");
		String mensualidad = req.getParameter("mensualidad");

		try{

			int semestresInt = Integer.parseInt(semestres);
			int mensualidadInt = Integer.parseInt(mensualidad);

			Carrera carrera = new Carrera(nombre, descripcion, semestresInt, mensualidadInt);

			pm.makePersistent(carrera);
			resp.getWriter().print("1");//La operacion se realizo Correctamente

		}
		catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
	}
}

