package controlador.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class usuarioAcceso extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


			try {
				req.getRequestDispatcher("/WEB-INF/Vistas/Usuario/usuarioAcceso.jsp").forward(req, resp);

			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}



}
