package controlador.factura;

import java.io.IOException;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import modelo.Factura;
import modelo.PMF;
@SuppressWarnings("serial")
public class eliminar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String codigo = req.getParameter("id");
			Long codigoLong = Long.parseLong(codigo);
			Factura factura = pm.getObjectById(Factura.class, codigoLong);
			pm.deletePersistent(factura);
		}
		catch(Exception e){
			System.out.println("ERROR "+e.getMessage());
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/factura");
	}

}

