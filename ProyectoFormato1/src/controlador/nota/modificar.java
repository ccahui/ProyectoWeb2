package controlador.nota;

import java.io.IOException;
import javax.servlet.http.*;
import javax.jdo.PersistenceManager;
import modelo.Alumno;
import modelo.Nota;
import modelo.Curso;
import modelo.PMF;
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

