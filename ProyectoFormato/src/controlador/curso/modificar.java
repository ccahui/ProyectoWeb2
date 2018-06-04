package controlador.curso;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import modelo.carrera.Carrera;
import modelo.curso.Curso;
import modelo.profesor.Profesor;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jdo.pmf.*;
@SuppressWarnings("serial")
public class modificar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			Query query = pm.newQuery(Carrera.class);
			Query query1 = pm.newQuery(Profesor.class);

			List<Carrera> arrayCarrera = (List<Carrera>)query.execute("select from Carrera");
			List<Profesor> arrayProfesor = (List<Profesor>)query1.execute("select from Profesor");

			req.setAttribute("arrayCarrera", arrayCarrera);
			req.setAttribute("arrayProfesor", arrayProfesor);

			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id); 
			Curso curso = pm.getObjectById(Curso.class,idLong);

			req.setAttribute("curso",curso);
			req.getRequestDispatcher("/WEB-INF/Vistas/Curso/modificar.jsp").forward(req, resp);

			query.closeAll();
			query1.closeAll();

		} catch (Exception e) {
			System.out.println("Error " +e.getMessage());
		}
		finally{
			pm.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String id = req.getParameter("id");
		//		String idCarrera = req.getParameter("idCarrera");
		String idProfesor = req.getParameter("idProfesor");
		String nombre = req.getParameter("nombre");
		String semestre = req.getParameter("semestre");


		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{

			Long idLong = Long.parseLong(id);
			//			Long idCarreraLong = Long.parseLong(idCarrera);
			Long idProfesorLong = Long.parseLong(idProfesor);
			int semestreInt = Integer.parseInt(semestre);

			Curso carrera = pm.getObjectById(Curso.class,idLong);

			//			carrera.setIdCarrera(idCarreraLong);
			carrera.setNombre(nombre);

			
			//carrera.setIdCarrera(new Long("4573968371548160"));
			if(idProfesor.equals("0")){
				carrera.setIdProfesor(null);
			}
			else {
				carrera.setIdProfesor(idProfesorLong);
			}
			
			carrera.setSemestre(semestreInt);
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

