package controlador.web;
import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Carrera;
@SuppressWarnings("serial")
public class index extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");


		try {
			UserService us = UserServiceFactory.getUserService();
			User user = us.getCurrentUser();

			if(user != null){
				req.setAttribute("estado", "true");
				req.setAttribute("gmail", user.getEmail());
			}
			req.getRequestDispatcher("/WEB-INF/Views/Web/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
