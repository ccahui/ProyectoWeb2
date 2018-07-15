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
import model.entity.Curso;
import model.entity.Nota;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class AlumnosControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Carrera.class);
			List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");

			req.setAttribute("array", array);

			try {
				req.getRequestDispatcher("/WEB-INF/Views/Alumnos/add.jsp").forward(req, resp);
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

		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String idCarrera = req.getParameter("idCarrera");
		String dni = req.getParameter("dni");
		String gmail = req.getParameter("gmail");

		int semestre = 1; //Por Defecto Comienza en el Primer Semestre
		boolean estado = true; // true == ACTIVO 	false == INACTIVO 

		try{

			Long idRol = new Long("1"); //Rol por defecto //Los roles seran ADMIN, ALUMNO, PROFESOR
			Long idCarreraL = Long.parseLong(idCarrera);
			int dniI = Integer.parseInt(dni);

			if(dniUnico(dniI)){
				System.out.println("bien");
				Alumno prof = new Alumno(nombre, apellido, dniI, gmail, idCarreraL, semestre, estado, idRol);
				pm.makePersistent(prof);
				/*INSERTO NOTAS*/
				if(insertarNota(prof))
					resp.getWriter().print("1");
			}
			else {
				//Dni Duplicado
				resp.getWriter().print("2");
			}
			
		}
		catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
	}
	public static boolean insertarNota(Alumno alumno){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Integer nota1 = null;/*Nota por Defecto*/
		Integer nota2 = null;
		Integer nota3 = null;

		try{
			Long idAlumnoLong = alumno.getId();
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
			pm.close();
			return true;

		}
		catch(Exception e){
			System.out.println("Error "+e.getMessage());
			pm.close();
		}
		return false;
	}
	public static boolean dniUnico(int dni){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Query query = pm.newQuery(Alumno.class);
			query.setFilter("dni == idParam");
			query.declareParameters("int idParam");
			List<Curso> arrayAlumno= (List<Curso>) query.execute(dni); //Obtengo los Cursos que tiene la Carrera


			if(arrayAlumno.size() == 0){
				query.closeAll();
				pm.close();
				return true;
			}

			query.closeAll();
		}
		catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
		return false;

	}

}

