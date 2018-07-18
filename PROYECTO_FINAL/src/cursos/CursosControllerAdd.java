package cursos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Carrera;
import model.entity.Curso;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;;
@SuppressWarnings("serial")
public class CursosControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Carrera.class);
		//query.setOrdering("nombre descending");
		List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");
		
		req.setAttribute("array", array);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Cursos/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			query.closeAll();
			pm.close();
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

		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String idCarrera = req.getParameter("idCarrera");
		String nombre = req.getParameter("nombre");
		String semestre = req.getParameter("semestre");

		try{

			int semestreInt = Integer.parseInt(semestre);
			Long idCarreraLong = Long.parseLong(idCarrera);
			Long idProfesorId = null;//Por defecto NO tiene nigun profe Asignado al Curso
			
			Curso curso = new Curso(idCarreraLong,idProfesorId, nombre, semestreInt);
			pm.makePersistent(curso);
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

