package project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateNewServlet
 */
@WebServlet("/CreateNewServlet")
public class CreateNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		AccountManager accountManager = (AccountManager)context.getAttribute("accounts");
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		if(accountManager.checkForAccount(username)){
			request.setAttribute("currentName", username);
			RequestDispatcher dispatch = request.getRequestDispatcher("name_in_use.html");
			dispatch.forward(request,response);
		} else {
			accountManager.createAccount(username, password);
			request.setAttribute("currentName", username);
			RequestDispatcher dispatch = request.getRequestDispatcher("user_homepage.jsp");
			dispatch.forward(request,response);
		}
	}

}
