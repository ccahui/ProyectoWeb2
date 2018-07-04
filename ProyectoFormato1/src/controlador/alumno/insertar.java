package controlador.alumno;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import modelo.Alumno;
import modelo.Carrera;
import modelo.Curso;
import modelo.Nota;
import modelo.PMF;

@SuppressWarnings("serial")
public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Carrera.class);
		List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");
		
		req.setAttribute("array", array);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Alumno/insertar.jsp").forward(req, resp);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

			Alumno prof = new Alumno(nombre, apellido, dniI, gmail, idCarreraL, semestre, estado, idRol);

			pm.makePersistent(prof);
			
			/*INSERTO NOTAS*/
			if(insertarNota(prof,pm))
			resp.getWriter().print("1");//La operacion se realizo Correctamente

		}
		catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}
	}
	public static boolean insertarNota(Alumno alumno, PersistenceManager pmf){
				
				PersistenceManager pm = pmf;
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
					return true;
					
				}
				catch(Exception e){
					System.out.println("Error "+e.getMessage());
					return false;
				}
				finally{
					pm.close();
				}
		
	}
}

