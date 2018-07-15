package cursos;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.tools.ant.util.SymbolicLinkUtils;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Curso;
import model.entity.Nota;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class CursosControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try{
				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id);


				Curso curso = pm.getObjectById(Curso.class, idLong);
				Long carreraId = curso.getIdCarrera();


				/*Elimino todas las Notas con ese Curso*/
				Query query = pm.newQuery(Nota.class);	
				query.setFilter("idCurso == idParam");
				query.declareParameters("Long idParam");
				query.deletePersistentAll(curso.getId());

				query.closeAll();

				pm.deletePersistent(curso);
				resp.sendRedirect("/cursos/carrera?id="+carreraId);
			}
			catch(Exception e){
				System.out.println("ERROR "+e.getMessage());
				resp.sendRedirect("/cursos");
			}
			finally{
				pm.close();
			}

		}else {

			try {
				req.getRequestDispatcher("/WEB-INF/Views/AccesoDenegado.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

