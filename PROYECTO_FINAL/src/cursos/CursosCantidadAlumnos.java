package cursos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.CursoAux;
import controller.PMF;
import model.entity.Carrera;
import model.entity.Curso;
import model.entity.Users;
import model.entity.Nota;
@SuppressWarnings("serial")
public class CursosCantidadAlumnos extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Curso.class);
			//query.setOrdering("nombre descending");

			try{

				List<Curso> array = (List<Curso>)query.execute("select from Curso");
				List<CursoAux> reporte = new ArrayList<>();
				
				Query query1 = pm.newQuery(Nota.class);
				query1.setFilter("idCurso == idParam");
				query1.declareParameters("Long idParam");
				
				int cantidad = 0;
				CursoAux cursoA;
				Curso curso;
				for(int i = 0;i<array.size();i++){
					curso  = array.get(i);
					cantidad = ((List<Nota>) query1.execute(curso.getId())).size();
					cursoA = new CursoAux(curso.getId(),curso.getNombre(),cantidad);
					reporte.add(cursoA);
				}
				Collections.sort(reporte);
				
				
				req.setAttribute("array", reporte);
				req.getRequestDispatcher("/WEB-INF/Views/Cursos/cursosCantidadAlumnos.jsp").forward(req, resp);
				query.closeAll();
				query1.closeAll();
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
