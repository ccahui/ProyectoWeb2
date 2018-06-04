package controlador.profesor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
import modelo.nota.Nota;
import modelo.curso.*;
import modelo.alumno.Alumno;
import modelo.carrera.Carrera;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class misCursosNota extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Nota.class);
		//query.setOrdering("nombre descending");

		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);

			Curso curso = pm.getObjectById(Curso.class,idLong);

			query.setFilter("idCurso == idParam");
			query.declareParameters("Long idParam");
			
			List<Nota> array = (List<Nota>) query.execute(idLong); 

			List<String> arrayNombreAlumno = new ArrayList<String>();
			
			Alumno alumno;
			
			for(Nota nota:array){
				alumno = pm.getObjectById(Alumno.class,nota.getIdAlumno());
				arrayNombreAlumno.add(alumno.getNombre()+" "+alumno.getApellido());
			}
			
			req.setAttribute("curso", curso);
			req.setAttribute("array", array);
			req.setAttribute("arrayAlumno",arrayNombreAlumno);
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Profesor/misCursosNota.jsp").forward(req, resp);

			query.closeAll();

		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		
		System.out.println("Modificando");
		
		String id = req.getParameter("id");
		String nota1 = req.getParameter("nota1");
		String nota2 = req.getParameter("nota2");
		String nota3 = req.getParameter("nota3");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			
			Nota nota = pm.getObjectById(Nota.class,idLong);
			
			Integer nota1Integer = notaInteger(nota1);
			Integer nota2Integer = notaInteger(nota2);
			Integer nota3Integer = notaInteger(nota3);
			
			nota.setNota1(nota1Integer);
			nota.setNota2(nota2Integer);
			nota.setNota3(nota3Integer);
			
			resp.getWriter().print("1");
		}
		catch(Exception e){
			System.out.println("Se produjo un Error "+e.getMessage());
		}
		finally{
			pm.close();
		}
	}
private Integer notaInteger(String nota){
	if(nota == null || "".equals(nota))
		return null;
	return Integer.parseInt(nota);
	
} 
}
