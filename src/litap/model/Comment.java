package litap.model;

/*
 * Klasse Comment representiert ein Kommentar
 */
public class Comment {
	private int id;
	private int literature_id;
	private int user_id;
	private String comment;
	private String postdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLiterature_id() {
		return literature_id;
	}
	public void setLiterature_id(int literature_id) {
		this.literature_id = literature_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
}
