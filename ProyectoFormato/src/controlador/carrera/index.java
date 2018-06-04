package controlador.carrera;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.carrera.Carrera;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class index extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Carrera.class);
		//query.setOrdering("nombre descending");
		List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");
		
		req.setAttribute("array", array);
		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Carrera/index.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
