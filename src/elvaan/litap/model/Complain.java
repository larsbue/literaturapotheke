package elvaan.litap.model;

/*
 * Klasse Complain representiert eine Beschwerde
 */
public class Complain {
	private int id;
	private int literature_id;
	private int user_id;
	private String complain;
	private String complaindate;
	private String status;
	private String reason;
	private String source;
	
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
	public String getComplain() {
		return complain;
	}
	public void setComplain(String complain) {
		this.complain = complain;
	}
	public String getComplaindate() {
		return complaindate;
	}
	public void setComplaindate(String complaindate) {
		this.complaindate = complaindate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
