package elvaan.litap.model;

import elvaan.litap.utils.RoleCapability;

/*
 * Klasse Comment representiert ein Benutzer
 */
public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String role;
	private String birthdate;
	private String land;
	private String profession;
	private String status;
	private boolean newsletter;
	
	public User(int id, String name, String email, String password, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User() {
		this.id = 0;
		this.name = "Guest";
		this.email = "";
		this.password = "";
		this.role = "guest";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public boolean getNewsletter() {
		return newsletter;
	}
	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean hasCapability(String capability) {
		return RoleCapability.has(getRole(), capability);
	}
}
