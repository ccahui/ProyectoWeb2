package controlador.administrador;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;
import modelo.administrador.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jdo.pmf.*;
@SuppressWarnings("serial")
public class modificar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			Query query = pm.newQuery(Administrador.class);
			List<Administrador> array = (List<Administrador>)query.execute("select from Administrador");
			req.setAttribute("array", array);
			
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id); 
			Administrador admin = pm.getObjectById(Administrador.class,idLong);
			
			req.setAttribute("administrador",admin);
			req.getRequestDispatcher("/WEB-INF/Vistas/Administrador/modificar.jsp").forward(req, resp);
		
			query.closeAll();
		} catch (Exception e) {
			System.out.println("Error " +e.getMessage());
		}
		finally{
			pm.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String id = req.getParameter("id");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String gmail = req.getParameter("gmail");
		String estado = req.getParameter("estado");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			boolean estadoBoolean = Boolean.parseBoolean(estado);
			
			Administrador admin = pm.getObjectById(Administrador.class,idLong);
			
			admin.setNombre(nombre);
			admin.setApellido(apellido);
			admin.setGmail(gmail);
			admin.setEstado(estadoBoolean);
			
			resp.getWriter().print("1");
		}
		catch(Exception e){
			System.out.println("Se produjo un Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
	}
}

