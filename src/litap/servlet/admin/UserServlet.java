package litap.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import litap.model.User;
import litap.repository.LiteratureRepository;
import litap.repository.UserRepository;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/Admin/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user = (User) request.getSession().getAttribute("user");
        if(user == null) user = new User();
        if(!user.getRole().equals("admin") && !user.hasCapability("view+edit-user")) {
        	response.sendRedirect(request.getContextPath() + "/login.jsp");
        	return;
        }
        
        if(request.getParameter("action") != null && request.getParameter("id") != null) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	if(request.getParameter("action").equals("register") ) {
        		User u = UserRepository.findById(id);
        		u.setStatus("active");
				if(u.getRole().equals("guest")) // Entferne die admin rolle nicht, falls ein admin aktiviert wird
					u.setRole("user");
        		UserRepository.update(u);
        	}
        	if(request.getParameter("action").equals("edit") ) {
        		edit(request, response, id);
        		return;
        	}
        	if(request.getParameter("action").equals("delete") ) {
        		delete(request, response, id);
        	}
        }
        
    	list(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		User user = UserRepository.findById(id);
		if(user != null)
			UserRepository.remove(user);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		User user = UserRepository.findById(id);
        
		String submit = request.getParameter("user-submit");
		if(submit != null) {
			user.setEmail(request.getParameter("email"));
			if(request.getParameter("password") != null && !request.getParameter("password").isEmpty())
				user.setPassword(request.getParameter("password"));
			user.setBirthdate(request.getParameter("birthdate"));
			user.setProfession(request.getParameter("profession"));
			user.setLand(request.getParameter("land"));
			user.setNewsletter(request.getParameter("newsletter") != null?true:false);
			user.setRole(request.getParameter("role"));
			user.setStatus(request.getParameter("status"));
			UserRepository.update(user);
		}
		
		request.setAttribute("user_edit", user);
		request.getRequestDispatcher("/WEB-INF/admin/user_edit.jsp").forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, String> filter = new HashMap<String, String>();
        request.setAttribute("users", UserRepository.findAll());
		request.getRequestDispatcher("/WEB-INF/admin/user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
