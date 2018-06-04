package controlador.administrador;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
import modelo.administrador.Administrador;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class index extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Administrador.class);
		//query.setOrdering("nombre descending");
		List<Administrador> array = (List<Administrador>)query.execute("select from Administrador");
		
		try {
			req.setAttribute("array", array);
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Administrador/index.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
