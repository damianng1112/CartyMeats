package model;

public class Employee {
	private String id;
	private String fullname;
	private String username;
	private String password;
	private String email;
	private String contact;
	
	public Employee(String fullname, String username, String password, String email, String contact) {
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}

	public Employee() {
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
