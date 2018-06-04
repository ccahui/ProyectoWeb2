package controlador.profesor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.profesor.*;
import javax.jdo.PersistenceManager;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class modificar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			String codigo = req.getParameter("id");
			Long codigoLong = Long.parseLong(codigo); 
			Profesor prof = pm.getObjectById(Profesor.class,codigoLong);
			req.setAttribute("profesor",prof);
			req.getRequestDispatcher("/WEB-INF/Vistas/Profesor/modificar.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
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
		String dni = req.getParameter("dni");
		String idRol = req.getParameter("idRol");
		String estado = req.getParameter("estado");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			
			Long idLong = Long.parseLong(id);
			int dniInt = Integer.parseInt(dni);
			Long idRolLong = Long.parseLong(idRol);
			boolean estadoBoolean = Boolean.parseBoolean(estado);
			
			Profesor prof = pm.getObjectById(Profesor.class,idLong);
			
			prof.setNombre(nombre);
			prof.setApellido(apellido);
			prof.setDni(dniInt);
			prof.setGmail(gmail);
			prof.setIdRol(idRolLong);
			prof.setEstado(estadoBoolean);
			
			resp.getWriter().print("1");
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
	}
}

