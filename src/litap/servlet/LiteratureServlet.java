package litap.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import litap.model.Comment;
import litap.model.Complain;
import litap.model.Literature;
import litap.model.User;
import litap.repository.CommentRepository;
import litap.repository.ComplainRepository;
import litap.repository.LiteratureRepository;
import litap.utils.Mailer;

/**
 * Servlet implementation class WerkeServlet
 */
@WebServlet("/LiteratureServlet")
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
    	doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	User user = (User) request.getSession().getAttribute("user");
        if(user == null) user = new User(); // erstellt temporären  "Gast" Benutzer
    	
        if(request.getParameter("action") != null) {
        	if(request.getParameter("action").equals("add")) {
        		add(request, response, user);
        		return;
        	}
        	else if(request.getParameter("action").equals("view")) {
        		view(request, response, user);
        		return;
        	}
        	else if(request.getParameter("action").equals("complain")) {
        		complain(request, response, user);
        		return;
        	}
        }
        
    	list(request, response, user);
    }

    private void complain(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if(!user.hasCapability("rate+comment-literature")) {
        	response.sendRedirect("login.jsp");
        	return;
        }
        
        if(request.getParameter("id") != null) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	
        	if(request.getParameter("complain-submit") != null) {
        		Complain complain = new Complain();
        		complain.setLiterature_id(id);
        		complain.setUser_id(user.getId());
        		complain.setComplain(request.getParameter("complain"));
        		complain.setReason(request.getParameter("reason"));
        		complain.setSource(request.getParameter("source"));
        		
        		String reason = (String) request.getParameter("reason");
        		if(reason != null && reason.equals("Plagiat") && 
        				(request.getParameter("source") == null 
        				|| request.getParameter("source").isEmpty())) {
        			request.setAttribute("message", "Source is required");
        		}
        		else {
            		if(ComplainRepository.persist(complain)) {
            			request.setAttribute("message", "complain added!");
            		}
            		else {
            			request.setAttribute("message", "complain couldn't be added!");
            		}
            		
            		Mailer.sendmail(complain);
        		}
        	}
        	
        	Literature literature = LiteratureRepository.findById(id);
        	request.setAttribute("literature", literature);
        	request.getRequestDispatcher("/WEB-INF/literature_complain.jsp").forward(request, response);
        	return;
        }
        request.getRequestDispatcher("404.jsp").forward(request, response);
	}

	private void view(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if(!user.hasCapability("read-literature")) {
        	response.sendRedirect("login.jsp");
        	return;
        }
        
        if(request.getParameter("id") != null) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	
        	if(request.getParameter("comment-submit") != null) {
        		Comment comment = new Comment();
        		comment.setLiterature_id(id);
        		comment.setUser_id(user.getId());
        		comment.setComment(request.getParameter("comment"));
        		if(CommentRepository.persist(comment)) {
        			request.setAttribute("message", "Comment added!");
        		}
        		else {
        			request.setAttribute("message", "Comment couldn't be added!");
        		}
        	}
        	
        	Literature literature = LiteratureRepository.findById(id);
        	request.setAttribute("literature", literature);
        	request.setAttribute("comments", CommentRepository.findAllForLiterature(id));
        	request.getRequestDispatcher("/WEB-INF/literature_view.jsp").forward(request, response);
        	return;
        }
        request.getRequestDispatcher("404.jsp").forward(request, response);
    }
    private void list(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if(!user.hasCapability("read-literature")) {
        	response.sendRedirect("login.jsp");
        	return;
        }
        Map<String, String> filter = new HashMap<String, String>();
        filter.put("status", "publish");
        if(request.getParameter("search-submit") != null) {
        	if(user.hasCapability("use-search")) {
	        	String search = request.getParameter("search");
	        	filter.put("title_or_author_like", search);
        	}
        	else {
        		request.setAttribute("message", "Keine Zugriffsberechtigung auf die Suche.");
        	}
        }
        request.setAttribute("literature", LiteratureRepository.find(filter));
        request.getRequestDispatcher("/WEB-INF/literature_list.jsp").forward(request, response);
    }
	private void add(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if(!user.hasCapability("post-literature")) {
        	response.sendRedirect("login.jsp");
        	return;
        }
        
        String submit = request.getParameter("literature-submit");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String motivation = request.getParameter("motivation");
        String source = request.getParameter("source");
        String formating = request.getParameter("formating");
        String topic = request.getParameter("topic");
        String application = request.getParameter("application");
        String genre = request.getParameter("genre");
        String postdate = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        
        if(submit != null) {
	        Literature literature = new Literature();
	        literature.setAuthor(author);
	        literature.setTitle(title);
	        literature.setContent(content);
	        literature.setMotivation(motivation);
	        literature.setUser_id(user.getId());
	        literature.setSource(source);
	        literature.setPostdate(postdate);
	        literature.setFormating(formating);
	        literature.setTopic(topic);
	        literature.setApplication(application);
	        literature.setGenre(genre);
	        literature.setAdmincomment(null);
	        literature.setStatus("new");
	        
	        try {
		        if(LiteratureRepository.persist(literature)) {
					request.setAttribute("message", "Werk erfolgreich hinzugefügt.");
		        }
	        }
	        catch(SQLException e) {
				request.setAttribute("message", e.getMessage());
	        }
        }
        request.getRequestDispatcher("/WEB-INF/literature_add.jsp").forward(request, response);
	}
}
