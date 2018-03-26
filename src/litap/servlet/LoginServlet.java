package litap.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import litap.model.User;
import litap.repository.UserRepository;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = "login";
		if(request.getParameter("action") != null) {
			action = request.getParameter("action");
		}
		
    	if(action.equals("logout")) {
    		logout(request, response);
    	}
    	else {
    		login(request, response);
    	}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("index.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user = UserRepository.findByEmail(email);
        
        if(user != null) {
        	if(user.getPassword().equals(password)) {
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            request.getRequestDispatcher("index.jsp").forward(request, response);
        	}
        	else
        		throw new ServletException("Password does not match.");
        } else {
        	throw new ServletException("User not found with specified email address.");
        }
	}
}
