package controlador.nota;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.alumno.Alumno;
import modelo.carrera.Carrera;
import modelo.curso.Curso;
import modelo.nota.Nota;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jdo.pmf.*;
@SuppressWarnings("serial")

public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Alumno.class);
		List<Alumno> array = (List<Alumno>)query.execute("select from Alumno");
		
		req.setAttribute("array", array);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Nota/insertar.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
		}
		
		finally{
			query.closeAll();
			pm.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//Realizar la persistencia
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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

