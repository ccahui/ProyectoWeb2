package controlador.factura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import modelo.Alumno;
import modelo.Carrera;
import modelo.Factura;
import modelo.PMF;;

@SuppressWarnings("serial")
public class index extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
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
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Factura/index.jsp").forward(req, resp);
			query.closeAll();
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}
}
