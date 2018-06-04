package controlador.profesor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.profesor.*;
import javax.jdo.PersistenceManager;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Profesor/insertar.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String gmail = req.getParameter("gmail");
		String dni = req.getParameter("dni");
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		
		
		try{
			
			int dniInt = Integer.parseInt(dni);
			Long idRol = new Long("1");//Id Rol de Defecto
			boolean estado = true;
			
			Profesor prof = new Profesor(nombre, apellido, dniInt, gmail, estado, idRol);
			pm.makePersistent(prof);
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

