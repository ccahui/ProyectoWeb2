package controlador.curso;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import org.apache.tools.ant.util.SymbolicLinkUtils;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import modelo.Curso;
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
			
			
			Curso curso = pm.getObjectById(Curso.class, idLong);
			Long carreraId = curso.getIdCarrera();

			
			Query query = pm.newQuery(Nota.class);	
			query.setFilter("idCurso == idParam");
			query.declareParameters("Long idParam");
			query.deletePersistentAll(curso.getId());
			
			query.closeAll();
			System.out.println("Curso Eliminar");
			
			pm.deletePersistent(curso);
			resp.sendRedirect("/curso/carrera?id="+carreraId);
		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
			resp.sendRedirect("/curso");
		}
		finally{
			pm.close();
		}
	
	}

}

