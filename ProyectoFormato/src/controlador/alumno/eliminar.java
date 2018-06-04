package controlador.alumno;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.alumno.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class eliminar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);

			Alumno alumno = pm.getObjectById(Alumno.class, idLong);
			pm.deletePersistent(alumno);

		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/alumno");
	}

}

