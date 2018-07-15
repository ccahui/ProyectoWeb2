package notas;

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
import model.entity.Curso;
import model.entity.Nota;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
@SuppressWarnings("serial")

public class NotasControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Alumno.class);
		List<Alumno> array = (List<Alumno>)query.execute("select from Alumno");
		
		req.setAttribute("array", array);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Notas/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
		}
		
		finally{
			query.closeAll();
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

		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String idAlumno = req.getParameter("idAlumno");
		
		Integer nota1 = null;/*Nota por Defecto*/
		Integer nota2 = null;
		Integer nota3 = null;
		
		try{

			Long idAlumnoLong = Long.parseLong(idAlumno);
			Alumno alumno = pm.getObjectById(Alumno.class,idAlumnoLong);
			
			Long idCarrera = alumno.getIdCarrera();
			Query query = pm.newQuery(Curso.class);
			query.setFilter("idCarrera == idParam");
			query.declareParameters("Long idParam");
			List<Curso> arrayCurso = (List<Curso>) query.execute(idCarrera); //Obtengo los Cursos que tiene la Carrera
			
			for(Curso curso:arrayCurso){
				Nota nota = new Nota(idAlumnoLong,curso.getId(), nota1, nota2, nota3);
			    pm.makePersistent(nota);
			}
			query.closeAll();
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

