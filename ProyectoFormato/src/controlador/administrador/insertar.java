package controlador.administrador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.administrador.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jdo.pmf.*;
@SuppressWarnings("serial")
public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Administrador.class);
		List<Administrador> array = (List<Administrador>)query.execute("select from Administrador");
		
		req.setAttribute("array", array);
		
		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Administrador/insertar.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
		}
		
		finally{
			query.closeAll();
			pm.close();
			
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//Realizar la persistencia
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String gmail = req.getParameter("gmail");

		boolean estado = true; // true == ACTIVO 	false == INACTIVO 

		try{
			Administrador admin = new Administrador(nombre, apellido,gmail,estado);

			pm.makePersistent(admin);
			resp.getWriter().print("1");//La operacion se realizo Correctamente

		}
		catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
	}
}

