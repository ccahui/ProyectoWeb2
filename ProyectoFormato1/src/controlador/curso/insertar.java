package controlador.curso;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import modelo.Carrera;
import modelo.Curso;
import modelo.PMF;;
@SuppressWarnings("serial")
public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Carrera.class);
		//query.setOrdering("nombre descending");
		List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");
		
		req.setAttribute("array", array);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Curso/insertar.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
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

