package elvaan.litap.model;

import java.util.HashMap;
import java.util.Map;

/*
 * Klasse Literature representiert ein Werk
 */
public class Literature {
	private int id;
	private String author;
	private String title;
	private String content;
	private String motivation;
	private int user_id;
	private String source;
	private String postdate;
	private String formating;
	private String topic;
	private String application;
	private String genre;
	private String admincomment;
	private String status;
	private boolean keep;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	public String getFormating() {
		return formating;
	}
	public void setFormating(String formating) {
		this.formating = formating;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAdmincomment() {
		return admincomment;
	}
	public void setAdmincomment(String admincomment) {
		this.admincomment = admincomment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean getKeep() {
		return keep;
	}
	public void setKeep(boolean keep) {
		this.keep = keep;
	}
	
	public static Map<String, String> getStatusValues() {
		Map<String, String> status = new HashMap<String, String>();
		status.put("new", "New");
		status.put("publish", "Publish");
		status.put("review", "Under Review");
		status.put("editing", "Under Editing");
		return status;
	}
}
