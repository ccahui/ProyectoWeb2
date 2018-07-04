package controlador.alumno;

import java.io.IOException;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import modelo.Alumno;
import modelo.Nota;
import modelo.PMF;

@SuppressWarnings("serial")
public class eliminar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);

			Alumno alumno = pm.getObjectById(Alumno.class, idLong);
			Long idCarrera = alumno.getIdCarrera();
			
			Query query = pm.newQuery(Nota.class);
			query.setFilter("idAlumno == idParam");
			query.declareParameters("Long idParam");
			
			query.deletePersistentAll(idLong); 
			pm.deletePersistent(alumno);

			System.out.println("1");
			
			resp.sendRedirect("/alumno/carrera?id="+idCarrera);
		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
			resp.sendRedirect("/alumno");
		}
		finally{
			pm.close();
		}
		
	}

}

