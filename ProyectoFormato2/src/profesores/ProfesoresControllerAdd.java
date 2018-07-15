package profesores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.jdo.PersistenceManager;
import model.entity.Profesor;
import controller.*;
@SuppressWarnings("serial")
public class ProfesoresControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Profesores/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String gmail = req.getParameter("gmail");
		String dni = req.getParameter("dni");
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
			
		try{
			
			int dniInt = Integer.parseInt(dni);
			Long idRol = new Long("1");//Id Rol de Defecto
			boolean estado = true;
			
			Profesor prof = new Profesor(nombre, apellido, dniInt, gmail, estado, idRol);
			pm.makePersistent(prof);
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

