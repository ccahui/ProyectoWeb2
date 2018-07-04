package controlador.factura;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import modelo.Factura;
import modelo.Alumno;
import modelo.Carrera;
import modelo.PMF;

@SuppressWarnings("serial")
public class modificar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);
			
			Factura factura= pm.getObjectById(Factura.class,idLong);
			Alumno alumno = pm.getObjectById(Alumno.class,factura.getIdAlumno());
			Carrera carrera = pm.getObjectById(Carrera.class,alumno.getIdCarrera());
			
			req.setAttribute("factura",factura);
			req.setAttribute("alumno", alumno);
			req.setAttribute("carrera", carrera);
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Factura/modificar.jsp").forward(req, resp);
			
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
		String mes = req.getParameter("mes");
		String monto = req.getParameter("monto");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			
			Long idLong = Long.parseLong(id);
			int mesInt = Integer.parseInt(mes);
			int montoInt = Integer.parseInt(monto);
			Date fecha = new Date();
			
			Factura factura = pm.getObjectById(Factura.class,idLong);
			factura.setFecha(fecha);
			factura.setMes(mesInt);
			factura.setMonto(montoInt);
			
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

