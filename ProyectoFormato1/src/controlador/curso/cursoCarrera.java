package controlador.curso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.Carrera;
import modelo.Curso;
import modelo.Profesor;
import modelo.Nota;
import modelo.PMF;
@SuppressWarnings("serial")
public class cursoCarrera extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Curso.class);
		//query.setOrdering("nombre descending");
		

		try {
			
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);
			
			Carrera obj = pm.getObjectById(Carrera.class,idLong);
			String nombreCarrera = obj.getNombre();
			
			
			query.setFilter("idCarrera == idParam");
			query.declareParameters("Long idParam");
			List<Curso> array = (List<Curso>) query.execute(idLong); 
			List<String> arrayNombreProfesor = new ArrayList<String>();
			
			Long idProfesor;
			Profesor profesor;
			for(Curso curso:array){
				idProfesor = curso.getIdProfesor();
				if(idProfesor != null){
					 profesor = pm.getObjectById(Profesor.class,curso.getIdProfesor());
					 arrayNombreProfesor.add(profesor.getNombre());
				}
				else {
					arrayNombreProfesor.add("");
				}
			}
			req.setAttribute("array", array);
			req.setAttribute("arrayProfesor",arrayNombreProfesor);
			req.setAttribute("nombreCarrera",nombreCarrera );
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Curso/cursoCarrera.jsp").forward(req, resp);

			query.closeAll();

		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
