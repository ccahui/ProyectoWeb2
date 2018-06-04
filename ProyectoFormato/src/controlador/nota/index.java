package controlador.nota;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
import modelo.carrera.*;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class index extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Carrera.class);
		//query.setOrdering("nombre descending");
		
		try{
			List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");

			req.setAttribute("array", array);
			req.getRequestDispatcher("/WEB-INF/Vistas/Nota/index.jsp").forward(req, resp);
			
			query.closeAll();
		
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
