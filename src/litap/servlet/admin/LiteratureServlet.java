package litap.servlet.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import litap.model.Literature;
import litap.model.User;
import litap.repository.ComplainRepository;
import litap.repository.LiteratureRepository;
import litap.repository.UserRepository;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/Admin", "/Admin/Literature"})
public class LiteratureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiteratureServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) user = new User();
        if(!user.getRole().equals("admin") && !user.hasCapability("lock+delete-literature+user")) {
        	response.sendRedirect(request.getContextPath() + "/login.jsp");
        	return;
        }
        
        request.setCharacterEncoding("UTF-8");
        
        request.setAttribute("statusValues", Literature.getStatusValues());
        
        if(request.getParameter("action") != null && request.getParameter("id") != null) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	if(request.getParameter("action").equals("delete") ) {
        		delete(request, response, id);
        	}
        	else if(request.getParameter("action").equals("edit") ) {
            	edit(request, response, id);
            	return;
        	}
        	else if(request.getParameter("action").equals("setkeep") ) {
            	setkeep(request, response, id);
        	}
        	else {
        	}
        }
        
    	list(request, response);
	}

	private void setkeep(HttpServletRequest request, HttpServletResponse response, int id) {
		Literature literature = LiteratureRepository.findById(id);
		literature.setKeep(true);
		LiteratureRepository.update(literature);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		LiteratureRepository.remove(id);
		request.setAttribute("message", "Deleted");
	}

	private void edit(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		Literature literature = LiteratureRepository.findById(id);
		String submit = request.getParameter("literature-submit");
		if(submit != null) {
			literature.setAuthor(request.getParameter("author"));
			literature.setTitle(request.getParameter("title"));
			literature.setContent(request.getParameter("content"));
			literature.setMotivation(request.getParameter("motivation"));
			literature.setSource(request.getParameter("source"));
			literature.setFormating(request.getParameter("formating"));
			literature.setTopic(request.getParameter("topic"));
			literature.setApplication(request.getParameter("application"));
			literature.setGenre(request.getParameter("genre"));
			literature.setAdmincomment(request.getParameter("admincomment"));
			literature.setStatus(request.getParameter("status"));
			literature.setKeep(request.getParameter("keep")!=null);
			LiteratureRepository.update(literature);
		}
		
		request.setAttribute("literature", literature);
		request.setAttribute("complains", ComplainRepository.findAllForLiterature(id));
		request.getRequestDispatcher("/WEB-INF/admin/literature_edit.jsp").forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> filter = new HashMap<String, String>();
        if(request.getParameter("literature-filter-submit") != null) {
        	try {
        		String user_id = (String) request.getParameter("user");
            	if(user_id != null && Integer.valueOf(user_id) > 0) {
                	filter.put("user_id", user_id);
            	}
            	if(request.getParameter("keep") != null) {
                	filter.put("keep", "true");
            	}
            	if(request.getParameter("status") != null 
            			&& !request.getParameter("status").isEmpty()) {
                	filter.put("status", request.getParameter("status"));
            	}
            	if(request.getParameter("complain") != null) {
                	filter.put("complain", "true");
            	}
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }
		
		request.setAttribute("users", UserRepository.findAll());
        request.setAttribute("literature", LiteratureRepository.find(filter));
		request.getRequestDispatcher("/WEB-INF/admin/literature.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
