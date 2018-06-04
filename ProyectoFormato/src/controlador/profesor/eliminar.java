package controlador.profesor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.profesor.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class eliminar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String codigo = req.getParameter("id");
			Long codigoLong = Long.parseLong(codigo);

			Profesor prof = pm.getObjectById(Profesor.class, codigoLong);
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

