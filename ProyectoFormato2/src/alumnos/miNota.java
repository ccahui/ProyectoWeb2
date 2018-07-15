package alumnos;

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
import model.entity.Curso;
import model.entity.Nota;

@SuppressWarnings("serial")
public class miNota extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		Alumno alumnoSession;
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if((alumnoSession = ACCESO.accesoAlumno(user.getEmail())) != null && alumnoSession.isEstado() == true){
			
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {

				Carrera carrera = pm.getObjectById(Carrera.class,alumnoSession.getIdCarrera());

				Query query = pm.newQuery(Nota.class);
				query.setFilter("idAlumno == idParam");
				query.declareParameters("Long idParam");

				List<Nota> arrayNota = (List<Nota>) query.execute(alumnoSession.getId()); 
				List<String> arrayNombreCurso = new ArrayList<String>();

				Curso curso;

				for(Nota nota:arrayNota){
					curso = pm.getObjectById(Curso.class,nota.getIdCurso());
					arrayNombreCurso.add(curso.getNombre());
					System.out.println(curso.getNombre());
				}

				req.setAttribute("alumno", alumnoSession);
				req.setAttribute("carrera",carrera );
				req.setAttribute("arrayNota",arrayNota );
				req.setAttribute("arrayCurso",arrayNombreCurso);

				req.getRequestDispatcher("/WEB-INF/Views/Alumnos/miNota.jsp").forward(req, resp);

				query.closeAll();
			} catch (Exception e) {
				System.out.println("Error "+e.getMessage());
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
