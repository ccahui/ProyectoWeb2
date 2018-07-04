package controlador.factura;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import modelo.Factura;
import modelo.Alumno;
import modelo.Carrera;
import modelo.PMF;
@SuppressWarnings("serial")
public class insertar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		try {
			req.getRequestDispatcher("/WEB-INF/Vistas/Factura/insertar.jsp").forward(req, resp);
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

