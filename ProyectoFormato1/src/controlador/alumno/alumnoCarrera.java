package controlador.alumno;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import modelo.Alumno;
import modelo.Carrera;
import modelo.PMF;

@SuppressWarnings("serial")
public class alumnoCarrera extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Alumno.class);
		//query.setOrdering("nombre descending");
		
		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);
			
			Carrera carrera = pm.getObjectById(Carrera.class,idLong);
			
			
			query.setFilter("idCarrera == idParam");
			query.declareParameters("Long idParam");
			List<Alumno> array = (List<Alumno>) query.execute(idLong); 

			
			req.setAttribute("carrera",carrera);
			req.setAttribute("array", array);
			req.getRequestDispatcher("/WEB-INF/Vistas/Alumno/alumnoCarrera.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
