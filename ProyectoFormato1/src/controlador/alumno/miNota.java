package controlador.alumno;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import modelo.Alumno;
import modelo.Carrera;
import modelo.Curso;
import modelo.Nota;
import modelo.PMF;
@SuppressWarnings("serial")
public class miNota extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
	
			String id = "4785074604081152";
			Long idAlumno = Long.parseLong(id);
			
			Alumno alumno = pm.getObjectById(Alumno.class,idAlumno);
			Carrera carrera = pm.getObjectById(Carrera.class,alumno.getIdCarrera());
			
			Query query = pm.newQuery(Nota.class);
			query.setFilter("idAlumno == idParam");
			query.declareParameters("Long idParam");
			
			List<Nota> arrayNota = (List<Nota>) query.execute(alumno.getId()); 
			List<String> arrayNombreCurso = new ArrayList<String>();
			
			Curso curso;
			
			for(Nota nota:arrayNota){
				curso = pm.getObjectById(Curso.class,nota.getIdCurso());
				arrayNombreCurso.add(curso.getNombre());
				System.out.println(curso.getNombre());
			}
			
			req.setAttribute("alumno", alumno);
			req.setAttribute("carrera",carrera );
			req.setAttribute("arrayNota",arrayNota );
			req.setAttribute("arrayCurso",arrayNombreCurso);
			
			req.getRequestDispatcher("/WEB-INF/Vistas/Alumno/miNota.jsp").forward(req, resp);
		
			query.closeAll();
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
