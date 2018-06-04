package controlador.carrera;

import java.io.IOException;
import javax.servlet.http.*;

import modelo.alumno.Alumno;
import modelo.carrera.*;
import javax.jdo.PersistenceManager;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class modificar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id); 
			Carrera carrera = pm.getObjectById(Carrera.class,idLong);

			req.setAttribute("carrera",carrera);
			req.getRequestDispatcher("/WEB-INF/Vistas/Carrera/modificar.jsp").forward(req, resp);

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
		String descripcion = req.getParameter("descripcion");
		String semestres = req.getParameter("semestres");
		String mensualidad = req.getParameter("mensualidad");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			
			Long idLong = Long.parseLong(id);
			int semestresInt = Integer.parseInt(semestres);
			int mensualidadInt = Integer.parseInt(mensualidad);

			Carrera carrera = pm.getObjectById(Carrera.class,idLong);

			carrera.setNombre(nombre);
			carrera.setDescripcion(descripcion);
			carrera.setSemestres(semestresInt);
			carrera.setMensualidad(mensualidadInt);
						
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

