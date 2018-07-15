package profesores;

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
import model.entity.Curso;
import model.entity.Nota;
import model.entity.Profesor;
@SuppressWarnings("serial")
public class misCursosNota extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		Profesor profesorSession;
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		if(user == null){
			resp.sendRedirect(us.createLoginURL(req.getRequestURI()));}
		
		else if((profesorSession = ACCESO.accesoProfesor(user.getEmail())) != null && profesorSession.isEstado() == true){

			String id = req.getParameter("id");
			Long idLong = Long.parseLong(id);
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Nota.class);
			Query query2 = pm.newQuery(Curso.class);
			query2.setFilter("id == idParam1 && idProfesor == idParam2");
			query2.declareParameters("Long idParam1 , Long idParam2");

			List<Curso> arrayCurso = (List<Curso>) query2.execute(idLong,profesorSession.getId());
			
			if(arrayCurso.size() > 0){

				try{



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

					req.getRequestDispatcher("/WEB-INF/Views/Profesores/misCursosNota.jsp").forward(req, resp);

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
				query2.closeAll();
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
		
		System.out.println("Notas:" +nota1+" "+nota2+" "+nota3);
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
