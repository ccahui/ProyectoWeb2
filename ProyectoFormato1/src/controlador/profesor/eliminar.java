package controlador.profesor;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import modelo.Curso;
import modelo.Nota;
import modelo.Profesor;
import modelo.PMF;
@SuppressWarnings("serial")
public class eliminar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String codigo = req.getParameter("id");
			Long codigoLong = Long.parseLong(codigo);

			Profesor prof = pm.getObjectById(Profesor.class, codigoLong);
			
			Query query = pm.newQuery(Curso.class);
		
			query.setFilter("idProfesor == idParam");
			query.declareParameters("Long idParam");
			
			List<Curso> arrayNota = (List<Curso>) query.execute(codigoLong); 
			for(Curso curso : arrayNota){
				curso.setIdProfesor(null);
			}
			
			pm.deletePersistent(prof);

		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/profesor");
	}

}

