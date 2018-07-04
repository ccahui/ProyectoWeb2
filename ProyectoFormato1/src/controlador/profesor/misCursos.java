package controlador.profesor;

import java.io.IOException;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
import modelo.Curso;
import modelo.Profesor;
import modelo.PMF;
@SuppressWarnings("serial")
public class misCursos extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		

		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Curso.class);
		//query.setOrdering("nombre descending");
		
		try{
			String id = "6473924464345088"; //id Profesor
			Long idProfesor = Long.parseLong(id);
	
			query.setFilter("idProfesor == idParam");
			query.declareParameters("Long idParam");
			
			List<Curso> array = (List<Curso>) query.execute(idProfesor); 
			
			Profesor profesor = pm.getObjectById(Profesor.class,idProfesor); 
			
			req.setAttribute("array", array);
			req.setAttribute("profesor", profesor);
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Profesor/misCursos.jsp").forward(req, resp);
			query.closeAll();
			
			
		
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
