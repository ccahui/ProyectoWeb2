package controlador.nota;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;
import modelo.Nota;
import modelo.Curso;
import modelo.Alumno;
import modelo.PMF;
@SuppressWarnings("serial")
public class notaCarreraCurso extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");



		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Nota.class);
		//query.setOrdering("nombre descending");

		try{
			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);

			Curso curso = pm.getObjectById(Curso.class,idLong);
			String nombreCurso = curso.getNombre();
			Long idCarrera = curso.getIdCarrera();

			query.setFilter("idCurso == idParam");
			query.declareParameters("Long idParam");
			List<Nota> array = (List<Nota>) query.execute(idLong); 

			List<String> arrayNombreAlumno = new ArrayList<String>();
			List<String> arrayNombreCurso = new ArrayList<String>();
			Alumno alumno;
			Curso cursoAux;

			for(Nota nota:array){
				alumno = pm.getObjectById(Alumno.class,nota.getIdAlumno());
				cursoAux = pm.getObjectById(Curso.class,nota.getIdCurso());
				arrayNombreAlumno.add(alumno.getNombre());
				arrayNombreCurso.add(curso.getNombre());
			}
			req.setAttribute("idCarrera",idCarrera );
			req.setAttribute("nombreCurso",nombreCurso);
			req.setAttribute("array", array);
			req.setAttribute("arrayAlumno",arrayNombreAlumno);
			req.setAttribute("arrayCurso", arrayNombreCurso);
			req.getRequestDispatcher("/WEB-INF/Vistas/Nota/notaCarreraCurso.jsp").forward(req, resp);

			query.closeAll();

		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		finally {
			pm.close();
		}
	}
}
