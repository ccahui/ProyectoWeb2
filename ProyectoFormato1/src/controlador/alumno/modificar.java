package controlador.alumno;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import modelo.Alumno;
import modelo.Carrera;
import modelo.PMF;
@SuppressWarnings("serial")
public class modificar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			Query query = pm.newQuery(Carrera.class);
			List<Carrera> array = (List<Carrera>)query.execute("select from Carrera");
			req.setAttribute("array", array);
			
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id); 
			Alumno alumno = pm.getObjectById(Alumno.class,idLong);
			
			req.setAttribute("alumno",alumno);
			req.getRequestDispatcher("/WEB-INF/Vistas/Alumno/modificar.jsp").forward(req, resp);
		
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
	//	String carrera = req.getParameter("idCarrera");
		String semestre = req.getParameter("semestre");
		String gmail = req.getParameter("gmail");
		String dni = req.getParameter("dni");
	//	String idRol = req.getParameter("idRol");
		String estado = req.getParameter("estado");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
		//	Long idRolLong = Long.parseLong(idRol);
		//	Long idCarreraLong = Long.parseLong(carrera);
			int semestreInt = Integer.parseInt(semestre);
			int dniInt = Integer.parseInt(dni);
			boolean estadoBoolean = Boolean.parseBoolean(estado);
			
			Alumno alumno = pm.getObjectById(Alumno.class,idLong);
			
			alumno.setNombre(nombre);
			alumno.setApellido(apellido);
			alumno.setDni(dniInt);
			alumno.setGmail(gmail);
			alumno.setSemestre(semestreInt);
			alumno.setEstado(estadoBoolean);
		//	alumno.setIdRol(idRolLong);
		//	alumno.setIdCarrera(idCarreraLong);
	
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

