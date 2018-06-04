package controlador.nota;

import java.io.IOException;
import javax.servlet.http.*;
import modelo.nota.*;
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

			Nota nota = pm.getObjectById(Nota.class, idLong);
			pm.deletePersistent(nota);

		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/nota");
	}

}

