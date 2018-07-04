package controlador.nota;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
import modelo.Curso;
import modelo.Carrera;
import modelo.PMF;
@SuppressWarnings("serial")
public class notaCarrera extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Curso.class);
		//query.setOrdering("nombre descending");
		
		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);
			
			Carrera carrera = pm.getObjectById(Carrera.class,idLong);
			String nombreCarrera = carrera.getNombre();
			
			
			query.setFilter("idCarrera == idParam");
			query.declareParameters("Long idParam");
			List<Curso> array = (List<Curso>) query.execute(idLong); 
			
			req.setAttribute("nombreCarrera",nombreCarrera);
			req.setAttribute("array", array);
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Nota/notaCarrera.jsp").forward(req, resp);
			
			query.closeAll();
		
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
