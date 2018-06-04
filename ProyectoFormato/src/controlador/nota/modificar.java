package controlador.nota;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;
import modelo.alumno.*;
import modelo.nota.Nota;
import modelo.curso.Curso;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import jdo.pmf.*;
@SuppressWarnings("serial")
public class modificar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
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
			req.getRequestDispatcher("/WEB-INF/Vistas/Nota/modificar.jsp").forward(req, resp);
		
		}catch (Exception e) {
			System.out.println("Error " +e.getMessage());
		}
		finally{
			pm.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String id = req.getParameter("id");
		String nota1 = req.getParameter("nota1");
		String nota2 = req.getParameter("nota2");
		String nota3 = req.getParameter("nota3");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long idLong = Long.parseLong(id);
			Integer nota1Integer = Integer.parseInt(nota1);
			Integer nota2Integer = Integer.parseInt(nota2);
			Integer nota3Integer = Integer.parseInt(nota3);
			

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
}

