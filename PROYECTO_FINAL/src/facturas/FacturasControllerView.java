package facturas;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Alumno;
import model.entity.Carrera;
import model.entity.Factura;

import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class FacturasControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
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
			
			req.getRequestDispatcher("/WEB-INF/Views/Facturas/view.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			pm.close();
		}
	}
		else {

			try {
				req.getRequestDispatcher("/WEB-INF/Views/AccesoDenegado.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
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

