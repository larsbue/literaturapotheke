package litap.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import litap.model.User;
import litap.repository.UserRepository;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");		
		String submit = request.getParameter("register-submit");
		String birthdate = request.getParameter("birthdate");
		String land = request.getParameter("land");
		String profession = request.getParameter("profession");
		boolean tos = request.getParameter("tos") != null ? true : false;
		boolean newsletter = request.getParameter("newsletter") != null ? true : false;
		
		try {
			if(submit != null && tos == true) {
				if(password.equals(passwordRepeat)) {
					User user = new User();
					user.setEmail(email);
					user.setPassword(password);
					user.setRole("guest");
					user.setBirthdate(birthdate);
					user.setLand(land);
					user.setProfession(profession);
					user.setNewsletter(newsletter);
					user.setStatus("new");
					UserRepository.add(user);
					response.sendRedirect("login.jsp");
					return;
				}
				else throw new Exception("Password does not match");
			}
			else throw new Exception("TOS not accepted or no submit");
		} catch(Exception ex) {
			System.err.println(ex);
	        request.setAttribute("message", ex.getMessage());
	        request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}

