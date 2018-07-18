package alumnos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Alumno;
import model.entity.Carrera;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
@SuppressWarnings("serial")
public class AlumnosControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){

			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {

				Query query = pm.newQuery(Carrera.class);
				List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");
				req.setAttribute("array", array);

				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id); 
				Alumno alumno = pm.getObjectById(Alumno.class,idLong);

				req.setAttribute("alumno",alumno);
				req.getRequestDispatcher("/WEB-INF/Views/Alumnos/view.jsp").forward(req, resp);

				query.closeAll();

			} catch (Exception e) {
				System.out.println("Error " +e.getMessage());
			}
			finally{
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

	
		String id = req.getParameter("id");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		//	String carrera = req.getParameter("idCarrera");
		String semestre = req.getParameter("semestre");
		String gmail = req.getParameter("gmail");
		String dni = req.getParameter("dni");
		//	String idRol = req.getParameter("idRol");
		String estado = req.getParameter("estado");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Long idLong = Long.parseLong(id);
			//	Long idRolLong = Long.parseLong(idRol);
			//	Long idCarreraLong = Long.parseLong(carrera);
			int semestreInt = Integer.parseInt(semestre);
			int dniInt = Integer.parseInt(dni);
			boolean estadoBoolean = Boolean.parseBoolean(estado);

			Alumno alumno = pm.getObjectById(Alumno.class,idLong);

			alumno.setNombre(nombre);
			alumno.setApellido(apellido);
			alumno.setDni(dniInt);
			alumno.setGmail(gmail);
			alumno.setSemestre(semestreInt);
			alumno.setEstado(estadoBoolean);
			//	alumno.setIdRol(idRolLong);
			//	alumno.setIdCarrera(idCarreraLong);

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

