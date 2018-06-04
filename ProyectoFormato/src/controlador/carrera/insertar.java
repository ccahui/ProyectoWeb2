package controlador.carrera;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.carrera.*;
import javax.jdo.PersistenceManager;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Carrera/insertar.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Error "+e.getMessage());
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
		String descripcion = req.getParameter("descripcion");
		String semestres = req.getParameter("semestres");
		String mensualidad = req.getParameter("mensualidad");

		try{

			int semestresInt = Integer.parseInt(semestres);
			int mensualidadInt = Integer.parseInt(mensualidad);

			Carrera carrera = new Carrera(nombre, descripcion, semestresInt, mensualidadInt);

			pm.makePersistent(carrera);
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

