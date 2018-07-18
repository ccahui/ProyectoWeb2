package facturas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Alumno;
import model.entity.Carrera;
import model.entity.Factura;;

@SuppressWarnings("serial")
public class FacturasControllerIndex extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Factura.class);


			try {

				List<Factura> array = (List<Factura>)query.execute("select from Factura");
				List<Alumno> arrayAlumno = new ArrayList<Alumno>();
				List<Carrera> arrayCarrera = new ArrayList<Carrera>();

				
				for(Factura fact : array){
					
					Alumno alumno = pm.getObjectById(Alumno.class,fact.getIdAlumno());
					
						Carrera carrera = pm.getObjectById(Carrera.class,alumno.getIdCarrera());
						arrayAlumno.add(alumno);
						arrayCarrera.add(carrera);
				
				}

				req.setAttribute("array", array);
				req.setAttribute("arrayAlumno", arrayAlumno);
				req.setAttribute("arrayCarrera", arrayCarrera);

				req.getRequestDispatcher("/WEB-INF/Views/Facturas/index.jsp").forward(req, resp);
				query.closeAll();

			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
}
