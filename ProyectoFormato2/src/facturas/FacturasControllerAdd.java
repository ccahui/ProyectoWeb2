package facturas;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import javax.jdo.Query;
@SuppressWarnings("serial")
public class FacturasControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
		try {
			req.getRequestDispatcher("/WEB-INF/Views/Facturas/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		String dni = req.getParameter("dni");
		String mes = req.getParameter("mes");

		Query query = pm.newQuery(Alumno.class);

		try{

			int dniInt = Integer.parseInt(dni);
			int mesInt = Integer.parseInt(mes);

			query.setFilter("dni == idParam");
			query.declareParameters("int idParam");
			List<Alumno> arrayAlumno = (List<Alumno>)query.execute(dniInt);

			if(arrayAlumno.size()>0){

				System.out.println(dniInt + " "+arrayAlumno.get(0).getDni());
				Date fecha = new Date();
				Alumno alumno = arrayAlumno.get(0);
				Carrera carrera = pm.getObjectById(Carrera.class,alumno.getIdCarrera());	
				Factura factura = new Factura(alumno.getId(), mesInt,carrera.getMensualidad(), fecha);//
				pm.makePersistent(factura);
				resp.getWriter().print("1");//La operacion se realizo Correctamente
			}

			else {
				resp.getWriter().print("0");//Dni No existe en el Sistema

			}
		}

		catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}

		finally{
			pm.close();
		}
	}
}

