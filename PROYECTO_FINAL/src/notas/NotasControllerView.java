package notas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACCESO;
import controller.PMF;
import model.entity.Alumno;
import model.entity.Curso;
import model.entity.Nota;

import javax.jdo.PersistenceManager;
@SuppressWarnings("serial")
public class NotasControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		else if(us.isUserAdmin() || ACCESO.accesoRecurso(user.getEmail(),req.getRequestURI())){
			PersistenceManager pm = PMF.get().getPersistenceManager();

			try {	
				String id = req.getParameter("id");
				Long idLong = Long.parseLong(id); 

				Nota nota = pm.getObjectById(Nota.class,idLong);
				Alumno alumno = pm.getObjectById(Alumno.class,nota.getIdAlumno());
				Curso curso = pm.getObjectById(Curso.class,nota.getIdCurso());

				req.setAttribute("nota",nota);
				req.setAttribute("alumno", alumno);
				req.setAttribute("curso", curso);
				req.getRequestDispatcher("/WEB-INF/Views/Notas/view.jsp").forward(req, resp);

			}catch (Exception e) {
				System.out.println("Error " +e.getMessage());
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	

		String id = req.getParameter("id");
		String nota1 = req.getParameter("nota1");
		String nota2 = req.getParameter("nota2");
		String nota3 = req.getParameter("nota3");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try{
			Long idLong = Long.parseLong(id);
			Integer nota1Integer = notaInteger(nota1);
			Integer nota2Integer = notaInteger(nota2);
			Integer nota3Integer = notaInteger(nota3);


			Nota nota = pm.getObjectById(Nota.class,idLong);

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

