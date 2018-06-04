package controlador.curso;
import java.io.IOException;
import javax.servlet.http.*;
import modelo.curso.*;
import javax.jdo.PersistenceManager;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class eliminar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);

			Curso curso = pm.getObjectById(Curso.class, idLong);
			pm.deletePersistent(curso);

		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/curso");
	}

}

